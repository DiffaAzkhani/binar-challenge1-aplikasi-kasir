package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.UsersModel;

public interface UsersService {
    UsersModel addUser(UsersModel user);
    void deleteUser(Long userId);
    void updateUser(UsersModel user);
    void getUserByUsernameAndPassword(String username, String password);

}

