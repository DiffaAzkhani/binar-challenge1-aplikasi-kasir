package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.UsersModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UsersModel addUser(UsersModel user) {
        log.info("Menambahkan pengguna: {}", user.getUsername());

        if (user == null) {
            throw new IllegalArgumentException("Data pengguna tidak boleh null");
        }

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username tidak boleh kosong");
        }

        if (user.getEmailAddress() == null || user.getEmailAddress().isEmpty()) {
            throw new IllegalArgumentException("Alamat email tidak boleh kosong");
        }

        return usersRepository.save(user);
    }

    @Override
    public UsersModel deleteUser(UsersModel user) {
        log.info("Menghapus pengguna dengan ID: {}", user.getUserId());

        // Cek apakah pengguna dengan ID yang diberikan ada dalam database
        if (usersRepository.existsById(user.getUserId())){
            usersRepository.delete(user);
            return user;
        } else {
            throw new RuntimeException("Pengguna dengan ID " + user.getUserId() + " tidak ditemukan");
        }
    }

    @Override
    public UsersModel updateUser(UsersModel user) {
        log.info("Memperbarui pengguna dengan ID: {}", user.getUserId());

        // Cek apakah pengguna dengan ID yang diberikan ada dalam database
        if (usersRepository.existsById(user.getUserId())) {
            return usersRepository.save(user);
        } else {
            throw new RuntimeException("Pengguna dengan ID " + user.getUserId() + " tidak ditemukan");
        }
    }

    @Override
    public UsersModel getUserByUsernameAndPassword(String username, String password) {
        log.info("Mencari pengguna berdasarkan username dan password: username={}, password={}", username, password);

        // Menggunakan repository untuk mencari pengguna berdasarkan username
        Optional<UsersModel> userOptional = usersRepository.findUserByUsernameAndPassword(username, password);
        return userOptional.orElse(null);
    }
}
