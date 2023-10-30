package binar.bej1.diffaazkhani.BinarFudChallenge4.repository;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}
