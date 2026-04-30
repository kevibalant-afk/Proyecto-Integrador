package com.rosetas.vista;

import com.rosetas.modelo.Cliente;
import com.rosetas.modelo.Producto;
import java.util.List;
import java.util.ArrayList;


public class VistaCliente {

    
    private List<Cliente> clientes;
    

    public VistaCliente() {
        this.clientes = new ArrayList<>();
        
    }

    public void mostrarClientes() {
        System.out.println("Lista de Clientes:");
        for (Cliente cliente : clientes) {
            System.out.println("- " + cliente.getNombre());
        }
    }
    public void registrarCliente1(String nombre, String apellido, String telefono, Producto producto) {
        Cliente cliente = new Cliente(0, nombre, apellido, telefono, producto);
        this.clientes.add(cliente);
        // Aquí podrías agregar el cliente a una lista o base de datos
        System.out.println("Cliente registrado: " + cliente.getNombre());
    }
    public void mostrarCliente(Cliente cliente) {
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Apellido: " + cliente.getApellido());
        System.out.println("Teléfono: " + cliente.getTelefono());
        System.out.println("ID del producto: " + cliente.getProducto().getId_Producto());
    }
   
}