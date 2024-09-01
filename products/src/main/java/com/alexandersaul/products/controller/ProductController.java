package com.alexandersaul.products.controller;

import com.alexandersaul.products.constants.ProductConstants;
import com.alexandersaul.products.dto.ResponseDTO;
import com.alexandersaul.products.dto.product.ProductRequestDTO;
import com.alexandersaul.products.service.IProductService;
import com.alexandersaul.products.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createProduct (@RequestBody ProductRequestDTO productRequestDTO) {
        productService.createProduct(productRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(ProductConstants.STATUS_201, ProductConstants.MESSAGE_201));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateProduct (@PathVariable Long id , @RequestBody ProductRequestDTO productRequestDTO) {
        productService.updateProduct(id , productRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(ProductConstants.STATUS_200 , ProductConstants.MESSAGE_200));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteProduct (@PathVariable Long id){
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(ProductConstants.STATUS_200,ProductConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(ProductConstants.STATUS_500,ProductConstants.MESSAGE_500_DELETE));
        }

    }
}
