package com.alexandersaul.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ResponseDTO {

    private String statusCode;
    private String statusMsg;

}
