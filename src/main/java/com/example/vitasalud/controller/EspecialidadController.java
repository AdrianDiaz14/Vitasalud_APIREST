package com.example.vitasalud.controller;

import com.example.vitasalud.dtos.request.create.EspecialidadCreateDTO;
import com.example.vitasalud.dtos.request.patch.EspecialidadPatchDTO;
import com.example.vitasalud.dtos.request.update.EspecialidadUpdateDTO;
import com.example.vitasalud.dtos.response.EspecialidadDTO;
import com.example.vitasalud.service.EspecialidadService;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadController {

    private final EspecialidadService especialidadService;

    @Autowired
    public EspecialidadController(EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @GetMapping
    @Operation(summary = "Obtiene todas las especialidades")
    public ResponseEntity<List<EspecialidadDTO>> obtenerTodasLasEspecialidades() {
        List<EspecialidadDTO> especialidades = especialidadService.obtenerTodasLasEspecialidades();
        if (especialidades.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(especialidades); // 200 OK
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una especialidad por su ID")
    public ResponseEntity<EspecialidadDTO> obtenerEspecialidadPorId(@PathVariable Long id) {
        EspecialidadDTO especialidad = especialidadService.obtenerEspecialidadPorId(id);
        if (especialidad == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        return ResponseEntity.ok(especialidad); // 200 OK
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(summary = "Obtiene las especialidades por su nombre")
    public ResponseEntity<List<EspecialidadDTO>> obtenerEspecialidadesPorNombre(@PathVariable String nombre) {
        List<EspecialidadDTO> especialidades = especialidadService.obtenerEspecialidadesPorNombre(nombre);
        if (especialidades.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(especialidades); // 200 OK
    }

    @PostMapping
    @Operation(summary = "Crea una nueva especialidad")
    public ResponseEntity<EspecialidadDTO> crearEspecialidad(@Valid @RequestBody EspecialidadCreateDTO dto) {
        EspecialidadDTO especialidadCreada = especialidadService.crearEspecialidad(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(especialidadCreada); // 201 Created
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifica los datos de una especialidad")
    public ResponseEntity<EspecialidadDTO> actualizarEspecialidad(@PathVariable Long id, @Valid @RequestBody EspecialidadUpdateDTO dto) {
        EspecialidadDTO especialidadActualizada = especialidadService.actualizarEspecialidad(id, dto);
        if (especialidadActualizada == null) {
            throw new EntityNotFoundException("Especialidad no encontrada con id: " + id);
        }        return ResponseEntity.ok(especialidadActualizada); // 200 OK
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Modifica los datos de una especialidad")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "JSON con los datos de la especialidad a modificar. Todos los campos son opcionales.",
            content = @Content(schema = @Schema(implementation = EspecialidadPatchDTO.class))
    )
    public ResponseEntity<EspecialidadDTO> actualizarEspecialidadParcial(@PathVariable Long id, @RequestBody JsonNode patch) {
        EspecialidadDTO especialidadActualizada = especialidadService.actualizarEspecialidadParcial(id, patch);
        return ResponseEntity.ok(especialidadActualizada); // 200 OK
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una especialidad por su ID")
    public ResponseEntity<Void> eliminarEspecialidad(@PathVariable Long id) {
        especialidadService.eliminarEspecialidad(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
