package com.getir.readingIsGood.controller.vo.order;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class OrderQueryRequest {

    private Long orderId;
    private Date startDate;
    private Date endDate;
}
