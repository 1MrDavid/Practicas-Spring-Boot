package com.cesarlead.DAReports.service;

import com.cesarlead.DAReports.client.CourseFeignClient;
import com.cesarlead.DAReports.client.ProgressFeignClient;
import com.cesarlead.DAReports.client.StudentFeignClient;
import com.cesarlead.DAReports.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService {

    private final CourseFeignClient courseClient;
    private final ProgressFeignClient progressClient;
    private final StudentFeignClient studentClient;

    @Override
    public ReporteAnaliticaDTO generateAnalitics(Long cursoId) {
        log.info("Iniciando genereacion de reporte para curso {}", cursoId);

        // Obtener estudiantes del curso
        List<Long> estudiantesCurso = courseClient
                .getStudentsFromCourse(cursoId)
                .getBody();

        log.debug("Estudiantes inscritos: {}", estudiantesCurso);

        // Obtener eventos del servicio progreso
        List<EventoProgresoDTO> eventosCrudos = progressClient
                .getEventosPorCurso(cursoId)
                .getBody();

        log.debug("Eventos registrados: {}", eventosCrudos);

        // Agrupar eventos por estudiante
        Map<Long, List<EventoProgresoDTO>> eventosPorEstudiante = eventosCrudos.stream()
                .collect(Collectors.groupingBy(EventoProgresoDTO::estudianteId));

        // Procesar metricas por estudiante
        List<EstadisticaEstudianteDTO> estadisticas = new ArrayList<>();
        for (Long estudianteId : estudiantesCurso) {

            List<EventoProgresoDTO> eventosEstudiante = eventosPorEstudiante.getOrDefault(estudianteId, Collections.emptyList());

            // Promedio de calificaciones
            Double promedioCalificaciones = eventosEstudiante.stream()
                    .filter(e -> "EXAMEN_ENTREGADO".equals(e.tipoEvento()))
                    .map(e -> {
                        try {
                            return Double.parseDouble(e.valor());
                        } catch (NumberFormatException ex) {
                            return 0.0;
                        }
                    })
                    .mapToDouble(Double::doubleValue)
                    .average()
                    .orElse(0.0);

            // Total de lecciones vistas
            Long totalLeccionesVistas = eventosEstudiante.stream()
                    .filter(e -> "LECCION_VISTA".equals(e.tipoEvento()))
                    .count();

            // Participación en foros
            Long totalParticipacionForo = eventosEstudiante.stream()
                    .filter(e -> "FORO_POST".equals(e.tipoEvento()))
                    .count();

            // Días desde el último login
            Optional<LocalDateTime> ultimoLogin = eventosEstudiante.stream()
                    .filter(e -> "LOGIN".equals(e.tipoEvento()))
                    .map(EventoProgresoDTO::timestamp)
                    .max(LocalDateTime::compareTo);

            Long diasDesdeUltimoLogin = ultimoLogin.map(l -> ChronoUnit.DAYS.between(l, LocalDateTime.now())).orElse(null);

            // Inferir estado de rendimiento
            String estadoRendimiento;
            if (diasDesdeUltimoLogin != null && diasDesdeUltimoLogin > 20) {
                estadoRendimiento = "EN_RIESGO_POR_INACTIVIDAD";
            } else if (promedioCalificaciones < 5) {
                estadoRendimiento = "BAJO_RENDIMIENTO";
            } else {
                estadoRendimiento = "NORMAL";
            }

            // Enriquecer datos del estudiante
            EstudianteDTO estudianteDetalle = studentClient.getStudentByID(estudianteId);

            EstadisticaEstudianteDTO estadistica = new EstadisticaEstudianteDTO(
                    estudianteId,
                    estudianteDetalle.nombre(),
                    estudianteDetalle.email(),
                    promedioCalificaciones,
                    totalLeccionesVistas,
                    totalParticipacionForo,
                    diasDesdeUltimoLogin,
                    estadoRendimiento
            );

            estadisticas.add(estadistica);
        }

        // Armar DTO final del reporte
        CursoDTO cursoDTO = courseClient.getCourseByID(cursoId).getBody();

        return new ReporteAnaliticaDTO(
                cursoId,
                cursoDTO.titulo(),
                cursoDTO.descripcion(),
                estadisticas
        );
    }
}
