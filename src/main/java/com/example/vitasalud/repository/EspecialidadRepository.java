package com.example.vitasalud.repository;

import com.example.vitasalud.model.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {
    List<Especialidad> findByNombreContainingIgnoreCase(String nombre);
}
