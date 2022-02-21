package com.getir.readingIsGood.controller.vo.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {

    private Long orderId;
    private Long customerId;
    private String statusMessage;
}

