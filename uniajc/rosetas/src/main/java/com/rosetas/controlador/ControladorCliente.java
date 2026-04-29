package com.rosetas.controlador;

import com.rosetas.modelo.Cliente;
import com.rosetas.vista.VistaCliente;

public class ControladorCliente {
    
   private Cliente cliente;
    private VistaCliente vista;

    public ControladorCliente(VistaCliente vista) {
        this.vista = vista;
    }
    public void crearCliente( int id_Cliente, String nombre, String apellido, String telefono, int id_Producto) {
        cliente = new Cliente( id_Cliente, nombre, apellido, telefono, id_Producto);
        vista.mostrarCliente(cliente);
    }
    public void registrarCliente( int id_Cliente, String nombre, String apellido, String telefono, int id_Producto) {
         new Cliente( id_Cliente, nombre, apellido, telefono, id_Producto);

    }
    public void mostrarClientes() {
        vista.mostrarClientes();
    }
    public Cliente getCliente() {
        return cliente;

    }

    

}