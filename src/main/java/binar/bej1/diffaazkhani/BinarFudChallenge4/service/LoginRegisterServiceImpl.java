package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.Roles;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.UsersModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.enums.UserRoleModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.RoleRepository;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class LoginRegisterServiceImpl implements LoginRegisterService {
    @Autowired
    UsersRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void registerOauth2User(Authentication authentication) {
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
        Set<Roles> roles = new HashSet<>();
        roles.add(roleRepository.findByRoleName(UserRoleModel.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found")));
        userRepository.save(UsersModel.builder()
                .password(oAuth2User.getAttributes().get("id").toString())
                .provider("github")
                .roles(roles)
                .username(oAuth2User.getAttributes().get("login").toString())
                .build());
    }

    @Override
    public Optional<UsersModel> loginOauth2User(Authentication authentication) {
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
        return userRepository.findByUsername(oAuth2User.getAttributes().get("login").toString());
    }

}
