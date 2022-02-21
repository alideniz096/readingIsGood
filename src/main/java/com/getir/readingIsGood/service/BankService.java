package com.getir.readingIsGood.service;

import com.getir.readingIsGood.dto.bankPayment.BankPaymentRequest;
import com.getir.readingIsGood.dto.bankPayment.BankPaymentResponse;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    /**
     * Bank Latency Simulation (avg: 1 seconds)
     */
    public BankPaymentResponse pay(BankPaymentRequest request) {
        try {
            Thread.sleep(1000);
            return new BankPaymentResponse("200");
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
