package binar.bej1.diffaazkhani.BinarFudChallenge4.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "table_order")
@Data
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_time")
    private Date orderTime;

    @Column(name = "destination_address")
    private String destinationAddress;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "completed")
    private boolean completed;

    // Relasional ke UsersModel
    @ManyToOne
    @JoinColumn(name = "users_id")
    private UsersModel users;

    // Relasioanl ke OrderDetailModel
    @OneToMany(mappedBy = "order")
    private List<OrderDetailModel> orderDetail;
}

