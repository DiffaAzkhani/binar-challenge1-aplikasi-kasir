package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.UserRoleModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.UsersModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserRoleServiceImpl implements UserRoleService{
    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserRoleModel getUserRoleByUsernameAndPassword(String username, String password) {
        log.info("Mencari peran pengguna (USER/ADMIN) berdasarkan username dan password: username={}, password={}", username, password);

        Optional<UsersModel> userOptional = usersRepository.findUserByUsernameAndPassword(username, password);
        if (userOptional.isPresent()) {
            UsersModel user = userOptional.get();
            UserRoleModel userRole = user.getRole();
            log.info("Peran pengguna ditemukan: username={}, role={}", username, userRole.name());
            return user.getRole();
        } else {
            log.error("Pengguna dengan username dan password yang diberikan tidak ditemukan: username={}, password={}", username, password);
            throw new IllegalArgumentException("Pengguna dengan username dan password yang diberikan tidak ditemukan");
        }
    }
}
