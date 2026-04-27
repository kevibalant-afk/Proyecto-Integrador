package com.rosetas.controlador;
import java.util.List;

import com.rosetas.modelo.Producto;
import com.rosetas.vista.VistaProducto;
public class ControladorProducto {
    
    private Producto producto;
    private VistaProducto vista;
    public ControladorProducto(VistaProducto vista, List<Producto> productos) {
    this.vista = vista;
}

    public void crearProducto(int id_producto, String nombre, String tipo , double precio) {
        producto = new Producto(id_producto, nombre, tipo, precio);
        vista.mostrarProducto(producto);
    }
    
    public Producto getProducto(int id_Producto) {
        // Mostrar el producto temporal en la vista
        return producto; // Devolver el producto temporal
    }
   // Devolver el producto temporal
    
}
