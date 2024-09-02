package com.example.inventory.service;

import com.example.inventory.constants.MovementType;
import com.example.inventory.dto.inventory.InventoryRequestDTO;
import com.example.inventory.dto.inventory.InventoryResponseDTO;
import com.example.inventory.entity.Inventory;


public interface IInventoryService {

    void createInventory(InventoryRequestDTO inventoryRequestDTO);
    InventoryResponseDTO findByProductId (Long productId);
    Inventory updateQuantity (Long productId , int quantity , MovementType movementType);
    boolean deleteInventoryByProductId (Long productId);

}
