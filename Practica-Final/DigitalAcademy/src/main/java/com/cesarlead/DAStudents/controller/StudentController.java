package com.cesarlead.DAStudents.controller;

import java.util.List;

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
@Tag(name = "administracion-estudiantes", description = "administracion de estudiantes de la Digital Academy")
@Slf4j
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @Operation(summary = "Obtiene estudiante por ID")
  @GetMapping
  public ResponseEntity<List<EstudianteDTO>> getAllStudents() {

      return ResponseEntity.ok(studentService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<EstudianteDTO> getStudentByID(@PathVariable Long id) {
      log.debug("Consultando");
    return ResponseEntity.ok(studentService.findById(id));
  }

  @PostMapping
  public ResponseEntity<EstudianteDTO> createStudent(
      @Valid @RequestBody CrearEstudianteDTO request) {
    EstudianteDTO createdStudent = studentService.createStudent(request);

    return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
  }
}
