package com.example.vitasalud.mappers;

import com.example.vitasalud.dtos.request.create.EspecialidadCreateDTO;
import com.example.vitasalud.dtos.request.patch.EspecialidadPatchDTO;
import com.example.vitasalud.dtos.request.update.EspecialidadUpdateDTO;
import com.example.vitasalud.dtos.response.EspecialidadDTO;
import com.example.vitasalud.model.Especialidad;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Data
@RequiredArgsConstructor
public class EspecialidadMapper {

    private final ProfesionalMapper profesionalMapper;

    public EspecialidadDTO toDto(Especialidad especialidad) {
        if (especialidad == null) {
            return null;
        }

        EspecialidadDTO dto = new EspecialidadDTO();
        dto.setId(especialidad.getIdEspecialidad());
        dto.setNombre(especialidad.getNombre());

        // Mapear lista de profesionales
        dto.setProfesionales(
                Optional.ofNullable(especialidad.getProfesionales())
                        .orElse(List.of())
                        .stream()
                        .map(profesionalMapper::toDto)
                        .toList()
        );

        return dto;
    }


    public Especialidad toEntity(EspecialidadCreateDTO especialidadCreateDTO) {
        if (especialidadCreateDTO == null) {
            return null;
        }

        Especialidad especialidad = new Especialidad();
        especialidad.setNombre(especialidadCreateDTO.getNombre());

        return especialidad;
    }

    public void updateEntityFromDto(EspecialidadUpdateDTO dto, Especialidad especialidad) {
        if (dto == null || especialidad == null) {
            return;
        }

        if (dto.getNombre() != null) {
            especialidad.setNombre(dto.getNombre());
        }
    }

    public void updateEntityFromPatchDto(EspecialidadPatchDTO dto, Especialidad especialidadExistente) {
        if (dto == null || especialidadExistente == null) {
            return;
        }

        // Actualizar el nombre si no es nulo en el DTO
        if (dto.getNombre() != null) {
            especialidadExistente.setNombre(dto.getNombre());
        }
    }
}