package com.cesarlead.DACourses.controller;

import com.cesarlead.DACourses.dto.CursoDTO;
import com.cesarlead.DACourses.service.CourseService;
import com.cesarlead.DACourses.service.EnrollmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CourseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CourseService courseService;

    @Mock
    private EnrollmentService enrollmentService;

    @InjectMocks
    private CourseController courseController;

    private ObjectMapper objectMapper;

    private CursoDTO cursoDTO1;
    private CursoDTO cursoDTO2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
        objectMapper = new ObjectMapper();

        cursoDTO1 = new CursoDTO(1L, "Java Básico", "Curso de Java");
        cursoDTO2 = new CursoDTO(2L, "Spring Boot", "Curso de Spring");
    }

    @Test
    void testGetAllCourses() throws Exception {
        Mockito.when(courseService.getAll()).thenReturn(List.of(cursoDTO1, cursoDTO2));

        mockMvc.perform(get("/api/v1/cursos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].titulo").value("Java Básico"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].titulo").value("Spring Boot"));
    }

    @Test
    void testGetCourseById() throws Exception {
        Mockito.when(courseService.findById(1L)).thenReturn(cursoDTO1);

        mockMvc.perform(get("/api/v1/cursos/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titulo").value("Java Básico"));
    }

    @Test
    void testCreateCourse() throws Exception {
        CursoDTO request = new CursoDTO(null, "Spring Boot Avanzado", "Curso avanzado");
        CursoDTO response = new CursoDTO(3L, "Spring Boot Avanzado", "Curso avanzado");

        Mockito.when(courseService.createCourse(any(CursoDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.titulo").value("Spring Boot Avanzado"));
    }

    @Test
    void testGetStudentsFromCourse() throws Exception {
        List<Long> estudiantes = List.of(1L, 2L, 3L);

        Mockito.when(enrollmentService.findEstudiantesFromCurso(1L)).thenReturn(estudiantes);

        mockMvc.perform(get("/api/v1/cursos/{cursoId}/estudiantes", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[0]").value(1L))
                .andExpect(jsonPath("$[1]").value(2L))
                .andExpect(jsonPath("$[2]").value(3L));
    }
}
