package com.alexandersaul.products.service;

import com.alexandersaul.products.dto.category.CategoryRequestDTO;

public interface ICategoryService {

    void createCategory (CategoryRequestDTO categoryRequestDTO);
    void updateCategory (Long id , CategoryRequestDTO categoryRequestDTO);
    boolean deleteCategory (Long id);


}
