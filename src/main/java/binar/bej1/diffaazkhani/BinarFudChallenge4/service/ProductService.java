package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.ProductModel;

public interface ProductService {
    ProductModel addProduct(ProductModel product);
    ProductModel deleteProduct(ProductModel product);
    ProductModel updateProduct(ProductModel product);
}
