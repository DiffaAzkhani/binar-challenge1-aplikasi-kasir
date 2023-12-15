package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.MerchantModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.request.AddMerchantRequest;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.request.UpdateMerchantRequest;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.MerchantResponse;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.MerchantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    @Transactional
    public MerchantResponse addMerchant(AddMerchantRequest request) {
        MerchantModel merchantModel = MerchantModel.builder()
                .merchantName(request.getMerchantName())
                .merchantLocation(request.getMerchantLocation())
                .open(request.isOpen())
                .build();

        log.info("Berhasil menyimpan Merchant: {}", request.getMerchantName());
        merchantRepository.save(merchantModel);
        return toMerchantResponse(merchantModel);
    }

    @Override
    @Transactional
    public void deleteMerchantByMerchantId(Long merchantId) {
        MerchantModel merchant = merchantRepository.findById(merchantId)
                .orElseThrow(() -> {
                    log.info("Merchant dengan ID : {}, tidak ditemukan", merchantId);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND);
                });

        log.info("Merchant dengan ID : {}, ditemukan", merchantId);

        merchantRepository.delete(merchant);

        log.info("Merchant berhasil dihapus");
    }

    @Override
    @Transactional
    public MerchantResponse updateMerchant(Long id, UpdateMerchantRequest request) {

        MerchantModel merchant = merchantRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("Merchant dengan ID : {}, tidak ditemukan", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND);
                });

        updateMerchantProperties(merchant, request);

        merchant.setOpen(request.isOpen());

        merchantRepository.save(merchant);
        return toMerchantResponse(merchant);
    }

    private void updateMerchantProperties(MerchantModel merchantModel, UpdateMerchantRequest request) {
        if (Objects.nonNull(request.getMerchantName())) {
            merchantModel.setMerchantName(request.getMerchantName());
        }

        if (Objects.nonNull(request.getLocation())) {
            merchantModel.setMerchantLocation(request.getLocation());
        }

    }

    @Override
    @Transactional
    public MerchantResponse getMerchant(Long id) {
        MerchantModel merchant = merchantRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("Merchant dengan ID : {}, tidak ditemukan", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND);
                });

        return toMerchantResponse(merchant);
    }

    @Override
    @Transactional
    public List<MerchantResponse> getAllMerchantIsOpen() {
        log.info("Mengambil daftar Merchant dengan status OPEN");
        Optional<List<MerchantModel>> openMerchantsOptional = Optional.ofNullable(merchantRepository.findOpenMerchants());

        if (openMerchantsOptional.isPresent()) {
            List<MerchantModel> openMerchants = openMerchantsOptional.get();
            return toMerchantResponse(openMerchants);
        } else {
            log.error("Merchant dengan status OPEN tidak ditemukan");
            throw new IllegalArgumentException("Merchant dengan status OPEN tidak ditemukan");
        }
    }

    public MerchantResponse toMerchantResponse(MerchantModel merchant) {
        return MerchantResponse.builder()
                .merchantName(merchant.getMerchantName())
                .location(merchant.getMerchantLocation())
                .open(merchant.isOpen())
                .build();
    }

    private List<MerchantResponse> toMerchantResponse(List<MerchantModel> merchants) {
        return merchants.stream()
                .map(this::toMerchantResponse)
                .collect(Collectors.toList());
    }
}
