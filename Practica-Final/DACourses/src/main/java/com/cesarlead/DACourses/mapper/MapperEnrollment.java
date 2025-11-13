package com.cesarlead.DACourses.mapper;

import com.cesarlead.DACourses.dto.InscripcionDTO;
import com.cesarlead.DACourses.model.Enrollment;
import org.springframework.stereotype.Component;

@Component
public class MapperEnrollment {
    public InscripcionDTO mapToInscripcionDTO(Enrollment inscripcion) {
        return new InscripcionDTO(
            inscripcion.getId(),
            inscripcion.getCursoId(),
            inscripcion.getEstudianteId(),
            inscripcion.getFechaInscripcion()
        );
    }
}
