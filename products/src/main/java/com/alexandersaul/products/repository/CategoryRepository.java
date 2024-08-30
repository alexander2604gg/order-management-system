package com.alexandersaul.products.repository;

import com.alexandersaul.products.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category , Long> {
}
