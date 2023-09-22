package binar.bej1.diffaazkhani.service;

import binar.bej1.diffaazkhani.model.MenuModel;
import binar.bej1.diffaazkhani.model.OrderModel;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    void addOrder(OrderModel order);
    void addItemToOrder(int orderId, MenuModel menu, int quantity);
    Optional<OrderModel> getOrderById(int orderId);
    List<OrderModel> getAllOrders();

    Optional<OrderModel> getCurrentOrder(); // Mengubah tipe pengembalian menjadi Optional
}
