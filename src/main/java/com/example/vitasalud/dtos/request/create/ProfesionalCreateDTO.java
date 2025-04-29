package com.example.vitasalud.dtos.request.create;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Time;

@Data
public class ProfesionalCreateDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre del profesional no puede superar los 50 caracteres")
    private String nombre;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(max = 100, message = "La dirección del profesional no puede superar los 100 caracteres")
    private String direccion;

    @NotBlank(message = "La localidad no puede estar vacía")
    @Size(max = 50, message = "La localidad del profesional no puede superar los 50 caracteres")
    private String localidad;

    @NotBlank(message = "El código postal no puede estar vacío")
    @Size(max = 5, message = "El código postal del profesional no puede superar los 5 caracteres")
    private String cp;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Size(max = 15, message = "El teléfono del profesional no puede superar los 15 caracteres")
    private String telefono;

    @NotBlank(message = "El email no puede estar vacío")
    @Size(max = 50, message = "El email del profesional no puede superar los 50 caracteres")
    private String email;

    @NotNull(message = "La hora de inicio no puede estar vacía")
    private Time horaInicio;

    @NotNull(message = "La hora de fin no puede estar vacía")
    private Time horaFin;

    @NotNull(message = "El precio no puede estar vacío")
    @DecimalMin(value = "0.0", inclusive = true, message = "El precio debe ser mayor o igual a 0")
    private Double precio;

    @Size(max = 255, message = "El enlace de la imagen del profesional no puede superar los 255 caracteres")
    private String imagen;

    @NotNull(message = "La latitud no puede estar vacía")
    private Double latitud;

    @NotNull(message = "La longitud no puede estar vacía")
    private Double longitud;

    @NotNull(message = "La especialidad no puede estar vacía")
    private Long especialidadId;
}
