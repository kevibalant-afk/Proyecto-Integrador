package com.rosetas.controlador;
import java.util.List;
import com.rosetas.modelo.Producto;
import com.rosetas.vista.VistaProducto;

public class ControladorProducto {

    private List<Producto> productos;
    private VistaProducto vista;

    public ControladorProducto(VistaProducto vista, List<Producto> productos) {
        this.vista = vista;
        this.productos = productos; 
    }

    // ControladorProducto
public Producto getProducto(int idProducto) {
    for (Producto p : productos) {
        if (p.getId_Producto() == idProducto) {
            return p;
        }
    }
    return null;
// 
    }

    public void mostrarProductos() {
        for (Producto p : productos) {
            vista.mostrarProducto(p);
        }
    }
}