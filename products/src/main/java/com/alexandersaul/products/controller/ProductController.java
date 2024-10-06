package com.alexandersaul.products.controller;

import com.alexandersaul.products.constants.ProductConstants;
import com.alexandersaul.products.dto.ResponseDTO;
import com.alexandersaul.products.dto.category.CategoryResponseDTO;
import com.alexandersaul.products.dto.product.ProductEditPriceDTO;
import com.alexandersaul.products.dto.product.ProductNameResponseDTO;
import com.alexandersaul.products.dto.product.ProductRequestDTO;
import com.alexandersaul.products.dto.product.ProductResponseDTO;
import com.alexandersaul.products.service.IProductService;
import com.alexandersaul.products.service.impl.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts () {
        List<ProductResponseDTO> productResponseDTOS = productService.getAllProducts();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productResponseDTOS);
    }

    @GetMapping("search/{productId}")
    public ResponseEntity<ProductResponseDTO> getProductById (@PathVariable @NotNull @Positive Long productId) {
        ProductResponseDTO productResponseDTO = productService.getProductById(productId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productResponseDTO);
    }

    @GetMapping("/search/by-brand")
    public ResponseEntity<List<ProductResponseDTO>> getProductByBrandId (@RequestParam @NotNull @Positive Long brandId){
        List<ProductResponseDTO> productResponseDTOS = productService.getProductsByBrandId(brandId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productResponseDTOS);
    }

    @GetMapping("/search/by-category")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByCategoryId (@RequestParam @NotNull @Positive Long categoryId) {
        List<ProductResponseDTO> productResponseDTOS = productService.getProductsByCategoryId(categoryId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productResponseDTOS);
    }

    @GetMapping("/search-name/{productId}")
    public ResponseEntity<ProductNameResponseDTO> getNameProductByProductId (@PathVariable @NotNull @Positive Long productId) {
        ProductNameResponseDTO productNameResponseDTO = productService.getProductNameByProductId(productId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productNameResponseDTO);
    }


    @PostMapping
    public ResponseEntity<ResponseDTO> createProduct (@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        productService.createProduct(productRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(ProductConstants.STATUS_201, ProductConstants.MESSAGE_201));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateProduct (@PathVariable @NotNull @Positive Long id , @RequestBody ProductRequestDTO productRequestDTO) {
        productService.updateProduct(id , productRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(ProductConstants.STATUS_200 , ProductConstants.MESSAGE_200));
    }

    @PatchMapping("editPrice/{productId}")
    public ResponseEntity<ResponseDTO> updatePrice (@PathVariable @NotNull @Positive Long productId , @RequestBody ProductEditPriceDTO productEditPriceDTO) {
        productService.editPrice(productId , productEditPriceDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(ProductConstants.STATUS_200 , ProductConstants.MESSAGE_200));
    }

}
