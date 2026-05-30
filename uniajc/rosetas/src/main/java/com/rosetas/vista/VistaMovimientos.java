package com.rosetas.vista;

import com.rosetas.modelo.Movimiento;

public class VistaMovimientos {

    public void mostrarMovimiento(Movimiento movimiento) {

        System.out.println("\n------ MOVIMIENTO ------");
        System.out.println("Saldo Anterior: " + movimiento.getSaldoAnterior());
        System.out.println("Monto: " + movimiento.getMonto());
        System.out.println("Saldo Final: " + movimiento.getSaldoFinal());

        if (movimiento.getProducto() != null) {
            System.out.println("Producto: " + movimiento.getProducto().getNombre());
        } else {
            System.out.println("Producto: No asignado");
        }
    }
}