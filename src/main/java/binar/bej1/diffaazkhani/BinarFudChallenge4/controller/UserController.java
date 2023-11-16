package binar.bej1.diffaazkhani.BinarFudChallenge4.controller;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.UsersModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.response.Response;
import binar.bej1.diffaazkhani.BinarFudChallenge4.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users")
@Slf4j
public class UserController {
    @Autowired
    private UsersService usersService;

    @Operation(summary = "Create new user")
    @PostMapping(
            value = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<UsersModel>> register(@RequestBody UsersModel usersModel) {
        try {
            // Menambahkan pengguna untuk registrasi
            UsersModel registeredUser = usersService.addUser(usersModel);

            // Membuat respons dengan status CREATED dan objek UsersModel yang terdaftar
            Response<UsersModel> response = Response.<UsersModel>builder()
                    .data(registeredUser)
                    .isSuccess(true)
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            // Exception jika terjadi error
            log.error("Gagal melakukan registrasi oleh pengguna", e);

            // Membuat respons dengan status error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Login user")
    @PostMapping(
            value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<String>> login(@RequestBody UsersModel usersModel) {
        try {
            // Mengambil user dengan username dan password
            usersService.getUserByUsernameAndPassword(usersModel.getUsername(), usersModel.getPassword());

            // Membuat respons dengan status OK dan pesan sukses
            Response<String> response = Response.<String>builder()
                    .data("Login berhasil")
                    .isSuccess(true)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Exception jika terjadi error
            log.error("Gagal melakukan login oleh pengguna", e);

            // Membuat respons dengan status error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Update user data")
    @PutMapping(
            value = "/update-user",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<String>> update(@RequestBody UsersModel updatedUser) {
        try {
            // Melakukan perubahan pada user
            usersService.updateUser(updatedUser);

            // Membuat respons dengan status OK dan pesan sukses
            Response<String> response = Response.<String>builder()
                    .data("Data pengguna berhasil diperbarui")
                    .isSuccess(true)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Exception jika terjadi error
            log.error("Gagal memperbarui data pengguna", e);

            // Membuat respons dengan status error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Delete user")
    @DeleteMapping(
            value = "/delete-user/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Response<String>> delete(@PathVariable Long userId) {
        try {
            // melakukan penghapusan pengguna berdasarkan ID
            // jika user sedang terhubung dengan pesanan maka tidak dapat dihapus
            usersService.deleteUser(userId);

            // Membuat respons dengan status OK dan pesan sukses
            Response<String> response = Response.<String>builder()
                    .data("User berhasil dihapus")
                    .isSuccess(true)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Gagal menghapus data pengguna", e);

            // Membuat respons dengan status error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
