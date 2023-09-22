package binar.bej1.diffaazkhani.view;

import binar.bej1.diffaazkhani.model.MenuModel;
import binar.bej1.diffaazkhani.model.OrderModel;
import binar.bej1.diffaazkhani.service.MenuService;
import binar.bej1.diffaazkhani.service.OrderService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuView {
    private final MenuService menuService;
    private final Scanner scanner;
    private final OrderService orderService;

    public MenuView(MenuService menuService, Scanner scanner, OrderService orderService) {
        this.menuService = menuService;
        this.scanner = scanner;
        this.orderService = orderService;
    }

    public void displayMenu() {
        while (true) {
            System.out.println("===================================");
            System.out.println("Selamat Datang di BinarFud         ");
            System.out.println("===================================");
            System.out.println("Silahkan pilih makanan :           ");

            List<MenuModel> menus = menuService.getAllMenus();

            // mengambil semua menu dari Model Menu
            menus.stream()
                    .map(menu -> menu.getId() + ". " + menu.getItemsName() + " - Rp. " + menu.getPrice())
                    .forEach(System.out::println);

            System.out.println("0. Kembali ke Menu Utama           ");
            System.out.print("=> ");

            try {
                int menuId = scanner.nextInt();
                if (menuId == 0) {
                    break;
                }

                Optional<MenuModel> selectedMenuOpt = menuService.getMenuById(menuId);

                // mengecek apakah menu yang diambil berdasarkan id tersedia
                if (selectedMenuOpt.isPresent()) {
                    MenuModel selectedMenu = selectedMenuOpt.get();
                    System.out.print("Masukkan jumlah pesanan (" + selectedMenu.getItemsName() + " ) : ");
                    int quantity = scanner.nextInt();

                    Optional<OrderModel> currentOrderOpt = orderService.getCurrentOrder();

                    // menambahkan pesanan ke keranjang jika sebelumnya sudah ada
                    if (currentOrderOpt.isPresent()) {
                        OrderModel currentOrder = currentOrderOpt.get();
                        orderService.addItemToOrder(currentOrder.getId(), selectedMenu, quantity);
                        System.out.println("Pesanan berhasil ditambahkan ke keranjang.");
                    } else {
                        // menambahkan pesanan ke keranjang jika menu baru
                        OrderModel newOrder = new OrderModel();
                        orderService.addOrder(newOrder);
                        orderService.addItemToOrder(newOrder.getId(), selectedMenu, quantity);
                        System.out.println("Pesanan baru berhasil dibuat dan pesanan ditambahkan ke keranjang.");
                    }
                } else {
                    System.out.println("Menu tidak ditemukan.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid");
                scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Silakan masukkan nomor yang sesuai.");
            }
        }
    }
}
