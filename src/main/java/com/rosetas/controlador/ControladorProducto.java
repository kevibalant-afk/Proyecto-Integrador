package com.rosetas.controlador;

import java.util.List;
import java.util.Optional;

import com.rosetas.vista.VistaProductoSwing;
import com.rosetas.modelo.Producto;
import com.rosetas.servicios.ProductoService;

public class ControladorProducto {

    private final ProductoService productoService;
    private final VistaProductoSwing vistaProducto;

    public ControladorProducto(VistaProductoSwing vistaProducto,
                               ProductoService productoService) {

        this.vistaProducto = vistaProducto;
        this.productoService = productoService;

        this.vistaProducto.setControlador(this); 

        actualizarVista();
    }

    // =========================
    // REGISTRAR
    // =========================
    public void registrarProducto(Producto producto) {

        try {

            if (producto == null) {
                vistaProducto.mostrarMensaje("El producto es nulo.");
                return;
            }

            if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
                vistaProducto.mostrarMensaje("El nombre es obligatorio.");
                return;
            }

            if (producto.getPrecio() <= 0) {
                vistaProducto.mostrarMensaje("El precio debe ser mayor a 0.");
                return;
            }

            productoService.registrarProducto(producto);

            vistaProducto.mostrarMensaje("Producto registrado exitosamente.");

            actualizarVista();

        } catch (Exception e) {
            e.printStackTrace();
            vistaProducto.mostrarMensaje( "Error: " + e.getMessage());
}
    }

  
    public void actualizarVista() {

        List<Producto> lista = productoService.obtenerTodosLosProductos();

        vistaProducto.mostrarTodosLosProductos(lista);
    }

    // =========================
    // ELIMINAR
    // =========================
    public void eliminarProducto(int id) {

        try {

            productoService.eliminarProducto(id);

            vistaProducto.mostrarMensaje("Producto eliminado.");

            actualizarVista();

        } catch (Exception e) {

            vistaProducto.mostrarMensaje("Error al eliminar.");
        }
    }

    // =========================
    // BUSCAR
    // =========================
    public Producto buscarProducto(int id) {

        Optional<Producto> producto = productoService.buscarProductoPorId(id);

        return producto.orElse(null);
    }

    // =========================
    // ACTUALIZAR
    // =========================
    public void actualizarProducto(Producto producto) {

        try {

            if (producto == null) {
                vistaProducto.mostrarMensaje("Producto inválido.");
                return;
            }

            productoService.actualizarProducto(producto);

            vistaProducto.mostrarMensaje("Producto actualizado.");

            actualizarVista();

        } catch (Exception e) {

            vistaProducto.mostrarMensaje("Error al actualizar.");
        }
    }
}