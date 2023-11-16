package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.MerchantModel;

import java.util.List;

public interface MerchantService {
    MerchantModel addMerchant(MerchantModel merchant);
    void deleteMerchantByMerchantId(Long merchantId);
    void updateMerchant(MerchantModel merchant);
    List<MerchantModel> getAllMerchantIsOpen();
    List<MerchantModel> getAllMerchants();
}
