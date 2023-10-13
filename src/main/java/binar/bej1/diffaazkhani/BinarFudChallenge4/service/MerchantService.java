package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.MerchantModel;

public interface MerchantService {
    MerchantModel addMerchant(MerchantModel merchant);
    MerchantModel deleteMerchant(MerchantModel merchant);
    MerchantModel updateMerchant(MerchantModel merchant);
}

