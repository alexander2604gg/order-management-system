package com.alexandersaul.products.repository;

import com.alexandersaul.products.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

    @Query("SELECT p FROM Product p WHERE p.isActive = true AND p.category.categoryId = :categoryId")
    List<Product> findProductsByCategoryId (@Param("categoryId") Long categoryId);

}
