package com.alexandersaul.products.service.impl;

import com.alexandersaul.products.dto.product.ProductEditPriceDTO;
import com.alexandersaul.products.dto.product.ProductNameResponseDTO;
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
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> products = (List<Product>) productRepository.findAll();
        return productMapper.toDTOs(products);
    }

    @Override
    public ProductResponseDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product" , "productId" , productId.toString())
        );
        return productMapper.toDTO(product);
    }

    @Override
    public List<ProductResponseDTO> getProductsByBrandId(Long brandId) {
        List <Product> products = productRepository.findProductsByBrandId(brandId);
        System.out.println(products);
        return productMapper.toDTOs(products);
    }

    @Override
    public List<ProductResponseDTO> getProductsByCategoryId(Long categoryId) {
        List<Product> products = productRepository.findProductsByCategoryId(categoryId);
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

    @Override
    public ProductNameResponseDTO getProductNameByProductId(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product" , "productId" , productId.toString())
        );
        return productMapper.toDTOName(product);
    }

    @Override
    public void editPrice(Long productId , ProductEditPriceDTO productEditPriceDTO) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product", "productId" , productId.toString())
        );

        product.setUpdatedAt(LocalDateTime.now());
        product.setUpdatedBy("Alexander");
        product.setPrice(productEditPriceDTO.getPrice());
        productRepository.save(product);

    }

}
