package binar.bej1.diffaazkhani.BinarFudChallenge4.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "table_users")
@Data
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

    // Relasional ke OrderModel
    @OneToMany(mappedBy = "users")
    private List<OrderModel> order;
}
