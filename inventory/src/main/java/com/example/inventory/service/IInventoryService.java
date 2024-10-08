package com.example.inventory.service;

import com.example.inventory.constants.MovementType;
import com.example.inventory.dto.inventory.InventoryInfoDTO;
import com.example.inventory.dto.inventory.InventoryRequestDTO;
import com.example.inventory.dto.inventory.InventoryResponseDTO;
import com.example.inventory.entity.Inventory;

import java.util.List;


public interface IInventoryService {

    void createInventory(InventoryRequestDTO inventoryRequestDTO);
    InventoryResponseDTO findByProductId (Long productId);
    InventoryInfoDTO getInfoByProductId(Long productId);
    Inventory updateQuantity (Long productId , int quantity , MovementType movementType);
    void deleteInventoryByProductId (Long productId);
    List<InventoryResponseDTO> findInventoryByProductIds (List<Long> ids);


}
