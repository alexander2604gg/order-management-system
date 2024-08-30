package com.alexandersaul.products.service;

import com.alexandersaul.products.dto.product.ProductRequestDTO;

public interface IProductService {

    void createProduct (ProductRequestDTO productRequestDTO);
    void updateProduct (Long id , ProductRequestDTO productRequestDTO);
    boolean deleteProduct (Long id);

}
