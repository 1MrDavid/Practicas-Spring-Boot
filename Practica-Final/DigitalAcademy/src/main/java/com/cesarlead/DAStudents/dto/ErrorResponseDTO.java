package com.cesarlead.DAStudents.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
    String message,
    LocalDateTime timestamp
)
{
}
