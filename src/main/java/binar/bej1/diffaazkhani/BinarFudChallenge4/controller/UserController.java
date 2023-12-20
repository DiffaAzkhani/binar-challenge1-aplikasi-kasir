package binar.bej1.diffaazkhani.BinarFudChallenge4.controller;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.request.DeleteUserRequest;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.request.UpdateUserRequest;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.Response;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.UserResponse;
import binar.bej1.diffaazkhani.BinarFudChallenge4.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users")
@Slf4j
public class UserController {
    @Autowired
    private UsersService usersService;

    @Operation(summary = "Update user data")
    @PutMapping(
            value = "/update-user/{username}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<UserResponse> updateUser(@PathVariable String username, @RequestBody UpdateUserRequest request) {
        UserResponse userResponse = usersService.updateUser(username, request);

        return Response.<UserResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .data(userResponse)
                .build();
    }

    @Operation(summary = "Delete user")
    @DeleteMapping(
            value = "/delete-user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<String> deleteUser(@RequestBody DeleteUserRequest request) {
        usersService.deleteUser(request);

        return Response.<String>builder()
                .code(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .build();
    }

}
