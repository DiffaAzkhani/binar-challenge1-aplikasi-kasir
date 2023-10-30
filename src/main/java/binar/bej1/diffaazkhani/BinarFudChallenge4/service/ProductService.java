package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.ProductModel;

import java.util.List;

public interface ProductService {
    ProductModel addProduct(ProductModel product);
    ProductModel deleteProductByProductId(Long productId);
    ProductModel updateProduct(ProductModel product);
    List<ProductModel> findProductsByMerchantId(Long merchantId);
}
