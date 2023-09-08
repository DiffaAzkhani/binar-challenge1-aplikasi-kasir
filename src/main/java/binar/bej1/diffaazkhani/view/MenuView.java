package binar.bej1.diffaazkhani.view;

import binar.bej1.diffaazkhani.model.MenuModel;
import lombok.AllArgsConstructor;
import binar.bej1.diffaazkhani.model.OrderModel;
import binar.bej1.diffaazkhani.service.MenuService;
import binar.bej1.diffaazkhani.service.OrderService;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class MenuView {
    private final MenuService menuService;
    private final Scanner scanner;
    private final OrderService orderService;

    public void displayMenu() {
        while (true) {
            System.out.println("===================================");
            System.out.println("Selamat Datang di BinarFud         ");
            System.out.println("===================================");
            System.out.println("Silahkan pilih makanan :           ");
            List<MenuModel> menus = menuService.getAllMenus();

            for (MenuModel menu : menus) {
                System.out.println(menu.getId() + ". " + menu.getItemsName() + " - $" + menu.getPrice());
            }
            System.out.println("0. Kembali ke Menu Utama           ");

            System.out.print("=> ");
            int menuId = scanner.nextInt();

            if (menuId == 0) {
                break;
            }


            MenuModel selectedMenu = null;
            try {
                selectedMenu = menuService.getMenuById(menuId);
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan dalam mendapatkan menu. Silakan coba lagi.");
            }
//            MenuModel selectedMenu = menuService.getMenuById(menuId);


            if (selectedMenu != null) {
                System.out.print("Masukkan jumlah pesanan: ");
                int quantity = scanner.nextInt();

                // Mendapatkan pesanan yang sedang aktif dari orderService
                OrderModel currentOrder = orderService.getCurrentOrder();

                // Memeriksa apakah ada pesanan yang aktif atau belum
                if (currentOrder == null) {
                    // Jika tidak ada, buat pesanan baru
                    currentOrder = new OrderModel();
                    orderService.addOrder(currentOrder);
                }

                // Menambahkan item pesanan ke pesanan yang sedang aktif
                orderService.addItemToOrder(currentOrder.getId(), selectedMenu, quantity);
                System.out.println("Pesanan berhasil ditambahkan ke keranjang.");
            } else {
                System.out.println("Menu tidak ditemukan.");
            }
        }
    }
}
