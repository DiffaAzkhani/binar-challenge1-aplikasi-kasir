package binar.bej1.diffaazkhani.BinarFudChallenge4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.OrderModel;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    // Mencari Orders berdasarkan id user
    @Query(nativeQuery = true, value = "SELECT o FROM OrderModel o WHERE o.users.userId = :userId")
    List<OrderModel> findOrdersByUserId(Long userId);
}
