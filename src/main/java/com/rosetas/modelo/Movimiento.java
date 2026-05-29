package com.rosetas.modelo;

public class Movimiento {

    private int id_Movimiento;
    private double saldoAnterior;
    private double monto;
    private double saldoFinal;
    private double cantidad;
    private double precioVenta;
    private double costoUnitario;
    private String tipo; // INGRESO o EGRESO
    private Producto producto;

    public Movimiento(int id_Movimiento, double saldoAnterior, double monto, double saldoFinal, double cantidad, double precioVenta, double costoUnitario, String tipo, Producto producto) {
        this.id_Movimiento = id_Movimiento;
        this.saldoAnterior = saldoAnterior;
        this.monto = monto;
        this.saldoFinal = saldoFinal;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.costoUnitario = costoUnitario;
        this.tipo = tipo;
        this.producto = producto;
    }
    public Movimiento() {
    }
    public int getId_Movimiento() {
        return id_Movimiento;
    }
    public void setId_Movimiento(int id_Movimiento) {
        this.id_Movimiento = id_Movimiento;
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

    public double getSaldoFinal() {
        return saldoFinal;
    }
    public void setSaldoFinal(double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public double getCantidad() { 
        return cantidad; }

    public void setCantidad(double cantidad) { 
        this.cantidad = cantidad; 
    }

    public double getPrecioVenta() {
         return precioVenta;
         }
    public void setPrecioVenta(double precioVenta) { 
        this.precioVenta = precioVenta;
     }

    public double getCostoUnitario() { 
        return costoUnitario;
     }
    public void setCostoUnitario(double costoUnitario) { 
        this.costoUnitario = costoUnitario; }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    @Override
public String toString() {
    return "Movimiento{" +
            "id=" + id_Movimiento +
            ", cantidad=" + cantidad +
            ", precioVenta=" + precioVenta +
            ", saldoFinal=" + saldoFinal +
            ", tipo='" + tipo + '\'' +
            '}';
}
}
