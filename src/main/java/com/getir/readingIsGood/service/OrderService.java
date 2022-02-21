package com.getir.readingIsGood.service;

import com.getir.readingIsGood.controller.vo.order.OrderQueryResponseDto;
import com.getir.readingIsGood.dto.order.BookItemDto;
import com.getir.readingIsGood.dto.order.OrderRequestDto;
import com.getir.readingIsGood.dto.order.OrderResponseDto;
import com.getir.readingIsGood.entity.Book;
import com.getir.readingIsGood.entity.Order;
import com.getir.readingIsGood.entity.OrderDetail;
import com.getir.readingIsGood.exception.MissingBookException;
import com.getir.readingIsGood.exception.OutOfStockException;
import com.getir.readingIsGood.repository.BookRepository;
import com.getir.readingIsGood.repository.OrderDetailRepository;
import com.getir.readingIsGood.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final PaymentService paymentService;
    private final BookRepository bookRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, PaymentService paymentService, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.paymentService = paymentService;
        this.bookRepository = bookRepository;
    }

    public synchronized OrderResponseDto addNewOrder(OrderRequestDto orderRequestDto) {

        List<BookItemDto> requestedBooks = orderRequestDto.getBooks();

        List<Long> requestedBookIds = requestedBooks.stream().map(BookItemDto::getBookId).collect(Collectors.toList());

        //Check stock status
        for (Long bookId : requestedBookIds) {
            if (!bookRepository.existsById(bookId)) {
                throw new MissingBookException("Added book does not exist");
            }
        }

        List<Book> books = bookRepository.findAllById(requestedBookIds);
        BigDecimal totalPrice = BigDecimal.ZERO;
        Long totalQuantity = 0L;

        for (BookItemDto requestedBook : requestedBooks) {
            Book book = books.stream().filter(item -> Objects.equals(item.getBookId(), requestedBook.getBookId())).findFirst().orElse(null);

            if (book == null) throw new MissingBookException("Added book does not exist");

            if (book.getBookStock() < requestedBook.getQuantity())
                throw new OutOfStockException(String.format("Stock of %s book is not enough", book.getBookName()));

            book.setBookStock(book.getBookStock() - requestedBook.getQuantity());

            bookRepository.save(book);

            BigDecimal bookPrice = book.getBookPrice();

            totalQuantity += requestedBook.getQuantity();
            totalPrice = totalPrice.add(bookPrice.multiply(BigDecimal.valueOf(requestedBook.getQuantity())));
        }

        paymentService.pay(totalPrice);

        //if payment succeeded now we can set an order
        Order newOrder = Order.builder()
                .customerId(orderRequestDto.getCustomerId())
                .totalPrice(totalPrice)
                .orderItemQuantity(totalQuantity)
                .build();

        Long orderId = orderRepository.save(newOrder).getOrderId();

        List<OrderDetail> orderDetailList = new ArrayList<>();

        for (BookItemDto requestedBook : requestedBooks) {
            Book book = books.stream().filter(item -> Objects.equals(item.getBookId(), requestedBook.getBookId())).findFirst().orElse(null);

            OrderDetail newOrderBook = OrderDetail.builder()
                    .orderId(orderId)
                    .bookId(requestedBook.getBookId())
                    .quantity(requestedBook.getQuantity())
                    .price(book.getBookPrice())
                    .customerId(orderRequestDto.getCustomerId())
                    .build();

            orderDetailList.add(newOrderBook);
        }

        orderDetailRepository.saveAll(orderDetailList);

        return OrderResponseDto.builder()
                .orderId(orderId)
                .customerId(orderRequestDto.getCustomerId())
                .statusMessage("Payment successful your order has been created.")
                .build();

    }

    public List<OrderQueryResponseDto> getOrderDetailsByOrderId(Long orderId) {
        List<OrderQueryResponseDto> queryResponseDtoList = new ArrayList<>();
        List<OrderDetail> orderDetails = orderDetailRepository.findOrderDetailByOrderIdIn(List.of(orderId));

        for (OrderDetail orderDetail : orderDetails) {
            queryResponseDtoList.add(convertToOrderQueryResponseDto(orderDetail));
        }

        return queryResponseDtoList;
    }

    public List<OrderQueryResponseDto> getOrderDetailsByTimeInterval(Date startDate, Date endDate) {
        List<OrderQueryResponseDto> queryResponseDtoList = new ArrayList<>();
        List<OrderDetail> orderDetails = orderDetailRepository.findOrderDetailByCreationDateBetween(startDate, endDate);

        for (OrderDetail orderDetail : orderDetails) {
            queryResponseDtoList.add(convertToOrderQueryResponseDto(orderDetail));
        }

        return queryResponseDtoList;
    }

    private OrderQueryResponseDto convertToOrderQueryResponseDto(OrderDetail orderDetail) {
        return OrderQueryResponseDto.builder()
                .orderId(orderDetail.getOrderId())
                .orderDetailId(orderDetail.getOrderDetailId())
                .customerId(orderDetail.getCustomerId())
                .orderedBookId(orderDetail.getBookId())
                .quantity(orderDetail.getQuantity())
                .price(orderDetail.getPrice())
                .creationDate(orderDetail.getCreationDate())
                .build();

    }

}
