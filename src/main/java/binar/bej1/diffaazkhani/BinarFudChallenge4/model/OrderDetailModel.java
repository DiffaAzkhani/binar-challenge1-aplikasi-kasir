package binar.bej1.diffaazkhani.BinarFudChallenge4.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "table_order_detail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetailModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long orderDetailId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_price")
    private double totalPrice;


    // Relasi ke ProductModel
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel product;

    // Relasi ke OrderModel
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;
}

