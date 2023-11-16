package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.OrderModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderModel saveOrder(Long userId, Long productId, Integer quantity, String destinationAddress);
    List<OrderResponse> getListOrder(Long userId);
}

