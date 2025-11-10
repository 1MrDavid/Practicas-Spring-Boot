package com.cesarlead.DAStudents.dto;

import java.time.LocalDateTime;

public record EstudianteDTO(
    Long id,
    String nombre,
    String apellido,
    String email,
    LocalDateTime fechaCreacion) {
}
