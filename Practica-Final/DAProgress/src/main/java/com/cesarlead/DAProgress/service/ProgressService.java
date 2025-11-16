package com.cesarlead.DAProgress.service;

import com.cesarlead.DAProgress.dto.CrearEventoDTO;
import com.cesarlead.DAProgress.dto.EventoProgresoDTO;

import java.util.List;

public interface ProgressService {
    EventoProgresoDTO registrarEvento(CrearEventoDTO request);
    List<EventoProgresoDTO> getEventos(Long cursoId);
}
