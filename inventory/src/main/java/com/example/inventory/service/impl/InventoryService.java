package com.example.inventory.service.impl;

import com.example.inventory.constants.MovementType;
import com.example.inventory.dto.inventory.InventoryRequestDTO;
import com.example.inventory.dto.inventory.InventoryResponseDTO;
import com.example.inventory.entity.Inventory;
import com.example.inventory.exceptions.ConflictException;
import com.example.inventory.exceptions.InsufficientInventoryException;
import com.example.inventory.exceptions.ResourceNotFoundException;
import com.example.inventory.mapper.InventoryMapper;
import com.example.inventory.repository.InventoryRepository;
import com.example.inventory.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class InventoryService implements IInventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public void createInventory(InventoryRequestDTO inventoryRequestDTO) {

        Optional<Inventory> existingInventory = inventoryRepository.findByProductId(inventoryRequestDTO.getProductId());
        if (existingInventory.isPresent()) {
            throw new ConflictException("An inventory record already exists for the product with ID: " + inventoryRequestDTO.getProductId());
        }

        Inventory inventory = inventoryMapper.toEntity(inventoryRequestDTO);
        inventory.setQuantity(0);
        inventory.setLastUpdated(LocalDateTime.now());

        inventoryRepository.save(inventory);
    }

    @Override
    public InventoryResponseDTO findByProductId(Long productId){

        Inventory inventory = inventoryRepository.findByProductId(productId).orElseThrow(
                () -> new ResourceNotFoundException("Inventory" , "productId" , productId.toString())
        );

        InventoryResponseDTO inventoryResponseDTO = inventoryMapper.toResponseDTO(inventory);

        return inventoryResponseDTO;

    }


    @Override
    public Inventory updateQuantity(Long productId, int quantity , MovementType movementType) {

        Inventory inventory = inventoryRepository.findByProductId(productId).orElseThrow(
                () -> new ResourceNotFoundException("Inventory", "productId", productId.toString())
        );

        int currentQuantity = inventory.getQuantity();

        if (movementType == MovementType.OUTGOING && quantity > currentQuantity) {
            throw new InsufficientInventoryException("Insufficient quantity. Available: "
                    + currentQuantity + ", Requested: " + quantity);
        }

        if (movementType == MovementType.INCOMING) {
            inventory.setQuantity(currentQuantity + quantity);
        } else if (movementType == MovementType.OUTGOING) {
            inventory.setQuantity(currentQuantity - quantity);
        }

        inventoryRepository.save(inventory);
        return inventory;

    }

    @Override
    public boolean deleteInventoryByProductId(Long productId) {
        Inventory inventory = inventoryRepository.findByProductId(productId).orElseThrow(
                () -> new ResourceNotFoundException("Inventory" , "productId" , productId.toString())
        );
        inventoryRepository.delete(inventory);
        return true;
    }

}
