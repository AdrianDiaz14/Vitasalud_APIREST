package com.example.vitasalud.service.impl;

import com.example.vitasalud.dtos.request.create.EspecialidadCreateDTO;
import com.example.vitasalud.dtos.request.patch.EspecialidadPatchDTO;
import com.example.vitasalud.dtos.request.update.EspecialidadUpdateDTO;
import com.example.vitasalud.dtos.response.EspecialidadDTO;
import com.example.vitasalud.mappers.EspecialidadMapper;
import com.example.vitasalud.model.Especialidad;
import com.example.vitasalud.model.Profesional;
import com.example.vitasalud.repository.EspecialidadRepository;
import com.example.vitasalud.repository.ProfesionalRepository;
import com.example.vitasalud.service.EspecialidadService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class EspecialidadServiceImpl implements EspecialidadService {

    private final EspecialidadRepository especialidadRepository;
    private final ProfesionalRepository profesionalRepository;
    private final EspecialidadMapper especialidadMapper;
    private final ObjectMapper objectMapper;
    private final Validator validator;

    @Override
    public List<EspecialidadDTO> obtenerTodasLasEspecialidades() {
        return especialidadRepository.findAll().stream()
                .map(especialidadMapper::toDto)
                .toList();
    }
    @Override
    public EspecialidadDTO obtenerEspecialidadPorId(Long Id) {
        return especialidadRepository.findById(Id)
                .map(especialidadMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<EspecialidadDTO> obtenerEspecialidadesPorNombre(String nombre) {
        return especialidadRepository.findByNombreContainingIgnoreCase(nombre).stream()   // Ignora mayusculas/minusculas y filtra por parte de la palabra
                .map(especialidadMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public EspecialidadDTO crearEspecialidad(EspecialidadCreateDTO dto) {
        Especialidad especialidad = especialidadMapper.toEntity(dto);

        Especialidad especialidadGuardado = especialidadRepository.save(especialidad);
        EspecialidadDTO especialidadDTO = especialidadMapper.toDto(especialidadGuardado);

        return especialidadDTO;
    }

    @Override
    @Transactional
    public EspecialidadDTO actualizarEspecialidad(Long id, EspecialidadUpdateDTO dto) {
        Especialidad especialidadExistente = especialidadRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Especialidad no encontrada con id: " + id));

        especialidadMapper.updateEntityFromDto(dto, especialidadExistente);

        return especialidadMapper.toDto(especialidadExistente);
    }

    @Override
    public EspecialidadDTO actualizarEspecialidadParcial(Long id, JsonNode patch) {
        // Buscar la especialidad existente por ID
        Especialidad especialidadExistente = especialidadRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Especialidad no encontrada con id: " + id));

        // Convertir el JsonNode a EspecialidadPatchDTO
        EspecialidadPatchDTO especialidadPatchDTO = objectMapper.convertValue(patch, EspecialidadPatchDTO.class);

        try {
            // Actualizar el DTO con los valores del JsonNode
            objectMapper.readerForUpdating(especialidadPatchDTO).readValue(patch);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al procesar el JSON: " + e.getMessage());
        }

        System.out.println("especialidadPatchDTO = " + especialidadPatchDTO);

        // Validar el DTO PATCH
        Set<ConstraintViolation<EspecialidadPatchDTO>> violations = validator.validate(especialidadPatchDTO);
        if (!violations.isEmpty()) {
            Map<String, String> errores = new HashMap<>();
            for (ConstraintViolation<EspecialidadPatchDTO> violation : violations) {
                errores.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            throw new ConstraintViolationException(errores.toString(), violations);
        }

        // Actualizar la entidad existente con los cambios del DTO PATCH
        especialidadMapper.updateEntityFromPatchDto(especialidadPatchDTO, especialidadExistente);

        // Guardar la entidad actualizada
        Especialidad especialidadActualizada = especialidadRepository.save(especialidadExistente);

        // Convertir la entidad actualizada a DTO y devolverla
        return especialidadMapper.toDto(especialidadActualizada);    }

    @Override
    public void eliminarEspecialidad(Long id) {
        // Buscar la especialidad por ID
        Especialidad especialidad = especialidadRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Especialidad no encontrada con id: " + id));

        // Desvincular a los profesionales asociados a la especialidad
        List<Profesional> profesionales = profesionalRepository.findByEspecialidad(especialidad);
        for (Profesional profesional : profesionales) {
            profesional.setEspecialidad(null);
            profesionalRepository.save(profesional);
        }

        // Eliminar la especialidad
        especialidadRepository.delete(especialidad);
    }
}
