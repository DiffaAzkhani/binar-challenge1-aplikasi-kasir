package binar.bej1.diffaazkhani.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderModel {
    private Integer id;
    private List<MenuModel> menuModels;
    private List<Integer> orderQuantities;

    public void addOrderItem(MenuModel menu, int quantity) {
        if (menu != null && quantity > 0) {
            if (menuModels == null) {
                menuModels = new ArrayList<>();
                orderQuantities = new ArrayList<>();
            }
            menuModels.add(menu);
            orderQuantities.add(quantity);
        }
    }
}
