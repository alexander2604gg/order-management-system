package com.example.inventory.dto.inventoryMovement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class InventoryMovementHistoryDTO {

    private Long productId;
    private int quantity;
    private String movementType;
    private LocalDateTime movementDate;

}
