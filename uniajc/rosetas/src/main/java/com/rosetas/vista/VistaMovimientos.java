package com.rosetas.vista;

import com.rosetas.modelo.Movimiento;
import java.util.List;
import java.util.ArrayList;
public class VistaMovimientos {
 private List<Movimiento> movimientos;

    public VistaMovimientos() {
        this.movimientos = new ArrayList<>();
    }

    public void mostrarMovimientos(List<Movimiento> movimientos) {
        for (Movimiento movimiento : movimientos) {
            System.out.println("Saldo Anterior: " + movimiento.getSaldoAnterior());
            System.out.println("Movimiento: " + movimiento.getMovimiento());
            System.out.println("Saldo Final: " + movimiento.getSaldoFinal());
            System.out.println("-------------------------");
        }
    }
    public void registrarMovimiento(double saldoAnterior, double movimiento) {
        Movimiento nuevoMovimiento = new Movimiento(0, saldoAnterior, movimiento, null);
        movimientos.add(nuevoMovimiento);
        System.out.println("Movimiento registrado: Saldo Anterior: " + saldoAnterior + ", Movimiento: " + movimiento + ", Saldo Final: " + nuevoMovimiento.getSaldoFinal());
    }
    public void mostrarMovimiento(Movimiento movimiento) {
        System.out.println("Saldo Anterior: " + movimiento.getSaldoAnterior());
        System.out.println("Movimiento: " + movimiento.getMovimiento());
        System.out.println("Saldo Final: " + movimiento.getSaldoFinal());
    }
      
        
    public List<Movimiento> getMovimientos() {
        return movimientos;
}
}
