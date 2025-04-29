package com.example.vitasalud.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Especialidad")
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidad")
    private long idEspecialidad;

    private String nombre;

    @OneToMany(mappedBy = "especialidad")
    @JsonBackReference
    private List<Profesional> profesionales;
}
