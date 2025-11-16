package com.cesarlead.DAProgress.dto;

import java.time.LocalDateTime;

public record EstudianteDTO(
    Long id,
    String nombre,
    String apellido,
    String email,
    LocalDateTime fechaCreacion
)
{
}
