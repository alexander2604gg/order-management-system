package com.alexandersaul.products.service;

import com.alexandersaul.products.dto.brand.BrandRequestDTO;

public interface IBrandService {

    void createBrand (BrandRequestDTO brandRequestDTO);
    void updateBrand (Long id , BrandRequestDTO brandRequestDTO);
    boolean deleteBrand (Long id);


}
