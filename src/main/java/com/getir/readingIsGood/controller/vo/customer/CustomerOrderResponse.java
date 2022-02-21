package com.getir.readingIsGood.controller.vo.customer;

import com.getir.readingIsGood.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerOrderResponse {

    private Long customerId;
    private List<Order> orderList;
}
