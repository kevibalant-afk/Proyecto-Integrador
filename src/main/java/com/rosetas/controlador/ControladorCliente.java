package com.rosetas.controlador;

import com.rosetas.modelo.Cliente;
import com.rosetas.servicios.ClienteService;
import com.rosetas.vista.VistaClienteSwing;

import java.util.List;
import java.util.Optional;

public class ControladorCliente {

    private final ClienteService clienteService;
    private final VistaClienteSwing vistaCliente;

    public ControladorCliente(VistaClienteSwing vistaCliente, ClienteService clienteService) {
        this.vistaCliente = vistaCliente;
        this.clienteService = clienteService;

        this.vistaCliente.setControlador(this);
        init();
        cargarClientes();
    }

    private void init() {
        List<Cliente> clientes = clienteService.listarClientes();
        vistaCliente.mostrarClientes(clientes);
    }
       public void mostrarClientes(List<Cliente> clientes) {
            if (clientes == null || clientes.isEmpty()) {
                vistaCliente.mostrarMensaje("No hay clientes para mostrar.");
                return;
            }

            vistaCliente.mostrarClientes(clientes);
    }
    public void obtenerClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        vistaCliente.mostrarClientes(clientes);
    }

    private void cargarClientes() {
List<Cliente> clientes = clienteService.listarClientes();
        vistaCliente.mostrarClientes(clientes);
    }

    public void registrarCliente(Cliente cliente) {
        try {
            clienteService.guardarCliente(cliente);
            vistaCliente.mostrarMensaje("Cliente registrado exitosamente.");
            cargarClientes();
        } catch (Exception e) {
            vistaCliente.mostrarMensaje("Error: " + e.getMessage());
        }
    }

    public void eliminarCliente(int id) {
        try {
            clienteService.eliminarCliente(id);
            vistaCliente.mostrarMensaje("Cliente eliminado.");
            cargarClientes();
        } catch (Exception e) {
            vistaCliente.mostrarMensaje("Error al eliminar.");
        }
    }

    public Cliente buscarCliente(int id) {
        Optional<Cliente> cliente = clienteService.buscarClientePorId(id);
        return cliente.orElse(null);
    }

    public void actualizarCliente(Cliente cliente) {
        try {
            clienteService.actualizarCliente(cliente);
            vistaCliente.mostrarMensaje("Cliente actualizado.");
            cargarClientes();
        } catch (Exception e) {
            vistaCliente.mostrarMensaje("Error al actualizar.");
        }
    }
    public List<Cliente> obtenerTodoslosClientes() {
        return clienteService.listarClientes();
    }
}