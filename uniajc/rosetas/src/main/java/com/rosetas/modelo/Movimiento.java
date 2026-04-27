package com.rosetas.modelo;

public class Movimiento {
    private int id_Movimiento;
    private double saldoAnterior;
    private double movimiento;
    private double saldoFinal;
    private Producto producto;

    public Movimiento(int id_Movimiento, double saldoAnterior, double movimiento, Producto producto) {
        this.id_Movimiento = id_Movimiento;
        this.saldoAnterior = saldoAnterior;
        this.movimiento = movimiento;
        this.saldoFinal = calcularSaldo();
        this.producto = producto;
    }

    public Movimiento(double saldoAnterior, double movimiento, Producto producto) {
        this.saldoAnterior = saldoAnterior;
        this.movimiento = movimiento;
        this.saldoFinal = calcularSaldo();
        this.producto = producto;
    }
        public Movimiento(double saldoAnterior, Producto producto) {
            this.saldoAnterior = saldoAnterior;
            this.producto = producto;
        }
    public Producto getProducto(String nombre) {
        return producto;
    }

    public double calcularSaldo() {
        return saldoAnterior + movimiento;
    }

    public double getSaldoFinal() {
        return saldoFinal;
    }
    public void setSaldoFinal(double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }
    public double getSaldoAnterior() {
        return saldoAnterior;
    }
    public void setSaldoAnterior(double saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }
    public double getMovimiento() {
        return movimiento;
    }
    public void setMovimiento(double movimiento) {
        this.movimiento = movimiento;
    }
    public int getId_Movimiento() {
        return id_Movimiento;
    }
    public void setId_Movimiento(int id_Movimiento) {
        this.id_Movimiento = id_Movimiento;
    }
}
