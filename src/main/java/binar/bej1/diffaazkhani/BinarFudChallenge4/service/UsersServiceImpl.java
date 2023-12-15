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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

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

        Optional<UsersModel> userOptional = usersRepository.findUserByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (userOptional.isPresent()) {
            UsersModel user = userOptional.get();
            usersRepository.delete(user);
        } else {
            throw new RuntimeException("Pengguna dengan username " + request.getUsername() + " tidak ditemukan");
        }
    }
    
    @Override
    @Transactional
    public UserResponse updateUser(UpdateUserRequest request) {
        log.info("Memperbarui pengguna dengan usename: {}", request.getUsername());

        UsersModel usersModel = usersRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found"));

        usersRepository.save(usersModel);

        return UserResponse.build(usersModel);
    }

}
