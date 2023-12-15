package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.request.AddMerchantRequest;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.request.UpdateMerchantRequest;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.MerchantResponse;

import java.util.List;

public interface MerchantService {
    MerchantResponse addMerchant(AddMerchantRequest response);

    void deleteMerchantByMerchantId(Long merchantId);

    MerchantResponse updateMerchant(Long id, UpdateMerchantRequest request);

    MerchantResponse getMerchant(Long id);

    List<MerchantResponse> getAllMerchantIsOpen();

}
