package com.cesarlead.DACourses.repository;

import com.cesarlead.DACourses.model.Enrollment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EnrollmentRepositoryTest {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Test
    void testSaveEnrollment() {
        Enrollment enrollment = new Enrollment();
        enrollment.setCursoId(1L);
        enrollment.setEstudianteId(2L);
        enrollment.setFechaInscripcion(LocalDateTime.now());

        Enrollment saved = enrollmentRepository.save(enrollment);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getCursoId()).isEqualTo(1L);
        assertThat(saved.getEstudianteId()).isEqualTo(2L);
    }

    @Test
    void testFindById() {
        Enrollment enrollment = new Enrollment();
        enrollment.setCursoId(1L);
        enrollment.setEstudianteId(2L);
        enrollment.setFechaInscripcion(LocalDateTime.now());

        Enrollment saved = enrollmentRepository.save(enrollment);

        Optional<Enrollment> found = enrollmentRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getCursoId()).isEqualTo(1L);
        assertThat(found.get().getEstudianteId()).isEqualTo(2L);
    }

    @Test
    void testFindEstudianteIdByCursoId() {
        Enrollment e1 = new Enrollment();
        e1.setCursoId(1L);
        e1.setEstudianteId(10L);
        e1.setFechaInscripcion(LocalDateTime.now());

        Enrollment e2 = new Enrollment();
        e2.setCursoId(1L);
        e2.setEstudianteId(20L);
        e2.setFechaInscripcion(LocalDateTime.now());

        Enrollment e3 = new Enrollment();
        e3.setCursoId(2L);
        e3.setEstudianteId(30L);
        e3.setFechaInscripcion(LocalDateTime.now());

        enrollmentRepository.saveAll(List.of(e1, e2, e3));

        List<Long> estudiantesCurso1 = enrollmentRepository.findEstudianteIdByCursoId(1L);
        List<Long> estudiantesCurso2 = enrollmentRepository.findEstudianteIdByCursoId(2L);

        assertThat(estudiantesCurso1).hasSize(2).containsExactlyInAnyOrder(10L, 20L);
        assertThat(estudiantesCurso2).hasSize(1).containsExactly(30L);
    }

    @Test
    void testDeleteEnrollment() {
        Enrollment enrollment = new Enrollment();
        enrollment.setCursoId(1L);
        enrollment.setEstudianteId(2L);
        enrollment.setFechaInscripcion(LocalDateTime.now());

        Enrollment saved = enrollmentRepository.save(enrollment);

        enrollmentRepository.delete(saved);

        Optional<Enrollment> found = enrollmentRepository.findById(saved.getId());
        assertThat(found).isEmpty();
    }
}
