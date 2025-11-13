package com.cesarlead.DACourses.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CrearInscripcionDTO(
        @NotNull(message = "El id del curso es obligatorio")
        @Positive(message = "El ID del curso debe ser un número positivo")
        Long cursoId,

        @NotNull(message = "El id del estudiante es obligatorio")
        @Positive(message = "El ID del estudiante debe ser un número positivo")
        Long estudianteId
) {
}
