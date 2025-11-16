package com.cesarlead.DAStudents.service;

import com.cesarlead.DAStudents.config.AppConstant;
import com.cesarlead.DAStudents.dto.CrearEstudianteDTO;
import com.cesarlead.DAStudents.dto.EstudianteDTO;
import com.cesarlead.DAStudents.exception.ResourceNotFoundException;
import com.cesarlead.DAStudents.mapper.MapperStudent;
import com.cesarlead.DAStudents.model.Student;
import com.cesarlead.DAStudents.repository.StudentRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;
  private final MapperStudent mapper;

  // Busca estudiante por id
  @Override
  public EstudianteDTO findById(Long id) {

      log.info(AppConstant.LOG_FINDING_STUDENT, id);

      Student student = studentRepository.findById(id)
              .orElseThrow(() -> {
                  log.warn(AppConstant.LOG_STUDENT_NOT_FOUND, id);
                  return new ResourceNotFoundException(AppConstant.ERROR_STUDENT_NOT_FOUND);
              });

      log.info(AppConstant.LOG_USER_CONSULTED, id);
      return mapper.mapTopEstudianteDTO(student);
  }

  // Busca todos los estudiantes
  @Override
  public List<EstudianteDTO> getAll() {

      log.info(AppConstant.LOG_RETRIEVING_ALL_STUDENTS);

      return studentRepository.findAll().stream()
              .map(mapper::mapTopEstudianteDTO)
              .toList();
  }

  // Crea nuevo estudiante
  @Override
  @Transactional
  public EstudianteDTO createStudent(CrearEstudianteDTO request) {

      log.info(AppConstant.LOG_ENTERING_METHOD, "createStudent");

      Student estudiante = new Student();
      estudiante.setNombre(request.nombre());
      estudiante.setApellido(request.apellido());
      estudiante.setEmail(request.email());
      estudiante.setFechaCreacion(LocalDateTime.now());

      Student savedStudent = studentRepository.save(estudiante);

      log.info(AppConstant.LOG_STUDENT_CREATED, savedStudent.getId());
      log.info(AppConstant.LOG_EXITING_METHOD, "createStudent");

      return mapper.mapTopEstudianteDTO(savedStudent);
  }

}
