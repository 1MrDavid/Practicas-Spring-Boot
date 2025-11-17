package com.cesarlead.DAReports.controller;

import com.cesarlead.DAReports.dto.ReporteAnaliticaDTO;
import com.cesarlead.DAReports.service.ReportService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reportes/curso")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/{cursoId}/analitica")
    public ResponseEntity<ReporteAnaliticaDTO> obtenerReporte(@PathVariable Long cursoId) {
        ReporteAnaliticaDTO resultado = reportService.generateAnalitics(cursoId);
        return ResponseEntity.ok(resultado);
    }
}

