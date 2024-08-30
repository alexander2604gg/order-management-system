package com.alexandersaul.products.service.impl;

import com.alexandersaul.products.dto.category.CategoryRequestDTO;
import com.alexandersaul.products.entity.Category;
import com.alexandersaul.products.exception.ResourceNotFoundException;
import com.alexandersaul.products.mapper.CategoryMapper;
import com.alexandersaul.products.repository.CategoryRepository;
import com.alexandersaul.products.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void createCategory(CategoryRequestDTO categoryRequestDTO) {

        Category category = categoryMapper.toEntity(categoryRequestDTO);
        category.setCreatedBy("Alexander");
        category.setCreatedAt(LocalDateTime.now());
        categoryRepository.save(category);

    }

    @Override
    public void updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) {

        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category" , "CategoryId" , String.valueOf(id))
        );
        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());

        categoryRepository.save(category);

    }

    @Override
    public boolean deleteCategory(Long id) {

        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category" , "CategoryId" , String.valueOf(id))
        );

        categoryRepository.deleteById(id);
        return true;

    }
}
