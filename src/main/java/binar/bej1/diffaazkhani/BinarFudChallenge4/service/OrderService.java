package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.OrderModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.request.CreateOrderRequest;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse saveOrder(CreateOrderRequest request);

    List<OrderResponse> getListOrder(Long userId);
}

