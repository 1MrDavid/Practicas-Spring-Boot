package com.cesarlead.DACourses.controller;

import com.cesarlead.DACourses.dto.CursoDTO;
import com.cesarlead.DACourses.service.CourseService;
import com.cesarlead.DACourses.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cursos")
@AllArgsConstructor
@Tag(name = "administracion-cursos", description = "administracion de los cursos de la Digital Academy")
@Slf4j
public class CourseController {

  private final CourseService courseService;
  private final EnrollmentService enrollmentService;

  @Operation(summary = "Obtiene curso por ID")
  @GetMapping
  public ResponseEntity<List<CursoDTO>> getAllCourses() {
    return ResponseEntity.ok(courseService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CursoDTO> getCourseByID(@PathVariable Long id) {
      log.debug("Consultando");
    return ResponseEntity.ok(courseService.findById(id));
  }

  @PostMapping
  public ResponseEntity<CursoDTO> createCourse(
      @Valid @RequestBody CursoDTO request) {
      CursoDTO createdStudent = courseService.createCourse(request);

    return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
  }

  @GetMapping("/{cursoId}/estudiantes")
  public ResponseEntity<List<Long>> getStudentsFromCourse(@PathVariable Long cursoId) {
      log.info("Consultado estudiantes del curso: " + cursoId);

      return ResponseEntity.ok(enrollmentService.findEstudiantesFromCurso(cursoId));
  }
}
