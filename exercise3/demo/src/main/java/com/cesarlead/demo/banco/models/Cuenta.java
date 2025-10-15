package com.cesarlead.demo.banco.models;

import java.math.BigDecimal;

public class Cuenta {
    private Integer id;
    private Integer clienteId;
    private String tipo;
    private String moneda;
    private BigDecimal saldo;

    public Cuenta(Integer id, Integer clienteId, String tipo, String moneda, BigDecimal saldo) {
        this.id = id;
        this.clienteId = clienteId;
        this.tipo = tipo;
        this.moneda = moneda;
        this.saldo = saldo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
