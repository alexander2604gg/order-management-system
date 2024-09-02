package com.example.inventory.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor @NoArgsConstructor
@Getter
@Setter
public class ErrorResponseDTO {
    private  String apiPath;
    private HttpStatus errorCode;
    private  String errorMessage;
    private LocalDateTime errorTime;
}
