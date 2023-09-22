package binar.bej1.diffaazkhani.service;

import binar.bej1.diffaazkhani.model.MenuModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuServiceImpl implements MenuService {
    List<MenuModel> menuList = new ArrayList<>();

    @Override
    public List<MenuModel> getAllMenus() {
        return menuList;
    }

    @Override
    public Optional<MenuModel> getMenuById(Integer id) {
        return menuList.stream()
                .filter(menu -> menu.getId().equals(id))
                .findFirst();
    }

    @Override
    public void addMenu(MenuModel menu) {
        menuList.add(menu);
    }
}
