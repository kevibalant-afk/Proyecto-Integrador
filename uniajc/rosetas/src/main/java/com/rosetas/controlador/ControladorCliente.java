package com.rosetas.controlador;

import com.rosetas.modelo.Cliente;
import com.rosetas.vista.VistaCliente;
import com.rosetas.modelo.Producto;
public class ControladorCliente {
    
   private Cliente cliente;
    private VistaCliente vista;

    public ControladorCliente(VistaCliente vista) {
        this.vista = vista;
    }
    public void crearCliente( String nombre, String apellido, String telefono, Producto producto) {
        cliente = new Cliente(0, nombre, apellido, telefono, producto);
        vista.mostrarCliente(cliente);

}
   
    public Cliente getCliente() {
        return cliente;
    }
   

}