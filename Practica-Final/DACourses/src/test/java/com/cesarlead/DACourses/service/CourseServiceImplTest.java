package com.cesarlead.DACourses.service;

import com.cesarlead.DACourses.dto.CursoDTO;
import com.cesarlead.DACourses.exception.ResourceNotFoundException;
import com.cesarlead.DACourses.mapper.MapperCourse;
import com.cesarlead.DACourses.model.Course;
import com.cesarlead.DACourses.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private MapperCourse mapper;

    @InjectMocks
    private CourseServiceImpl courseService;

    private Course course;
    private CursoDTO cursoDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        course = new Course();
        course.setId(1L);
        course.setTitulo("Java Básico");
        course.setDescripcion("Curso de Java para principiantes");

        cursoDTO = new CursoDTO(1L, "Java Básico", "Curso de Java para principiantes");
    }

    @Test
    void testFindById_Success() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(mapper.mapToCursoDTO(course)).thenReturn(cursoDTO);

        CursoDTO result = courseService.findById(1L);

        assertThat(result).isNotNull();
        assertThat(result.titulo()).isEqualTo("Java Básico");
        verify(courseRepository).findById(1L);
        verify(mapper).mapToCursoDTO(course);
    }

    @Test
    void testFindById_NotFound() {
        when(courseRepository.findById(2L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> courseService.findById(2L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Curso no encontrado");
        verify(courseRepository).findById(2L);
        verifyNoInteractions(mapper);
    }

    @Test
    void testGetAllCourses() {
        List<Course> courses = List.of(course);
        List<CursoDTO> dtos = List.of(cursoDTO);

        when(courseRepository.findAll()).thenReturn(courses);
        when(mapper.mapToCursoDTO(course)).thenReturn(cursoDTO);

        List<CursoDTO> result = courseService.getAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).titulo()).isEqualTo("Java Básico");
        verify(courseRepository).findAll();
        verify(mapper).mapToCursoDTO(course);
    }

    @Test
    void testCreateCourse() {
        CursoDTO inputDTO = new CursoDTO(null, "Spring Boot", "Curso de Spring Boot");
        Course newCourse = new Course();
        newCourse.setTitulo("Spring Boot");
        newCourse.setDescripcion("Curso de Spring Boot");

        Course savedCourse = new Course();
        savedCourse.setId(2L);
        savedCourse.setTitulo("Spring Boot");
        savedCourse.setDescripcion("Curso de Spring Boot");

        CursoDTO savedDTO = new CursoDTO(2L, "Spring Boot", "Curso de Spring Boot");

        when(courseRepository.save(any(Course.class))).thenReturn(savedCourse);
        when(mapper.mapToCursoDTO(savedCourse)).thenReturn(savedDTO);

        CursoDTO result = courseService.createCourse(inputDTO);

        assertThat(result.id()).isEqualTo(2L);
        assertThat(result.titulo()).isEqualTo("Spring Boot");

        verify(courseRepository).save(any(Course.class));
        verify(mapper).mapToCursoDTO(savedCourse);
    }
}
