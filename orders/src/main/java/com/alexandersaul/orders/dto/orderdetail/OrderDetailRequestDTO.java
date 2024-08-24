package com.alexandersaul.orders.dto.orderdetail;

import com.alexandersaul.orders.entity.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class OrderDetailRequestDTO {

    private Long orderId;
    private Long productId;
    private int quantity;
    private BigDecimal pricePerUnit;
}
