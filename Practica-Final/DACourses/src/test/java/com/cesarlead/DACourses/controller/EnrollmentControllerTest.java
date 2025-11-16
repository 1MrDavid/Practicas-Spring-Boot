package com.cesarlead.DACourses.controller;

import com.cesarlead.DACourses.dto.CrearInscripcionDTO;
import com.cesarlead.DACourses.dto.InscripcionDTO;
import com.cesarlead.DACourses.service.EnrollmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EnrollmentControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Mock
    private EnrollmentService enrollmentService;

    @InjectMocks
    private EnrollmentController enrollmentController;

    private CrearInscripcionDTO requestDTO;
    private InscripcionDTO responseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(enrollmentController).build();
        objectMapper = new ObjectMapper();

        requestDTO = new CrearInscripcionDTO(1L, 2L);
        responseDTO = new InscripcionDTO(1L, 2L, 1L, LocalDateTime.now());
    }

    @Test
    void testCreateEnrollment() throws Exception {
        when(enrollmentService.createEnrollment(any(CrearInscripcionDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/v1/inscripciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.estudianteId").value(1))
                .andExpect(jsonPath("$.cursoId").value(2));

        verify(enrollmentService, times(1)).createEnrollment(any(CrearInscripcionDTO.class));
    }
}
