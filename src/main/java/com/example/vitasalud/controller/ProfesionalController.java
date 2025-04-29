package com.example.vitasalud.controller;

import com.example.vitasalud.dtos.request.create.ProfesionalCreateDTO;
import com.example.vitasalud.dtos.request.patch.ProfesionalPatchDTO;
import com.example.vitasalud.dtos.request.update.ProfesionalUpdateDTO;
import com.example.vitasalud.dtos.response.ProfesionalDTO;
import com.example.vitasalud.service.ProfesionalService;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesionales")
public class ProfesionalController {

    private final ProfesionalService profesionalService;

    @Autowired
    public ProfesionalController(ProfesionalService profesionalService) {
        this.profesionalService = profesionalService;
    }

    @GetMapping
    @Operation(summary = "Obtiene todos los profesionales")
    public ResponseEntity<List<ProfesionalDTO>> obtenerTodosLosProfesionales() {
        List<ProfesionalDTO> profesionales = profesionalService.obtenerTodosLosProfesionales();
        if (profesionales.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(profesionales); // 200 OK
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un profesional por su ID")
    public ResponseEntity<ProfesionalDTO> obtenerProfesionalPorId(@PathVariable Long id) {
        ProfesionalDTO profesional = profesionalService.obtenerProfesionalPorId(id);
        if (profesional == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        return ResponseEntity.ok(profesional); // 200 OK
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "Obtiene los profesionales por su nombre")
    public ResponseEntity<List<ProfesionalDTO>> obtenerProfesionalesPorNombre(@PathVariable String nombre) {
        List<ProfesionalDTO> profesionales = profesionalService.obtenerProfesionalesPorNombre(nombre);
        if (profesionales.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(profesionales); // 200 OK
    }

    @GetMapping("/localidad/{localidad}")
    @Operation(summary = "Obtiene los profesionales por su localidad")
    public ResponseEntity<List<ProfesionalDTO>> obtenerProfesionalesPorLocalidad(@PathVariable String localidad) {
        List<ProfesionalDTO> profesionales = profesionalService.obtenerProfesionalesPorLocalidad(localidad);
        if (profesionales.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(profesionales); // 200 OK
    }

    @GetMapping("/especialidad/{especialidadId}")
    @Operation(summary = "Obtiene los profesionales por el ID de una especialidad")
    public ResponseEntity<List<ProfesionalDTO>> obtenerProfesionalesPorEspecialidad(@PathVariable Long especialidadId) {
        List<ProfesionalDTO> profesionales = profesionalService.obtenerProfesionalesPorEspecialidad(especialidadId);
        if (profesionales.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(profesionales); // 200 OK
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo profesional")
    public ResponseEntity<ProfesionalDTO> crearProfesional(@Valid @RequestBody ProfesionalCreateDTO dto) {
        ProfesionalDTO profesionalCreado = profesionalService.crearProfesional(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(profesionalCreado); // 201 Created
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifica los datos de un profesional")
    public ResponseEntity<ProfesionalDTO> actualizarProfesional(@PathVariable Long id, @Valid @RequestBody ProfesionalUpdateDTO dto) {
        ProfesionalDTO profesionalActualizado = profesionalService.actualizarProfesional(id, dto);
        return ResponseEntity.ok(profesionalActualizado); // 200 OK
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Modifica los datos de un profesional")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "JSON con los datos del profesional a modificar. Todos los campos son opcionales.",
            content = @Content(schema = @Schema(implementation = ProfesionalPatchDTO.class))
    )
    public ResponseEntity<ProfesionalDTO> actualizarProfesionalParcial(@PathVariable Long id, @RequestBody JsonNode patch) {
        ProfesionalDTO profesionalActualizado = profesionalService.actualizarProfesionalParcial(id, patch);
        return ResponseEntity.ok(profesionalActualizado); // 200 OK
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un profesional por su ID")
    public ResponseEntity<Void> eliminarProfesional(@PathVariable Long id) {
        profesionalService.eliminarProfesional(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}