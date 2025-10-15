package com.cesarlead.demo.banco.controller;

import com.cesarlead.demo.banco.dto.CuentaDTO;
import com.cesarlead.demo.banco.models.Cuenta;
import com.cesarlead.demo.banco.service.PortfolioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/v1/portfolios")
public class PortfolioController {
    private final PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping
    public List<CuentaDTO> getAllCuentas() {
        return portfolioService.getAll();
    }

    @GetMapping("clientes/{clienteId}/cuentas")
    public ResponseEntity<List<CuentaDTO>> getPortfolioFiltrado(
            @PathVariable Integer clienteId,
            @RequestParam(required = false) String tipoCuenta,
            @RequestParam(required = false)BigDecimal saldoMinimo,
            @RequestParam(required = false) String moneda
            ) {
        return portfolioService.getPortfolioFiltrado(clienteId, tipoCuenta, saldoMinimo, moneda)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
