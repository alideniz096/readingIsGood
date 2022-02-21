package com.getir.readingIsGood.service;

import com.getir.readingIsGood.dto.bankPayment.BankPaymentRequest;
import com.getir.readingIsGood.dto.bankPayment.BankPaymentResponse;
import com.getir.readingIsGood.entity.Payment;
import com.getir.readingIsGood.exception.PaymentSystemException;
import com.getir.readingIsGood.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentRepository paymentRepository;
    private final TransactionTemplate transactionTemplate;
    private final BankService bankService;

    public PaymentService(PaymentRepository paymentRepository, TransactionTemplate transactionTemplate, BankService bankService) {
        this.paymentRepository = paymentRepository;
        this.transactionTemplate = transactionTemplate;
        this.bankService = bankService;
    }

    @Transactional(propagation = Propagation.NEVER)
    public void pay(BigDecimal price) {

        Payment payment = transactionTemplate.execute(item -> {
            Payment inner = new Payment();
            inner.setPrice(price);
            inner.setStatus(Payment.Status.IN_PROGRESS);
            return paymentRepository.saveAndFlush(inner);
        });

        BankPaymentRequest request = new BankPaymentRequest();
        request.setPrice(price);
        BankPaymentResponse response;

        Payment.Status status;
        String message = "500";

        response = bankService.pay(request);
        if (response != null) {
            status = Payment.Status.SUCCESS;
        } else {
            status = Payment.Status.ERROR;
        }

        BankPaymentResponse finalResponse = response;
        Payment.Status finalStatus = status;

        transactionTemplate.execute(item -> {
            payment.setBankResponse(finalResponse == null ? message : finalResponse.getResultCode());
            payment.setStatus(finalStatus);
            return paymentRepository.saveAndFlush(payment);
        });

        if (payment != null && Payment.Status.ERROR.equals(payment.getStatus())) {
            throw new PaymentSystemException("Payment failed!");
        }

        logger.info("Payment saved successfully!");
    }
}
