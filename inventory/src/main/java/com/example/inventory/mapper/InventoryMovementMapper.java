package com.example.inventory.mapper;

import com.example.inventory.dto.inventoryMovement.InventoryMovementHistoryDTO;
import com.example.inventory.dto.inventoryMovement.InventoryMovementRequestDTO;
import com.example.inventory.entity.Inventory;
import com.example.inventory.entity.InventoryMovement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryMovementMapper {

    @Mapping(target = "inventory.productId" , source = "productId")
    InventoryMovement toEntity (InventoryMovementRequestDTO inventoryMovementRequestDTO);
    @Mapping(target = "productId" , source = "inventory.productId")
    @Mapping(target = "movementDate" , source = "timestamp")
    InventoryMovementHistoryDTO toDTO (InventoryMovement inventoryMovement);
    List<InventoryMovementHistoryDTO> toDTOs (List<InventoryMovement> inventoryMovementList);

}
