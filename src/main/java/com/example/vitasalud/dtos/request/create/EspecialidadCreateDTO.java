package com.example.vitasalud.dtos.request.create;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EspecialidadCreateDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 30, message = "El nombre de la especialidad no puede superar los 30 caracteres")
    private String nombre;
}
