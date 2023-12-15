package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.request.AddProductRequest;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.request.UpdateProductRequest;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(AddProductRequest request);

    void deleteProduct(Long id);

    ProductResponse updateProduct(Long id, UpdateProductRequest request);

    ProductResponse getProduct(Long id);

    List<ProductResponse> getAllProducts(Long merchantId);
}
