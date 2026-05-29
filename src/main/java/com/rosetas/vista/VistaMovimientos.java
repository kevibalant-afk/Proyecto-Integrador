package com.rosetas.vista;

import java.util.List;
import java.util.Scanner;

import com.rosetas.modelo.Movimiento;
import com.rosetas.modelo.Producto;

public class VistaMovimientos {

    private final Scanner scanner;

    public VistaMovimientos() {
        this.scanner = new Scanner(System.in);
    }

    public Movimiento solicitarDatosMovimiento() {

        System.out.println("Ingrese el monto:");
        double monto = scanner.nextDouble();

        System.out.println("Ingrese el saldo anterior:");
        double saldoAnterior = scanner.nextDouble();

        double saldoFinal = saldoAnterior + monto;

        scanner.nextLine(); // limpiar buffer

        System.out.println("Ingrese el nombre del producto:");
        String nombreProducto = scanner.nextLine();

        Producto producto = new Producto(0, nombreProducto, null, 0);

        return new Movimiento(0, saldoAnterior, monto, saldoFinal, 0, 0, 0, nombreProducto, producto);
    }

    public void mostrarMovimiento(Movimiento movimiento) {

        System.out.println("\n------ MOVIMIENTO ------");
        System.out.println("Cantidad: " + movimiento.getSaldoAnterior());
        System.out.println("Precio: " + movimiento.getMonto());
        System.out.println("Ganancia: " + movimiento.getSaldoFinal());

        if (movimiento.getProducto() != null) {
            System.out.println("Producto: " + movimiento.getProducto().getNombre());
        } else {
            System.out.println("Producto: No asignado");
        }
    }

    public void mostrarMovimientos(List<Movimiento> lista) {

        System.out.println("\n==== LISTA DE MOVIMIENTOS ====");

        for (Movimiento m : lista) {
            mostrarMovimiento(m);
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

}