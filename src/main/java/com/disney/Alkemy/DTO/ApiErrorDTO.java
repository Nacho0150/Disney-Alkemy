package com.disney.alkemy.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
/**
 * PARA MANEJAR LOS ERRORES DE MI APLICACION
 */
public class ApiErrorDTO {
    private HttpStatus status;
    private String message;
    private List<String> errors;
}
