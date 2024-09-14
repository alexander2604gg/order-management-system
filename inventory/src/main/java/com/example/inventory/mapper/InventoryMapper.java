package com.example.inventory.mapper;

import com.example.inventory.dto.inventory.InventoryRequestDTO;
import com.example.inventory.dto.inventory.InventoryResponseDTO;
import com.example.inventory.entity.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    Inventory toEntity (InventoryRequestDTO inventoryRequestDTO);
    InventoryResponseDTO toResponseDTO (Inventory inventory);
    List<InventoryResponseDTO> toListResponseDTO (List<Inventory> inventories);

}
