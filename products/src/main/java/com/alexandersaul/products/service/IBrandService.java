package com.alexandersaul.products.service;

import com.alexandersaul.products.dto.brand.BrandRequestDTO;
import com.alexandersaul.products.dto.brand.BrandResponseDTO;

import java.util.List;

public interface IBrandService {

    List<BrandResponseDTO> getAllBrands ();
    void createBrand (BrandRequestDTO brandRequestDTO);
    void updateBrand (Long id , BrandRequestDTO brandRequestDTO);
    boolean deleteBrand (Long id);


}
