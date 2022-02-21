package com.getir.readingIsGood.dto.bankPayment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankPaymentResponse {

    private String resultCode;

    public BankPaymentResponse(String resultCode) {
        this.resultCode = resultCode;
    }

}