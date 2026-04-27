package com.rosetas.Dao;

import com.rosetas.modelo.Producto;
import java.util.List;
import java.util.ArrayList;
public class DaoProducto {
private Producto producto;
private List<Producto> productos;

    public DaoProducto(Producto producto) {
        this.producto = producto;
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        System.out.println("Producto agregado: " + producto.getNombre());
    }

    public void guardarProducto() {
        // Aquí puedes implementar la lógica para guardar el producto en una base de datos o archivo
        System.out.println("Guardando producto: " + producto.getNombre());
    }
    public List<Producto> getProductos() {
        return productos;
    }

}
