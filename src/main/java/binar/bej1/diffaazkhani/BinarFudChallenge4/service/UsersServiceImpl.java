package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.UsersModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.request.DeleteUserRequest;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.request.UpdateUserRequest;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.UserResponse;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponse loadUserByUsername(String username) {
        log.info("Menambahkan pengguna: {}", username);

        UsersModel usersModel = usersRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username " + username));
        return UserResponse.build(usersModel);
    }

    @Override
    @Transactional
    public void deleteUser(DeleteUserRequest request) {
        log.info("Menghapus pengguna dengan username: {}", request.getUsername());

        UsersModel user = usersRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
        }

        usersRepository.delete(user);

        log.info("Delete user success with username : {}", request.getUsername());
    }

    @Override
    @Transactional
    public UserResponse updateUser(String username, UpdateUserRequest request) {
        log.info("Memperbarui pengguna dengan usename: {}", username);

        UsersModel usersModel = usersRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found"));

        if (Objects.nonNull(request.getUsername())) {
            usersModel.setUsername(request.getUsername());
        }

        if (Objects.nonNull(request.getEmail())) {
            usersModel.setEmailAddress(request.getEmail());
        }

        usersRepository.save(usersModel);

        return UserResponse.build(usersModel);
    }

}
