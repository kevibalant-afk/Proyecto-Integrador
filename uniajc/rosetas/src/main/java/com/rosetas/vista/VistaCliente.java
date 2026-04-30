package com.rosetas.vista;

import com.rosetas.modelo.Cliente;

public class VistaCliente {

    public void mostrarCliente(Cliente cliente) {

        System.out.println("\n------ CLIENTE ------");
        System.out.println("Nombre: " + cliente.getNombre());
        System.out.println("Apellido: " + cliente.getApellido());
        System.out.println("Teléfono: " + cliente.getTelefono());

        if (cliente.getProducto() != null) {
            System.out.println("Producto asociado: " + cliente.getProducto().getNombre());
        }
    }
   
}