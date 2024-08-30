package com.alexandersaul.products.mapper;

import com.alexandersaul.products.dto.product.ProductRequestDTO;
import com.alexandersaul.products.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "name" , source = "name")
    @Mapping(target = "description" , source = "description")
    @Mapping(target = "price" , source = "price")
    @Mapping(target = "category.categoryId" , source = "categoryId")
    @Mapping(target = "brand.brandId" , source = "brandId")
    Product toEntity (ProductRequestDTO productRequestDTO);

}
