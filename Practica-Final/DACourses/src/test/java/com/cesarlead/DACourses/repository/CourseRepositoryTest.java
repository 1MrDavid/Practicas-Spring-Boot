package com.cesarlead.DACourses.repository;

import com.cesarlead.DACourses.model.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void testSaveCourse() {
        Course course = new Course();
        course.setTitulo("Java Avanzado");
        course.setDescripcion("Curso avanzado de Java");

        Course savedCourse = courseRepository.save(course);

        assertThat(savedCourse.getId()).isNotNull();
        assertThat(savedCourse.getTitulo()).isEqualTo("Java Avanzado");
    }

    @Test
    void testFindById() {
        Course course = new Course();
        course.setTitulo("Spring Boot");
        course.setDescripcion("Curso de Spring Boot");

        Course saved = courseRepository.save(course);

        Optional<Course> found = courseRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getTitulo()).isEqualTo("Spring Boot");
    }

    @Test
    void testFindAll() {
        Course course1 = new Course();
        course1.setTitulo("Java 8");
        course1.setDescripcion("Java básico");
        courseRepository.save(course1);

        Course course2 = new Course();
        course2.setTitulo("Spring MVC");
        course2.setDescripcion("Spring MVC básico");
        courseRepository.save(course2);

        List<Course> courses = courseRepository.findAll();

        assertThat(courses).hasSize(2)
                .extracting(Course::getTitulo)
                .containsExactlyInAnyOrder("Java 8", "Spring MVC");
    }

    @Test
    void testDeleteCourse() {
        Course course = new Course();
        course.setTitulo("Hibernate");
        course.setDescripcion("ORM con Hibernate");
        Course saved = courseRepository.save(course);

        courseRepository.delete(saved);

        Optional<Course> found = courseRepository.findById(saved.getId());
        assertThat(found).isEmpty();
    }
}
