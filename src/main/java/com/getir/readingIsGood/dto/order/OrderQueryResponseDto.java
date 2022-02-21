package com.getir.readingIsGood.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderQueryResponseDto {

    private Long orderId;
    private Long customerId;
    private Long orderDetailId;
    private Long bookId;
    private Integer quantity;
    private BigDecimal price;
    private LocalDateTime creationDate;
}
