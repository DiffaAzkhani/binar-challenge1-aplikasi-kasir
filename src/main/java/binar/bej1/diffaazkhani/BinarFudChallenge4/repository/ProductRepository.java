package binar.bej1.diffaazkhani.BinarFudChallenge4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.ProductModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    // Mencari produk berdasarkan nama produk
    Optional<ProductModel> findProductByProductName(String productName);

    // Mencari product berdasarkan kode merchant (pedagang)
    List<ProductModel> findProductsByMerchantCode(Integer merchantCode);
}
