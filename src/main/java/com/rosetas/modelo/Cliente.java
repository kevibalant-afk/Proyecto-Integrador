package com.rosetas.modelo;

public class Cliente {

    private int idCliente;
    private String nombre;
    private String apellido;
    private String telefono;
    private Producto producto;

    public Cliente() {
    }

    public Cliente(int idCliente, String nombre, String apellido, String telefono, Producto producto) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.producto = producto;
    }

    public int getId() {
        return idCliente;
    }

    public void setId(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getName() {
        return nombre;
    }

    public void setName(String nombre) {
        this.nombre = nombre;
    }

    public String getLastname() {
        return apellido;
    }

    public void setLastname(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}