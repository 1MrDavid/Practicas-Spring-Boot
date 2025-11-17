package com.cesarlead.DAReports.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
    String message,
    LocalDateTime timestamp
)
{
}
