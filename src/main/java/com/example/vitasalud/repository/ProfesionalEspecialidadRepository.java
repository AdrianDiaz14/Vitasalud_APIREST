package com.example.vitasalud.repository;

import com.example.vitasalud.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesionalEspecialidadRepository extends JpaRepository<ProfesionalEspecialidad, ProfesionalEspecialidadKey> {
    void deleteByProfesional(Profesional profesional);
}
