package com.alexandersaul.products.mapper;

import com.alexandersaul.products.dto.category.CategoryRequestDTO;
import com.alexandersaul.products.entity.Category;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity (CategoryRequestDTO categoryRequestDTO);

}
