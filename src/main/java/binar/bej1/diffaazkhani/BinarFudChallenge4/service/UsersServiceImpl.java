package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.UsersModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UsersModel addUser(UsersModel user) {
        return usersRepository.save(user);
    }

    @Override
    public UsersModel deleteUser(UsersModel user) {
        usersRepository.delete(user);
        return user;
    }

    @Override
    public UsersModel updateUser(UsersModel user) {
        // Cek apakah pengguna dengan ID yang diberikan ada dalam basis data
        if (usersRepository.existsById(user.getUserId())) {
            return usersRepository.save(user);
        } else {
            throw new RuntimeException("Pengguna dengan ID " + user.getUserId() + " tidak ditemukan");
        }
    }

    @Override
    public UsersModel getUserByUsername(String username) {
        // Menggunakan repository untuk mencari pengguna berdasarkan username
        Optional<UsersModel> userOptional = usersRepository.findUserByUsername(username);
        return userOptional.orElse(null);
    }
}
