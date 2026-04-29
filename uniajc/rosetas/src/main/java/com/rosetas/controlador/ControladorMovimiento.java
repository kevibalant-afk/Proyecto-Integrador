package com.rosetas.controlador;


import com.rosetas.modelo.Movimiento;
import com.rosetas.modelo.Producto;
import com.rosetas.vista.VistaMovimientos;

public class ControladorMovimiento {
    private Movimiento movimiento;
    private VistaMovimientos vista;

    public ControladorMovimiento(VistaMovimientos vista) {
        this.vista = vista;
    }

    public void crearMovimiento(int idMovimiento, double saldoAnterior, double monto, Producto producto) {

    double saldoFinal = saldoAnterior + monto;

    if (saldoFinal < 0) {
        System.out.println("No hay saldo suficiente.");
        return;
    }

    this.movimiento = new Movimiento(idMovimiento, saldoAnterior, monto, saldoFinal, producto);

    vista.mostrarMovimiento(this.movimiento); 
}
}