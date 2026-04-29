package com.rosetas.servicios;

import com.rosetas.modelo.Cliente;
import com.rosetas.modelo.Movimiento;
public class RosetasServicio {

    public void mostrarCliente(Cliente cliente) {
        System.out.println("Cliente: " + cliente.getNombre());
     //   System.out.println("Producto: " + cliente.getProducto().getNombre());
    }

    public void procesarMovimiento(Movimiento movimiento) {
        System.out.println("Saldo final: " + movimiento.getSaldoFinal());
    }
}

