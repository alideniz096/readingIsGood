package com.getir.readingIsGood.mappers;

import com.getir.readingIsGood.controller.vo.order.OrderQueryResponse;
import com.getir.readingIsGood.controller.vo.order.OrderQueryResponseDto;
import com.getir.readingIsGood.controller.vo.order.OrderRequest;
import com.getir.readingIsGood.controller.vo.order.OrderResponse;
import com.getir.readingIsGood.dto.order.OrderRequestDto;
import com.getir.readingIsGood.dto.order.OrderResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper {

    OrderRequestDto orderRequestToOrderRequestDto(OrderRequest orderRequest);

    OrderResponse orderResponseDtoToOrderResponse(OrderResponseDto orderResponseDto);

    OrderQueryResponse orderQueryResponseDtoToOrderQueryResponse(OrderQueryResponseDto orderQueryResponseDto);

}
