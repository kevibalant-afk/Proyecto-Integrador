package com.rosetas.controlador;


import com.rosetas.modelo.Movimiento;
import com.rosetas.modelo.Producto;
import com.rosetas.Dao.DaoMovimiento;
import com.rosetas.vista.VistaMovimientos;

public class ControladorMovimiento {
   private DaoMovimiento movimientoDAO = new DaoMovimiento();
   private VistaMovimientos vistaMovimientos;

    public ControladorMovimiento(VistaMovimientos vistaMovimientos) {
        this.vistaMovimientos = vistaMovimientos;
    }
public void crearMovimiento(int idMovimiento, double saldoAnterior, double monto, Producto producto) {

    double saldoFinal = saldoAnterior + monto;

    if (saldoFinal < 0) {
        System.out.println("No hay saldo suficiente.");
        return;
    }

    Movimiento movimiento = new Movimiento(idMovimiento, saldoAnterior, monto, saldoFinal, producto);

    movimientoDAO.guardar(movimiento); 

    vistaMovimientos.mostrarMovimiento(movimiento);
}


}