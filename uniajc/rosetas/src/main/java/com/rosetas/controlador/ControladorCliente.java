package com.rosetas.controlador;

import com.rosetas.modelo.Cliente;
import com.rosetas.modelo.Producto;
import com.rosetas.Dao.DaoCliente;
import com.rosetas.vista.VistaCliente;

public class ControladorCliente {

    private DaoCliente clienteDAO;
    private VistaCliente vista;

    public ControladorCliente(VistaCliente vista) {
        this.clienteDAO = new DaoCliente();
        this.vista = vista;
    }

    public void registrarCliente(int id, String nombre, String apellido, String telefono, Producto producto) {

        Cliente cliente = new Cliente(id, nombre, apellido, telefono, producto);

        clienteDAO.guardar(cliente);

        vista.mostrarCliente(cliente);
    }
}