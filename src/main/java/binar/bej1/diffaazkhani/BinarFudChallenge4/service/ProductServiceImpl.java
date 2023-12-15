package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.MerchantModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.ProductModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.request.AddProductRequest;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.request.UpdateProductRequest;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.ProductResponse;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.MerchantRepository;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public ProductResponse addProduct(AddProductRequest request) {
        log.info("Menambahkan produk: {}", request.getProductName());

        if (request.getProductName() == null || request.getProductName().isEmpty()) {
            throw new IllegalArgumentException("Nama produk tidak boleh kosong");
        }

        if (request.getPrice() <= 0) {
            throw new IllegalArgumentException("Harga produk harus lebih dari 0");
        }

        ProductModel productModel = ProductModel.builder()
                .productName(request.getProductName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();

        productRepository.save(productModel);

        return toProductResponse(productModel);
    }

    @Override
    public void deleteProduct(Long id) {
        log.info("Menghapus produk dengan ID: {}", id);

        ProductModel productModel = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        productRepository.delete(productModel);
    }

    @Override
    public ProductResponse updateProduct(Long id, UpdateProductRequest request) {
        log.info("Mengupdate produk: {}", id);

        ProductModel productModel = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        productRepository.save(productModel);
        return toProductResponse(productModel);
    }

    @Override
    public ProductResponse getProduct(Long id) {
        log.info("Mengambil produk : {}", id);

        ProductModel productModel = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        return toProductResponse(productModel);
    }

    @Override
    public List<ProductResponse> getAllProducts(Long merchantId) {

        MerchantModel merchantModel = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Merchant not found"));

        Optional<List<ProductModel>> productModelOptional = Optional.ofNullable(productRepository.findProductByMerchantId(merchantModel.getMerchantId()));

        if (productModelOptional.isPresent()) {
            List<ProductModel> productsModel = productModelOptional.get();
            return toProductResponse(productsModel);
        } else {
            log.error("Produk tidak ditemukan pada merchant: {}", merchantModel.getMerchantName());
            throw new IllegalArgumentException("Produk tidak ditemukan");
        }
    }

    private ProductResponse toProductResponse(ProductModel productModel) {
        return ProductResponse.builder()
                .productId(productModel.getProductId())
                .productName(productModel.getProductName())
                .price(productModel.getPrice())
                .quantity(productModel.getQuantity())
                .build();
    }

    private List<ProductResponse> toProductResponse(List<ProductModel> products) {
        return products.stream()
                .map(this::toProductResponse)
                .collect(Collectors.toList());
    }

}
