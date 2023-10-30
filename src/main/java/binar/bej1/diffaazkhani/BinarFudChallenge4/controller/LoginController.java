package binar.bej1.diffaazkhani.BinarFudChallenge4.controller;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.UserRoleModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.UsersModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.service.UserRoleService;
import binar.bej1.diffaazkhani.BinarFudChallenge4.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.Scanner;

@Controller
public class LoginController {
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    private UsersService usersService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserController userController;

    @Autowired
    private AdminController adminController;

    // Menu piliha login atau register
    @PostConstruct
    public void mainMenuLoginRegister() {
        System.out.println("=====================================================");
        System.out.println("Selamat datang di Aplikasi Pemesanan Makanan BinarFud");
        System.out.println("                    Diffa Azkhani                    ");
        System.out.println("=====================================================");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.print("Pilih : ");
        String choice = scanner.nextLine();
        switch (choice){
            case "1":
                loginMenu();
                break;
            case "2":
                registerMenu();
                break;
            default:
                System.out.println("Pilihan tidak valid!");
                mainMenuLoginRegister();
        }
    }

    // menu register ketika user baru ingin membuat akun
    private void registerMenu() {
        System.out.println("=====================================================");
        System.out.println("Selamat datang di Aplikasi Pemesanan Makanan         ");
        System.out.println("Silakan lakukan registrasi:                          ");
        System.out.println("=====================================================");
        System.out.print("Masukkan nama pengguna (username):");
        String username = scanner.nextLine();

        System.out.print("Masukkan alamat email: ");
        String email = scanner.nextLine();

        System.out.print("Masukkan kata sandi: ");
        String password = scanner.nextLine();

        System.out.print("Masukkan peran (user/admin): ");
        String roleInput = scanner.nextLine();
        UserRoleModel role = UserRoleModel.valueOf(roleInput.toUpperCase());

        UsersModel userRegister = UsersModel.builder()
                .username(username)
                .emailAddress(email)
                .password(password)
                .role(role)
                .build();

        UsersModel registeredUser = usersService.addUser(userRegister);

        if (registeredUser != null) {
            System.out.println("Registrasi berhasil!");
            mainMenuLoginRegister();
        } else {
            System.out.println("Registrasi gagal! Coba lagi.");
        }
    }

    // menu login user yang sudah terdaftar
    private void loginMenu() {
        System.out.println("=====================================================");
        System.out.println("Selamat datang di Aplikasi Pemesanan Makanan         ");
        System.out.println("Silakan lakukan login:                               ");
        System.out.println("=====================================================");
        System.out.print("Masukkan nama pengguna (username): ");
        String username = scanner.nextLine();

        System.out.print("Masukkan kata sandi (password): ");
        String password = scanner.nextLine();

        UserRoleModel role = userRoleService.getUserRoleByUsernameAndPassword(username, password);

        UsersModel userLogin = UsersModel.builder()
                .username(username)
                .password(password)
                .role(role)
                .build();

        if (isValidLogin(userLogin)) {
            System.out.println("Login berhasil!");
            if (userLogin.getRole() == UserRoleModel.ADMIN){
                adminController.mainMenuAdmin();
            } else if (userLogin.getRole() == UserRoleModel.USER) {
                userController.mainMenuUser();
            }
        } else {
            System.out.println("Login gagal! Coba lagi.");
        }
    }

    // melakukan pengecekan apakah login valid
    private boolean isValidLogin(UsersModel userDataLogin) {
        UsersModel user = usersService.getUserByUsernameAndPassword(userDataLogin.getUsername(), userDataLogin.getPassword());
        return user != null;
    }
}
