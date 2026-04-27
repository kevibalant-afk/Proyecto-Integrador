package com.rosetas.vista;

import com.rosetas.modelo.Cliente;
import java.util.List;
import java.util.ArrayList;
import com.rosetas.modelo.Producto;


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
    public void registrarCliente(String nombre, String apellido, String telefono, Producto producto) {
        Cliente cliente = new Cliente(0, nombre, apellido, telefono, producto);
        this.clientes.add(cliente);
        // Aquí podrías agregar el cliente a una lista o base de datos
        System.out.println("Cliente registrado: " + cliente.getNombre());
    }
    public void mostrarCliente(Cliente cliente) {
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Telefono: " + cliente.getTelefono());
        System.out.println("Apellido: " + cliente.getApellido());
        System.out.println("Producto: " + cliente.getProducto());
}
   
    public List<Cliente> getClientes() {
        return clientes;
    }
}