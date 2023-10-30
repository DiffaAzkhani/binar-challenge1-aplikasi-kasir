package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.OrderDetailModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.OrderDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetailModel saveOrderDetail(OrderDetailModel orderDetail) {
        log.info("Menyimpan OrderDetail: {}", orderDetail);
        return orderDetailRepository.save(orderDetail);
    }
}
