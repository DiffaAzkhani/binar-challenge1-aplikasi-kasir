package binar.bej1.diffaazkhani.BinarFudChallenge4.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Date orderTime;
    private String destinationAddress;
    private boolean completed;
    private List<OrderDetailResponse> detailOrder;
}
