package com.cesarlead.DACourses.dto;

import java.time.LocalDateTime;

public record InscripcionDTO(
        Long id,
        Long cursoId,
        Long estudianteId,
        LocalDateTime fechaInscripcion
) {
}
