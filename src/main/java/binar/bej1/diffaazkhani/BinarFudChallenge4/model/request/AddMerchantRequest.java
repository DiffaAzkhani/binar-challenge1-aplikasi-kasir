package binar.bej1.diffaazkhani.BinarFudChallenge4.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddMerchantRequest {
    private Long merchantId;

    private String merchantName;

    private String merchantLocation;

    private boolean open;
}
