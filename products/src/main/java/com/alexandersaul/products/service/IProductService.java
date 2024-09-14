package com.alexandersaul.products.service;

import com.alexandersaul.products.dto.product.ProductRequestDTO;
import com.alexandersaul.products.dto.product.ProductResponseDTO;

import java.util.List;

public interface IProductService {

    List<ProductResponseDTO> getAllProducts ();
    ProductResponseDTO getProductById (Long productId);
    List<ProductResponseDTO> getProductsByBrandId (Long brandId);
    List<ProductResponseDTO> getProductsByCategoryId (Long categoryId);
    void createProduct (ProductRequestDTO productRequestDTO);
    void updateProduct (Long id , ProductRequestDTO productRequestDTO);
    boolean deleteProduct (Long id);

}
