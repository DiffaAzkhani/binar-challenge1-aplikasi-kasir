package binar.bej1.diffaazkhani.BinarFudChallenge4.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateMerchantRequest {

    private String merchantName;

    private String location;

    private boolean open;
}
