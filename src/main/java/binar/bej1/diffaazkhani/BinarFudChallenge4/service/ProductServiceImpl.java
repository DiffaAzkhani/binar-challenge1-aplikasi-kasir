package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.MerchantModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.ProductModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public void addProduct(ProductModel product) {
        log.info("Menambahkan produk: {}", product);

        if (product == null) {
            throw new IllegalArgumentException("Data produk tidak boleh kosong");
        }

        if (product.getProductName() == null || product.getProductName().isEmpty()) {
            throw new IllegalArgumentException("Nama produk tidak boleh kosong");
        }

        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Harga produk harus lebih dari 0");
        }

        productRepository.save(product);
    }

    @Override
    public void deleteProductByProductId(Long productId) {
        log.info("Menghapus produk dengan ID: {}", productId);

        Optional<ProductModel> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            ProductModel existingProduct = productOptional.get();
            productRepository.delete(existingProduct);
        } else {
            throw new IllegalArgumentException("Product dengan ID " + productId + " tidak ditemukan");
        }
    }

    @Override
    public void updateProduct(ProductModel product) {
        log.info("Mengupdate produk: {}", product);

        // Cek apakah produk dengan ID yang diberikan ada dalam database
        if (productRepository.existsById(product.getProductId())) {
            productRepository.save(product);
        } else {
            throw new RuntimeException("Produk dengan ID " + product.getProductId() + " tidak ditemukan");
        }
    }

    @Override
    public List<ProductModel> findProductsByMerchantId(Long merchantId) {
        log.info("Mencari produk berdasarkan ID merchant: {}", merchantId);

        List<ProductModel> product = productRepository.findProductByMerchantId(merchantId);
        return product;
    }
}
