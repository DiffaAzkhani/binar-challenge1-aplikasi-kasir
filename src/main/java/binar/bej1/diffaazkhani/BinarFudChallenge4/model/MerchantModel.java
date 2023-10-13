package binar.bej1.diffaazkhani.BinarFudChallenge4.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "table_merchant")
@Data
public class MerchantModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "merchant_id")
    private Long merchantId;

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "merchant_location")
    private String merchantLocation;

    @Column(name = "open")
    private boolean open;


    // Relasional ke ProductModel
    @OneToMany(mappedBy = "merchant")
    private List<ProductModel> product;
}
