package com.rosetas.modelo;

public class Movimiento {
    private double saldoAnterior;
    private double movimiento;
    private double saldoFinal;

    public Movimiento(double saldoAnterior, double movimiento) {
        this.saldoAnterior = saldoAnterior;
        this.movimiento = movimiento;
        this.saldoFinal = calcularSaldo();
    }

    public double calcularSaldo() {
        return saldoAnterior + movimiento;
    }

    public double getSaldoFinal() {
        return saldoFinal;
    }
}
