package com.getir.readingIsGood.controller;

import com.getir.readingIsGood.controller.vo.customer.CustomerRequest;
import com.getir.readingIsGood.controller.vo.customer.CustomerResponse;
import com.getir.readingIsGood.dto.customer.CustomerOrderResponseDto;
import com.getir.readingIsGood.dto.customer.CustomerRequestDto;
import com.getir.readingIsGood.dto.customer.CustomerResponseDto;
import com.getir.readingIsGood.dto.order.OrderListItemDto;
import com.getir.readingIsGood.service.CustomerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;

    public final Long customerId = 1L;
    public CustomerResponseDto customerResponseDto = CustomerResponseDto.builder()
            .customerId(1L)
            .customerName("Ali")
            .customerSurname("Deniz")
            .customerEmail("alideniz@rig.com.tr")
            .customerPhoneNumber("5397244989")
            .customerAddress("Istanbul")
            .creationDate(Date.from(Instant.now()))
            .build();

    @Test
    public void addNewCustomer() {

        CustomerResponse expectedResponse = CustomerResponse.builder()
                .customerId(customerId)
                .customerName("Ali")
                .customerSurname("Deniz")
                .customerEmail("alideniz@rig.com.tr")
                .customerPhoneNumber("5397244989")
                .customerAddress("Istanbul")
                .creationDate(Date.from(Instant.now()))
                .build();

        CustomerRequest customerRequest = CustomerRequest.builder()
                .customerName("Ali")
                .customerSurname("Deniz")
                .customerEmail("alideniz@rig.com.tr")
                .customerPhoneNumber("5397244989")
                .customerAddress("Istanbul")
                .build();


        when(customerService.addNewCustomer(any(CustomerRequestDto.class))).thenReturn(customerResponseDto);

        ResponseEntity<CustomerResponse> result = customerController.addNewCustomer(customerRequest);

        Assert.assertEquals(expectedResponse.getCustomerId(), Objects.requireNonNull(result.getBody()).getCustomerId());
    }

    @Test
    public void deleteCustomer() {

        String successMessage = "Customer removed";

        when(customerService.deleteCustomer(customerId)).thenReturn(successMessage);

        ResponseEntity<String> response = customerController.deleteCustomer(customerId);

        Assert.assertEquals(successMessage, response.getBody());
    }

    @Test
    public void getCustomer() {

        CustomerResponse expectedResponse = CustomerResponse.builder()
                .customerId(customerId)
                .customerName("Ali")
                .customerSurname("Deniz")
                .customerEmail("alideniz@rig.com.tr")
                .customerPhoneNumber("5397244989")
                .customerAddress("Istanbul")
                .creationDate(Date.from(Instant.now()))
                .build();

        when(customerService.getCustomer(customerId)).thenReturn(customerResponseDto);

        ResponseEntity<CustomerResponse> response = customerController.getCustomer(customerId);

        Assert.assertEquals(expectedResponse.getCustomerId(), Objects.requireNonNull(response.getBody()).getCustomerId());
    }

    @Test
    public void getCustomerOrders() {

        PageRequest pageable = PageRequest.of(1,10);

        List<OrderListItemDto> orderList = new ArrayList<>();

        CustomerOrderResponseDto customerOrders = CustomerOrderResponseDto.builder()
                .orderList(orderList)
                .customerOrderOriginalSize(1)
                .build();

        Page<OrderListItemDto> expectedResult = new PageImpl<>(customerOrders.getOrderList(), pageable, customerOrders.getCustomerOrderOriginalSize());

        when(customerService.getCustomer(customerId)).thenReturn(customerResponseDto);
        when(customerService.getCustomerOrders(customerResponseDto.getCustomerId(), pageable)).thenReturn(customerOrders);


        ResponseEntity<Page<OrderListItemDto>> response = customerController.getCustomerOrders(customerId, pageable);

        Assert.assertEquals(expectedResult.getTotalElements(), Objects.requireNonNull(response.getBody()).getTotalElements());
    }
}
