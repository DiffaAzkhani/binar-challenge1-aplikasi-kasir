package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.ProductModel;

import java.util.List;

public interface ProductService {
    void addProduct(ProductModel product);
    void deleteProductByProductId(Long productId);
    void updateProduct(ProductModel product);
    List<ProductModel> findProductsByMerchantId(Long merchantId);
}
