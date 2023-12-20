package binar.bej1.diffaazkhani.BinarFudChallenge4.model.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddProductRequest {

    @Size(max = 255)
    private String productName;

    @Min(1)
    private Long price;

    @Min(0)
    private Long quantity;
}
