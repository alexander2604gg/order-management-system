package com.example.inventory.service.impl;

import com.example.inventory.constants.MovementType;
import com.example.inventory.dto.inventoryMovement.InventoryMovementHistoryDTO;
import com.example.inventory.dto.inventoryMovement.InventoryMovementRequestDTO;
import com.example.inventory.entity.Inventory;
import com.example.inventory.entity.InventoryMovement;
import com.example.inventory.exceptions.InvalidMovementTypeException;
import com.example.inventory.exceptions.InventoryUpdateException;
import com.example.inventory.exceptions.ResourceNotFoundException;
import com.example.inventory.mapper.InventoryMovementMapper;
import com.example.inventory.repository.InventoryMovementRepository;
import com.example.inventory.repository.InventoryRepository;
import com.example.inventory.service.IInventoryMovementService;
import com.example.inventory.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventoryMovementService implements IInventoryMovementService {

    @Autowired
    private InventoryMovementRepository inventoryMovementRepository;
    @Autowired
    private InventoryMovementMapper inventoryMovementMapper;
    @Autowired
    private IInventoryService inventoryService;
    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public void createMovementInventory(InventoryMovementRequestDTO inventoryMovementRequestDTO) {

        MovementType movementType;
        try {
            movementType = MovementType.valueOf(inventoryMovementRequestDTO.getMovementType());
        } catch (IllegalArgumentException e) {
            throw new InvalidMovementTypeException("Invalid movement type: " + inventoryMovementRequestDTO.getMovementType());
        }

        Inventory inventory = inventoryService.updateQuantity(
                inventoryMovementRequestDTO.getProductId(),
                inventoryMovementRequestDTO.getQuantity(),
                movementType
        );

        if (inventory!=null) {
            InventoryMovement inventoryMovement = inventoryMovementMapper.toEntity(inventoryMovementRequestDTO);
            inventoryMovement.setTimestamp(LocalDateTime.now());
            inventoryMovement.setInventory(inventory);
            inventoryMovementRepository.save(inventoryMovement);
        } else {
            throw new InventoryUpdateException("Failed to update inventory for product ID: " + inventoryMovementRequestDTO.getProductId());
        }
    }

    @Override
    public List<InventoryMovementHistoryDTO> getHistoryByProductId (Long productId) {
        List<InventoryMovement> inventoryMovements = inventoryMovementRepository.findInventoryMovementsByProductId(productId);

        if (inventoryMovements != null && !inventoryMovements.isEmpty()){
            List<InventoryMovementHistoryDTO> historyDTOS = inventoryMovementMapper.toDTOs(inventoryMovements);
            return historyDTOS;
        } else {
            throw new ResourceNotFoundException("InventoryMovement" , "productId" , productId.toString());
        }

    }


}
