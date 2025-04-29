package com.example.vitasalud.dtos.response;

import lombok.Data;

@Data
public class ProfesionalEspecialidadDTO {
    private String nombreProfesional;
    private String nombreEspecialidad;
    private int anyosExperiencia;
}
