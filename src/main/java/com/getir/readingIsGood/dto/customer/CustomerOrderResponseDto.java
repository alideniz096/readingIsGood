package com.getir.readingIsGood.dto.customer;

import com.getir.readingIsGood.dto.order.OrderListItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderResponseDto {

    private List<OrderListItemDto> orderList;
    private Integer customerOrderOriginalSize;
}