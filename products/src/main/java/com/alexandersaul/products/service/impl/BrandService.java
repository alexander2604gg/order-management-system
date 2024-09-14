package com.alexandersaul.products.service.impl;

import com.alexandersaul.products.dto.brand.BrandRequestDTO;
import com.alexandersaul.products.dto.brand.BrandResponseDTO;
import com.alexandersaul.products.entity.Brand;
import com.alexandersaul.products.exception.ResourceNotFoundException;
import com.alexandersaul.products.mapper.BrandMapper;
import com.alexandersaul.products.repository.BrandRepository;
import com.alexandersaul.products.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BrandService implements IBrandService {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private BrandMapper brandMapper;

    @Override
    public List<BrandResponseDTO> getAllBrands() {
        List<Brand> brands = (List<Brand>) brandRepository.findAll();
        return brandMapper.toDTOs(brands);
    }

    @Override
    public void createBrand(BrandRequestDTO brandRequestDTO) {
        Brand brand = brandMapper.toEntity(brandRequestDTO);
        brand.setCreatedAt(LocalDateTime.now());
        brand.setCreatedBy("Alexander");
        brandRepository.save(brand);
    }

    @Override
    public void updateBrand(Long id, BrandRequestDTO brandRequestDTO) {
        Brand brand = brandRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Brand" , "brandId" , id.toString())
        );
        brand.setName(brandRequestDTO.getName());
        brand.setAddress(brandRequestDTO.getAddress());
        brandRepository.save(brand);
    }

    @Override
    public boolean deleteBrand(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Brand" , "brandId" , id.toString())
        );
        brandRepository.deleteById(id);
        return true;
    }
}
