import binar.bej1.diffaazkhani.model.MenuModel;
import binar.bej1.diffaazkhani.model.OrderModel;
import binar.bej1.diffaazkhani.service.OrderService;
import binar.bej1.diffaazkhani.service.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestServiceOrder {
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImpl();
    }

    @Test
    void testAddOrder() {
        OrderModel order = new OrderModel();
        orderService.addOrder(order);

        List<OrderModel> orders = orderService.getAllOrders();
        assertEquals(1, orders.size());
        assertEquals(order, orders.get(0));
        System.out.println("Berhasil : menambahkan pesanan");
    }

    @Test
    void testAddItemToOrder() {
        OrderModel order = new OrderModel();
        MenuModel menu = new MenuModel(1, "coba", 10);
        int quantity = 2;

        // mencoba menambahkan order (pesanan)
        orderService.addOrder(order);
        orderService.addItemToOrder(order.getId(), menu, quantity);

        // mencoba mengecek apaka order ada / berhasil di tambahkan
        Optional<OrderModel> availableOrder = orderService.getOrderById(order.getId());
        assertTrue(availableOrder.isPresent());
        System.out.println("Berhasil : Order tersedia");

        // mencoba mengecek seharusnya ditemukan 1 pesanan dan pesanan tersebut sesuai dengan menu
        OrderModel orders = availableOrder.get();
        assertEquals(1, orders.getMenuModels().size());
        assertEquals(menu, orders.getMenuModels().get(0));
        System.out.println("Berhasil : Menambah pesanan dan sesuai");

        // mencoba mengecek seharusnya terdapat 1 quantity pesanan, dengan quantity sebanyak 2
        assertEquals(1, orders.getOrderQuantities().size());
        assertEquals(quantity, orders.getOrderQuantities().get(0));
        System.out.println("Berhasil : Quantity order sesuai");
    }

    @Test
    void testGetAllOrders() {
        OrderModel order1 = new OrderModel();
        OrderModel order2 = new OrderModel();

        // mencoba menambahkan order
        orderService.addOrder(order1);
        orderService.addOrder(order2);

        // mengambil semua order dan di cek apakah di dalam orders mengandung ke 2 order yang diinputkan tadi
        List<OrderModel> orders = orderService.getAllOrders();
        assertEquals(2, orders.size());
        assertTrue(orders.contains(order1));
        assertTrue(orders.contains(order2));
        System.out.println("Berhasil : Mengambil semua order");
    }

    @Test
    void testGetCurrentOrder() {
        OrderModel order1 = new OrderModel();
        OrderModel order2 = new OrderModel();

        // mencoba menambahkan order
        orderService.addOrder(order1);
        orderService.addOrder(order2);

        // mencoba mengecek apakah order tersedia dan mengecek apakah order 1 ada pada currentOrder
        Optional<OrderModel> currentOrder = orderService.getCurrentOrder();
        assertTrue(currentOrder.isPresent());
        assertEquals(order1, currentOrder.get());
        System.out.println("Berhasil : pesanan yang akan dipesan berhasil di ambil dan sesuai");
    }

    @Test
    void testGetCurrentOrderWhenEmpty() {
        // megecek apakaj currentOrder itu kosong karena mengembalikan .empty()
        Optional<OrderModel> currentOrder = orderService.getCurrentOrder();
        assertFalse(currentOrder.isPresent());
        System.out.println("Berhasil : Current order Empty");
    }
}
