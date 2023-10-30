package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.UserRoleModel;

public interface UserRoleService {
    UserRoleModel getUserRoleByUsernameAndPassword(String username, String password);
}
