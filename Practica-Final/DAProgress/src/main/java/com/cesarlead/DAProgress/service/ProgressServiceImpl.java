package com.cesarlead.DAProgress.service;

import com.cesarlead.DAProgress.client.CourseServiceClient;
import com.cesarlead.DAProgress.client.EnrollmentServiceClient;
import com.cesarlead.DAProgress.client.StudentServiceClient;
import com.cesarlead.DAProgress.config.AppConstant;
import com.cesarlead.DAProgress.dto.CrearEventoDTO;
import com.cesarlead.DAProgress.dto.EventoProgresoDTO;
import com.cesarlead.DAProgress.mapper.MapperProgress;
import com.cesarlead.DAProgress.model.Progress;
import com.cesarlead.DAProgress.repository.ProgressRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgressServiceImpl implements ProgressService {

    private final StudentServiceClient studentClient;
    private final CourseServiceClient courseClient;
    private final EnrollmentServiceClient enrollmentClient;
    private final ProgressRepository progressRepository;
    // Encapsulamiento (private)
    private final MapperProgress mapper;

    // Polimorfismo
    @Override
    @Transactional
    public EventoProgresoDTO registrarEvento(CrearEventoDTO request) {

        log.info("{} registrarEvento", AppConstant.LOG_ENTERING_METHOD);

        // Usando WebClient para consultas

        // Validar estudiante
        studentClient.getStudentByID(request.estudianteId()).block();

        // Validar curso
        courseClient.getCourseByID(request.cursoId()).block();

        // Validar inscripcion
        enrollmentClient.validateEnrollment(request.cursoId(), request.estudianteId()).block();

        // Crear evento
        Progress progress = new Progress(
                null,
                request.cursoId(),
                request.estudianteId(),
                request.tipoEvento(),
                request.valor(),
                LocalDateTime.now()
        );

        Progress saved = progressRepository.save(progress);

        log.info("{} registrarEvento", AppConstant.LOG_EXITING_METHOD);

        return mapper.mapToEventoProgresoDTO(saved);
    }

    @Override
    public List<EventoProgresoDTO> getEventos(Long cursoId) {

        log.info("{} getEventos", AppConstant.LOG_ENTERING_METHOD);

        // Valida que existe el curso
        courseClient.getCourseByID(cursoId).block();

        List<Progress> eventosCurso = progressRepository.findByCursoId(cursoId);

        List<EventoProgresoDTO> eventosDTO = eventosCurso.stream()
                .map(mapper::mapToEventoProgresoDTO)
                .toList();

        log.info("{} getEventos", AppConstant.LOG_EXITING_METHOD);

        return eventosDTO;

    }
}
