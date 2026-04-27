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
    public void crearMovimiento(int id_movimiento, double saldoAnterior, double movimiento, Producto producto) {
        this.movimiento = new Movimiento(id_movimiento, saldoAnterior, movimiento, producto);
        vista.mostrarMovimiento(this.movimiento);

}
    public Movimiento getMovimiento() {
        return movimiento;
    }
   }
