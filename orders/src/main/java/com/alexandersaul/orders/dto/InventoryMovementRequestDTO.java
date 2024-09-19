package com.alexandersaul.orders.dto;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class InventoryMovementRequestDTO {

    private Long productId;
    private String movementType;
    private int quantity;
    private String reason;

}