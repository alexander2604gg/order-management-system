package com.example.inventory.repository;

import com.example.inventory.entity.InventoryMovement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryMovementRepository extends CrudRepository<InventoryMovement , Long> {

    @Query("SELECT m FROM InventoryMovement m INNER JOIN m.inventory i WHERE i.productId = :productId")
    List<InventoryMovement> findInventoryMovementsByProductId (@Param("productId") Long productId);

}
