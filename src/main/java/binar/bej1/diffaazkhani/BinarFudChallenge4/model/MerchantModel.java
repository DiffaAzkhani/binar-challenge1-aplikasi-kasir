package binar.bej1.diffaazkhani.BinarFudChallenge4.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "table_merchant")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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
}
