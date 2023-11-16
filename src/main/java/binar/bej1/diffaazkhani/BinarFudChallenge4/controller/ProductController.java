package binar.bej1.diffaazkhani.BinarFudChallenge4.controller;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.ProductModel;
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
    public ResponseEntity<Response<String>> addProduct(@RequestBody ProductModel productModel) {
        try {
            // Melakukan penambahan produk
            productService.addProduct(productModel);

            // Membuat respons dengan status CREATED dan pesan sukses
            Response<String> response = Response.<String>builder()
                    .data("Produk berhasil ditambahkan")
                    .isSuccess(true)
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            // Exception jika terjadi error
            log.error("Gagal menambahkan produk", e);

            // Membuat respons dengan status error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Delete product")
    @DeleteMapping(
            value = "/delete-product/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<String>> deleteProduct(@PathVariable Long productId) {
        try {
            // Melakukan penghapusan produk berdasarkan ID produk
            productService.deleteProductByProductId(productId);

            // Membuat respons dengan status OK dan pesan sukses
            Response<String> response = Response.<String>builder()
                    .data("Produk berhasil dihapus")
                    .isSuccess(true)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Exception jika terjadi error
            log.error("Gagal menghapus produk", e);

            // Membuat respons dengan status error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Update product")
    @PutMapping(
            value = "/update-product",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<String>> updateProduct(@RequestBody ProductModel productModel) {
        try {
            // Logika untuk memperbarui produk
            productService.updateProduct(productModel);

            // Membuat respons dengan status OK dan pesan sukses
            Response<String> response = Response.<String>builder()
                    .data("Produk berhasil diperbarui")
                    .isSuccess(true)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Exception jika terjadi error
            log.error("Gagal memperbarui produk", e);

            // Membuat respons dengan status error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get products by merchant id")
    @GetMapping(
            value = "/get-products/{merchantId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<List<ProductModel>>> getProductsByMerchantId(@PathVariable Long merchantId) {
        try {
            // Logika untuk mendapatkan produk berdasarkan ID merchant
            List<ProductModel> products = productService.findProductsByMerchantId(merchantId);

            // Membuat respons dengan status OK dan data produk
            Response<List<ProductModel>> response = Response.<List<ProductModel>>builder()
                    .data(products)
                    .isSuccess(true)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Exception jika terjadi error
            log.error("Gagal mendapatkan produk berdasarkan ID merchant", e);

            // Membuat respons dengan status error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
