package com.cesarlead.DAStudents.repository;

import com.cesarlead.DAStudents.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

// Inicializa solo la capa de JPA (repositories y entidades)
@DataJpaTest
@ActiveProfiles("test")
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testSaveAndFindById() {
        // Crear y guardar estudiante
        Student student = new Student();
        student.setNombre("Juan");
        student.setApellido("Perez");
        student.setEmail("juan@example.com");
        student.setFechaCreacion(LocalDateTime.now());

        Student saved = studentRepository.save(student);

        // Recuperar por ID
        Optional<Student> found = studentRepository.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getNombre()).isEqualTo("Juan");
    }

    @Test
    void testFindAll() {
        Student s1 = new Student();
        s1.setNombre("Ana");
        s1.setApellido("Lopez");
        s1.setEmail("ana@example.com");
        s1.setFechaCreacion(LocalDateTime.now());

        Student s2 = new Student();
        s2.setNombre("Luis");
        s2.setApellido("Gomez");
        s2.setEmail("luis@example.com");
        s2.setFechaCreacion(LocalDateTime.now());

        studentRepository.save(s1);
        studentRepository.save(s2);

        assertThat(studentRepository.findAll()).hasSize(2);
    }
}
