package com.getir.readingIsGood.controller;

import com.getir.readingIsGood.controller.vo.order.BookItem;
import com.getir.readingIsGood.controller.vo.order.OrderQueryResponse;
import com.getir.readingIsGood.controller.vo.order.OrderQueryResponseDto;
import com.getir.readingIsGood.controller.vo.order.OrderRequest;
import com.getir.readingIsGood.controller.vo.order.OrderResponse;
import com.getir.readingIsGood.dto.order.BookItemDto;
import com.getir.readingIsGood.dto.order.OrderRequestDto;
import com.getir.readingIsGood.dto.order.OrderResponseDto;
import com.getir.readingIsGood.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    private Long customerId = 1L;

    @Test
    public void addNewOrder() {

        BookItem bookItem = BookItem.builder()
                .bookId(1L)
                .quantity(10L)
                .build();

        List<BookItem> bookItemDtoList = new ArrayList<>();
        bookItemDtoList.add(bookItem);

        OrderRequest orderRequest = OrderRequest.builder()
                .customerId(customerId)
                .books(bookItemDtoList)
                .build();

        OrderResponseDto orderResponseDto = OrderResponseDto.builder()
                .orderId(1L)
                .customerId(customerId)
                .statusMessage("Payment successful your order has been created.")
                .build();

        when(orderService.addNewOrder(any(OrderRequestDto.class))).thenReturn(orderResponseDto);

        ResponseEntity<OrderResponse> response = orderController.addNewOrder(orderRequest);

        Assert.assertEquals(customerId, Objects.requireNonNull(response.getBody()).getCustomerId());
    }

    @Test
    public void getOrderDetailsByOrderId() {

        Long orderId = 1L;

        OrderQueryResponseDto item = OrderQueryResponseDto.builder()
                .customerId(customerId)
                .orderDetailId(1L)
                .orderedBookId(1L)
                .price(BigDecimal.TEN)
                .quantity(10L)
                .creationDate(Date.from(Instant.now()))
                .build();

        List<OrderQueryResponseDto> orderQueryResponseDto = new ArrayList<>();
        orderQueryResponseDto.add(item);

        when(orderService.getOrderDetailsByOrderId(orderId)).thenReturn(orderQueryResponseDto);

        ResponseEntity<List<OrderQueryResponse>> response = orderController.getOrderDetailsByOrderId(orderId);

        Assert.assertEquals(customerId, Objects.requireNonNull(response.getBody()).get(0).getCustomerId());
    }
}
