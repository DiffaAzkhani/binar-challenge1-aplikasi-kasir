package binar.bej1.diffaazkhani.BinarFudChallenge4.service;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.request.DeleteUserRequest;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.request.UpdateUserRequest;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.UserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {

    UserResponse loadUserByUsername(String username);

    void deleteUser(DeleteUserRequest request);

    UserResponse updateUser(String username, UpdateUserRequest request);

}

