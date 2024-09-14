package com.alexandersaul.orders.service.client;

import com.alexandersaul.orders.dto.inventory.InventoryResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("inventory")
public interface InventoryFeignClient {

    @GetMapping(value = "/api/inventory/findInventory/{productId}" , consumes = "application/json")
    ResponseEntity<InventoryResponseDTO> getInventoryByProductId (@PathVariable Long productId);

    @PostMapping("api/inventory/by-product-ids")
    ResponseEntity<List<InventoryResponseDTO>> findInventoryByProductIds(@RequestBody List<Long> productIds);

}
