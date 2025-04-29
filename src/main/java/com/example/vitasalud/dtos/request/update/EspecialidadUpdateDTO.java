package com.example.vitasalud.dtos.request.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EspecialidadUpdateDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 30, message = "El nombre de la especialidad no puede superar los 30 caracteres")
    private String nombre;
}
