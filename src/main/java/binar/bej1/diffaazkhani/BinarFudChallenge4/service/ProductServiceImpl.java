package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.ProductModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductModel addProduct(ProductModel product) {
        return productRepository.save(product);
    }

    @Override
    public ProductModel deleteProduct(ProductModel product) {
        productRepository.delete(product);
        return product;
    }

    @Override
    public ProductModel updateProduct(ProductModel product) {
        // Cek apakah produk dengan ID yang diberikan ada dalam basis data
        if (productRepository.existsById(product.getProductId())) {
            return productRepository.save(product);
        } else {
            throw new RuntimeException("Produk dengan ID " + product.getProductId() + " tidak ditemukan");
        }
    }
}
