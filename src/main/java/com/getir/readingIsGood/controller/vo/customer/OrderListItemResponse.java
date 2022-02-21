package com.getir.readingIsGood.controller.vo.customer;

import com.getir.readingIsGood.dto.order.OrderListItemDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderListItemResponse {

    private List<OrderListItemDto> orderList;
    private Integer customerOrderOriginalSize;
}
