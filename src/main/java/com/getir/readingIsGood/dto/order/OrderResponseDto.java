package com.getir.readingIsGood.dto.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderResponseDto {

    private Long orderId;
    private Long customerId;
    private String statusMessage;
}
