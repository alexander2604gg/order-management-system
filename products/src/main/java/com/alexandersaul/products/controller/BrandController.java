package com.alexandersaul.products.controller;

import com.alexandersaul.products.constants.BrandConstants;
import com.alexandersaul.products.constants.CategoryConstants;
import com.alexandersaul.products.dto.ResponseDTO;
import com.alexandersaul.products.dto.brand.BrandRequestDTO;
import com.alexandersaul.products.dto.brand.BrandResponseDTO;
import com.alexandersaul.products.dto.category.CategoryRequestDTO;
import com.alexandersaul.products.service.IBrandService;
import com.alexandersaul.products.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    @GetMapping
    public ResponseEntity<List<BrandResponseDTO>> getAllBrands () {
       List<BrandResponseDTO> brandResponseDTOS = brandService.getAllBrands();
       return ResponseEntity
               .status(HttpStatus.OK)
               .body(brandResponseDTOS);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createBrand (@RequestBody BrandRequestDTO brandRequestDTO){
        brandService.createBrand(brandRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(BrandConstants.STATUS_201 , BrandConstants.MESSAGE_201));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateBrand (@PathVariable Long id,  @RequestBody BrandRequestDTO brandRequestDTO){
        brandService.updateBrand(id,brandRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(BrandConstants.STATUS_200 , BrandConstants.MESSAGE_200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteCategory (@PathVariable Long id){

        boolean isDeleted = brandService.deleteBrand(id);
        if (isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(BrandConstants.STATUS_200,BrandConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(BrandConstants.STATUS_500,BrandConstants.MESSAGE_500_DELETE));
        }

    }
}
