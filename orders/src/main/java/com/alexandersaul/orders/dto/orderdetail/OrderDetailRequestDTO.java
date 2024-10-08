package com.alexandersaul.orders.dto.orderdetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class OrderDetailRequestDTO {

    private Long productId;
    private int quantity;
    private BigDecimal pricePerUnit;

}
