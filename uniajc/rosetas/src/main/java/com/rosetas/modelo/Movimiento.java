package com.rosetas.modelo;

public class Movimiento {
    private int id_Movimiento;
    private double saldoAnterior;
    private double monto;
    private double saldoFinal;
    private Producto producto;

    
    public Movimiento(double saldoAnterior, double monto, double saldoFinal, double saldoFinal2, Producto producto) {
        this.saldoAnterior = saldoAnterior;
        this.monto = monto;
        this.saldoFinal = saldoFinal;
        this.producto = producto;
            
        }

    public Movimiento(int idMovimiento, double saldoAnterior2, double monto2, double saldoFinal2, Producto producto2) {
        //TODO Auto-generated constructor stub
    }

    public Producto getProducto() {
        return producto;
    }

    public double calcularSaldo() {
        return saldoAnterior + monto;
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
    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
    public int getId_Movimiento() {
        return id_Movimiento;
    }
    public void setId_Movimiento(int id_Movimiento) {
        this.id_Movimiento = id_Movimiento;
    }
    
}
