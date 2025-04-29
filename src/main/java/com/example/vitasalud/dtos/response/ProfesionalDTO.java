package com.example.vitasalud.dtos.response;

import com.example.vitasalud.model.Especialidad;
import lombok.Data;

import java.sql.Time;

@Data
public class ProfesionalDTO {
    private long id;

    private String nombre;
    private String direccion;
    private String localidad;
    private String cp;
    private String telefono;
    private String email;
    private Time horaInicio;
    private Time horaFin;
    private double precio;
    private String imagen;
    private double latitud;
    private double longitud;
    private String especialidad;
}
