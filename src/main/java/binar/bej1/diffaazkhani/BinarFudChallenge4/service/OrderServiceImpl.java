package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.OrderModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderModel createOrder(OrderModel order) {
        return orderRepository.save(order);
    }

    @Override
    public OrderModel getOrderById(Long orderId) {
        Optional<OrderModel> orderOptional = orderRepository.findById(orderId);
        return orderOptional.orElse(null);
    }

    @Override
    public List<OrderModel> getUserOrders(Long userId) {
        return orderRepository.findOrdersByUserId(userId);
    }
}
