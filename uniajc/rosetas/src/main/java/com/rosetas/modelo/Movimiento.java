package com.rosetas.modelo;

public class Movimiento {

    private int id_Movimiento;
    private double saldoAnterior;
    private double monto;
    private double saldoFinal;
    private Producto producto;

    public Movimiento(int id_Movimiento, double saldoAnterior, double monto, double saldoFinal, Producto producto) {
        this.id_Movimiento = id_Movimiento;
        this.saldoAnterior = saldoAnterior;
        this.monto = monto;
        this.saldoFinal = saldoFinal;
        this.producto = producto;
    }
    public int getId_Movimiento() {
        return id_Movimiento;
    }

    public double getSaldoAnterior() {
        return saldoAnterior;
    }

    public double getMonto() {
        return monto;
    }

    public double getSaldoFinal() {
        return saldoFinal;
    }

    public Producto getProducto() {
        return producto;
    }
}
