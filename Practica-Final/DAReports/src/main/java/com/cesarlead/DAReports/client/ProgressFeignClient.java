package com.cesarlead.DAReports.client;

import com.cesarlead.DAReports.dto.EventoProgresoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "progress-service",
        url = "${progress-service.url}"
)
public interface ProgressFeignClient {
    @GetMapping("/curso/{cursoId}/eventos")
    public ResponseEntity<List<EventoProgresoDTO>> getEventosPorCurso(@PathVariable Long cursoId);
}
