package com.getir.readingIsGood.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class OrderQueryRequestDto {

    private Long orderId;
    private Date startDate;
    private Date endDate;
}
