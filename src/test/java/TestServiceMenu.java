import binar.bej1.diffaazkhani.model.MenuModel;
import binar.bej1.diffaazkhani.service.MenuService;
import binar.bej1.diffaazkhani.service.MenuServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestServiceMenu {
    private MenuService menuService;

    @BeforeEach
    void setUp() {
        menuService = new MenuServiceImpl();
    }

    @Test
    void testAddMenu() {
        // mencoba menambahkan menu
        MenuModel menu = new MenuModel(1, "Coba 1", 10);
        menuService.addMenu(menu);

        // mencoba mengambil semua menu yang di inisialisasi sebelumnya
        List<MenuModel> menus = menuService.getAllMenus();
        assertEquals(1, menus.size());
        assertEquals(menu, menus.get(0));
        System.out.println("Berhasil : Menu di tambahkan dan sesuai");
    }

    @Test
    void testGetAllMenus() {
        MenuModel menu1 = new MenuModel(1, "Coba 1", 10);
        MenuModel menu2 = new MenuModel(2, "Coba 2", 20);

        // menambahkan menu dummy
        menuService.addMenu(menu1);
        menuService.addMenu(menu2);

        // mencoba mengecek apakah menus berisikan semua menu dummy yang diisikan sebelumnya
        List<MenuModel> menus = menuService.getAllMenus();
        assertEquals(2, menus.size());
        assertTrue(menus.contains(menu1));
        assertTrue(menus.contains(menu2));
        System.out.println("Berhasil : Menu dummy berhasil di dapatkan semua");
    }

    @Test
    void testGetMenuById() {
        MenuModel menu1 = new MenuModel(1, "Coba 1", 10);
        MenuModel menu2 = new MenuModel(2, "Coba 2", 20);

        // menambahkan menu dummy
        menuService.addMenu(menu1);
        menuService.addMenu(menu2);

        // mencoba mengecek apakah menu dengan id 1 tersedia
        Optional<MenuModel> foundMenu1 = menuService.getMenuById(1);
        assertTrue(foundMenu1.isPresent());
        assertEquals(menu1, foundMenu1.get());
        System.out.println("Berhasil : Menu 1 tersedia");

        // mencoba untuk mengecek menu dengan id 3 apakah ada (seharusnya tidak ada)
        Optional<MenuModel> foundMenu3 = menuService.getMenuById(3);
        assertFalse(foundMenu3.isPresent());
        System.out.println("Berhasil : Menu 3 tidak ada");
    }
}
