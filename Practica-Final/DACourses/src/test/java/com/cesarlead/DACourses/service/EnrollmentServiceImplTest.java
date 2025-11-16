package com.cesarlead.DACourses.service;

import com.cesarlead.DACourses.client.StudentFeignClient;
import com.cesarlead.DACourses.dto.CrearInscripcionDTO;
import com.cesarlead.DACourses.dto.CursoDTO;
import com.cesarlead.DACourses.dto.EstudianteDTO;
import com.cesarlead.DACourses.dto.InscripcionDTO;
import com.cesarlead.DACourses.exception.ResourceNotFoundException;
import com.cesarlead.DACourses.mapper.MapperEnrollment;
import com.cesarlead.DACourses.model.Enrollment;
import com.cesarlead.DACourses.repository.EnrollmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class EnrollmentServiceImplTest {

    @Mock
    private StudentFeignClient studentFeignClient;

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    private CourseService courseService;

    @Mock
    private MapperEnrollment mapper;

    @InjectMocks
    private EnrollmentServiceImpl enrollmentService;

    private EstudianteDTO estudianteDTO;
    private CursoDTO cursoDTO;
    private Enrollment enrollment;
    private InscripcionDTO inscripcionDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        estudianteDTO = new EstudianteDTO(1L, "Juan", "Perez", "juan@example.com", LocalDateTime.now());
        cursoDTO = new CursoDTO(1L, "Java Básico", "Curso de Java");
        enrollment = new Enrollment();
        enrollment.setId(1L);
        enrollment.setCursoId(1L);
        enrollment.setEstudianteId(1L);
        enrollment.setFechaInscripcion(LocalDateTime.now());

        inscripcionDTO = new InscripcionDTO(1L, 1L, 1L, enrollment.getFechaInscripcion());
    }

    // Validar estudiante
    @Test
    void testValidateStudentById_Success() {
        when(studentFeignClient.getStudentByID(1L)).thenReturn(estudianteDTO);

        boolean result = enrollmentService.validateStudentById(1L);

        assertThat(result).isTrue();
        verify(studentFeignClient).getStudentByID(1L);
    }

    @Test
    void testValidateStudentById_NotFound() {
        when(studentFeignClient.getStudentByID(2L)).thenThrow(new ResourceNotFoundException("Estudiante no encontrado"));

        assertThatThrownBy(() -> enrollmentService.validateStudentById(2L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Estudiante no encontrado");

        verify(studentFeignClient).getStudentByID(2L);
    }

    // Crear inscripción
    @Test
    void testCreateEnrollment_Success() {
        CrearInscripcionDTO request = new CrearInscripcionDTO(1L, 1L);

        when(courseService.findById(1L)).thenReturn(cursoDTO);
        when(studentFeignClient.getStudentByID(1L)).thenReturn(estudianteDTO);
        when(enrollmentRepository.save(any(Enrollment.class))).thenReturn(enrollment);
        when(mapper.mapToInscripcionDTO(enrollment)).thenReturn(inscripcionDTO);

        InscripcionDTO result = enrollmentService.createEnrollment(request);

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.cursoId()).isEqualTo(1L);
        assertThat(result.estudianteId()).isEqualTo(1L);

        verify(courseService).findById(1L);
        verify(studentFeignClient).getStudentByID(1L);
        verify(enrollmentRepository).save(any(Enrollment.class));
        verify(mapper).mapToInscripcionDTO(enrollment);
    }

    // Consultar estudiantes de un curso
    @Test
    void testFindEstudiantesFromCurso() {
        when(enrollmentRepository.findEstudianteIdByCursoId(1L)).thenReturn(List.of(1L, 2L, 3L));

        List<Long> result = enrollmentService.findEstudiantesFromCurso(1L);

        assertThat(result).containsExactly(1L, 2L, 3L);
        verify(enrollmentRepository).findEstudianteIdByCursoId(1L);
    }
}
