package com.alexandersaul.products.repository;

import com.alexandersaul.products.entity.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends CrudRepository<Brand,Long> {
}
