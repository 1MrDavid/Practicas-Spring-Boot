package com.cesarlead.DACourses.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoDTO(
    Long id,

    @NotBlank(message = "El titulo no puede estar en blanco")
    @NotNull(message = "El titulo es obligatorio")
    String titulo,

    @NotBlank(message = "La descripcion no puede estar en blanco")
    @NotNull(message = "La descripcion es obligatorio")
    String descripcion
)
{
}
