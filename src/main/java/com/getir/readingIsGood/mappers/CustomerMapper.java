package com.getir.readingIsGood.mappers;

import com.getir.readingIsGood.controller.vo.customer.CustomerRequest;
import com.getir.readingIsGood.controller.vo.customer.CustomerResponse;
import com.getir.readingIsGood.dto.customer.CustomerRequestDto;
import com.getir.readingIsGood.dto.customer.CustomerResponseDto;
import com.getir.readingIsGood.entity.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerRequestDto customerRequestToCustomerRequestDto(CustomerRequest customerRequest);

    CustomerResponse customerResponseDtoToCustomerResponse(CustomerResponseDto customerResponseDto);

    CustomerResponseDto customerToCustomerResponseDto(Customer customer);

    Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto);

}
