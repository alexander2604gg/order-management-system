package com.example.inventory.repository;

import com.example.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory,Long> {
    Optional<Inventory> findByProductId(Long productId);
    @Query("SELECT i FROM Inventory i WHERE i.productId IN :ids")
    List<Inventory> findByProductsIds(@Param("ids") List<Long> ids);
}
