package com.example.vitasalud.dtos.request.patch;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EspecialidadPatchDTO {
    @Size(max = 30, message = "El nombre de la especialidad no puede superar los 30 caracteres")
    private String nombre;
}
