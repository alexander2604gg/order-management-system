package com.example.inventory.controller;

import com.example.inventory.constants.InventoryConstants;
import com.example.inventory.dto.ResponseDTO;
import com.example.inventory.dto.inventory.InventoryRequestDTO;
import com.example.inventory.dto.inventory.InventoryResponseDTO;
import com.example.inventory.service.IInventoryService;
import com.example.inventory.service.impl.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/inventory")
public class InventoryController {

    @Autowired
    private IInventoryService inventoryService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createInventory (@RequestBody InventoryRequestDTO inventoryRequestDTO){
        inventoryService.createInventory(inventoryRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(InventoryConstants.STATUS_201 , InventoryConstants.MESSAGE_201));
    }

    @GetMapping("/findInventory/{productId}")
    public ResponseEntity<InventoryResponseDTO> getInventoryByProductId (@PathVariable Long productId){
        InventoryResponseDTO inventoryResponseDTO = inventoryService.findByProductId(productId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(inventoryResponseDTO);
    }

    @DeleteMapping("delete/{productId}")
    public ResponseEntity<ResponseDTO> deleteInventoryByProductId (@PathVariable Long productId) {
        boolean isDeleted = inventoryService.deleteInventoryByProductId(productId);
        if (isDeleted){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(InventoryConstants.STATUS_200 , InventoryConstants.MESSAGE_200));
        } else {
            return ResponseEntity.
                    status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(InventoryConstants.STATUS_500 , InventoryConstants.MESSAGE_500_DELETE));

        }
    }

}
