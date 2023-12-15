package binar.bej1.diffaazkhani.BinarFudChallenge4.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddMerchantRequest {
    private Long merchantId;

    private String merchantName;

    private String merchantLocation;

    private boolean open;
}
