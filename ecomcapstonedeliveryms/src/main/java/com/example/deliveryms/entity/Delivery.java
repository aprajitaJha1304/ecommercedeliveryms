package com.example.deliveryms.entity;// Delivery.java
import com.example.deliveryms.dtos.DeliveryStatus;
import com.example.deliveryms.dtos.OrderInfo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode(of="id")
@Data
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "order_info_id")
    private OrderInfo orderInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_status")
    private DeliveryStatus deliveryStatus;

    @Column(name = "delivery_agent_location")
    private String deliveryAgentLocation;

    // Getters and setters

    // Additional constructors, if needed
}
