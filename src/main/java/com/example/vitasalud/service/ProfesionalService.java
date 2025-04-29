package com.example.vitasalud.service;

import com.example.vitasalud.dtos.request.create.EspecialidadCreateDTO;
import com.example.vitasalud.dtos.request.create.ProfesionalCreateDTO;
import com.example.vitasalud.dtos.request.update.EspecialidadUpdateDTO;
import com.example.vitasalud.dtos.request.update.ProfesionalUpdateDTO;
import com.example.vitasalud.dtos.response.EspecialidadDTO;
import com.example.vitasalud.dtos.response.ProfesionalDTO;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface ProfesionalService {
    List<ProfesionalDTO> obtenerTodosLosProfesionales();
    ProfesionalDTO obtenerProfesionalPorId(Long id);
    List<ProfesionalDTO> obtenerProfesionalesPorNombre(String nombre);
    List<ProfesionalDTO> obtenerProfesionalesPorLocalidad(String localidad);
    List<ProfesionalDTO> obtenerProfesionalesPorEspecialidad(Long especialidadId);
    ProfesionalDTO crearProfesional(ProfesionalCreateDTO dto);
    ProfesionalDTO actualizarProfesional(Long id, ProfesionalUpdateDTO dto);
    ProfesionalDTO actualizarProfesionalParcial(Long id, JsonNode patch);
    void eliminarProfesional(Long id);


}
