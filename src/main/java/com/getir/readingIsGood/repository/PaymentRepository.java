package com.getir.readingIsGood.repository;

import com.getir.readingIsGood.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}