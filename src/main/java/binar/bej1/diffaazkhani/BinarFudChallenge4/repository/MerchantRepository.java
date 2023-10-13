package binar.bej1.diffaazkhani.BinarFudChallenge4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.MerchantModel;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<MerchantModel, Long> {
    // Mencari merchant berdasarkan id merchant
    MerchantModel findMerchantByMerchantId(String merchantId);

    // Mencari merchant yang buka
    List<MerchantModel> findMerchantsByOpenIsTrue();
}
