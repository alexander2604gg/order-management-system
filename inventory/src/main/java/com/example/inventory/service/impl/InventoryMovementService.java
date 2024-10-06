package com.example.inventory.service.impl;

import com.example.inventory.constants.MovementType;
import com.example.inventory.dto.inventory.InventoryRequestDTO;
import com.example.inventory.dto.inventory.InventoryResponseDTO;
import com.example.inventory.dto.inventoryMovement.InventoryMovementHistoryDTO;
import com.example.inventory.dto.inventoryMovement.InventoryMovementRequestDTO;
import com.example.inventory.dto.product.ProductResponseDTO;
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
import com.example.inventory.service.client.ProductFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public void createMovementInventory(InventoryMovementRequestDTO inventoryMovementRequestDTO) {

        MovementType movementType;
        try {
            movementType = MovementType.valueOf(inventoryMovementRequestDTO.getMovementType());
        } catch (IllegalArgumentException e) {
            throw new InvalidMovementTypeException("Invalid movement type: " + inventoryMovementRequestDTO.getMovementType());
        }

        if (movementType == MovementType.INCOMING) {
            inventoryRepository.findByProductId(inventoryMovementRequestDTO.getProductId())
                    .ifPresentOrElse(
                            inventory -> {

                            },
                            () -> {
                                ResponseEntity<ProductResponseDTO> productResponse =
                                        productFeignClient.getProductById(inventoryMovementRequestDTO.getProductId());

                                System.out.println(productResponse.getBody());
                                if (!productResponse.getStatusCode().is2xxSuccessful() || productResponse.getBody() == null) {
                                    throw new ResourceNotFoundException("Order", "orderId", inventoryMovementRequestDTO.getProductId().toString());
                                }

                                InventoryRequestDTO inventoryRequestDTO = new InventoryRequestDTO(inventoryMovementRequestDTO.getProductId());
                                inventoryService.createInventory(inventoryRequestDTO);
                            }
                    );
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
    public void createMovementsInventory(List<InventoryMovementRequestDTO> inventoryMovementRequestDTOS) {

        for (InventoryMovementRequestDTO movement : inventoryMovementRequestDTOS) {
            createMovementInventory(movement);
        }

    }

    @Override
    public List<InventoryMovementHistoryDTO> getHistoryByProductId (Long productId) {
        List<InventoryMovement> inventoryMovements = inventoryMovementRepository.findInventoryMovementsByProductId(productId);

        if (inventoryMovements != null && !inventoryMovements.isEmpty()){
            return inventoryMovementMapper.toDTOs(inventoryMovements);
        } else {
            throw new ResourceNotFoundException("InventoryMovement" , "productId" , productId.toString());
        }

    }


}
