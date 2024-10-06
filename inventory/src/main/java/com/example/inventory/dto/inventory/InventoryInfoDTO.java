package com.example.inventory.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryInfoDTO {
    private Long productId;
    private String name;
    private int stock;
}
