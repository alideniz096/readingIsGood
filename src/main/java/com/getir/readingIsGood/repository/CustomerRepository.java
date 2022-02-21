package com.getir.readingIsGood.repository;

import com.getir.readingIsGood.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByCustomerEmail(String email);

    boolean existsByCustomerPhoneNumber(String phoneNumber);
}
