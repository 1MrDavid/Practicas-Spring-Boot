package com.cesarlead.DACourses.controller;

import com.cesarlead.DACourses.dto.CrearInscripcionDTO;
import com.cesarlead.DACourses.dto.InscripcionDTO;
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
@Tag(name = "administracion-inscripciones", description = "administracion de las inscripciones  a cursos")
@Slf4j
@AllArgsConstructor
public class EnrollmentController {

  private final EnrollmentService enrollmentService;

  @PostMapping
  public ResponseEntity<InscripcionDTO> createEnrollment(
      @Valid @RequestBody CrearInscripcionDTO request) {
    InscripcionDTO createEnrollment = enrollmentService.createEnrollment(request);

    return new ResponseEntity<>(createEnrollment, HttpStatus.CREATED);
  }
}
