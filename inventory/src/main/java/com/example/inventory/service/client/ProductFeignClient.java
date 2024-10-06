package com.example.inventory.service.client;
import com.example.inventory.dto.product.ProductNameResponseDTO;
import com.example.inventory.dto.product.ProductResponseDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products", contextId = "productsFeignClient")
public interface ProductFeignClient {

    @GetMapping(value = "/api/products/search-name/{productId}" )
    ResponseEntity<ProductNameResponseDTO> getNameProductByProductId (@PathVariable Long productId);
    @GetMapping("api/products/search/{productId}")
    ResponseEntity<ProductResponseDTO> getProductById (@PathVariable Long productId);

}
