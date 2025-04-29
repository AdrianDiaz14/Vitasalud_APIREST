package com.example.vitasalud.dtos.request.patch;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Time;

@Data
public class ProfesionalPatchDTO {
    @Size(max = 50, message = "El nombre del profesional no puede superar los 50 caracteres")
    private String nombre;

    @Size(max = 100, message = "La dirección del profesional no puede superar los 100 caracteres")
    private String direccion;

    @Size(max = 50, message = "La localidad del profesional no puede superar los 50 caracteres")
    private String localidad;

    @Size(max = 5, message = "El código postal del profesional no puede superar los 5 caracteres")
    private String cp;

    @Size(max = 15, message = "El teléfono del profesional no puede superar los 15 caracteres")
    private String telefono;

    @Size(max = 50, message = "El email del profesional no puede superar los 50 caracteres")
    private String email;

    private Time horaInicio;

    private Time horaFin;

    private Double precio;

    @Size(max = 255, message = "El enlace de la imagen del profesional no puede superar los 5 caracteres")
    private String imagen;

    private Double latitud;

    private Double longitud;

    @Valid
    private EspecialidadPatchDTO especialidad;
}
