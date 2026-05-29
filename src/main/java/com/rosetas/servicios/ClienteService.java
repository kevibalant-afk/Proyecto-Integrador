package com.rosetas.servicios;

import java.util.Optional;
import java.util.List;

import com.rosetas.Dao.DaoCliente;
import com.rosetas.modelo.Cliente;

public class ClienteService {

    private final DaoCliente daoCliente;

    public ClienteService(DaoCliente daoCliente) {
        this.daoCliente = daoCliente;
    }

   
    public List<Cliente> listarClientes() {
        return daoCliente.obtenerTodos();
    }
    
    public void mostrarClientes(List<Cliente> clientes) {
        if (clientes == null || clientes.isEmpty()) {
            System.out.println("No hay clientes para mostrar.");
            return;
        }

        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }
    

    public void guardarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }

        validarCliente(cliente);
        daoCliente.guardar(cliente);
    }

    public Optional<Cliente> buscarClientePorId(int id) {
        return daoCliente.buscarPorId(id);
    }

    public void actualizarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }

        if (cliente.getId() <= 0) {
            throw new IllegalArgumentException("ID de cliente inválido.");
        }

        Optional<Cliente> existente = daoCliente.buscarPorId(cliente.getId());
        if (existente.isEmpty()) {
            throw new IllegalArgumentException("Cliente no encontrado con ID: " + cliente.getId());
        }

        validarCliente(cliente);
        daoCliente.actualizar(cliente);
    }

    public void eliminarCliente(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID de cliente inválido.");
        }

        Optional<Cliente> existente = daoCliente.buscarPorId(id);
        if (existente.isEmpty()) {
            throw new IllegalArgumentException("Cliente no encontrado con ID: " + id);
        }

        daoCliente.eliminar(id);
    }

    // =========================
    // VALIDACIÓN
    // =========================
    private void validarCliente(Cliente cliente) {

        if (cliente.getName() == null || cliente.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }

        if (cliente.getLastname() == null || cliente.getLastname().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido es obligatorio.");
        }

        if (cliente.getTelefono() == null || cliente.getTelefono().trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono es obligatorio.");
        }

        if (!cliente.getTelefono().matches("\\d{7,15}")) {
            throw new IllegalArgumentException("Teléfono inválido (7-15 dígitos).");
        }
    }
}