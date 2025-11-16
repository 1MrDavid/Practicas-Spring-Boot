package com.cesarlead.DAProgress.dto;

import java.time.LocalDateTime;

public record EventoProgresoDTO(
        Long id,
        Long cursoId,
        Long estudianteId,
        String tipoEvento,
        String valor,
        LocalDateTime timestamp
) {
}
