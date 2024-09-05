package com.alexandersaul.products.service.impl;

import com.alexandersaul.products.dto.product.ProductRequestDTO;
import com.alexandersaul.products.dto.product.ProductResponseDTO;
import com.alexandersaul.products.entity.Brand;
import com.alexandersaul.products.entity.Category;
import com.alexandersaul.products.entity.Product;
import com.alexandersaul.products.exception.ResourceNotFoundException;
import com.alexandersaul.products.mapper.ProductMapper;
import com.alexandersaul.products.repository.BrandRepository;
import com.alexandersaul.products.repository.CategoryRepository;
import com.alexandersaul.products.repository.ProductRepository;
import com.alexandersaul.products.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<ProductResponseDTO> getProductByCategoryId(Long categoryId) {
        List<Product> products = (List<Product>) productRepository.findProductsByCategoryId(categoryId);
        return productMapper.toDTOs(products);
    }

    @Override
    public void createProduct(ProductRequestDTO productRequestDTO) {
        Product product = productMapper.toEntity(productRequestDTO);
        product.setIsActive(false);
        product.setCreatedAt(LocalDateTime.now());
        product.setCreatedBy("Alexander Saul");
        productRepository.save(product);

    }

    @Override
    public void updateProduct(Long id , ProductRequestDTO productRequestDTO) {

        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product" , "productId" , String.valueOf(id))
        );

        Brand brand = brandRepository.findById(productRequestDTO.getBrandId()).orElseThrow(
                () -> new ResourceNotFoundException("Brand", "brandID", String.valueOf(productRequestDTO.getBrandId()))
        );
        Category category = categoryRepository.findById(productRequestDTO.getCategoryId()).orElseThrow(
                () -> new ResourceNotFoundException("Category", "categoryId", String.valueOf(productRequestDTO.getCategoryId()))
        );

        product.setBrand(brand);
        product.setCategory(category);
        product.setName(productRequestDTO.getName());
        product.setDescription(productRequestDTO.getDescription());
        product.setPrice(productRequestDTO.getPrice());
        productRepository.save(product);

    }

    @Override
    public boolean deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product" ,"orderId", String.valueOf(id))
        );
        productRepository.deleteById(product.getProductId());
        return true;
    }

}
