package binar.bej1.diffaazkhani.BinarFudChallenge4.controller;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.MerchantModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.ProductModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.MerchantRepository;
import binar.bej1.diffaazkhani.BinarFudChallenge4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserController {
    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private ProductRepository productRepository;

    private Scanner scanner = new Scanner(System.in);
    private List<ProductModel> cart = new ArrayList<>();

    public void mainMenuUser() {
        System.out.println("=====================================================");
        System.out.println("Selamat datang di Aplikasi Pemesanan Makanan         ");
        System.out.println("Role : User                                          ");
        System.out.println("=====================================================");
        System.out.println("1. Lihat Merchant Tersedia                           ");
        System.out.println("2. Lihat Keranjang                                   ");
        System.out.println("3. Keluar Aplikasi                                   ");
        String pilih = scanner.nextLine();
        switch (pilih) {
            case "1":
                showMerchant();
                break;
            case "2":
                showCart();
                break;
            case "3":
                System.out.println("Terima kasih, sampai jumpa!");
                System.exit(0);
                break;
            default:
                System.out.println("Pilihan tidak valid!");
                mainMenuUser();
        }
    }

    // menampilkan semua merchant yang sendang buka
    public void showMerchant() {
        System.out.println("=====================================================");
        System.out.println("Selamat datang di Aplikasi Pemesanan Makanan         ");
        System.out.println("Menu : Merchant                   Status : Open      ");
        System.out.println("=====================================================");

        // Mengambil daftar merchant yang sedang buka dari MerchantRepository
        List<MerchantModel> openMerchants = merchantRepository.findOpenMerchants();

        // mengecek apakah merchant ada ?
        if (openMerchants.isEmpty()) {
            System.out.println("Tidak ada merchant yang tersedia saat ini.");
        } else {
            // mengambil semua merchant yang buka
            for (int i = 0; i < openMerchants.size(); i++) {
                System.out.println((i + 1) + ". " + openMerchants.get(i).getMerchantName());
            }

            // memilih merchant yang buka untuk di tampilkan
            System.out.print("Pilih Merchant : ");
            int selectedMerchantIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (selectedMerchantIndex >= 0 && selectedMerchantIndex < openMerchants.size()) {
                MerchantModel selectedMerchant = openMerchants.get(selectedMerchantIndex);
                showMerchantProducts(selectedMerchant);
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }

        mainMenuUser();
    }

    // menampilkan semua produk dari merchant yang terpilih
    private void showMerchantProducts(MerchantModel merchant) {
        System.out.println("=====================================================");
        System.out.println("Selamat datang di " + merchant.getMerchantName());
        System.out.println("Menu : Merchant                   Status : Open      ");
        System.out.println("=====================================================");

        // Daftar produk yang dijual oleh merchant
        List<ProductModel> merchantProducts = productRepository.findProductsByMerchantCode(Math.toIntExact(merchant.getMerchantId()));

        // melakukan pengecekan apakah pada merchant tersebut terdapat produk yang dijual ?
        if (merchantProducts.isEmpty()) {
            System.out.println("Tidak ada produk yang tersedia saat ini.");
            mainMenuUser();
        } else {
            // mengambil semua peroduk di merchant tersebut
            for (int i = 0; i < merchantProducts.size(); i++) {
                ProductModel product = merchantProducts.get(i);
                System.out.println((i + 1) + ". " + product.getProductName() + " - Harga: " + product.getPrice());
            }

            // memilih produk yang tersedia untuk di berikan ke keranjang
            System.out.print("Pilih menu : ");
            int selectedProductIndex = Integer.parseInt(scanner.nextLine()) - 1;
            if (selectedProductIndex >= 0 && selectedProductIndex < merchantProducts.size()) {
                ProductModel selectedProduct = merchantProducts.get(selectedProductIndex);
                addToCart(selectedProduct);
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }
    }

    // menambahkan produk yang dibeli dari merchant ke dalam keranjang
    private void addToCart(ProductModel product) {
        System.out.println("=====================================================");
        System.out.println("Berhasil menambahkan " + product.getProductName() + " ke keranjang.");
        cart.add(product);
        mainMenuUser();
    }

    // menampilkan semua kerjang dari user tersebut
    private void showCart() {
        System.out.println("=====================================================");
        System.out.println("Keranjang Belanja Anda:");
        if (cart.isEmpty()) {
            System.out.println("Keranjang kosong.");
            System.out.println("=====================================================");
            System.out.println("1. Kembali ke menu utama");
        } else {
            // Tampilkan isi keranjang
            double total = 0;
            for (int i = 0; i < cart.size(); i++) {
                ProductModel product = cart.get(i);
                System.out.println((i + 1) + ". " + product.getProductName() + " - Harga: " + product.getPrice());
                total += product.getPrice();
            }
            System.out.println("Total Pembelian: " + total);
            System.out.println("=====================================================");
            System.out.println("1. Checkout");
            System.out.println("2. Hapus item dari keranjang");
            System.out.println("3. Kembali ke menu utama");
        }

        String pilih = scanner.nextLine();
        switch (pilih) {
            case "1":
                checkout();
                break;
            case "2":
                mainMenuUser();
                break;
            default:
                System.out.println("Pilihan tidak valid!");
                showCart();
        }
    }

    // melakukan pembayaran / pembelian
    private void checkout() {

        // harusnya disini logic checkout dengan output berupa file
    }
}
