package com.cesarlead.DAStudents.controller;

import com.cesarlead.DAStudents.dto.CrearEstudianteDTO;
import com.cesarlead.DAStudents.dto.EstudianteDTO;
import com.cesarlead.DAStudents.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// No lo tenia antes
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    // Permite probar los controllers de Spring MVC sin necesidad de levantar un servidor web real.
    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetAllStudents() throws Exception {
        EstudianteDTO student1 = new EstudianteDTO(1L, "Juan", "Perez", "juan@example.com", LocalDateTime.now());
        EstudianteDTO student2 = new EstudianteDTO(2L, "Ana", "Lopez", "ana@example.com", LocalDateTime.now());

        when(studentService.getAll()).thenReturn(Arrays.asList(student1, student2));

        mockMvc.perform(get("/api/v1/student"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[1].nombre").value("Ana"));

        verify(studentService, times(1)).getAll();
    }

    @Test
    void testGetStudentByID() throws Exception {
        EstudianteDTO student = new EstudianteDTO(1L, "Juan", "Perez", "juan@example.com", LocalDateTime.now());

        when(studentService.findById(1L)).thenReturn(student);

        mockMvc.perform(get("/api/v1/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan"));

        verify(studentService, times(1)).findById(1L);
    }

    @Test
    void testCreateStudent() throws Exception {
        CrearEstudianteDTO request = new CrearEstudianteDTO("Juan", "Perez", "juan@example.com");
        EstudianteDTO created = new EstudianteDTO(1L, "Juan", "Perez", "juan@example.com", LocalDateTime.now());

        when(studentService.createStudent(any(CrearEstudianteDTO.class))).thenReturn(created);

        mockMvc.perform(post("/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan"));

        verify(studentService, times(1)).createStudent(any(CrearEstudianteDTO.class));
    }
}