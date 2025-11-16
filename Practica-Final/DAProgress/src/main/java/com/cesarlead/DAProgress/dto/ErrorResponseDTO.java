package com.cesarlead.DAProgress.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
    String message,
    LocalDateTime timestamp
)
{
}
