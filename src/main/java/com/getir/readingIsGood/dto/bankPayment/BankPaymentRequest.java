package com.getir.readingIsGood.dto.bankPayment;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BankPaymentRequest {

    private BigDecimal price;
}