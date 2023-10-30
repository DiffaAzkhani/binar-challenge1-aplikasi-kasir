package binar.bej1.diffaazkhani.BinarFudChallenge4.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "table_order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_time")
    private Date orderTime;

    @Column(name = "destination_address")
    private String destinationAddress;

    @Column(name = "completed")
    private boolean completed;


    // Relasional ke UsersModel
    @ManyToOne
    @JoinColumn(name = "users_id")
    private UsersModel users;

    // Relasional ke OrderDetailModel
    @OneToMany(mappedBy = "order")
    private List<OrderDetailModel> orderDetails;
}
