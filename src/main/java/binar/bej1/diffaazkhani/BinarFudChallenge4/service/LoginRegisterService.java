package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.UsersModel;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface LoginRegisterService {
    void registerOauth2User(Authentication authentication);

    Optional<UsersModel> loginOauth2User(Authentication authentication);
}
