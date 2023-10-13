package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.UsersModel;

public interface UsersService {
    UsersModel addUser(UsersModel user);
    UsersModel deleteUser(UsersModel user);
    UsersModel updateUser(UsersModel user);

}

