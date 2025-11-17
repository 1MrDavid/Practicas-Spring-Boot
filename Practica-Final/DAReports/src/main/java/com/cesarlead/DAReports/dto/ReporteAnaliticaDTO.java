package com.cesarlead.DAReports.dto;

import java.util.List;

public record ReporteAnaliticaDTO(
        Long cursoId,
        String nombreCurso,
        String descripcionCurso,
        List<EstadisticaEstudianteDTO> estadisticas
) {
}
