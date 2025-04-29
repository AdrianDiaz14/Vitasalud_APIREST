package com.example.vitasalud.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Profesional_Especialidad")
public class ProfesionalEspecialidad {

    @EmbeddedId
    ProfesionalEspecialidadKey id;

    @Column(name = "anyos_experiencia")
    private int anyosExperiencia;

    @ManyToOne
    @MapsId("idProfesional")
    @JoinColumn(name = "id_profesional")
    @JsonManagedReference
    private Profesional profesional;

    @ManyToOne
    @MapsId("idEspecialidad")
    @JoinColumn(name = "id_especialidad")
    @JsonBackReference
    private Especialidad especialidad;
}
