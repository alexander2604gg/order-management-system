package com.alexandersaul.orders.dto.orderdetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponseDTO {

    private Long orderDetailId;
    private Long productId;
    private String productName;
    private int quantity;
    private BigDecimal pricePerUnit;

}
