package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.OrderDetailModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public OrderDetailModel addOrderDetail(OrderDetailModel orderDetail) {
        if (orderDetail == null) {
            throw new IllegalArgumentException("Data Order Detail tidak boleh null");
        }

        return orderDetailRepository.save(orderDetail);
    }
}
