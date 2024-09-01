package com.alexandersaul.products.mapper;

import com.alexandersaul.products.dto.brand.BrandRequestDTO;
import com.alexandersaul.products.entity.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    Brand toEntity (BrandRequestDTO brandRequestDTO);

}
