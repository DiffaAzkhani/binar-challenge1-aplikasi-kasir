package binar.bej1.diffaazkhani.BinarFudChallenge4.controller;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.MerchantModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.ProductModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.UsersModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.service.MerchantService;
import binar.bej1.diffaazkhani.BinarFudChallenge4.service.ProductService;
import binar.bej1.diffaazkhani.BinarFudChallenge4.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller
public class AdminController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private ProductService productService;

    private final Scanner scanner = new Scanner(System.in);

    // Menu utama tampilan admin
    public void mainMenuAdmin() {
        System.out.println("=====================================================");
        System.out.println("Selamat datang di Aplikasi Pemesanan Makanan         ");
        System.out.println("Role: Admin                                          ");
        System.out.println("=====================================================");
        System.out.println("1. Merchant Editor                                   ");
        System.out.println("2. Product Editor                                    ");
        System.out.println("3. Users Editor                                      ");
        System.out.println("90. Keluar Aplikasi                                  ");
        System.out.print("Pilih: ");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                merchantEditor();
                break;
            case "2":
                productEditor();
                break;
            case "3":
                usersEditor();
                break;
            case "90":
                System.out.println("Keluar Aplikasi");
                return;
            default:
                System.out.println("Pilihan tidak valid!");
                mainMenuAdmin();
        }
    }

    public void merchantEditor(){
        System.out.println("=====================================================");
        System.out.println("Selamat datang di Aplikasi Pemesanan Makanan         ");
        System.out.println("Role: Admin               Menu: Merchant Editor      ");
        System.out.println("=====================================================");
        System.out.println("1. Tambah Merchant                                   ");
        System.out.println("2. Hapus Merchant                                    ");
        System.out.println("3. Ubah Status Merchant (OPEN/CLOSE)                 ");
        System.out.println("90. Kembali ke Main Menu                             ");
        System.out.print("Pilih: ");

        String choice = scanner.nextLine();
        switch (choice){
            case "1":
                addMerchant();
                break;
            case "2":
                deleteMerchant();
                break;
            case "3":
                editStatusMerchant();
                break;
            case "90":
                mainMenuAdmin();
                break;
            default:
                System.out.println("Pilihan tidak valid!");
                merchantEditor();
        }
    }

    public void productEditor(){
        System.out.println("=====================================================");
        System.out.println("Selamat datang di Aplikasi Pemesanan Makanan         ");
        System.out.println("Role: Admin               Menu: Product Editor       ");
        System.out.println("=====================================================");
        System.out.println("1. Tambah Product                                    ");
        System.out.println("2. Hapus Product                                     ");
        System.out.println("3. Edit Product                                      ");
        System.out.println("90. Kembali ke Main Menu                             ");
        System.out.print("Pilih: ");

        String choice = scanner.nextLine();
        switch (choice){
            case "1":
                addProduct();
                break;
            case "2":
                deleteProduct();
                break;
            case "3":
                editProductDetail();
                break;
            case "90":
                mainMenuAdmin();
                break;
            default:
                System.out.println("Pilihan tidak valid!");
                productEditor();
        }
    }

    public void usersEditor(){
        System.out.println("=====================================================");
        System.out.println("Selamat datang di Aplikasi Pemesanan Makanan         ");
        System.out.println("Role: Admin               Menu: Users Editor         ");
        System.out.println("=====================================================");
        System.out.println("1. Ubah User                                         ");
        System.out.println("2. Hapus User                                        ");
        System.out.println("90. Kembali ke Main Menu                             ");
        System.out.print("Pilih: ");
        String choice = scanner.nextLine();
        switch (choice){
            case "1":
                editUser();
                break;
            case "2":
                deleteUser();
                break;
            case "90":
                mainMenuAdmin();
                break;
            default:
                System.out.println("Pilihan tidak valid!");
                usersEditor();
        }
    }

    // menu menambahkan merchant
    public void addMerchant() {
        System.out.println("=====================================================");
        System.out.println("Selamat datang di Aplikasi Pemesanan Makanan");
        System.out.println("Menu: Menambahkan Merchant");
        System.out.println("=====================================================");
        System.out.print("Masukkan Nama Merchant: ");
        String merchantName = scanner.nextLine();
        System.out.print("Masukkan lokasi merchant: ");
        String merchantLocation = scanner.nextLine();
        System.out.print("Status Merchant (Open/Close): ");
        String status = scanner.nextLine().toLowerCase();

        boolean isOpen = status.equals("open");

        // membuat objek untuk menginisialisasi atribut-atribut dari objek merchant
        MerchantModel merchant = MerchantModel.builder()
                .merchantName(merchantName)
                .merchantLocation(merchantLocation)
                .open(isOpen)
                .build();

        merchantService.addMerchant(merchant);
        System.out.println("Merchant anda berhasil dibuat.");
        merchantEditor();
    }

    // menu edit status merchant
    public void editStatusMerchant() {
        System.out.println("=====================================================");
        System.out.println("Selamat datang di Aplikasi Pemesanan Makanan         ");
        System.out.println("Menu: Edit Status Merchant OPEN/CLOSE                ");
        System.out.println("=====================================================");

        // Menampilkan daftar merchant
        List<MerchantModel> merchants = merchantService.getAllMerchants();
        if (merchants.isEmpty()) {
            System.out.println("Tidak ada merchant yang tersedia saat ini.");
            merchantEditor();
            return;
        }

        System.out.println("Pilih merchant berdasarkan nomor:");
        for (int i = 0; i < merchants.size(); i++) {
            MerchantModel merchant = merchants.get(i);
            System.out.println((i + 1) + ". " + merchant.getMerchantName());
        }

        // meminta pengguna untuk memilih berchant yang ingin di edit berdasarkan nomor
        System.out.print("Pilih nomor merchant yang ingin di edit: ");
        int selectedMerchantIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (selectedMerchantIndex >= 0 && selectedMerchantIndex < merchants.size()) {
            MerchantModel merchantToEdit = merchants.get(selectedMerchantIndex);

            // meminta pengguna untuk mengubah status merchant open/close
            System.out.println("=====================================================");
            System.out.println("Ubah status merchant OPEN/CLOSE: " + merchantToEdit.getMerchantName());
            System.out.println("====================================================");
            System.out.print("Masukkan status merchant (OPEN/CLOSE): ");
            String status = scanner.nextLine().toLowerCase();

            boolean isOpen = false;

            if (status.equals("open")) {
                isOpen = true;
            } else if (status.equals("close")) {
                isOpen = false;
            } else {
                System.out.println("Status tidak valid. Menggunakan nilai default 'false'.");
            }

            // melakukan update status pada merchant (open/close)
            merchantToEdit.setOpen(isOpen);
            merchantService.updateMerchant(merchantToEdit);
            System.out.println("Merchant status pada " + merchantToEdit.getMerchantName() + " berhasil diubah.");
            merchantEditor();
        } else {
            System.out.println("Nomor merchant yang dipilih tidak valid.");
            merchantEditor();
        }
    }

    public void deleteMerchant() {
        System.out.println("=====================================================");
        System.out.println("Selamat datang di Aplikasi Pemesanan Makanan         ");
        System.out.println("Menu : Delete Merchant                               ");
        System.out.println("=====================================================");

        // Menampilkan daftar merchant
        List<MerchantModel> merchants = merchantService.getAllMerchants();
        if (merchants.isEmpty()) {
            System.out.println("Tidak ada merchant yang tersedia saat ini.");
            editStatusMerchant();
            return;
        }

        System.out.println("Pilih merchant berdasarkan nomor:");
        for (int i = 0; i < merchants.size(); i++) {
            MerchantModel merchant = merchants.get(i);
            System.out.println((i + 1) + ". " + merchant.getMerchantName());
        }

        // meminta pengguna memilih merchant berdasarkan nomer
        System.out.print("Pilih nomor merchant yang ingin dihapus: ");
        int selectedMerchantIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (selectedMerchantIndex >= 0 && selectedMerchantIndex < merchants.size()) {
            MerchantModel merchantToDelete = merchants.get(selectedMerchantIndex);

            // Periksa apakah merchant memiliki produk yang dijual
            List<ProductModel> merchantProducts = productService.findProductsByMerchantId(merchantToDelete.getMerchantId());
            if (!merchantProducts.isEmpty()) {
                System.out.println("Merchant ini masih memiliki produk yang dijual, tolong hapus produk terlebih dahulu!");
                editStatusMerchant();
                return;
            }

            // Hapus merchant dari sistem jika tidak ada produk yang dijual
            merchantService.deleteMerchantByMerchantId(merchantToDelete.getMerchantId());
            System.out.println("Merchant " + merchantToDelete.getMerchantName() + " telah dihapus.");
            merchantEditor();
        } else {
            System.out.println("Nomor merchant yang dipilih tidak valid.");
            merchantEditor();
        }
    }

    public void addProduct() {
        System.out.println("=====================================================");
        System.out.println("Selamat datang di Aplikasi Pemesanan Makanan         ");
        System.out.println("Menu: Menambahkan Product                            ");
        System.out.println("=====================================================");

        // Menampilkan daftar merchant
        List<MerchantModel> merchants = merchantService.getAllMerchants();
        if (merchants.isEmpty()) {
            System.out.println("Tidak ada merchant yang tersedia saat ini.");
            mainMenuAdmin();
            return;
        }

        // mengambil semua data semua merchant
        System.out.println("Pilih merchant berdasarkan nomor:");
        for (int i = 0; i < merchants.size(); i++) {
            MerchantModel merchant = merchants.get(i);
            System.out.println((i + 1) + ". " + merchant.getMerchantName());
        }

        // meminta pengguna memilih merchant berdasarkan nomer
        System.out.print("Pilih nomor merchant: ");
        int selectedMerchantIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (selectedMerchantIndex >= 0 && selectedMerchantIndex < merchants.size()) {
            MerchantModel selectedMerchant = merchants.get(selectedMerchantIndex);

            // meeminta pengguna untuk memasukkan data pada atribut product
            System.out.println("=====================================================");
            System.out.println("Menambahkan product pada merchant: " + selectedMerchant.getMerchantName());
            System.out.println("====================================================");
            System.out.print("Masukkan nama product: ");
            String productName = scanner.nextLine();
            System.out.print("Masukkan harga product: ");
            int productPrice = Integer.parseInt(scanner.nextLine());

            // membuat objek untuk menginisialisasi atribut atribut dari objek Product
            ProductModel product = ProductModel.builder()
                    .productName(productName)
                    .price(productPrice)
                    .merchant(selectedMerchant)
                    .build();

            productService.addProduct(product);
            System.out.println("Product anda berhasil dibuat.");
            mainMenuAdmin();
        } else {
            System.out.println("Pilihan merchant tidak valid.");
            mainMenuAdmin();
        }
    }

    public void deleteProduct() {
        System.out.println("=====================================================");
        System.out.println("Selamat datang di Aplikasi Pemesanan Makanan         ");
        System.out.println("Menu : Delete Produk                                 ");
        System.out.println("=====================================================");
        System.out.println("Pilih Merchant : ");

        // Menampilkan daftar merchant yang tersedia
        List<MerchantModel> merchants = merchantService.getAllMerchants();
        if (merchants.isEmpty()) {
            System.out.println("Tidak ada merchant yang tersedia saat ini.");
            return;
        }

        for (int i = 0; i < merchants.size(); i++) {
            MerchantModel merchant = merchants.get(i);
            System.out.println((i + 1) + ". " + merchant.getMerchantName());
        }

        // Meminta pengguna memilih merchant berdasarkan nomor
        System.out.print("Pilih nomor merchant: ");
        int selectedMerchantIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (selectedMerchantIndex >= 0 && selectedMerchantIndex < merchants.size()) {
            MerchantModel selectedMerchant = merchants.get(selectedMerchantIndex);

            // Menampilkan daftar produk dari merchant yang terpilih
            List<ProductModel> products = productService.findProductsByMerchantId(selectedMerchant.getMerchantId());
            if (products.isEmpty()) {
                System.out.println("Tidak ada produk yang tersedia dari merchant " + selectedMerchant.getMerchantName());
                productEditor();
            }

            for (int i = 0; i < products.size(); i++) {
                ProductModel product = products.get(i);
                System.out.println((i + 1) + ". " + product.getProductName());
            }

            // Meminta pengguna memilih produk berdasarkan nomor
            System.out.print("Pilih nomor produk yang ingin dihapus: ");
            int selectedProductIndex = Integer.parseInt(scanner.nextLine()) - 1;

            if (selectedProductIndex >= 0 && selectedProductIndex < products.size()) {
                ProductModel productToDelete = products.get(selectedProductIndex);
                productService.deleteProductByProductId(productToDelete.getProductId());
                System.out.println("Produk " + productToDelete.getProductName() + " telah dihapus.");
            } else {
                System.out.println("Nomor produk yang dipilih tidak valid.");
            }
        } else {
            System.out.println("Nomor merchant yang dipilih tidak valid.");
        }
        productEditor();
    }

    public void editProductDetail() {
        System.out.println("=====================================================");
        System.out.println("Selamat datang di Aplikasi Pemesanan Makanan         ");
        System.out.println("Menu : Edit Merchant (Detail Produk)                 ");
        System.out.println("=====================================================");

        // Menampilkan daftar merchant
        List<MerchantModel> merchants = merchantService.getAllMerchantIsOpen();
        if (merchants.isEmpty()) {
            System.out.println("Tidak ada merchant yang tersedia saat ini.");
            return;
        }

        for (int i = 0; i < merchants.size(); i++) {
            MerchantModel merchant = merchants.get(i);
            System.out.println((i + 1) + ". " + merchant.getMerchantName());
        }

        // Meminta pengguna memilih merchant berdasarkan nomor
        System.out.print("Pilih nomor merchant: ");
        int selectedMerchantIndex = Integer.parseInt(scanner.nextLine()) - 1;

        if (selectedMerchantIndex >= 0 && selectedMerchantIndex < merchants.size()) {
            MerchantModel selectedMerchant = merchants.get(selectedMerchantIndex);

            // Menampilkan daftar produk pada merchant yang dipilih
            List<ProductModel> products = productService.findProductsByMerchantId(selectedMerchant.getMerchantId());
            if (products.isEmpty()) {
                System.out.println("Tidak ada produk yang tersedia pada merchant ini.");
                return;
            }

            for (int i = 0; i < products.size(); i++) {
                ProductModel product = products.get(i);
                System.out.println((i + 1) + ". " + product.getProductName() + " - Harga: " + product.getPrice());
            }

            // Meminta pengguna memilih produk berdasarkan nomor
            System.out.print("Pilih nomor produk yang ingin di edit: ");
            int selectedProductIndex = Integer.parseInt(scanner.nextLine()) - 1;

            // memasukkan input untuk mengubah atribut produk
            if (selectedProductIndex >= 0 && selectedProductIndex < products.size()) {
                ProductModel productToEdit = products.get(selectedProductIndex);
                System.out.println("Ubah detail product pada merchant: " + selectedMerchant.getMerchantName());
                System.out.print("Masukkan Nama produk: ");
                String productNameEdit = scanner.nextLine();
                System.out.print("Masukkan harga produk: ");
                int productPriceEdit = Integer.parseInt(scanner.nextLine());

                // membuat objek untuk menginisialisasi atribut atribut dari objek Product
                ProductModel product = ProductModel.builder()
                        .productName(productNameEdit)
                        .price(productPriceEdit)
                        .productId(productToEdit.getProductId())
                        .merchant(selectedMerchant)
                        .build();

                productService.updateProduct(product);
                System.out.println("Product pada merchant " + selectedMerchant.getMerchantName() + " berhasil diubah.");
                mainMenuAdmin();
            } else {
                System.out.println("Nomor produk yang dipilih tidak valid.");
            }
        } else {
            System.out.println("Nomor merchant yang dipilih tidak valid.");
        }
    }

    public void editUser() {
        System.out.println("=====================================================");
        System.out.println("Selamat datang di Aplikasi Pemesanan Makanan         ");
        System.out.println("Menu: Update Status User                             ");
        System.out.println("=====================================================");
        System.out.print("Masukkan Nama User yang ingin diubah: ");
        String userName = scanner.nextLine();
        System.out.print("Masukkan Password User yang ingin diubah: ");
        String password = scanner.nextLine();

        // Cari user berdasarkan username dan password
        UsersModel userToEdit = usersService.getUserByUsernameAndPassword(userName, password);

        if (userToEdit != null) {
            System.out.println("=====================================================");
            System.out.println("Edit data user: " + userToEdit.getUsername());
            System.out.println("====================================================");

            System.out.print("Masukkan Password baru: ");
            String newUserPassword = scanner.nextLine();
            System.out.print("Masukkan Email User baru: ");
            String newUserEmail = scanner.nextLine();

            // Membangun objek user dengan data baru
            UsersModel updatedUser = UsersModel.builder()
                    .username(userName)
                    .password(newUserPassword)
                    .emailAddress(newUserEmail)
                    .role(userToEdit.getRole())
                    .userId(userToEdit.getUserId())
                    .build();

            // Memperbarui data user
            usersService.updateUser(updatedUser);
            System.out.println("Data user " + userName + " berhasil diubah.");
        } else {
            System.out.println("User dengan username " + userName + " tidak ditemukan.");
        }
        usersEditor();
    }

    public void deleteUser(){
        System.out.println("=====================================================");
        System.out.println("Selamat datang di Aplikasi Pemesanan Makanan         ");
        System.out.println("Menu: Delete Status User                             ");
        System.out.println("=====================================================");
        System.out.print("Masukkan username user yang ingin di hapus : ");
        String usernameDelete = scanner.nextLine();
        System.out.print("Masukkan password user yang ingin di hapus : ");
        String passwordDelete = scanner.nextLine();

        UsersModel userToDelete = usersService.getUserByUsernameAndPassword(usernameDelete,passwordDelete);

        if (userToDelete != null) {
            // Hapus produk dari sistem
            usersService.deleteUser(userToDelete);
            System.out.println("Username: " + usernameDelete + ", telah dihapus.");
        } else {
            System.out.println("Username:  " + usernameDelete + ", tidak ditemukan.");
        }
        usersEditor();
    }
}
