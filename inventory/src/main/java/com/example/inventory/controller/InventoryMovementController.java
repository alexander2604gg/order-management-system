package com.example.inventory.controller;

import com.example.inventory.constants.InventoryConstants;
import com.example.inventory.dto.ResponseDTO;
import com.example.inventory.dto.inventory.InventoryRequestDTO;
import com.example.inventory.dto.inventoryMovement.InventoryMovementHistoryDTO;
import com.example.inventory.dto.inventoryMovement.InventoryMovementRequestDTO;
import com.example.inventory.service.IInventoryMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/inventoryMovement")
public class InventoryMovementController {

    @Autowired
    private IInventoryMovementService inventoryMovementService;

    @PostMapping
    public ResponseEntity<ResponseDTO> createInventoryMovements (@RequestBody List<InventoryMovementRequestDTO> inventoryMovementRequestDTO) {
        inventoryMovementService.createMovementsInventory(inventoryMovementRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(InventoryConstants.STATUS_201 , InventoryConstants.MESSAGE_201));
    }

    @GetMapping("/historyByProductId")
    public ResponseEntity<List<InventoryMovementHistoryDTO>> getHistoryByProductId ( @RequestParam(name = "productId") Long productId) {
        List<InventoryMovementHistoryDTO> historyDTOS = inventoryMovementService.getHistoryByProductId(productId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(historyDTOS);
    }

}
