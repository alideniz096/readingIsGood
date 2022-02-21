package com.getir.readingIsGood.controller.vo.order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderQueryResponse {

    private Long orderId;
    private Long customerId;
    private Long orderDetailId;
    private Long orderedBookId;
    private Integer quantity;
    private BigDecimal price;
    private LocalDateTime creationDate;
}
