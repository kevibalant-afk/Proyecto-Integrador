package com.rosetas.Dao;


import com.rosetas.modelo.Cliente;
import java.util.ArrayList;
public class DaoCliente {
private Cliente cliente;
private ArrayList<Cliente> clientes;

    public DaoCliente(Cliente cliente) {
        this.cliente = cliente;
        this.clientes = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
        System.out.println("Cliente agregado: " + cliente.getNombre());
    }

    public void guardarCliente() {
        // Aquí puedes implementar la lógica para guardar el cliente en una base de datos o archivo
        System.out.println("Guardando cliente: " + cliente.getNombre());
    }
    public Cliente getCliente() {
        return cliente;
    } 
    
    
}
