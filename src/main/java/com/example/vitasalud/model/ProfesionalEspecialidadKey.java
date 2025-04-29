package com.example.vitasalud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class ProfesionalEspecialidadKey implements Serializable {
    @Column(name = "id_profesional")
    private long idProfesional;

    @Column(name = "id_especialidad")
    private long idEspecialidad;
}
