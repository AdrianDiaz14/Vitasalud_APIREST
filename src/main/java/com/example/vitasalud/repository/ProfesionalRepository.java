package com.example.vitasalud.repository;

import com.example.vitasalud.model.Especialidad;
import com.example.vitasalud.model.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface ProfesionalRepository extends JpaRepository<Profesional, Long> {
    List<Profesional> findByNombreContainingIgnoreCase(String nombre);
    List<Profesional> findByLocalidadContainingIgnoreCase(String localidad);
    // Buscar centros por especialidad de sus médicos
    List<Profesional> findByEspecialidad(Especialidad especialidad);
    List<Profesional> findByEspecialidadIdEspecialidad(Long idEspecialidad);
}
