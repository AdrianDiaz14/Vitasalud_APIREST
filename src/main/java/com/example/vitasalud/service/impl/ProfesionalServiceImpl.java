package com.example.vitasalud.service.impl;

import com.example.vitasalud.dtos.request.create.ProfesionalCreateDTO;
import com.example.vitasalud.dtos.request.update.ProfesionalUpdateDTO;
import com.example.vitasalud.dtos.request.patch.ProfesionalPatchDTO;
import com.example.vitasalud.dtos.response.ProfesionalDTO;
import com.example.vitasalud.mappers.ProfesionalMapper;
import com.example.vitasalud.model.Profesional;
import com.example.vitasalud.repository.ProfesionalEspecialidadRepository;
import com.example.vitasalud.repository.ProfesionalRepository;
import com.example.vitasalud.service.ProfesionalService;
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
public class ProfesionalServiceImpl implements ProfesionalService {

    private final ProfesionalRepository profesionalRepository;
    private final ProfesionalEspecialidadRepository profesionalEspecialidadRepository;
    private final ProfesionalMapper profesionalMapper;
    private final ObjectMapper objectMapper;
    private final Validator validator;

    @Override
    public List<ProfesionalDTO> obtenerTodosLosProfesionales() {
        return profesionalRepository.findAll().stream()
                .map(profesionalMapper::toDto)
                .toList();
    }
    @Override
    public ProfesionalDTO obtenerProfesionalPorId(Long Id) {
        return profesionalRepository.findById(Id)
                .map(profesionalMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<ProfesionalDTO> obtenerProfesionalesPorNombre(String nombre) {
        return profesionalRepository.findByNombreContainingIgnoreCase(nombre).stream()   // Ignora mayusculas/minusculas y filtra por parte de la palabra
                .map(profesionalMapper::toDto)
                .toList();
    }

    @Override
    public List<ProfesionalDTO> obtenerProfesionalesPorLocalidad(String localidad) {
        return profesionalRepository.findByLocalidadContainingIgnoreCase(localidad).stream()   // Ignora mayusculas/minusculas y filtra por parte de la palabra
                .map(profesionalMapper::toDto)
                .toList();
    }

    @Override
    public List<ProfesionalDTO> obtenerProfesionalesPorEspecialidad(Long idEspecialidad) {
        return profesionalRepository.findByEspecialidadIdEspecialidad(idEspecialidad).stream()
                .map(profesionalMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public ProfesionalDTO crearProfesional(ProfesionalCreateDTO dto) {
        // 1. Convertir el DTO a una entidad Paciente
        Profesional profesional = profesionalMapper.toEntity(dto);

        // 2. Guardar la entidad en la base de datos
        Profesional profesionalGuardado = profesionalRepository.save(profesional);

        // 3. Convertir la entidad guardada a un DTO para la respuesta
        ProfesionalDTO profesionalDTO = profesionalMapper.toDto(profesionalGuardado);

        // 4. Retornar el DTO
        return profesionalDTO;
    }

    @Override
    @Transactional
    public ProfesionalDTO actualizarProfesional(Long id, ProfesionalUpdateDTO dto) {
        Profesional profesionalExistente = profesionalRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Profesional no encontrado con id: " + id));

        profesionalMapper.updateEntityFromDto(dto, profesionalExistente);
        Profesional profesionalActualizado = profesionalRepository.save(profesionalExistente);

        return profesionalMapper.toDto(profesionalActualizado);
    }

    @Override
    @Transactional
    public ProfesionalDTO actualizarProfesionalParcial(Long id, JsonNode patch) {
        // Buscar el profesional existente por ID
        Profesional profesionalExistente = profesionalRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Profesional no encontrado con id: " + id));

        // Convertir el JsonNode a ProfesionalPatchDTO
        ProfesionalPatchDTO profesionalPatchDTO = objectMapper.convertValue(patch, ProfesionalPatchDTO.class);

        // Validar el DTO
        Set<ConstraintViolation<ProfesionalPatchDTO>> violations = validator.validate(profesionalPatchDTO);
        if (!violations.isEmpty()) {
            Map<String, String> errores = new HashMap<>();
            for (ConstraintViolation<ProfesionalPatchDTO> violation : violations) {
                errores.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            throw new ConstraintViolationException(errores.toString(), violations);
        }

        // Actualizar la entidad con los cambios del DTO
        profesionalMapper.updateEntityFromPatchDto(profesionalPatchDTO, profesionalExistente);

        // Guardar la entidad actualizada
        Profesional profesionalActualizado = profesionalRepository.save(profesionalExistente);

        // Convertir la entidad actualizada a DTO y devolverla
        return profesionalMapper.toDto(profesionalActualizado);
    }

    @Override
    @Transactional
    public void eliminarProfesional(Long id) {
        // Buscar el profesional por ID
        Profesional profesional = profesionalRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Profesional no encontrado con id: " + id));


        // Eliminar las relaciones en Profesional_Especialidad
        profesionalEspecialidadRepository.deleteByProfesional(profesional);

        // Eliminar el profesional
        profesionalRepository.delete(profesional);
    }
}