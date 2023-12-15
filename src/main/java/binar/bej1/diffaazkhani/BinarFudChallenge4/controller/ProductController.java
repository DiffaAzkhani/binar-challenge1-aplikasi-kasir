package binar.bej1.diffaazkhani.BinarFudChallenge4.controller;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.request.AddProductRequest;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.request.UpdateProductRequest;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.ProductResponse;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.Response;
import binar.bej1.diffaazkhani.BinarFudChallenge4.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/products")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(summary = "Add product")
    @PostMapping(
            value = "/add-product",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<ProductResponse>> addProduct(@RequestBody AddProductRequest request) {
        ProductResponse productResponse = productService.addProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.<ProductResponse>builder()
                        .code(HttpStatus.CREATED.value())
                        .message(HttpStatus.CREATED.getReasonPhrase())
                        .data(productResponse)
                        .build());
    }

    @Operation(summary = "Delete product")
    @DeleteMapping(
            value = "/delete-product/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<String> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);

        return Response.<String>builder()
                .code(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .build();
    }

    @Operation(summary = "Update product")
    @PutMapping(
            value = "/update-product",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody UpdateProductRequest request) {
        ProductResponse productResponse = productService.updateProduct(id, request);

        return Response.<ProductResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .data(productResponse)
                .build();
    }

    @Operation(summary = "Get product")
    @GetMapping(
            value = "/get-product/{merchantId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<ProductResponse> getProduct(@PathVariable Long id) {
        ProductResponse productResponse = productService.getProduct(id);

        return Response.<ProductResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .data(productResponse)
                .build();
    }

    @Operation(summary = "Get product")
    @GetMapping(
            value = "/get-all-products/{merchantId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<List<ProductResponse>> getAllProducts(@PathVariable Long merchantId) {
        List<ProductResponse> productResponse = productService.getAllProducts(merchantId);

        return Response.<List<ProductResponse>>builder()
                .code(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .data(productResponse)
                .build();
    }
}
