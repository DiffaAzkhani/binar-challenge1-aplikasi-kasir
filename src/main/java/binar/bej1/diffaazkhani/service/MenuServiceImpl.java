package binar.bej1.diffaazkhani.service;

import binar.bej1.diffaazkhani.model.MenuModel;

import java.util.ArrayList;
import java.util.List;

public class MenuServiceImpl implements MenuService {
    List<MenuModel> menuList = new ArrayList<>();

    @Override
    public List<MenuModel> getAllMenus() {
        return menuList;
    }

    @Override
    public MenuModel getMenuById(Integer id) {
        for (MenuModel menu : menuList) {
            if (menu.getId().equals(id)) {
                return menu;
            }
        }
        return null;
    }

    @Override
    public void addMenu(MenuModel menu) {
        menuList.add(menu);
    }
}
