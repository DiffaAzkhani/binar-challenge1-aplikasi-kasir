package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.OrderDetailModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.OrderModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.ProductModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.UsersModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.OrderDetailResponse;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.OrderResponse;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.OrderDetailRepository;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.OrderRepository;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.ProductRepository;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderModel saveOrder(Long userId, Long productId, Integer quantity, String destinationAddress) {
        log.info("Creating order for user with ID: {}", userId);

        // Mendapatkan dan melakukan pengecekan pengguna berdasarkan userId
        UsersModel user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User dengan ID " + userId + " tidak ditemukan"));

        log.info("User found: {}", user.getUsername());

        // Mendapatkancd dan melakukan pengecekan produk berdasarkan productId
        ProductModel product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produk dengan ID " + productId + " tidak ditemukan"));

        log.info("Product found: {}", product.getProductName());

        // Set properti order yang diperlukan
        OrderModel order = OrderModel.builder()
                .orderTime(new Date())
                .destinationAddress(destinationAddress)
                .completed(false)
                .users(user)
                .orderDetails(new ArrayList<>())
                .build();

        log.info("Order created: {}", order);

        // Set properti orderDetail yang diperlukan
        OrderDetailModel orderDetail = OrderDetailModel.builder()
                .quantity(quantity)
                .totalPrice(product.getPrice() * quantity)
                .product(product)
                .order(order)
                .build();

        log.info("Order detail created: {}", orderDetail);

        // Menyimpan Order dan OrderDetail ke database
        orderRepository.save(order);
        orderDetailRepository.save(orderDetail);

        log.info("Order saved successfully");

        return order;
    }

    @Override
    public List<OrderResponse> getListOrder(Long userId) {
        log.info("Mengambil daftar pesanan untuk user dengan ID: {}", userId);
        List<OrderModel> orderList = orderRepository.findOrderByUserId(userId);

        List<OrderResponse> orderResponseList = new ArrayList<>();

        // Set properti orderResponse yang diperlukan
        for (OrderModel orderModel : orderList) {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setDate(orderModel.getOrderTime());
            orderResponse.setDestinationAddress(orderModel.getDestinationAddress());
            orderResponse.setCompleted(orderModel.isCompleted());

            // Set properti orderDetailResponse yang diperlukan
            List<OrderDetailResponse> orderDetailResponseList = new ArrayList<>();
            for (OrderDetailModel orderDetailModel : orderModel.getOrderDetails()) {
                OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
                orderDetailResponse.setQuantity(orderDetailModel.getQuantity());
                orderDetailResponse.setTotalPrice(orderDetailModel.getTotalPrice());
                orderDetailResponse.setProductName(orderDetailModel.getProduct().getProductName());

                // Menambahkan orderDetailResponse ke dalam list
                orderDetailResponseList.add(orderDetailResponse);
            }

            // Mengatur orderDetailResponse ke dalam OrderResponse
            orderResponse.setDetailOrder(orderDetailResponseList);
            orderResponseList.add(orderResponse);
        }

        return orderResponseList;
    }
}
