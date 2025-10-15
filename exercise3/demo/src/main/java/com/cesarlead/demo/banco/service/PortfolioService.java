package com.cesarlead.demo.banco.service;

import com.cesarlead.demo.banco.dto.CuentaDTO;
import com.cesarlead.demo.banco.models.Cuenta;
import org.springframework.stereotype.Service;

import com.cesarlead.demo.banco.repository.PortfolioRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PortfolioService {
    private PortfolioRepository cuentaRepository;

    public PortfolioService(PortfolioRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    private CuentaDTO convertToDTO(Cuenta cuenta) {
        return new CuentaDTO(
                cuenta.getId(),
                cuenta.getTipo(),
                cuenta.getMoneda(),
                cuenta.getSaldo()
        );
    }

    public List<CuentaDTO> getAll() {
        return cuentaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<List<CuentaDTO>> getPortfolioFiltrado(Integer clienteId, String tipoCuenta,
                                                BigDecimal saldoMinimo, String moneda) {
        List<Cuenta> cuentasDelCliente = cuentaRepository.findByClienteId(clienteId);

        if (cuentasDelCliente.isEmpty()) {
            return Optional.empty();
        }

        Stream<Cuenta> stream = cuentasDelCliente.stream();

        // Aplicar filtros opcionales
        if (tipoCuenta != null && !tipoCuenta.isEmpty()) {
            stream = stream.filter(c -> c.getTipo().equalsIgnoreCase(tipoCuenta));
        }
        if (saldoMinimo != null) {
            stream = stream.filter(c -> c.getSaldo().compareTo(saldoMinimo) >= 0);
        }
        if (moneda != null && !moneda.isEmpty()) {
            stream = stream.filter(c -> c.getMoneda().equalsIgnoreCase(moneda));
        }

        List<CuentaDTO> result = stream.map(this::convertToDTO)
                .collect(Collectors.toList());

        return Optional.of(result);
    }
}
