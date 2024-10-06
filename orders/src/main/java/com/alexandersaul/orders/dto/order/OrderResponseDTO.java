package com.alexandersaul.orders.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {

    private Long orderId;
    private String userId;
    private LocalDateTime createdAt;
    private String status;
    private BigDecimal totalAmount;

}
