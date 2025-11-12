package com.cesarlead.DAStudents.service;

import com.cesarlead.DAStudents.dto.CrearEstudianteDTO;
import com.cesarlead.DAStudents.dto.EstudianteDTO;
import com.cesarlead.DAStudents.exception.ResourceNotFoundException;
import com.cesarlead.DAStudents.mapper.MapperStudent;
import com.cesarlead.DAStudents.model.Student;
import com.cesarlead.DAStudents.repository.StudentRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;
  private final MapperStudent mapper;

  public StudentServiceImpl(
      StudentRepository studentRepository,
      MapperStudent mapper) {
    this.studentRepository = studentRepository;
    this.mapper = mapper;
  }

  // Busca estudiante por id
  @Override
  public EstudianteDTO findById(Long id) {

    Student student = studentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado"));

    return mapper.mapTopEstudianteDTO(student);
  }

  // Busca todos los estudiantes
  @Override
  public List<EstudianteDTO> getAll() {
    return studentRepository.findAll().stream()
        .map(mapper::mapTopEstudianteDTO)
        .toList();
  }

  // Crea nuevo estudiante
  @Override
  @Transactional
  public EstudianteDTO createStudent(CrearEstudianteDTO request) {

    Student estudiante = new Student();
    estudiante.setNombre(request.nombre());
    estudiante.setApellido(request.apellido());
    estudiante.setEmail(request.email());
    estudiante.setFechaCreacion(LocalDateTime.now());

    Student savedStudent = studentRepository.save(estudiante);
    return mapper.mapTopEstudianteDTO(savedStudent);
  }

}
