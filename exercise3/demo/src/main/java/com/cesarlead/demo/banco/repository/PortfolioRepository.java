package com.cesarlead.demo.banco.repository;

import com.cesarlead.demo.banco.models.Cuenta;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PortfolioRepository {
    private final List<Cuenta> cuentas = new ArrayList<>();

    public PortfolioRepository() {
        cuentas.add(new Cuenta(1, 101, "Ahorros", "USD", new BigDecimal("1250.75")));
        cuentas.add(new Cuenta(2, 101, "Corriente", "USD", new BigDecimal("5300.00")));
        cuentas.add(new Cuenta(3, 102, "Inversión", "EUR", new BigDecimal("8200.50")));
        cuentas.add(new Cuenta(4, 101, "Ahorros", "EUR", new BigDecimal("250.00")));
        cuentas.add(new Cuenta(5, 103, "Corriente", "USD", new BigDecimal("15000.00")));
    }

    public List<Cuenta> findAll() {
        return new ArrayList<>(cuentas);
    }

    public List<Cuenta> findByClienteId(Integer clienteId) {
        return cuentas.stream()
                .filter(cuenta -> cuenta.getClienteId().equals(clienteId))
                .collect(Collectors.toList());
    }
}
