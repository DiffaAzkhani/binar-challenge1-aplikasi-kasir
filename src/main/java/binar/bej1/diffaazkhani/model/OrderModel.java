package binar.bej1.diffaazkhani.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderModel {
    private Integer id;
    private List<MenuModel> menuModels;
    private List<Integer> orderQuantities;
}
