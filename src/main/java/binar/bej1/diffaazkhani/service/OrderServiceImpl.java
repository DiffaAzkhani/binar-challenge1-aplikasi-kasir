package binar.bej1.diffaazkhani.service;

import binar.bej1.diffaazkhani.model.MenuModel;
import binar.bej1.diffaazkhani.model.OrderModel;

import java.util.ArrayList;
import java.util.List;

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
        OrderModel order = getOrderById(orderId);
        if (order != null) {
            if (order.getMenuModels() == null) {
                order.setMenuModels(new ArrayList<>());
                order.setOrderQuantities(new ArrayList<>());
            }
            order.getMenuModels().add(menu);
            order.getOrderQuantities().add(quantity);
        }
    }

    @Override
    public OrderModel getOrderById(int orderId) {
        for (OrderModel order : orders) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        return null;
    }

    @Override
    public List<OrderModel> getAllOrders() {
        return new ArrayList<>(orders);
    }

    @Override
    public OrderModel getCurrentOrder() {
        for (OrderModel order : orders) {
            return order;
        }
        return null;
    }
}
