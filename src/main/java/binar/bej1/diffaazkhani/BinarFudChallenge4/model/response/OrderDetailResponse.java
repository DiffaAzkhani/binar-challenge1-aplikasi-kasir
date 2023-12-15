package binar.bej1.diffaazkhani.BinarFudChallenge4.model.response;

import binar.bej1.diffaazkhani.BinarFudChallenge4.model.ProductModel;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponse {

    private Long quantity;

    private double totalPrice;

    private String productName;
}
