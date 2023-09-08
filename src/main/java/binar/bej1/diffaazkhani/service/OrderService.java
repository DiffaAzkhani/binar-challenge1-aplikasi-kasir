package binar.bej1.diffaazkhani.service;

import binar.bej1.diffaazkhani.model.MenuModel;
import binar.bej1.diffaazkhani.model.OrderModel;

import java.util.List;

public interface OrderService {
    void addOrder(OrderModel order);
    void addItemToOrder(int orderId, MenuModel menu, int quantity);
    OrderModel getOrderById(int orderId);

    List<OrderModel> getAllOrders();

    OrderModel getCurrentOrder();
}
