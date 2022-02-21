package com.getir.readingIsGood.service;

import com.getir.readingIsGood.dto.customer.CustomerRequestDto;
import com.getir.readingIsGood.dto.customer.CustomerResponseDto;
import com.getir.readingIsGood.entity.Customer;
import com.getir.readingIsGood.exception.CustomerException;
import com.getir.readingIsGood.repository.CustomerRepository;
import com.getir.readingIsGood.repository.OrderDetailRepository;
import com.getir.readingIsGood.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderDetailRepository orderDetailRepository;

    private final Long customerId = 1L;

    @Test
    public void addNewCustomer() {

        CustomerRequestDto customerRequestDto = CustomerRequestDto.builder()
                .customerName("customerName")
                .customerSurname("customerSurname")
                .customerAddress("customerAddress")
                .customerEmail("customer@email.com")
                .customerPhoneNumber("5397244989")
                .build();

        CustomerResponseDto expectedResponse = CustomerResponseDto.builder()
                .customerId(customerId)
                .customerName("customerName")
                .customerSurname("customerSurname")
                .customerAddress("customerAddress")
                .customerEmail("customer@email.com")
                .customerPhoneNumber("5397244989")
                .creationDate(Date.from(Instant.now()))
                .build();

        CustomerResponseDto actualResult = customerService.addNewCustomer(customerRequestDto);

        Assert.assertEquals(expectedResponse.getCustomerName(), actualResult.getCustomerName());
    }

    @Test
    public void deleteCustomer() {

        String expectedResult = "Customer removed";

        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setCustomerName("customerName");
        customer.setCustomerSurname("customerSurname");
        customer.setCustomerAddress("customerAddress");
        customer.setCustomerEmail("customer@email.com");
        customer.setCustomerPhoneNumber("5397244989");
        customer.setCreationDate(Date.from(Instant.now()));

        Optional<Customer> optCustomer = Optional.of(customer);
        when(customerRepository.findById(customerId)).thenReturn(optCustomer);

        String actualResult = customerService.deleteCustomer(customerId);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test(expected = CustomerException.class)
    public void deleteCustomerWithCustomerException() {

        String expectedResult = "Customer removed";

        Optional<Customer> optCustomer = Optional.empty();
        when(customerRepository.findById(customerId)).thenReturn(optCustomer);

        String actualResult = customerService.deleteCustomer(customerId);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getCustomer() {

        CustomerResponseDto expectedResponseDto = CustomerResponseDto.builder()
                .customerId(1L)
                .customerName("Ali")
                .customerSurname("Deniz")
                .customerEmail("alideniz@rig.com.tr")
                .customerPhoneNumber("5397244989")
                .customerAddress("Istanbul")
                .creationDate(Date.from(Instant.now()))
                .build();

        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setCustomerName("customerName");
        customer.setCustomerSurname("customerSurname");
        customer.setCustomerAddress("customerAddress");
        customer.setCustomerEmail("customer@email.com");
        customer.setCustomerPhoneNumber("5397244989");
        customer.setCreationDate(Date.from(Instant.now()));

        Optional<Customer> optCustomer = Optional.of(customer);
        when(customerRepository.findById(customerId)).thenReturn(optCustomer);

        CustomerResponseDto actualResult = customerService.getCustomer(customerId);

        Assert.assertEquals(expectedResponseDto.getCustomerId(), actualResult.getCustomerId());
    }

    @Test(expected = CustomerException.class)
    public void getCustomerWithCustomerException() {

        CustomerResponseDto expectedResponseDto = CustomerResponseDto.builder()
                .customerId(1L)
                .customerName("Ali")
                .customerSurname("Deniz")
                .customerEmail("alideniz@rig.com.tr")
                .customerPhoneNumber("5397244989")
                .customerAddress("Istanbul")
                .creationDate(Date.from(Instant.now()))
                .build();

        Optional<Customer> optCustomer = Optional.empty();
        when(customerRepository.findById(customerId)).thenReturn(optCustomer);

        CustomerResponseDto actualResult = customerService.getCustomer(customerId);

        Assert.assertEquals(expectedResponseDto.getCustomerId(), actualResult.getCustomerId());
    }
}
