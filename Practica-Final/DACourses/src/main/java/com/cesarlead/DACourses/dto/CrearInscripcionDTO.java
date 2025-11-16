package com.cesarlead.DACourses.dto;

import com.cesarlead.DACourses.config.AppConstant;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CrearInscripcionDTO(
        @NotNull(message = AppConstant.MSG_CURSO_ID_REQUIRED)
        @Positive(message = AppConstant.MSG_CURSO_ID_POSITIVE)
        Long cursoId,

        @NotNull(message = AppConstant.MSG_ESTUDIANTE_ID_REQUIRED)
        @Positive(message = AppConstant.MSG_ESTUDIANTE_ID_POSITIVE)
        Long estudianteId
) {
}
