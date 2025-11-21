package com.cesarlead.DAProgress.dto;

import com.cesarlead.DAProgress.config.AppConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Inmutabilidad
public record CursoDTO(
    Long id,

    @NotBlank(message = AppConstant.MSG_TITULO_NOT_BLANK)
    @NotNull(message = AppConstant.MSG_TITULO_REQUIRED)
    String titulo,

    @NotBlank(message = AppConstant.MSG_DESCRIPCION_NOT_BLANK)
    @NotNull(message = AppConstant.MSG_DESCRIPCION_REQUIRED)
    String descripcion
)
{
}
