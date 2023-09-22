package binar.bej1.diffaazkhani;

import binar.bej1.diffaazkhani.model.MenuModel;
import binar.bej1.diffaazkhani.service.MenuService;
import binar.bej1.diffaazkhani.service.MenuServiceImpl;
import binar.bej1.diffaazkhani.view.MenuView;
import binar.bej1.diffaazkhani.view.OrderView;
import binar.bej1.diffaazkhani.service.OrderServiceImpl;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inisialisasi MenuService dan OrderService sesuai dengan implementasi
        MenuService menuService = new MenuServiceImpl();
        OrderServiceImpl orderService = new OrderServiceImpl();

        Scanner scanner = new Scanner(System.in);

        // Inisialisasi MenuView dan OrderView
        MenuView menuView = new MenuView(menuService, scanner, orderService);
        OrderView orderView = new OrderView(orderService, scanner);

        // Menambahkan beberapa menu ke daftar menu
        menuService.addMenu(new MenuModel(1, "Nasi Goreng", 15000));
        menuService.addMenu(new MenuModel(2, "Mie Goreng", 13000));
        menuService.addMenu(new MenuModel(3, "Nasi + Ayam", 18000));
        menuService.addMenu(new MenuModel(4, "Es Teh Gede", 3000));
        menuService.addMenu(new MenuModel(5, "Es Jeruk", 5000));

        try{
            boolean isTrue = true;
            while (isTrue) {
                System.out.println("===================================");
                System.out.println("Program Pemesanan Makanan ");
                System.out.println("By Diffa Azkhani          ");
                System.out.println("===================================");
                System.out.println("1. Pilih Menu Makanan");
                System.out.println("2. Lihat Keranjang");
                System.out.println("3. Keluar");
                System.out.print("=> ");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        menuView.displayMenu();
                        break;
                    case 2:
                        orderView.displayOrder();
                        break;
                    case 3:
                        System.out.println("Terima kasih telah menggunakan aplikasi.");
                        isTrue = false;
                        scanner.close();
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
                        break;
                }
            }
        }catch (Exception e){
            System.out.println("Pilihan tidak valid. Silakan pilih lagi");
        }
        scanner.close();
    }
}
