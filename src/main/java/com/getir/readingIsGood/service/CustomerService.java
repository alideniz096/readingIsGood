package com.getir.readingIsGood.service;

import com.getir.readingIsGood.dto.customer.CustomerOrderResponseDto;
import com.getir.readingIsGood.dto.customer.CustomerRequestDto;
import com.getir.readingIsGood.dto.customer.CustomerResponseDto;
import com.getir.readingIsGood.dto.order.OrderListItemDto;
import com.getir.readingIsGood.entity.Customer;
import com.getir.readingIsGood.entity.Order;
import com.getir.readingIsGood.entity.OrderDetail;
import com.getir.readingIsGood.exception.CustomerException;
import com.getir.readingIsGood.mappers.CustomerMapper;
import com.getir.readingIsGood.repository.CustomerRepository;
import com.getir.readingIsGood.repository.OrderDetailRepository;
import com.getir.readingIsGood.repository.OrderRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mapstruct.factory.Mappers.getMapper;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CustomerMapper customerMapper = getMapper(CustomerMapper.class);

    public CustomerService(CustomerRepository customerRepository, OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public CustomerResponseDto addNewCustomer(CustomerRequestDto customerRequestDto) {

        Customer newCustomer = customerMapper.customerRequestDtoToCustomer(customerRequestDto);

        if (isPhoneOrMailExist(newCustomer.getCustomerEmail(), newCustomer.getCustomerPhoneNumber())) {
            throw new CustomerException("Email or phone already in use.");
        }

        customerRepository.save(newCustomer);
        customerMapper.customerToCustomerResponseDto(newCustomer);
        return CustomerResponseDto.builder()
                .customerId(newCustomer.getCustomerId())
                .customerName(newCustomer.getCustomerName())
                .customerSurname(newCustomer.getCustomerSurname())
                .customerPhoneNumber(newCustomer.getCustomerPhoneNumber())
                .customerAddress(newCustomer.getCustomerAddress())
                .creationDate(newCustomer.getCreationDate())
                .build();
    }

    public String deleteCustomer(Long customerId) {

        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer.isPresent()) {
            customerRepository.deleteById(customerId);
            return "Customer removed";
        } else {
            throw new CustomerException("There is no customer for this customerId:{}");
        }
    }

    public CustomerResponseDto getCustomer(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer.isPresent()) {
            return customerMapper.customerToCustomerResponseDto(customer.get());
        } else {
            throw new CustomerException("There is no customer for this customerId:{}");
        }
    }

    public CustomerOrderResponseDto getCustomerOrders(Long customerId, Pageable pageable) {

        CustomerOrderResponseDto response = new CustomerOrderResponseDto();

        List<OrderListItemDto> responseList = new ArrayList<>();
        List<Order> customerOrders = orderRepository.findOrdersByCustomerId(customerId);
        List<OrderDetail> customerOrderDetails = orderDetailRepository.findOrderDetailByOrderIdIn(customerOrders.stream().map(Order::getOrderId).collect(Collectors.toList()));


        for (OrderDetail orderDetails : customerOrderDetails) {
            responseList.add(convertToCustomerOrderResponseDto(orderDetails, customerId));
        }

        response.setCustomerOrderOriginalSize(responseList.size());
        responseList.sort(Comparator.comparing(OrderListItemDto::getCreationDate));
        response.setOrderList(responseList);

        return response;
    }

    private OrderListItemDto convertToCustomerOrderResponseDto(OrderDetail orderDetail, Long customerId) {
        return OrderListItemDto.builder()
                .customerId(customerId)
                .orderId(orderDetail.getOrderId())
                .orderDetailId(orderDetail.getOrderDetailId())
                .orderedBookId(orderDetail.getBookId())
                .quantity(orderDetail.getQuantity())
                .price(orderDetail.getPrice())
                .creationDate(orderDetail.getCreationDate())
                .build();

    }

    private boolean isPhoneOrMailExist(String email, String phoneNumber) {

        return customerRepository.existsByCustomerEmail(email) || customerRepository.existsByCustomerPhoneNumber(phoneNumber);
    }
}
