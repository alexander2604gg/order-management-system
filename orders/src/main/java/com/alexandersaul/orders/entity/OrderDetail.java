package com.alexandersaul.orders.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Table(name = "Order_Details")
public class OrderDetail extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailId;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @Column(name = "product_id")
    private Long productId;
    private int quantity;
    @Column(name = "price_per_unit")
    private BigDecimal pricePerUnit;
}
