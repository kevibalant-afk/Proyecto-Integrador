package com.rosetas;

import com.rosetas.modelo.Cliente;
import com.rosetas.modelo.Movimiento;
import com.rosetas.modelo.Producto;
import com.rosetas.servicios.RosetasServicio;

public class Main {
    public static void main(String[] args) {

        Producto producto = new Producto("Roseta Dorada", "Dorada", 100.0);
        Cliente cliente = new Cliente("Kevin", producto);

        Movimiento movimiento = new Movimiento(1000, -200);

        RosetasServicio service = new RosetasServicio();

        service.mostrarCliente(cliente);
        service.procesarMovimiento(movimiento);
    }
}