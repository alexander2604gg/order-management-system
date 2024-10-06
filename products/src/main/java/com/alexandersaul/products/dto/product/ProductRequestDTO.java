package com.alexandersaul.products.dto.product;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotNull(message = "Price cannot be null")
    @PositiveOrZero(message = "Price must be a positive number or zero")
    private Double price;

    @NotNull(message = "Category ID is required")
    @Positive(message = "Category ID must be a positive number")
    private Long categoryId;

    @NotNull(message = "Brand ID is required")
    @Positive(message = "Brand ID must be a positive number")
    private Long brandId;

}
