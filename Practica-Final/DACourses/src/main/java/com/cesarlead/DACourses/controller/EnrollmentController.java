package com.cesarlead.DACourses.controller;

import com.cesarlead.DACourses.config.AppConstant;
import com.cesarlead.DACourses.dto.CrearInscripcionDTO;
import com.cesarlead.DACourses.dto.InscripcionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cesarlead.DACourses.service.EnrollmentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/inscripciones")
@Tag(
        name = "API-Inscripciones",
        description = "Gestion de las inscripciones a los cursos")
@Slf4j
@AllArgsConstructor
public class EnrollmentController {

  private final EnrollmentService enrollmentService;

  // Inscribe un estudiante a un curso
  @Operation(summary = "Inscribe estudiantes a un curso")
  @ApiResponse(responseCode = "201", description = "Inscripcion creada")
  @ApiResponse(responseCode = "404", description = "Curso o estudiantes no encontrado")
  @PostMapping
  public ResponseEntity<InscripcionDTO> createEnrollment(
      @Valid @RequestBody CrearInscripcionDTO request
  ) {

    InscripcionDTO createEnrollment = enrollmentService.createEnrollment(request);

    log.info(AppConstant.LOG_ENROLLMENT_CREATED, createEnrollment.estudianteId(), createEnrollment.cursoId());

    return new ResponseEntity<>(createEnrollment, HttpStatus.CREATED);
  }
}
