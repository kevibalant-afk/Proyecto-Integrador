package com.rosetas.vista;

import java.util.List;
import java.util.Scanner;

import com.rosetas.modelo.Cliente;
import com.rosetas.modelo.Producto;

public class VistaCliente {

    private final Scanner scanner;

    public VistaCliente() {
        this.scanner = new Scanner(System.in);
    }

    public Cliente solicitarDatosCliente() {
        System.out.println("Ingrese nombre del cliente:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese apellido del cliente:");
        String apellido = scanner.nextLine();

        System.out.println("Ingrese el número de teléfono:");
        String telefono = scanner.nextLine();
        scanner.nextLine(); // limpiar buffer

        System.out.println("Ingrese el producto:");
        String nombreProducto = scanner.nextLine();

        Producto producto = new Producto(0, nombreProducto, null, 0);

        return new Cliente(0, nombre, apellido, telefono, producto);
    }

    public void mostrarCliente(Cliente cliente) {
        System.out.println("\n------ CLIENTE ------");
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nombre: " + cliente.getName());
        System.out.println("Apellido: " + cliente.getLastname());
        System.out.println("Teléfono: " + cliente.getTelefono());

        if (cliente.getProducto() != null) {
            System.out.println("Producto: " + cliente.getProducto().getNombre());
        }
    }

    public void mostrarTodosLosClientes(List<Cliente> clientes) {
        System.out.println("\n==== LISTA DE CLIENTES ====");

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        for (Cliente c : clientes) {
            mostrarCliente(c);
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    
}