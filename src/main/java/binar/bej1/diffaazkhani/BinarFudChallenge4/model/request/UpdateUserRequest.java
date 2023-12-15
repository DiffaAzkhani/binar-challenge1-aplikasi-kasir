package binar.bej1.diffaazkhani.BinarFudChallenge4.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequest {

    private String username;

    private String password;

    private String name;

    private String email;
    
}
