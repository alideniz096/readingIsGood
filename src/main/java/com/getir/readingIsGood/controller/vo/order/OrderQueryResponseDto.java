package com.getir.readingIsGood.controller.vo.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
public class OrderQueryResponseDto {

    private Long orderId;
    private Long orderDetailId;
    private Long orderedBookId;
    private Long customerId;
    private Long quantity;
    private BigDecimal price;
    private Date creationDate;
}
