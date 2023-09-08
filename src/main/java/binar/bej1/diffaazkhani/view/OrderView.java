package binar.bej1.diffaazkhani.view;

import binar.bej1.diffaazkhani.model.MenuModel;
import binar.bej1.diffaazkhani.model.OrderModel;
import binar.bej1.diffaazkhani.service.OrderService;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    private final OrderService orderService;
    private final Scanner scanner;

    private static final String NAMA_FILE_NOTA = "PaymentNote.txt";


    public OrderView(OrderService orderService, Scanner scanner) {
        this.orderService = orderService;
        this.scanner = scanner;
    }

    public void displayOrder() {
        while (true) {
            System.out.println("=== Keranjang Pesanan ===");
            List<OrderModel> orders = orderService.getAllOrders();

            if (orders.isEmpty()) {
                System.out.println("Keranjang pesanan kosong.");
            } else {
                for (OrderModel order : orders) {
                    List<MenuModel> menuModels = order.getMenuModels();
                    List<Integer> orderQuantities = order.getOrderQuantities();

                    for (int i = 0; i < menuModels.size(); i++) {
                        MenuModel menu = menuModels.get(i);
                        int quantity = orderQuantities.get(i);
                        System.out.println(menu.getItemsName() + " - Jumlah: " + quantity);
                    }
                }
            }

            System.out.println("1. Konfirmasi dan Bayar");
            System.out.println("2. Kembali ke Menu Utama");
            System.out.print("=> ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    savePaymentNote();
                    printPaymentNote();
                    return;
                case 2:
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
                    break;
            }
        }
    }

    private void savePaymentNote() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NAMA_FILE_NOTA))) {
            writer.println("===================================");
            writer.println("BinarFud                           ");
            writer.println("===================================\n");
            writer.println("Terima kasih sudah memesan         ");
            writer.println("di BinarFud                        \n");
            int totalPrice = 0;
            int totalOrders = 0;

            List<OrderModel> orders = orderService.getAllOrders();
            for (OrderModel order : orders) {
                List<MenuModel> menuModels = order.getMenuModels();
                List<Integer> orderQuantities = order.getOrderQuantities();
                for (int i = 0; i < menuModels.size(); i++) {
                    MenuModel menu = menuModels.get(i);
                    int quantity = orderQuantities.get(i);
                    writer.println(menu.getItemsName() + "    \t\t" + quantity + " \t\t" + menu.getPrice());
                    totalPrice += menu.getPrice() * quantity;
                    totalOrders += quantity;
                }
            }

            writer.println("===================================");
            writer.println("Total \t\t\t\t" + totalOrders + "\t\t" + totalPrice + "\n");
            writer.println("Pembayaran : BinarCash\n           ");
            writer.println("===================================");
            writer.println("Simpan struk ini sebagai bukti     ");
            writer.println("pembayaran                         ");
            writer.println("===================================");

            System.out.println("Nota berhasil disimpan di file: " + NAMA_FILE_NOTA);
        } catch (IOException e) {
            System.out.println("Gagal menyimpan nota: " + e.getMessage());
        }
    }

    private void printPaymentNote() {
        savePaymentNote();
        try (BufferedReader reader = new BufferedReader(new FileReader(NAMA_FILE_NOTA))) {
            String line;

            // membaca baris file yang sudah dituliskan tadi pada method simpanNota
            // hasil file yang sudah dibaca akan disimpan di lokasi projek
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca nota: " + e.getMessage());
        }
    }
}
