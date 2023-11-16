package binar.bej1.diffaazkhani.BinarFudChallenge4.controller;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.MerchantModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.Response;
import binar.bej1.diffaazkhani.BinarFudChallenge4.service.MerchantService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/merchants")
@Slf4j
public class MerchantController {
    @Autowired
    private MerchantService merchantService;

    @Operation(summary = "Add merchant")
    @PostMapping(
            value = "/add-merchant",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<MerchantModel>> addMerchant(@RequestBody MerchantModel merchantModel) {
        try {
            // Simpan merchant
            MerchantModel addedMerchant = merchantService.addMerchant(merchantModel);

            // Membuat respons dengan status CREATED dan data MerchantModel
            Response<MerchantModel> response = Response.<MerchantModel>builder()
                    .data(addedMerchant)
                    .isSuccess(true)
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            // Tangani exception
            log.error("Gagal menambahkan merchant", e);

            // Membuat respons dengan status error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Delete merchant")
    @DeleteMapping(
            value = "/delete-merchant/{merchantId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<String>> deleteMerchant(@PathVariable Long merchantId) {
        try {
            // Hapus merchant berdasarkan ID
            merchantService.deleteMerchantByMerchantId(merchantId);

            // Membuat respons dengan status OK dan pesan sukses
            Response<String> response = Response.<String>builder()
                    .data("Merchant berhasil dihapus")
                    .isSuccess(true)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Exception jika terjadi error
            log.error("Gagal menghapus merchant", e);

            // Membuat respons dengan status error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Update merchant")
    @PutMapping(
            value = "/update-merchant",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<String>> updateMerchant(@RequestBody MerchantModel merchantModel) {
        try {
            // Update merchant
            merchantService.updateMerchant(merchantModel);

            // Membuat respons dengan status OK dan pesan sukses
            Response<String> response = Response.<String>builder()
                    .data("Merchant berhasil diperbarui")
                    .isSuccess(true)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Exception jika terjadi error
            log.error("Gagal memperbarui merchant", e);

            // Membuat respons dengan status error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get all merchants")
    @GetMapping(
            value = "/list-merchant",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<List<MerchantModel>>> getAllMerchants() {
        try {
            // Mendapatkan semua merchant
            List<MerchantModel> merchantList = merchantService.getAllMerchants();

            // Membuat respons dengan status OK dan data merchantList
            Response<List<MerchantModel>> response = Response.<List<MerchantModel>>builder()
                    .data(merchantList)
                    .isSuccess(true)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Exception jika terjadi error
            log.error("Gagal mendapatkan daftar merchant", e);

            // Membuat respons dengan status error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get open merchants")
    @GetMapping(
            value = "/list-open-merchants",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<List<MerchantModel>>> getOpenMerchants() {
        try {
            // Mendapatkan semua merchant yang masih buka
            List<MerchantModel> openMerchantList = merchantService.getAllMerchantIsOpen();

            // Membuat respons dengan status OK dan data openMerchantList
            Response<List<MerchantModel>> response = Response.<List<MerchantModel>>builder()
                    .data(openMerchantList)
                    .isSuccess(true)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Exception jika terjadi error
            log.error("Gagal mendapatkan daftar merchant yang masih buka", e);

            // Membuat respons dengan status error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
