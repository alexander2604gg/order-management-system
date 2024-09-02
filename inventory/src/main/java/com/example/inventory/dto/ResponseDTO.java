package com.example.inventory.dto;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ResponseDTO {
    private String statusCode;
    private String statusMsg;

}
