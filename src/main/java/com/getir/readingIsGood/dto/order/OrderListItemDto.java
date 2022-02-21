package com.getir.readingIsGood.dto.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
public class OrderListItemDto {

    private Long customerId;
    private Long orderId;
    private Long orderDetailId;
    private Long orderedBookId;
    private Long quantity;
    private BigDecimal price;
    private Date creationDate;
}
