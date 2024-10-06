package com.example.inventory.controller;

import com.example.inventory.constants.InventoryConstants;
import com.example.inventory.dto.ResponseDTO;
import com.example.inventory.dto.inventory.InventoryInfoDTO;
import com.example.inventory.dto.inventory.InventoryRequestDTO;
import com.example.inventory.dto.inventory.InventoryResponseDTO;
import com.example.inventory.service.IInventoryService;
import com.example.inventory.service.impl.InventoryService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/inventory")
public class InventoryController {

    @Autowired
    private IInventoryService inventoryService;

    @PostMapping("/by-product-ids")
    public ResponseEntity<List<InventoryResponseDTO>> findInventoryByProductIds(@RequestBody List<Long> productIds) {
        List<InventoryResponseDTO> inventories = inventoryService.findInventoryByProductIds(productIds);

        if (inventories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(inventories);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createInventory (@RequestBody InventoryRequestDTO inventoryRequestDTO){
        inventoryService.createInventory(inventoryRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(InventoryConstants.STATUS_201 , InventoryConstants.MESSAGE_201));
    }

    @GetMapping("/findInventory/{productId}")
    public ResponseEntity<InventoryInfoDTO> getInventoryByProductId (@PathVariable @NotNull @Positive Long productId){
        InventoryInfoDTO inventoryInfoDTO = inventoryService.getInfoByProductId(productId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryInfoDTO);
    }


    @DeleteMapping("/{productId}")
        public ResponseEntity<ResponseDTO> deleteInventoryByProductId (@PathVariable Long productId) {
        inventoryService.deleteInventoryByProductId(productId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDTO(InventoryConstants.STATUS_200 , InventoryConstants.MESSAGE_200));
    }



}
