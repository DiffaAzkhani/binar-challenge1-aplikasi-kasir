package binar.bej1.diffaazkhani.BinarFudChallenge4.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MerchantResponse {

    private String merchantName;

    private String location;

    private boolean open;

}
