package com.cesarlead.DACourses.controller;

import com.cesarlead.DACourses.config.AppConstant;
import com.cesarlead.DACourses.dto.CursoDTO;
import com.cesarlead.DACourses.service.CourseService;
import com.cesarlead.DACourses.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
@Tag(
        name = "API-Cursos",
        description = "Gestión del catálogo de cursos de la Digital Academy"
)
@Slf4j
public class CourseController {

  private final CourseService courseService;
  private final EnrollmentService enrollmentService;

  // Obtiene todos los cursos
  @Operation(summary = "Obtener todos los cursos")
  @ApiResponse(responseCode = "200", description = "Lista de cursos")
  @GetMapping
  public ResponseEntity<List<CursoDTO>> getAllCourses() {
    log.debug(AppConstant.LOG_RETRIEVING_ALL_COURSE);
    return ResponseEntity.ok(courseService.getAll());
  }

  // Obtiene cusro por ID
  @Operation(summary = "Obtener un curso por ID")
  @ApiResponse(responseCode = "200", description = "Curso encontrado")
  @ApiResponse(responseCode = "404", description = "Curso no encontrado")
  @GetMapping("/{id}")
  public ResponseEntity<CursoDTO> getCourseByID(@PathVariable Long id) {
    log.debug(AppConstant.LOG_FINDING_COURSE, id);
    return ResponseEntity.ok(courseService.findById(id));
  }

  // Crea nuevo curso
  @Operation(summary = "Crear un nuevo curso")
  @ApiResponse(responseCode = "201", description = "Curso creado correctamente")
  @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
  @PostMapping
  public ResponseEntity<CursoDTO> createCourse(
    @Valid
    @RequestBody CursoDTO request
  ) {

    CursoDTO createdCourse = courseService.createCourse(request);

    log.info(AppConstant.LOG_COURSE_CREATED, createdCourse.id());

    return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
  }

  // Obtiene los estudiantes inscrito a un curso
  @Operation(summary = "Obtener IDs de estudiantes inscritos en un curso")
  @ApiResponse(responseCode = "200", description = "Lista de IDs de estudiantes")
  @ApiResponse(responseCode = "404", description = "Curso no encontrado")
  @GetMapping("/{cursoId}/estudiantes")
  public ResponseEntity<List<Long>> getStudentsFromCourse(@PathVariable Long cursoId) {

      List<Long> estudiantes = enrollmentService.findEstudiantesFromCurso(cursoId);

      log.info(AppConstant.LOG_COURSE_STUDENTS_CONSULTED, cursoId);

      return ResponseEntity.ok(estudiantes);
  }
}
