package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.OrderDetailModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.OrderModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.ProductModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.UsersModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.request.CreateOrderRequest;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.OrderDetailResponse;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.OrderResponse;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.OrderDetailRepository;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.OrderRepository;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.ProductRepository;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public OrderResponse saveOrder(CreateOrderRequest request) {
        log.info("Creating order for user with ID: {}", request.getUserId());

        UsersModel user = usersRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User dengan ID " + request.getUserId() + " tidak ditemukan"));

        log.info("User found: {}", user.getUsername());

        ProductModel product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produk dengan ID " + request.getProductId() + " tidak ditemukan"));

        log.info("Product found: {}", product.getProductName());

        OrderModel order = OrderModel.builder()
                .orderTime(new Date())
                .destinationAddress(request.getDestinationAddress())
                .completed(false)
                .users(user)
                .orderDetails(new ArrayList<>())
                .build();

        log.info("Order created: {}", order);

        OrderDetailModel orderDetail = OrderDetailModel.builder()
                .quantity(request.getQuantity())
                .totalPrice(product.getPrice() * request.getQuantity())
                .product(product)
                .order(order)
                .build();

        log.info("Order detail created: {}", orderDetail);

        orderRepository.save(order);
        orderDetailRepository.save(orderDetail);

        log.info("Order saved successfully");

        return toOrderResponse(order);
    }

    public static OrderResponse toOrderResponse(OrderModel order) {
        return OrderResponse.builder()
                .orderTime(order.getOrderTime())
                .destinationAddress(order.getDestinationAddress())
                .completed(order.isCompleted())
                .detailOrder(order.getOrderDetails()
                        .stream()
                        .map(OrderServiceImpl::toOrderDetailResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    private static OrderDetailResponse toOrderDetailResponse(OrderDetailModel orderDetail) {
        return OrderDetailResponse.builder()
                .quantity(orderDetail.getQuantity())
                .totalPrice(orderDetail.getTotalPrice())
                .productName(orderDetail.getProduct().getProductName())
                .build();
    }

    @Override
    public List<OrderResponse> getListOrder(Long userId) {
        log.info("Mengambil daftar pesanan untuk user dengan ID: {}", userId);
        List<OrderModel> orderList = orderRepository.findOrderByUserId(userId);

        List<OrderResponse> orderResponseList = new ArrayList<>();

        // Set properti orderResponse yang diperlukan
        for (OrderModel orderModel : orderList) {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setOrderTime(orderModel.getOrderTime());
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
