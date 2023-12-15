package binar.bej1.diffaazkhani.BinarFudChallenge4.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteUserRequest {

    String username;

    String password;
}
