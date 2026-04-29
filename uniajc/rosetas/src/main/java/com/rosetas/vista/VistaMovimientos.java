package com.rosetas.vista;

import com.rosetas.modelo.Movimiento;
import java.util.List;
import java.util.ArrayList;

public class VistaMovimientos {

    private List<Movimiento> movimientos;

    public VistaMovimientos() {
        this.movimientos = new ArrayList<>();
    }

    public void mostrarMovimiento(Movimiento movimiento) {

    movimientos.add(movimiento);

    System.out.println("------ MOVIMIENTO ------");
    System.out.println("Saldo Anterior: " + movimiento.getSaldoAnterior());
    System.out.println("Monto: " + movimiento.getMonto());
    System.out.println("Saldo Final: " + movimiento.getSaldoFinal());

    if (movimiento.getProducto() != null) {
        System.out.println("Producto: " + movimiento.getProducto().getNombre());
    } else {
        System.out.println("Producto: No asignado");
    }
}

    public void mostrarHistorial() {
        System.out.println("\n--- HISTORIAL DE MOVIMIENTOS ---");
        for (Movimiento m : movimientos) {
            System.out.println("Producto: " + m.getProducto().getNombre()
                    + " | Saldo Final: " + m.getSaldoFinal());
        }
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }
}
