package com.getir.readingIsGood.controller;


import com.getir.readingIsGood.controller.vo.customer.CustomerRequest;
import com.getir.readingIsGood.controller.vo.customer.CustomerResponse;
import com.getir.readingIsGood.dto.customer.CustomerOrderResponseDto;
import com.getir.readingIsGood.dto.customer.CustomerRequestDto;
import com.getir.readingIsGood.dto.customer.CustomerResponseDto;
import com.getir.readingIsGood.dto.order.OrderListItemDto;
import com.getir.readingIsGood.mappers.CustomerMapper;
import com.getir.readingIsGood.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.mapstruct.factory.Mappers.getMapper;


@RestController
@RequestMapping(value = "/rig")
public class CustomerController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;
    private final CustomerMapper customerMapper = getMapper(CustomerMapper.class);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/addNewCustomer")
    public ResponseEntity<CustomerResponse> addNewCustomer(@Valid @RequestBody CustomerRequest customerRequest) {

        CustomerRequestDto customerRequestDto = customerMapper.customerRequestToCustomerRequestDto(customerRequest);
        CustomerResponseDto customerResponseDto = customerService.addNewCustomer(customerRequestDto);

        return ResponseEntity.ok(customerMapper.customerResponseDtoToCustomerResponse(customerResponseDto));
    }

    @DeleteMapping(value = "/deleteCustomer")
    public ResponseEntity<String> deleteCustomer(@RequestParam Long customerId) {

        return ResponseEntity.ok(customerService.deleteCustomer(customerId));
    }

    @GetMapping(value = "/getCustomer")
    public ResponseEntity<CustomerResponse> getCustomer(@RequestParam Long customerId) {

        CustomerResponseDto customerResponseDto = customerService.getCustomer(customerId);

        return ResponseEntity.ok(customerMapper.customerResponseDtoToCustomerResponse(customerResponseDto));
    }

    @GetMapping(value = "/getCustomerOrders")
    public ResponseEntity<Page<OrderListItemDto>> getCustomerOrders(@RequestParam Long customerId, Pageable pageable) {

        CustomerResponseDto customerResponseDto = customerService.getCustomer(customerId);

        CustomerOrderResponseDto customerOrders = customerService.getCustomerOrders(customerResponseDto.getCustomerId(), pageable);

        return ResponseEntity.ok(new PageImpl<>(customerOrders.getOrderList(), pageable, customerOrders.getCustomerOrderOriginalSize()));
    }

}
