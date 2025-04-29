package com.example.vitasalud.mappers;

import com.example.vitasalud.dtos.request.create.ProfesionalCreateDTO;
import com.example.vitasalud.dtos.request.patch.ProfesionalPatchDTO;
import com.example.vitasalud.dtos.request.update.ProfesionalUpdateDTO;
import com.example.vitasalud.dtos.response.ProfesionalDTO;
import com.example.vitasalud.model.Especialidad;
import com.example.vitasalud.model.Profesional;
import com.example.vitasalud.repository.EspecialidadRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class ProfesionalMapper {

    private final EspecialidadRepository especialidadRepository;

    public ProfesionalDTO toDto(Profesional profesional) {
        if (profesional == null) {
            return null;
        }

        ProfesionalDTO dto = new ProfesionalDTO();
        dto.setId(profesional.getIdProfesional());
        dto.setNombre(profesional.getNombre());
        dto.setDireccion(profesional.getDireccion());
        dto.setLocalidad(profesional.getLocalidad());
        dto.setCp(profesional.getCp());
        dto.setTelefono(profesional.getTelefono());
        dto.setEmail(profesional.getEmail());
        dto.setHoraInicio(profesional.getHoraInicio());
        dto.setHoraFin(profesional.getHoraFin());
        dto.setPrecio(profesional.getPrecioConsulta());
        dto.setImagen(profesional.getImagen());
        dto.setLatitud(profesional.getLatitud());
        dto.setLongitud(profesional.getLongitud());
        dto.setEspecialidad(profesional.getEspecialidad() != null ? profesional.getEspecialidad().getNombre() : null);

        return dto;
    }

    public Profesional toEntity(ProfesionalCreateDTO profesionalCreateDTO) {
        if (profesionalCreateDTO == null) {
            return null;
        }

        Profesional profesional = new Profesional();
        profesional.setNombre(profesionalCreateDTO.getNombre());
        profesional.setDireccion(profesionalCreateDTO.getDireccion());
        profesional.setEmail(profesionalCreateDTO.getEmail());
        profesional.setLocalidad(profesionalCreateDTO.getLocalidad());
        profesional.setCp(profesionalCreateDTO.getCp());
        profesional.setTelefono(profesionalCreateDTO.getTelefono());
        profesional.setHoraInicio(profesionalCreateDTO.getHoraInicio());
        profesional.setHoraFin(profesionalCreateDTO.getHoraFin());
        profesional.setPrecioConsulta(profesionalCreateDTO.getPrecio());
        profesional.setImagen(profesionalCreateDTO.getImagen());
        profesional.setLatitud(profesionalCreateDTO.getLatitud());
        profesional.setLongitud(profesionalCreateDTO.getLongitud());

        Especialidad especialidad = especialidadRepository.findById(profesionalCreateDTO.getEspecialidadId())
                .orElseThrow(() -> new EntityNotFoundException("Especialidad no encontrada con id: " + profesionalCreateDTO.getEspecialidadId()));
        profesional.setEspecialidad(especialidad);

        return profesional;
    }

    public void updateEntityFromDto(ProfesionalUpdateDTO dto, Profesional profesionalExistente) {
        if (dto == null || profesionalExistente == null) {
            return;
        }

        if (dto.getDireccion() != null) {
            profesionalExistente.setDireccion(dto.getDireccion());
        }
        if (dto.getTelefono() != null) {
            profesionalExistente.setTelefono(dto.getTelefono());
        }
        if (dto.getEmail() != null) {
            profesionalExistente.setEmail(dto.getEmail());
        }
        if (dto.getNombre() != null) {
            profesionalExistente.setNombre(dto.getNombre());
        }
        if (dto.getCp() != null) {
            profesionalExistente.setCp(dto.getCp());
        }
        if (dto.getHoraInicio() != null) {
            profesionalExistente.setHoraInicio(dto.getHoraInicio());
        }
        if (dto.getHoraFin() != null) {
            profesionalExistente.setHoraFin(dto.getHoraFin());
        }
        if (dto.getPrecio() != null) {
            profesionalExistente.setPrecioConsulta(dto.getPrecio());
        }
        if (dto.getImagen() != null) {
            profesionalExistente.setImagen(dto.getImagen());
        }
        if (dto.getLatitud() != null) {
            profesionalExistente.setLatitud(dto.getLatitud());
        }
        if (dto.getLongitud() != null) {
            profesionalExistente.setLongitud(dto.getLongitud());
        }
    }

    public void updateEntityFromPatchDto(ProfesionalPatchDTO dto, Profesional profesional) {
        if (dto == null || profesional == null) {
            return;
        }

        if (dto.getDireccion() != null) {
            profesional.setDireccion(dto.getDireccion());
        }
        if (dto.getTelefono() != null) {
            profesional.setTelefono(dto.getTelefono());
        }
        if (dto.getEmail() != null) {
            profesional.setEmail(dto.getEmail());
        }
        if (dto.getNombre() != null) {
            profesional.setNombre(dto.getNombre());
        }
        if (dto.getCp() != null) {
            profesional.setCp(dto.getCp());
        }
        if (dto.getHoraInicio() != null) {
            profesional.setHoraInicio(dto.getHoraInicio());
        }
        if (dto.getHoraFin() != null) {
            profesional.setHoraFin(dto.getHoraFin());
        }
        if (dto.getPrecio() != null) {
            profesional.setPrecioConsulta(dto.getPrecio());
        }
        if (dto.getImagen() != null) {
            profesional.setImagen(dto.getImagen());
        }
        if (dto.getLatitud() != null) {
            profesional.setLatitud(dto.getLatitud());
        }
        if (dto.getLongitud() != null) {
            profesional.setLongitud(dto.getLongitud());
        }
    }

}
