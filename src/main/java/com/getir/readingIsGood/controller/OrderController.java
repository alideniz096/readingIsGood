package com.getir.readingIsGood.controller;

import com.getir.readingIsGood.controller.vo.order.OrderQueryResponse;
import com.getir.readingIsGood.controller.vo.order.OrderQueryResponseDto;
import com.getir.readingIsGood.controller.vo.order.OrderRequest;
import com.getir.readingIsGood.controller.vo.order.OrderResponse;
import com.getir.readingIsGood.dto.order.OrderResponseDto;
import com.getir.readingIsGood.mappers.OrderMapper;
import com.getir.readingIsGood.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.mapstruct.factory.Mappers.getMapper;

@RestController
@RequestMapping(value = "/rig")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper = getMapper(OrderMapper.class);

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/addNewOrder")
    public ResponseEntity<OrderResponse> addNewOrder(@Valid @RequestBody OrderRequest orderRequest) {

        OrderResponseDto orderResponseDto = orderService.addNewOrder(orderMapper.orderRequestToOrderRequestDto(orderRequest));

        return ResponseEntity.ok(orderMapper.orderResponseDtoToOrderResponse(orderResponseDto));
    }

    @GetMapping(value = "/getOrderDetailsByOrderId")
    public ResponseEntity<List<OrderQueryResponse>> getOrderDetailsByOrderId(@RequestParam Long orderId) {

        List<OrderQueryResponseDto> orderQueryResponseDto = orderService.getOrderDetailsByOrderId(orderId);

        List<OrderQueryResponse> responseList = orderQueryResponseDto.stream().map(orderMapper::orderQueryResponseDtoToOrderQueryResponse).collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    @GetMapping(value = "/getOrderDetailsByTimeInterval")
    public ResponseEntity<List<OrderQueryResponse>> getOrderDetailsByTimeInterval(@RequestParam Date startDate, @RequestParam Date endDate) {

        List<OrderQueryResponseDto> orderQueryResponseDto = orderService.getOrderDetailsByTimeInterval(startDate, endDate);

        List<OrderQueryResponse> responseList = orderQueryResponseDto.stream().map(orderMapper::orderQueryResponseDtoToOrderQueryResponse).collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }
}
