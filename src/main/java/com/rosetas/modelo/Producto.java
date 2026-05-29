package com.rosetas.modelo;

public class Producto {
    private int id_Producto;
    private String nombreProducto;
    private String tipo;
    private double precio;

    public Producto(int id_Producto, String nombre_Producto, String tipo, double precio) {
        this.id_Producto = id_Producto;
        this.nombreProducto = nombre_Producto;
        this.tipo = tipo;
        this.precio = precio;
    }

    public int getId() {
        return id_Producto;
    }
    public void setId(int id_Producto) {
        this.id_Producto = id_Producto;
    }

     public Producto(String nombre_Producto, String tipo, double precio) {
        this.nombreProducto = nombre_Producto;
        this.tipo = tipo;
        this.precio = precio;

        
    }

    public String getNombre() {
        return nombreProducto;
    }

    public void setNombre(String nombre_Producto) {
        this.nombreProducto = nombre_Producto;
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

    @Override
public String toString() {
    return nombreProducto; // o nombreProducto
}

    public char[] getId_Producto() {
        return String.valueOf(id_Producto).toCharArray();
    }    

}
