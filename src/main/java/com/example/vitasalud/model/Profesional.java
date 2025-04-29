package com.example.vitasalud.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Profesional")
public class Profesional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesional")
    private long idProfesional;

    private String nombre;
    private String direccion;
    private String localidad;
    private String cp;
    private String telefono;
    private String email;
    @Column(name = "hora_inicio")
    private Time horaInicio;
    @Column(name = "hora_fin")
    private Time horaFin;
    @Column(name = "precio_consulta")
    private double precioConsulta;
    private String imagen;
    private double latitud;
    private double longitud;

    @ManyToOne
    @JoinColumn(name = "id_especialidad")
    @JsonManagedReference
    private Especialidad especialidad;
}
