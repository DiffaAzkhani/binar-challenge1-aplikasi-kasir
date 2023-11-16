package binar.bej1.diffaazkhani.BinarFudChallenge4.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponse {
    private int quantity;
    private double totalPrice;
    private String productName;
}
