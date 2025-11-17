package com.cesarlead.DAReports.dto;

public record EstadisticaEstudianteDTO(
        Long estudianteId,
        String nombre,
        String email,

        Double promedioCalificaciones,
        Long totalLeccionesVistas,
        Long totalParticipacionForo,
        Long diasDesdeUltimoLogin,

        String estadoRendimiento
) {
}
