package binar.bej1.diffaazkhani.BinarFudChallenge4.controller;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.request.CreateOrderRequest;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.OrderResponse;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.Response;
import binar.bej1.diffaazkhani.BinarFudChallenge4.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "Create order")
    @PostMapping(
            value = "/create-order",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        OrderResponse createdOrder = orderService.saveOrder(request);

        return Response.<OrderResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(createdOrder)
                .build();
    }

    @Operation(summary = "Get all orders by userId")
    @GetMapping(
            value = "/list-order/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<List<OrderResponse>> getListOrder(@PathVariable Long userId) {
        List<OrderResponse> orderList = orderService.getListOrder(userId);

        return Response.<List<OrderResponse>>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(orderList)
                .build();
    }
    
}
