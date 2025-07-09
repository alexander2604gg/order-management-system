package com.alexandersaul.orders.service.client;

import com.alexandersaul.orders.dto.InventoryMovementRequestDTO;
import com.alexandersaul.orders.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient(
    name = "inventory",
    url = "https://order-management-system-3-bix0.onrender.com",
    contextId = "inventoryFeignClient"
)
public interface InventoryMovementFeignClient {

    @PostMapping("/api/inventoryMovement")
    ResponseEntity<ResponseDTO> createInventoryMovements (@RequestBody List<InventoryMovementRequestDTO> inventoryMovementRequestDTOS);

}
