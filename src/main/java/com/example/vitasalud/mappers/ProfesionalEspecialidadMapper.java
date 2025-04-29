package com.example.vitasalud.mappers;

import com.example.vitasalud.dtos.response.ProfesionalEspecialidadDTO;
import com.example.vitasalud.model.Especialidad;
import com.example.vitasalud.model.Profesional;
import com.example.vitasalud.model.ProfesionalEspecialidad;
import org.springframework.stereotype.Component;

@Component
public class ProfesionalEspecialidadMapper {
    public ProfesionalEspecialidadDTO toDto(ProfesionalEspecialidad profesionalEspecialidad) {
        if (profesionalEspecialidad == null) {
            return null;
        }
        ProfesionalEspecialidadDTO dto = new ProfesionalEspecialidadDTO();
        dto.setAnyosExperiencia(profesionalEspecialidad.getAnyosExperiencia());

        if (profesionalEspecialidad.getProfesional() != null) {
            Profesional profesional = profesionalEspecialidad.getProfesional();
            dto.setNombreProfesional(profesional.getNombre());
        }

        if (profesionalEspecialidad.getEspecialidad() != null) {
            Especialidad especialidad = profesionalEspecialidad.getEspecialidad();
            dto.setNombreEspecialidad(especialidad.getNombre());
        }

        return dto;
    }
}
