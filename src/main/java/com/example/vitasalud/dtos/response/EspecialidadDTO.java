package com.example.vitasalud.dtos.response;

import lombok.Data;

import java.util.List;

@Data
public class EspecialidadDTO {
    private long id;
    private String nombre;
    private List<ProfesionalDTO> profesionales;
}
