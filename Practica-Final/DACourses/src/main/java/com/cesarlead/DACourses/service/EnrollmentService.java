package com.cesarlead.DACourses.service;

import com.cesarlead.DACourses.dto.CrearInscripcionDTO;
import com.cesarlead.DACourses.dto.InscripcionDTO;

public interface EnrollmentService {
    InscripcionDTO createEnrollment(CrearInscripcionDTO request);
}
