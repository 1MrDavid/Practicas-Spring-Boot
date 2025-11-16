package com.cesarlead.DAProgress.mapper;

import com.cesarlead.DAProgress.dto.EventoProgresoDTO;
import com.cesarlead.DAProgress.model.Progress;
import org.springframework.stereotype.Component;

@Component
public class MapperProgress {
    public EventoProgresoDTO mapToEventoProgresoDTO(Progress eventoProgreso) {
        return new EventoProgresoDTO(
                eventoProgreso.getId(),
                eventoProgreso.getCursoId(),
                eventoProgreso.getEstudianteId(),
                eventoProgreso.getTipoEvento(),
                eventoProgreso.getValor(),
                eventoProgreso.getTimestamp()
        );
    }
}
