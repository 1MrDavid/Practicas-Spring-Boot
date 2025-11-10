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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

  private final StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping
  public ResponseEntity<List<EstudianteDTO>> getAllStudents() {
    return ResponseEntity.ok(studentService.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<EstudianteDTO> getStudentByID(@PathVariable Long id) {
    return ResponseEntity.ok(studentService.findById(id));
  }

  @PostMapping
  public ResponseEntity<EstudianteDTO> createStudent(
      @Valid @RequestBody CrearEstudianteDTO request) {
    EstudianteDTO createdStudent = studentService.createStudent(request);

    return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
  }
}
