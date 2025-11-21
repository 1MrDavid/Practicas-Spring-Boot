package com.cesarlead.DAProgress.controller;

import com.cesarlead.DAProgress.dto.CrearEventoDTO;
import com.cesarlead.DAProgress.dto.EventoProgresoDTO;
import com.cesarlead.DAProgress.service.ProgressService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Get, Post, Update...
@RestController
// PATH base
@RequestMapping("/api/v1/progreso")
@Tag(
        name = "API-Progreso",
        description = "Registro de eventos de progreso en cursos"
)
// Simple Log 4 Java
@Slf4j
@RequiredArgsConstructor
public class ProgressController {

    //private static final Logger logger = LoggerFactory.getLogger(ProgressController.class);

    private final ProgressService progressService;

    @Operation(summary = "Registrar un evento de progreso")
    @ApiResponse(responseCode = "201", description = "Evento registrado correctamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "404", description = "Curso o estudiante no encontrado")
    @PostMapping
    public ResponseEntity<EventoProgresoDTO> registrarEvento(
            @Valid @RequestBody CrearEventoDTO request
    ) {

        EventoProgresoDTO response = progressService.registrarEvento(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Obtener todos los eventos de progreso de un curso",
            description = "Devuelve la lista completa de eventos registrados para un curso específico"
    )
    @ApiResponse(responseCode = "200", description = "Eventos obtenidos con éxito")
    @ApiResponse(responseCode = "404", description = "Curso no encontrado")
    @GetMapping("/curso/{cursoId}/eventos")
    public ResponseEntity<List<EventoProgresoDTO>> getEventosPorCurso(@PathVariable Long cursoId) {
        log.info("Consultando eventos del curso {}", cursoId);
        return ResponseEntity.ok(progressService.getEventos(cursoId));
    }
}