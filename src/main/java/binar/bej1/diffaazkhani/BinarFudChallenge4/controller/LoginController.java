package binar.bej1.diffaazkhani.BinarFudChallenge4.controller;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.UsersModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.service.UsersService;

import java.util.Scanner;

public class LoginController {
    private Scanner scanner = new Scanner(System.in);
    private final UsersService usersService;

    private UserController userController;

    public LoginController(UsersService usersService) {
        this.usersService = usersService;
    }

    // Menu piliha login atau register
    public void mainMenuLoginRegister() {
        System.out.println("=====================================================");
        System.out.println("Selamat datang di Aplikasi Pemesanan Makanan BinarFud");
        System.out.println("                    Diffa Azkhani                    ");
        System.out.println("=====================================================");
        System.out.println("1. Login");
        System.out.println("2. Register");
        String pilih = scanner.nextLine();
        if (pilih.equals("1")) {
            loginMenu();
        } else if (pilih.equals("2")) {
            registerMenu();
        } else {
            System.out.println("Pilihan anda salah !");
        }
    }

    // menu register ketika user baru ingin membuat akun
    private void registerMenu() {
        System.out.println("=====================================================");
        System.out.println("Selamat datang di Aplikasi Pemesanan Makanan         ");
        System.out.println("Silakan lakukan registrasi:                          ");
        System.out.println("=====================================================");
        System.out.print("Masukkan nama pengguna (username):                     ");
        String username = scanner.nextLine();

        System.out.print("Masukkan alamat email: ");
        String email = scanner.nextLine();

        System.out.print("Masukkan kata sandi: ");
        String password = scanner.nextLine();

        UsersModel user = new UsersModel();
        user.setUsername(username);
        user.setEmailAddress(email);
        user.setPassword(password);

        UsersModel registeredUser = usersService.addUser(user);

        if (registeredUser != null) {
            System.out.println("Registrasi berhasil!");

            // logic register
            loginMenu();
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
        System.out.print("Masukkan nama pengguna (username):                     ");
        String username = scanner.nextLine();

        System.out.print("Masukkan kata sandi: ");
        String password = scanner.nextLine();

        if (isValidLogin(username, password)) {
            System.out.println("Login berhasil!");
            userController.mainMenuUser();
        } else {
            System.out.println("Login gagal! Coba lagi.");
        }
    }

    // melakukan pengecekan apakah login valid
    private boolean isValidLogin(String username, String password) {
        UsersModel user = usersService.getUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}
