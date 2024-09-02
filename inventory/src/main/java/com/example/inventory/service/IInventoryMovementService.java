package com.example.inventory.service;

import com.example.inventory.dto.inventoryMovement.InventoryMovementHistoryDTO;
import com.example.inventory.dto.inventoryMovement.InventoryMovementRequestDTO;

import java.util.List;

public interface IInventoryMovementService {

    void createMovementInventory (InventoryMovementRequestDTO inventoryMovementRequestDTO);
    List<InventoryMovementHistoryDTO> getHistoryByProductId (Long productId);

}
