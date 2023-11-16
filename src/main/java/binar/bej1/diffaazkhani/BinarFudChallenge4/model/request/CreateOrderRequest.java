package binar.bej1.diffaazkhani.BinarFudChallenge4.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderRequest {
    private Long userId;
    private Long productId;
    private Integer quantity;
    private String destinationAddress;
}
