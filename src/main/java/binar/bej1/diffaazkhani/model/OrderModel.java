package binar.bej1.diffaazkhani.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderModel {
    private Integer id;
    private List<MenuModel> menuModels;
    private List<Integer> orderQuantities;
}




