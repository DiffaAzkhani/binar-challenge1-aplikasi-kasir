package binar.bej1.diffaazkhani.BinarFudChallenge4.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "table_product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private double price;


    // Relasional ke MerchantModel
    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private MerchantModel merchant;
}
