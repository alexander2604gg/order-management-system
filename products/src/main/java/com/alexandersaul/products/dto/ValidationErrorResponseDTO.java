package com.alexandersaul.products.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ValidationErrorResponseDTO extends ErrorResponseDTO {

    private Map<String, String> validationErrors;

    public ValidationErrorResponseDTO(String apiPath, HttpStatus errorCode, String errorMessage, LocalDateTime errorTime, Map<String, String> validationErrors) {
        super(apiPath, errorCode, errorMessage, errorTime);
        this.validationErrors = validationErrors;
    }
}
