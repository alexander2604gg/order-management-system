package com.alexandersaul.products.service;

import com.alexandersaul.products.dto.category.CategoryRequestDTO;
import com.alexandersaul.products.dto.category.CategoryResponseDTO;

import java.util.List;

public interface ICategoryService {

    List<CategoryResponseDTO> getCategories ();
    void createCategory (CategoryRequestDTO categoryRequestDTO);
    void updateCategory (Long id , CategoryRequestDTO categoryRequestDTO);
    boolean deleteCategory (Long id);


}
