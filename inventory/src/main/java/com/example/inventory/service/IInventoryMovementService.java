package com.example.inventory.service;

import com.example.inventory.dto.inventoryMovement.InventoryMovementHistoryDTO;
import com.example.inventory.dto.inventoryMovement.InventoryMovementRequestDTO;

import java.util.List;

public interface IInventoryMovementService {

    void createMovementInventory (InventoryMovementRequestDTO inventoryMovementRequestDTO);
    void createMovementsInventory (List<InventoryMovementRequestDTO> inventoryMovementRequestDTOS);
    List<InventoryMovementHistoryDTO> getHistoryByProductId (Long productId);

}
