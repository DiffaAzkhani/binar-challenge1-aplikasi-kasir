package binar.bej1.diffaazkhani.service;

import binar.bej1.diffaazkhani.model.MenuModel;

import java.util.List;
import java.util.Optional;

public interface MenuService {
    List<MenuModel> getAllMenus();
    Optional<MenuModel> getMenuById(Integer id);
    void addMenu(MenuModel menu);
}
