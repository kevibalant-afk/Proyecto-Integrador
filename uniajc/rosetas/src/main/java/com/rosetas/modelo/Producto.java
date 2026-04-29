package com.rosetas.modelo;

public class Producto {
    private int id_Producto;
    private String nombre;
    private String tipo;
    private double precio;

    public Producto(int id_Producto, String nombre, String tipo, double precio) {
        this.id_Producto = id_Producto;
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
    }

    public int getId_Producto() {
        return id_Producto;
    }
    public void setId_Producto(int id_Producto) {
        this.id_Producto = id_Producto;
    }

     public Producto(String nombre, String tipo, double precio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;

        
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

}
