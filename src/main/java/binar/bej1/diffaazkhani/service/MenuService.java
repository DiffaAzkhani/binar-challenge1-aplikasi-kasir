package binar.bej1.diffaazkhani.service;

import binar.bej1.diffaazkhani.model.MenuModel;

import java.util.List;

public interface MenuService {
    List<MenuModel> getAllMenus();
    MenuModel getMenuById(Integer id);
    void addMenu(MenuModel menu);
}
