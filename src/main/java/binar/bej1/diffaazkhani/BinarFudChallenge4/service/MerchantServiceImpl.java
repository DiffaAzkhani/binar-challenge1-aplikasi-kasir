package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.MerchantModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MerchantServiceImpl implements MerchantService {
    private final MerchantRepository merchantRepository;

    @Autowired
    public MerchantServiceImpl(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    @Override
    @Transactional
    public MerchantModel addMerchant(MerchantModel merchant) {
        if (merchant == null) {
            throw new IllegalArgumentException("Data Merchant tidak boleh null");
        }

        if (merchant.getMerchantName() == null || merchant.getMerchantName().isEmpty()) {
            throw new IllegalArgumentException("Merchant name tidak boleh kosong");
        }

        if (merchantRepository.existsById(merchant.getMerchantId())) {
            throw new IllegalArgumentException("Merchant dengan ID " + merchant.getMerchantId() + " sudah ada");
        }

        return merchantRepository.save(merchant);
    }

    @Override
    @Transactional
    public MerchantModel deleteMerchant(Long merchantId) {
        MerchantModel existingMerchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new IllegalArgumentException("Merchant dengan ID " + merchantId + " tidak ditemukan"));

        merchantRepository.deleteById(merchantId);

        return existingMerchant;
    }

    @Override
    @Transactional
    public MerchantModel updateMerchant(MerchantModel merchant) {
        if (merchant == null) {
            throw new IllegalArgumentException("Data Merchant tidak boleh null");
        }

        if (merchant.getMerchantName() == null || merchant.getMerchantName().isEmpty()) {
            throw new IllegalArgumentException("Merchant name tidak boleh kosong");
        }

        Long merchantId = merchant.getMerchantId();
        MerchantModel existingMerchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new IllegalArgumentException("Merchant dengan ID " + merchantId + " tidak ditemukan"));

        // Set properti yang diperlukan
        existingMerchant.setMerchantName(merchant.getMerchantName());
        existingMerchant.setMerchantLocation(merchant.getMerchantLocation());
        existingMerchant.setOpen(merchant.isOpen());

        return merchantRepository.save(existingMerchant);
    }
}
