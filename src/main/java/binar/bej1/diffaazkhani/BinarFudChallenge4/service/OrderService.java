package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.OrderModel;

import java.util.List;

public interface OrderService {
    OrderModel createOrder(OrderModel order);
    OrderModel getOrderById(Long orderId);
    List<OrderModel> getUserOrders(Long userId);
}

