package com.cesarlead.demo.banco.dto;

import java.math.BigDecimal;

public class CuentaDTO {
    private final Integer id;
    private final String tipo;
    private final String moneda;
    private final BigDecimal saldo;

    public CuentaDTO(Integer id, String tipo, String moneda, BigDecimal saldo) {
        this.id = id;
        this.tipo = tipo;
        this.moneda = moneda;
        this.saldo = saldo;
    }

    public Integer getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMoneda() {
        return moneda;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
}
