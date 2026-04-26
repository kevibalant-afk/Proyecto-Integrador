package com.rosetas.vista;

import com.rosetas.modelo.Producto;
import java.util.List;
import java.util.ArrayList;
public class VistaProducto {

        
        private List<Producto> productos;

    public VistaProducto() {
        this.productos = new ArrayList<>();
    }


    public void mostrarProductos() {
        System.out.println("Lista de Productos:");
        for (Producto producto : this.productos) {
            System.out.println("- " + producto.getNombre());
        }
    }
   
    
    public void registrarProducto(String nombre, String tipo, double precio) {
        Producto producto = new Producto(nombre, tipo, precio);
        this.productos.add(producto);
        System.out.println("Producto registrado: " + producto.getNombre());
    }
    public void mostrarProducto(Producto producto) {
        System.out.println("Producto: " + producto.getNombre());
        System.out.println("Tipo: " + producto.getTipo());
        System.out.println("Precio: " + producto.getPrecio());
    }
}
