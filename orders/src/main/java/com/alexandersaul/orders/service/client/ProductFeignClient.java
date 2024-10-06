package com.alexandersaul.orders.service.client;

import com.alexandersaul.orders.dto.ProductNameResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products", contextId = "productsFeignClient")
public interface ProductFeignClient {

    @GetMapping(value = "/api/products/search-name/{productId}" )
    ResponseEntity<ProductNameResponseDTO> getNameProductByProductId (@PathVariable Long productId);


}
