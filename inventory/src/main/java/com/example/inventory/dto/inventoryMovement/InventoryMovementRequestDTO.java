package com.example.inventory.dto.inventoryMovement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryMovementRequestDTO {

    private Long productId;
    private String movementType;
    private int quantity;
    private String reason;

}
