package com.cesarlead.DACourses.service;

import com.cesarlead.DACourses.dto.CrearInscripcionDTO;
import com.cesarlead.DACourses.dto.InscripcionDTO;

import java.util.List;

public interface EnrollmentService {
    InscripcionDTO createEnrollment(CrearInscripcionDTO request);

    public List<Long> findEstudiantesFromCurso(Long cursoId);

    void existEnrollment(Long cursoId, Long estudianteId);
}
