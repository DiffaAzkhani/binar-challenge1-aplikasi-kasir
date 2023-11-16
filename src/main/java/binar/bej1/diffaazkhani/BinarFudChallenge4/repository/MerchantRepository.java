package binar.bej1.diffaazkhani.BinarFudChallenge4.repository;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.MerchantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<MerchantModel, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM table_merchant m WHERE m.open = true")
    List<MerchantModel> findOpenMerchants();
}
