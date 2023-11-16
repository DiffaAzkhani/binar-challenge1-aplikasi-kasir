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

        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username tidak boleh kosong");
        }

        if (user.getEmailAddress() == null || user.getEmailAddress().isEmpty()) {
            throw new IllegalArgumentException("Alamat email tidak boleh kosong");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password tidak boleh kosong");
        }

        return usersRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        log.info("Menghapus pengguna dengan ID: {}", userId);

        // Cek apakah pengguna dengan ID yang diberikan ada dalam database
        Optional<UsersModel> userOptional = usersRepository.findById(userId);
        if (userOptional.isPresent()) {
            UsersModel user = userOptional.get();
            usersRepository.delete(user);
        } else {
            throw new RuntimeException("Pengguna dengan ID " + userId + " tidak ditemukan");
        }
    }


    @Override
    public void updateUser(UsersModel user) {
        log.info("Memperbarui pengguna dengan ID: {}", user.getUserId());

        // Cek apakah pengguna dengan ID yang diberikan ada dalam database
        if (usersRepository.existsById(user.getUserId())) {
            usersRepository.save(user);
        } else {
            throw new RuntimeException("Pengguna dengan ID " + user.getUserId() + " tidak ditemukan");
        }
    }

    @Override
    public void getUserByUsernameAndPassword(String username, String password) {
        log.info("Mencari pengguna berdasarkan username dan password: username={}, password={}", username, password);

        // Menggunakan repository untuk mencari pengguna berdasarkan username
        usersRepository.findUserByUsernameAndPassword(username, password);
    }
}
