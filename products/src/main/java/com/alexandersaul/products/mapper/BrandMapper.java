package com.alexandersaul.products.mapper;

import com.alexandersaul.products.dto.brand.BrandRequestDTO;
import com.alexandersaul.products.dto.brand.BrandResponseDTO;
import com.alexandersaul.products.entity.Brand;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    Brand toEntity (BrandRequestDTO brandRequestDTO);

    BrandResponseDTO toDto (Brand brand);
    List<BrandResponseDTO> toDTOs (List<Brand> brands);

}
