package com.cesarlead.DACourses.service;

import com.cesarlead.DACourses.client.StudentFeignClient;
import com.cesarlead.DACourses.dto.CrearInscripcionDTO;
import com.cesarlead.DACourses.dto.EstudianteDTO;
import com.cesarlead.DACourses.dto.InscripcionDTO;
import com.cesarlead.DACourses.exception.ResourceNotFoundException;
import com.cesarlead.DACourses.mapper.MapperEnrollment;
import com.cesarlead.DACourses.model.Enrollment;
import com.cesarlead.DACourses.repository.EnrollmentRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class EnrollmentServiceImpl implements EnrollmentService {

    private final StudentFeignClient studentFeignClient;
    private final EnrollmentRepository enrollmentRepository;
    private final MapperEnrollment mapper;

    public EnrollmentServiceImpl(
            StudentFeignClient studentFeignClient,
            EnrollmentRepository enrollmentRepository,
            MapperEnrollment mapper
    ) {
        this.studentFeignClient = studentFeignClient;
        this.enrollmentRepository = enrollmentRepository;
        this.mapper = mapper;
    }

    // Valida la existencia del estudiante
    public boolean validateStudentById(Long id) {
        try {
            log.info("Solicitando informacion del estudiante con ID: " + id);

            EstudianteDTO estudiante = studentFeignClient.getStudentByID(id);

            log.info("Estudiante validado exitosamente: {} {}",
                    estudiante.nombre(), estudiante.apellido());

            return true;

        } catch (ResourceNotFoundException e) {
            log.error("Estudiante no encontrado: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error inesperado validando al estudiante: {}", e.getMessage());
            throw new RuntimeException("Error inesperado validando al estudiante: " + e.getMessage());
        }
    }

    // Crea la inscripcion del curso
    @Override
    @Transactional
    public InscripcionDTO createEnrollment(CrearInscripcionDTO request) {

        boolean existStudent = validateStudentById(request.estudianteId());

        Enrollment inscripcion = new Enrollment();
        inscripcion.setCursoId(request.cursoId());
        inscripcion.setEstudianteId(request.estudianteId());
        inscripcion.setFechaInscripcion(LocalDateTime.now());

        Enrollment savedEnrollment = enrollmentRepository.save(inscripcion);
        return mapper.mapToInscripcionDTO(savedEnrollment);
    }
}
