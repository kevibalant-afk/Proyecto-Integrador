package com.rosetas.controlador;

import com.rosetas.modelo.Movimiento;
import com.rosetas.modelo.Producto;
import com.rosetas.servicios.MovimientoService;
import com.rosetas.servicios.ProductoService;
import com.rosetas.vista.VistaDashboard;
import com.rosetas.vista.VistaMovimientoSwing;

import java.util.List;

public class ControladorMovimiento {

    private final MovimientoService movimientoService;
    private final ProductoService productoService;
    private final VistaMovimientoSwing vistaMovimiento;
    private final VistaDashboard vistaDashboard;

    public ControladorMovimiento( VistaMovimientoSwing vistaMovimiento, MovimientoService movimientoService, ProductoService productoService, VistaDashboard vistaDashboard) {
        this.vistaMovimiento = vistaMovimiento;
        this.movimientoService = movimientoService;
        this.productoService = productoService;
        this.vistaDashboard = vistaDashboard;

        this.vistaMovimiento.setControlador(this);

        cargarMovimientos();
        actualizarVista();
    }

    public void cargarMovimientos() {

        List<Movimiento> lista = movimientoService.obtenerTodos();

        vistaMovimiento.mostrarMovimientos(lista);
    }


    public void mostrarMovimientos(List<Movimiento> movimientos) {

        if (movimientos == null || movimientos.isEmpty()) {

            vistaMovimiento.mostrarMensaje(
                    "No hay movimientos para mostrar."
            );
            return;
        }

        vistaMovimiento.mostrarMovimientos(movimientos);
    }

    public void guardarMovimiento(
            double saldoAnterior,
            double monto,
            double saldoFinal,
            double cantidad,
            double precioVenta,
            double costoUnitario,
            String tipo,
            Producto producto) {

        try {

            if (producto == null) {
                vistaMovimiento.mostrarMensaje(
                        "Debe seleccionar un producto."
                );
                return;
            }

            if (cantidad <= 0) {
                vistaMovimiento.mostrarMensaje(
                        "La cantidad debe ser mayor que cero."
                );
                return;
            }

            if (precioVenta <= 0) {
                vistaMovimiento.mostrarMensaje(
                        "El precio debe ser mayor que cero."
                );
                return;
            }

            Movimiento movimiento = new Movimiento();

            movimiento.setSaldoAnterior(saldoAnterior);
            movimiento.setMonto(monto);
            movimiento.setSaldoFinal(saldoFinal);
            movimiento.setCantidad(cantidad);
            movimiento.setPrecioVenta(precioVenta);
            movimiento.setCostoUnitario(costoUnitario);
            movimiento.setTipo(tipo);
            movimiento.setProducto(producto);

            movimientoService.registrarMovimiento(movimiento);

            vistaMovimiento.mostrarMensaje(
                    "Movimiento registrado correctamente."
            );

            cargarMovimientos();

            if (vistaDashboard != null) {
                vistaDashboard.actualizarDashboard();
            }
            actualizarVista();
            
        } catch (Exception e) {

            e.printStackTrace();

            vistaMovimiento.mostrarMensaje(
                    "Error: " + e.getMessage()
            );
        }
    }

    private void actualizarVista() {

        List<Movimiento> lista = movimientoService.obtenerTodos();

        vistaMovimiento.mostrarMovimientos(lista);
    }

    public void eliminarMovimiento(int id) {

        try {

            movimientoService.eliminarMovimiento(id);

            vistaMovimiento.mostrarMensaje(
                    "Movimiento eliminado correctamente."
            );
            actualizarVista();
            

            cargarMovimientos();

            if (vistaDashboard != null) {
                vistaDashboard.actualizarDashboard();
            }

        } catch (Exception e) {

            e.printStackTrace();

            vistaMovimiento.mostrarMensaje(
                    "Error al eliminar: " + e.getMessage()
            );
        }
    }
   public void editarMovimiento(
        int id,
        double cantidad,
        double precioVenta) {

    try {

        Movimiento movimiento =
                movimientoService.buscarPorId(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Movimiento no encontrado"));

        movimiento.setCantidad(cantidad);
        movimiento.setPrecioVenta(precioVenta);

        movimiento.setMonto(
                cantidad * precioVenta);

        movimientoService.actualizarMovimiento(
                movimiento);

        cargarMovimientos();

        if (vistaDashboard != null) {
            vistaDashboard.actualizarDashboard();
        }

        vistaMovimiento.mostrarMensaje(
                "Movimiento actualizado correctamente");

    } catch (Exception e) {

        vistaMovimiento.mostrarMensaje(
                "Error: " + e.getMessage());
    }
}
    

    public List<Movimiento> obtenerTodos() {
        return movimientoService.obtenerTodos();
    }

    public List<Producto> obtenerProductos() {
        return productoService.obtenerTodosLosProductos();
    }

    public Movimiento obtenerPorId(int id) {
        return movimientoService.obtenerPorId(id);
    }

    public void guardarMovimiento(Movimiento m) {
        try {

            movimientoService.registrarMovimiento(m);

            vistaMovimiento.mostrarMensaje(
                    "Movimiento registrado correctamente."
            );

            cargarMovimientos();

            if (vistaDashboard != null) {
                vistaDashboard.actualizarDashboard();
            }
            actualizarVista();
            
        } catch (Exception e) {

            e.printStackTrace();

            vistaMovimiento.mostrarMensaje(
                    "Error: " + e.getMessage()
            );
        }
    }
}