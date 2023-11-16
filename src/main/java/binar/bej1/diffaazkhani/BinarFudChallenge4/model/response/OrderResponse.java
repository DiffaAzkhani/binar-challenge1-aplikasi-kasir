package binar.bej1.diffaazkhani.BinarFudChallenge4.model.response;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Date date;
    private String destinationAddress;
    private boolean completed;
    private List<OrderDetailResponse> detailOrder;
}
