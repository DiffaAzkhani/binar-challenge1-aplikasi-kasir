package binar.bej1.diffaazkhani.BinarFudChallenge4.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddProductRequest {
    
    @Size(max = 255)
    private String productName;

    @Min(1)
    private Long price;

    @Min(0)
    private Long quantity;
}
