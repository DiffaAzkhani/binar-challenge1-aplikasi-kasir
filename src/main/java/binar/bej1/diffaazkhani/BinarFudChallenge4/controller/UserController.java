package binar.bej1.diffaazkhani.BinarFudChallenge4.controller;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.MerchantModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.OrderDetailModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.OrderModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.model.ProductModel;
import binar.bej1.diffaazkhani.BinarFudChallenge4.service.MerchantService;
import binar.bej1.diffaazkhani.BinarFudChallenge4.service.OrderDetailService;
import binar.bej1.diffaazkhani.BinarFudChallenge4.service.OrderService;
import binar.bej1.diffaazkhani.BinarFudChallenge4.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.*;
import java.util.*;

@Controller
public class UserController {
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    private final Scanner scanner = new Scanner(System.in);
    private final Map<ProductModel, Integer> cart = new HashMap<>();

    private static final String NAMA_FILE_NOTA = "PaymentNote.txt";

    public void mainMenuUser() {
        System.out.println("=====================================================");
        System.out.println("Selamat datang di Aplikasi Pemesanan Makanan         ");
        System.out.println("Role : User                                          ");
        System.out.println("=====================================================");
        System.out.println("1. Lihat Merchant Tersedia                           ");
        System.out.println("2. Lihat Keranjang                                   ");
        System.out.println("90. Keluar Aplikasi                                  ");
        System.out.print("Pilih : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                showMerchant();
                break;
            case 2:
                showCart();
                break;
            case 90:
                System.out.println("Terima kasih, sampai jumpa!");
                return;
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
        List<MerchantModel> openMerchants = merchantService.getAllMerchantIsOpen();

        // mengecek apakah merchant ada ?
        if (openMerchants.isEmpty()) {
            System.out.println("Tidak ada merchant yang tersedia saat ini.");
        } else {
            // mengambil semua merchant yang buka
            for (int i = 0; i < openMerchants.size(); i++) {
                System.out.println((i + 1) + ". " + openMerchants.get(i).getMerchantName());
            }

            // memilih merchant yang buka untuk ditampilkan
            int selectedMerchantIndex = -1;
            while (selectedMerchantIndex < 0 || selectedMerchantIndex >= openMerchants.size()) {
                System.out.print("Pilih Merchant : ");
                int input = scanner.nextInt();
                try {
                    selectedMerchantIndex = input - 1;
                    if (selectedMerchantIndex < 0 || selectedMerchantIndex >= openMerchants.size()) {
                        System.out.println("Pilihan tidak valid!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input harus berupa angka");
                }
            }

            // menampilkan semua produk dari merchant yang dipilih
            MerchantModel selectedMerchant = openMerchants.get(selectedMerchantIndex);
            showMerchantProducts(selectedMerchant);
        }

        mainMenuUser();
    }

    // menampilkan produk pada merchant yang dipilih
    private void showMerchantProducts(MerchantModel merchant) {
        System.out.println("=====================================================");
        System.out.println("Selamat datang di ( " + merchant.getMerchantName() + " )");
        System.out.println("Menu : Merchant                   Status : Open      ");
        System.out.println("=====================================================");

        // daftar produk yang dijual oleh merchant
        List<ProductModel> merchantProducts = productService.findProductsByMerchantId(merchant.getMerchantId());

        // melakukan pengecekan apakah pada merchant tersebut terdapat produk yang dijual ?
        if (merchantProducts.isEmpty()) {
            System.out.println("Tidak ada produk yang tersedia saat ini.");
            showMerchant();
        } else {
            // mengambil semua produk di merchant tersebut
            for (int i = 0; i < merchantProducts.size(); i++) {
                ProductModel product = merchantProducts.get(i);
                System.out.println((i + 1) + ". " + product.getProductName() + " - Harga: " + product.getPrice());
            }

            // memilih produk yang tersedia untuk dimasukkan ke dalam keranjang
            System.out.print("Pilih menu : ");
            int selectedProductIndex = scanner.nextInt() - 1;

            if (selectedProductIndex >= 0 && selectedProductIndex < merchantProducts.size()) {
                ProductModel selectedProduct = merchantProducts.get(selectedProductIndex);

                // Meminta input jumlah pesanan
                System.out.print("Masukkan jumlah order untuk " + selectedProduct.getProductName() + ": ");
                int quantity = scanner.nextInt();

                if (quantity > 0) {
                    addToCart(selectedProduct, quantity); // Menambahkan produk ke keranjang
                } else {
                    System.out.println("Jumlah pesanan tidak valid!");
                }
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }
    }

    // menambahkan produk yang dibeli dari merchant ke dalam keranjang
    private void addToCart(ProductModel product, int quantity) {
        System.out.println("=====================================================");
        System.out.println("Berhasil menambahkan " + product.getProductName() + " - " + quantity + " ke keranjang.");

        // Periksa apakah produk sudah ada di keranjang
        if (cart.containsKey(product)) {
            int existingQuantity = cart.get(product);
            cart.put(product, existingQuantity + quantity); // Update jumlah pesanan jika produk sudah ada
        } else {
            cart.put(product, quantity); // Tambahkan produk ke keranjang jika belum ada
        }

        mainMenuUser();
    }

    // menampilkan pesanan yang telah dibuat user
    private void showCart() {
        System.out.println("=====================================================");
        System.out.println("Keranjang Belanja Anda:");

        if (cart.isEmpty()) {
            System.out.println("Keranjang kosong.");
        } else {
            double total = 0;
            int itemNumber = 1;

            // mengambil semua pesanan yang disimpan di dalam keranjang
            for (Map.Entry<ProductModel, Integer> entry : cart.entrySet()) {
                ProductModel product = entry.getKey();
                int quantity = entry.getValue();
                double subtotal = product.getPrice() * quantity;

                System.out.println(itemNumber + ". " + product.getProductName() + " - Harga: " + product.getPrice() + " - Jumlah: " + quantity + " - Subtotal: " + subtotal);
                total += subtotal;
                itemNumber++;
            }

            System.out.println("Total Pembelian: " + total);
        }

        System.out.println("=====================================================");
        System.out.println("1. Checkout");
        System.out.println("90. Kembali ke menu utama");
        System.out.print("Pilih : ");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                checkout();
                break;
            case 90:
                mainMenuUser();
                break;
            default:
                System.out.println("Pilihan tidak valid!");
                showCart();
        }
    }

    // melakukan pembayaran sekaligus mencetak nota dari hasil pesanan
    private void checkout() {
        System.out.println("===================================");
        System.out.println("Checkout Keranjang Belanja Anda:");

        if (cart.isEmpty()) {
            System.out.println("Keranjang kosong.");
            mainMenuUser();
        } else {
            System.out.print("Masukkan alamat tujuan: ");
            String destinationAddress = scanner.nextLine();

            OrderModel order = OrderModel.builder()
                    .orderTime(new Date())
                    .destinationAddress(destinationAddress)
                    .completed(true)
                    .build();


            // Simpan objek OrderModel ke dalam database
            orderService.saveOrder(order);

            // Membuat objek OrderDetailModel untuk setiap produk dalam keranjang
            for (Map.Entry<ProductModel, Integer> entry : cart.entrySet()) {
                ProductModel product = entry.getKey();
                int quantity = entry.getValue();

                // Buat objek OrderDetailModel
                OrderDetailModel orderDetail = OrderDetailModel.builder()
                        .quantity(quantity)
                        .totalPrice(product.getPrice() * quantity)
                        .product(product)
                        .order(order)
                        .build();

                // Simpan objek OrderDetailModel ke dalam database
                orderDetailService.saveOrderDetail(orderDetail);
            }

            // Simpan nota ke dalam file teks
            savePaymentNoteToFile();

            // Menghapus data pesanan setelah checkout
            cart.clear();
        }
    }

    // menyimpan nota file menggunakan string builder
    private void savePaymentNoteToFile(){
        int totalQuantity = 0;
        double totalPrice = 0;

        // Membuat nota dengan list nama produk dan quantity
        StringBuilder nota = new StringBuilder();
        nota.append("===================================\n");
        nota.append("BinarFud\n");
        nota.append("===================================\n\n");
        nota.append("Terima kasih sudah memesan\n");
        nota.append("di BinarFud\n\n");

        for (Map.Entry<ProductModel, Integer> entry : cart.entrySet()) {
            ProductModel product = entry.getKey();
            int quantity = entry.getValue();
            double subtotal = product.getPrice() * quantity;
            nota.append(product.getProductName()).append("\t\t\t").append(quantity).append("\t\t").append(subtotal).append("\n");
            totalPrice += subtotal;
            totalQuantity += quantity;
        }

        nota.append("===================================\n");
        nota.append("Total\t\t\t\t").append(totalQuantity).append("\t\t").append(totalPrice).append("\n\n");
        nota.append("Pembayaran : BinarCash\n\n");
        nota.append("===================================\n");
        nota.append("Simpan struk ini sebagai bukti\n");
        nota.append("pembayaran\n");
        nota.append("===================================");

        // Simpan nota dalam file teks
        try (PrintWriter writer = new PrintWriter(new FileWriter(NAMA_FILE_NOTA))) {
            writer.print(nota);
            System.out.println("Nota berhasil disimpan di file: " + NAMA_FILE_NOTA);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan nota: " + e.getMessage());
        }

        // Menampilkan isi nota pembayaran di layar
        printPaymentNote();
    }

    // Mencetak isi dari nota pembayaran yang telah disimpan dalam file teks
    private void printPaymentNote() {
        try (BufferedReader reader = new BufferedReader(new FileReader(NAMA_FILE_NOTA))) {
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca nota: " + e.getMessage());
        }

        // Kembali ke menu utama setelah menampilkan nota
        mainMenuUser();
    }
}
