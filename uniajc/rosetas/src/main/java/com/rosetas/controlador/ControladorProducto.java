package com.rosetas.controlador;
import java.util.List;
import com.rosetas.Dao.DaoProducto;
import com.rosetas.modelo.Producto;
import com.rosetas.vista.VistaProducto;

public class ControladorProducto {

    private DaoProducto productoDAO = new DaoProducto();
    private VistaProducto vista;

    public ControladorProducto(VistaProducto vista) {
        this.vista = vista;
    }

    public void registrarProducto(int id_producto, String nombre, String tipo, double precio) {
        Producto producto = new Producto(id_producto, nombre, tipo, precio);
        productoDAO.guardar(producto); 
        vista.registrarProducto(id_producto, nombre, tipo, precio);
    }

    public void mostrarProductos() {
        List<Producto> productos = productoDAO.obtenerTodos();
        for (Producto producto : productos) {
            vista.mostrarProducto(producto);
        }
    }
    


}