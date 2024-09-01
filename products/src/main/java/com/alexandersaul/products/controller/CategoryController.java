package com.alexandersaul.products.controller;

import com.alexandersaul.products.constants.CategoryConstants;
import com.alexandersaul.products.dto.ResponseDTO;
import com.alexandersaul.products.dto.category.CategoryRequestDTO;
import com.alexandersaul.products.service.ICategoryService;
import com.alexandersaul.products.service.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createCategory (@RequestBody CategoryRequestDTO categoryRequestDTO){
        categoryService.createCategory(categoryRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(CategoryConstants.STATUS_201 , CategoryConstants.MESSAGE_201));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateCategory (@PathVariable Long id,  @RequestBody CategoryRequestDTO categoryRequestDTO){
        categoryService.updateCategory(id,categoryRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDTO(CategoryConstants.STATUS_200 , CategoryConstants.MESSAGE_200));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteCategory (@PathVariable Long id){

        Boolean isDeleted = categoryService.deleteCategory(id);
        if (isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(CategoryConstants.STATUS_200,CategoryConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(CategoryConstants.STATUS_500,CategoryConstants.MESSAGE_500_DELETE));
        }

    }


}
