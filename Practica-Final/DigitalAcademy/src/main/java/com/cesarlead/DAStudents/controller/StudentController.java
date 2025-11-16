package com.cesarlead.DAStudents.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesarlead.DAStudents.dto.CrearEstudianteDTO;
import com.cesarlead.DAStudents.dto.EstudianteDTO;
import com.cesarlead.DAStudents.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
@Tag(name = "API-Estudiantes", description = "Gestiona el CRUD de estudiantes")
@Slf4j
public class StudentController {

  private final StudentService studentService;

  @Operation(summary = "Obtener todos los estudiantes")
  @ApiResponse(responseCode = "200", description = "Lista de estudiantes")
  @GetMapping
  public ResponseEntity<List<EstudianteDTO>> getAllStudents() {

      return ResponseEntity.ok(studentService.getAll());
  }

  @Operation(summary = "Obtener un estudiante por su ID")
  @ApiResponse(responseCode = "200", description = "Estudiante encontrado")
  @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
  @GetMapping("/{id}")
  public ResponseEntity<EstudianteDTO> getStudentByID(@PathVariable Long id) {
      log.debug("Consultando");
    return ResponseEntity.ok(studentService.findById(id));
  }

  @Operation(summary = "Crear un nuevo estudiante")
  @ApiResponse(responseCode = "201", description = "Estudiante creado exitosamente")
  @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
  @ApiResponse(responseCode = "409", description = "El email ya existe")
  @PostMapping
  public ResponseEntity<EstudianteDTO> createStudent(
      @Valid @RequestBody CrearEstudianteDTO request
  ) {
    EstudianteDTO createdStudent = studentService.createStudent(request);

    return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
  }
}
