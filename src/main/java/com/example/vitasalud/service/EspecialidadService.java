package com.example.vitasalud.service;

import com.example.vitasalud.dtos.request.create.EspecialidadCreateDTO;
import com.example.vitasalud.dtos.request.update.EspecialidadUpdateDTO;
import com.example.vitasalud.dtos.response.EspecialidadDTO;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface EspecialidadService {
    List<EspecialidadDTO> obtenerTodasLasEspecialidades();
    EspecialidadDTO obtenerEspecialidadPorId(Long id);
    List<EspecialidadDTO> obtenerEspecialidadesPorNombre(String nombre);

    EspecialidadDTO crearEspecialidad(EspecialidadCreateDTO dto);
    EspecialidadDTO actualizarEspecialidad(Long id, EspecialidadUpdateDTO dto);
    EspecialidadDTO actualizarEspecialidadParcial(Long id, JsonNode patch);
    void eliminarEspecialidad(Long id);
}
