package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.MerchantModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.MerchantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public MerchantModel addMerchant(MerchantModel merchant) {
        if (merchant == null) {
            log.error("Data Merchant tidak boleh null");
            throw new IllegalArgumentException("Data Merchant tidak boleh null");
        }

        if (merchant.getMerchantName() == null || merchant.getMerchantName().isEmpty()) {
            log.error("Merchant name tidak boleh kosong");
            throw new IllegalArgumentException("Merchant name tidak boleh kosong");
        }

        log.info("Menyimpan Merchant: {}", merchant.getMerchantName());
        return merchantRepository.save(merchant);
    }

    @Override
    public MerchantModel deleteMerchantByMerchantId(Long merchantId) {
        Optional<MerchantModel> merchantOptional = merchantRepository.findById(merchantId);
        if (merchantOptional.isPresent()) {
            MerchantModel existingMerchant = merchantOptional.get();
            merchantRepository.delete(existingMerchant);
            return existingMerchant;
        } else {
            log.error("Merchant dengan ID {} tidak ditemukan", merchantId);
            throw new IllegalArgumentException("Merchant dengan ID " + merchantId + " tidak ditemukan");
        }
    }

    @Override
    public MerchantModel updateMerchant(MerchantModel merchant) {
        if (merchant == null) {
            log.error("Data Merchant tidak boleh null");
            throw new IllegalArgumentException("Data Merchant tidak boleh null");
        }

        if (merchant.getMerchantName() == null || merchant.getMerchantName().isEmpty()) {
            log.error("Merchant name tidak boleh kosong");
            throw new IllegalArgumentException("Merchant name tidak boleh kosong");
        }

        Long merchantId = merchant.getMerchantId();
        MerchantModel existingMerchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> new IllegalArgumentException("Merchant dengan ID " + merchantId + " tidak ditemukan"));

        // Set properti yang diperlukan
        MerchantModel updatedMerchant = MerchantModel.builder()
                .merchantId(existingMerchant.getMerchantId())
                .merchantName(merchant.getMerchantName())
                .merchantLocation(merchant.getMerchantLocation())
                .open(merchant.isOpen())
                .build();

        return merchantRepository.save(updatedMerchant);
    }

    @Override
    public List<MerchantModel> getAllMerchantIsOpen() {
        log.info("Mengambil daftar Merchant dengan status OPEN");
        Optional<List<MerchantModel>> openMerchantsOptional = Optional.ofNullable(merchantRepository.findOpenMerchants());

        if (openMerchantsOptional.isPresent()) {
            return openMerchantsOptional.get();
        } else {
            log.error("Merchant dengan status OPEN tidak ditemukan");
            throw new IllegalArgumentException("Merchant dengan status OPEN tidak ditemukan");
        }
    }

    @Override
    public List<MerchantModel> getAllMerchants() {
        log.info("Mengambil semua Merchant");
        return merchantRepository.findAll();
    }
}
