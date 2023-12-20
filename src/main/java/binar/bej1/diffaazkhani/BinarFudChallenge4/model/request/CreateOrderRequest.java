package binar.bej1.diffaazkhani.BinarFudChallenge4.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderRequest {
    private Long userId;
    private Long productId;
    private Long quantity;
    private String destinationAddress;
}
