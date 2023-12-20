package binar.bej1.diffaazkhani.BinarFudChallenge4.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponse {

    private Long quantity;

    private double totalPrice;

    private String productName;
}
