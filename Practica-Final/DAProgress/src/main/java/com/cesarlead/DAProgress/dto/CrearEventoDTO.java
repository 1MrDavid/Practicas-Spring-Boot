package com.cesarlead.DAProgress.dto;

import com.cesarlead.DAProgress.config.AppConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CrearEventoDTO(
        @NotNull(message = AppConstant.MSG_CURSO_ID_REQUIRED)
        @Positive(message = AppConstant.MSG_CURSO_ID_POSITIVE)
        Long cursoId,

        @NotNull(message = AppConstant.MSG_ESTUDIANTE_ID_REQUIRED)
        @Positive(message = AppConstant.MSG_ESTUDIANTE_ID_POSITIVE)
        Long estudianteId,

        @NotNull(message = AppConstant.MSG_EVENT_REQUIRED)
        @NotBlank(message = AppConstant.MSG_EVENT_NOT_BLANK)
        String tipoEvento,

        String valor
) {
}
