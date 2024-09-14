package com.alexandersaul.products.dto.brand;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class BrandResponseDTO {

    private Long brandId;
    private String name;
    private String address;

}
