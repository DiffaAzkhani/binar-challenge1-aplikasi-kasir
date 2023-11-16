package binar.bej1.diffaazkhani.BinarFudChallenge4.controller;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.OrderModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.request.CreateOrderRequest;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.OrderResponse;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.Response;
import binar.bej1.diffaazkhani.BinarFudChallenge4.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Response<OrderResponse>> createOrder(@RequestBody CreateOrderRequest request) {
        try {
            OrderModel createdOrder = orderService.saveOrder(request.getUserId(), request.getProductId(), request.getQuantity(), request.getDestinationAddress());

            // Membuat OrderResponse dari OrderModel
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setDate(createdOrder.getOrderTime());
            orderResponse.setDestinationAddress(createdOrder.getDestinationAddress());
            orderResponse.setCompleted(createdOrder.isCompleted());

            // Membuat respons dengan status CREATED dan data OrderResponse
            Response<OrderResponse> response = Response.<OrderResponse>builder()
                    .data(orderResponse)
                    .isSuccess(true)
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            // Tangani exception
            log.error("Gagal saat membuat order", e);

            // Membuat respons dengan status error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get all orders by userId")
    @GetMapping(
            value = "/list-order/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<List<OrderResponse>>> getListOrder(@PathVariable Long userId) {
        try {
            // Implementasi metode untuk mendapatkan daftar pesanan
            List<OrderResponse> orderList = orderService.getListOrder(userId);

            // Membuat respons dengan status OK dan data pesanan
            Response<List<OrderResponse>> response = Response.<List<OrderResponse>>builder()
                    .data(orderList)
                    .isSuccess(true)
                    .build();

            // Membuat respons dengan status OK dan data OrderResponse
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Exception jika terjadi error
            log.error("Error saat mengambil daftar pesanan", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
