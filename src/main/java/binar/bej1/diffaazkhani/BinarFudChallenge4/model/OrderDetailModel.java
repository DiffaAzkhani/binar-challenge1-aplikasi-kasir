package binar.bej1.diffaazkhani.BinarFudChallenge4.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "table_order_detail")
@Data
public class OrderDetailModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long orderDetailId;

    @Column(name = "product_code")
    private int productCode;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_price")
    private double totalPrice;

    // Relasi ke OrderModel
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;
}

