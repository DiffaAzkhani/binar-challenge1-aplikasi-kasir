package binar.bej1.diffaazkhani.BinarFudChallenge4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.OrderDetailModel;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailModel, Long> {
    // Mencari OrderDetail berdasarkan order id yang ditemukan
    List<OrderDetailModel> findOrderDetailsByOrderDetailId(Integer orderDetailId);
}
