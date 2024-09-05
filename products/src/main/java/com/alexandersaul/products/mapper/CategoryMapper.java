package com.alexandersaul.products.mapper;

import com.alexandersaul.products.dto.category.CategoryRequestDTO;
import com.alexandersaul.products.dto.category.CategoryResponseDTO;
import com.alexandersaul.products.entity.Category;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity (CategoryRequestDTO categoryRequestDTO);
    CategoryResponseDTO toDTO (Category category);
    List<CategoryResponseDTO> toDTOs (List<Category> categories);

}
