package binar.bej1.diffaazkhani.service;

import binar.bej1.diffaazkhani.model.MenuModel;
import binar.bej1.diffaazkhani.model.OrderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private final List<OrderModel> orders = new ArrayList<>();
    private int nextOrderId = 1;

    @Override
    public void addOrder(OrderModel order) {
        order.setId(nextOrderId);
        orders.add(order);
        nextOrderId++;
    }

    @Override
    public void addItemToOrder(int orderId, MenuModel menu, int quantity) {
        Optional<OrderModel> optionalOrder = getOrderById(orderId);
        if (optionalOrder.isPresent()) {
            OrderModel order = optionalOrder.get();

            if (order.getMenuModels() == null) {
                order.setMenuModels(new ArrayList<>());
            }

            if (order.getOrderQuantities() == null) {
                order.setOrderQuantities(new ArrayList<>());
            }

            order.getMenuModels().add(menu);
            order.getOrderQuantities().add(quantity);
        }
    }

    @Override
    public Optional<OrderModel> getOrderById(int orderId) {
        for (OrderModel order : orders) {
            if (order.getId() == orderId) {
                return Optional.ofNullable(order);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<OrderModel> getAllOrders() {
        return new ArrayList<>(orders);
    }

    @Override
    public Optional<OrderModel> getCurrentOrder() {
        for (OrderModel order : orders) {
            return Optional.ofNullable(order);
        }
        return Optional.empty();
    }
}
