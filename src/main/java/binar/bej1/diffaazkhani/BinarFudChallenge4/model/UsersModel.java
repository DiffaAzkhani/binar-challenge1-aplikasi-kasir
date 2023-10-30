package binar.bej1.diffaazkhani.BinarFudChallenge4.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "table_users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRoleModel role;
}
