package binar.bej1.diffaazkhani.BinarFudChallenge4.controller;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.request.AddMerchantRequest;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.request.UpdateMerchantRequest;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.MerchantResponse;
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
    public ResponseEntity<Response<MerchantResponse>> addMerchant(@RequestBody AddMerchantRequest request) {
        MerchantResponse merchantResponse = merchantService.addMerchant(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.<MerchantResponse>builder()
                        .code(HttpStatus.CREATED.value())
                        .message(HttpStatus.CREATED.getReasonPhrase())
                        .data(merchantResponse)
                        .build());
    }

    @Operation(summary = "Delete merchant")
    @DeleteMapping(
            value = "/delete-merchant/{merchantId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<String> deleteMerchant(@PathVariable Long merchantId) {
        merchantService.deleteMerchantByMerchantId(merchantId);
        return Response.<String>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .build();
    }

    @Operation(summary = "Update merchant")
    @PutMapping(
            path = "/update-merchant",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<MerchantResponse> updateMerchant(@PathVariable Long id,
                                                     @RequestBody UpdateMerchantRequest request) {
        MerchantResponse merchantResponse = merchantService.updateMerchant(id, request);

        return Response.<MerchantResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(merchantResponse)
                .build();
    }

    @Operation(summary = "Get merchant")
    @GetMapping(
            value = "/get-merchant",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<MerchantResponse> getMerchant(@PathVariable Long id) {
        MerchantResponse merchantResponse = merchantService.getMerchant(id);
        return Response.<MerchantResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(merchantResponse)
                .build();
    }

    @Operation(summary = "Get open merchants")
    @GetMapping(
            value = "/list-open-merchants",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<List<MerchantResponse>> getOpenMerchants() {
        List<MerchantResponse> openMerchantList = merchantService.getAllMerchantIsOpen();

        return Response.<List<MerchantResponse>>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .data(openMerchantList)
                .build();
    }
}
