package com.cesarlead.DACourses.service;

import com.cesarlead.DACourses.client.StudentFeignClient;
import com.cesarlead.DACourses.config.AppConstant;
import com.cesarlead.DACourses.dto.CrearInscripcionDTO;
import com.cesarlead.DACourses.dto.CursoDTO;
import com.cesarlead.DACourses.dto.EstudianteDTO;
import com.cesarlead.DACourses.dto.InscripcionDTO;
import com.cesarlead.DACourses.exception.ResourceNotFoundException;
import com.cesarlead.DACourses.mapper.MapperEnrollment;
import com.cesarlead.DACourses.model.Enrollment;
import com.cesarlead.DACourses.repository.EnrollmentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

  private final StudentFeignClient studentFeignClient;
  private final EnrollmentRepository enrollmentRepository;
  private final CourseService courseService;
  private final MapperEnrollment mapper;

  // Valida la existencia del estudiante
  public boolean validateStudentById(Long id) {

      // Eliminado el try {} catch {} para manejar de manera mas elegante la funcion

      log.info(AppConstant.LOG_VALIDATE_STUDENT, id);

      // Esta llamada lanzará ResourceNotFoundException si el estudiante no existe
      EstudianteDTO estudiante = studentFeignClient.getStudentByID(id);

      // Si llega hasta aquí, el estudiante existe
      log.info(AppConstant.LOG_STUDENT_VALIDATED, estudiante.nombre(), estudiante.apellido());
      return true;
  }

  // Crea la inscripcion del curso
  @Override
  @Transactional
  public InscripcionDTO createEnrollment(CrearInscripcionDTO request) {

    log.info(AppConstant.LOG_VALIDATE_COURSE, request.cursoId());

    // Validación del curso
    CursoDTO curso = courseService.findById(request.cursoId());

    // Validación del estudiante
    validateStudentById(request.estudianteId());

    log.info(AppConstant.LOG_ENROLLMENT_CREATING, request.estudianteId(), request.cursoId());

    Enrollment inscripcion = new Enrollment();
    inscripcion.setCursoId(request.cursoId());
    inscripcion.setEstudianteId(request.estudianteId());
    inscripcion.setFechaInscripcion(LocalDateTime.now());

    Enrollment savedEnrollment = enrollmentRepository.save(inscripcion);

    log.info(AppConstant.LOG_ENROLLMENT_SAVED, savedEnrollment.getId());

    return mapper.mapToInscripcionDTO(savedEnrollment);
  }

  // Consulta estudiantes de un curso
  @Override
  public List<Long> findEstudiantesFromCurso(Long cursoId) {
      log.info(AppConstant.LOG_COURSE_STUDENTS_CONSULTED, cursoId);
      return enrollmentRepository.findEstudianteIdByCursoId(cursoId);
  }

  @Override
  public void existEnrollment(Long cursoId, Long estudianteId) {
      log.info(AppConstant.LOG_ENROLLMENT_CONSULTING, estudianteId, cursoId);

      if (!enrollmentRepository.existsByCursoIdAndEstudianteId(cursoId, estudianteId)) {
          throw new ResourceNotFoundException("El estudiante " + estudianteId + " no está inscrito en el curso " + cursoId);
      }

      // Si no lanza excepcion la inscripcion existe
  }
}


