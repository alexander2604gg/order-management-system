package com.example.inventory.repository;

import com.example.inventory.entity.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory,Long> {
    Optional<Inventory> findByProductId(Long productId);
}
