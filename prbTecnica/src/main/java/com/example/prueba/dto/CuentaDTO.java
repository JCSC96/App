package com.example.prueba.dto;

public class CuentaDTO {
    private Long numeroCuenta;
    private String tipoCuenta;
    private double saldoInicial;
    private String estado;

    // Getters y Setters
    public Long getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(Long numeroCuenta) { this.numeroCuenta = numeroCuenta; }
    public String getTipoCuenta() { return tipoCuenta; }
    public void setTipoCuenta(String tipoCuenta) { this.tipoCuenta = tipoCuenta; }
    public double getSaldoInicial() { return saldoInicial; }
    public void setSaldoInicial(double saldoInicial) { this.saldoInicial = saldoInicial; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}