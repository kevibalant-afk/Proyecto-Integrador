package com.rosetas.servicios;

import com.rosetas.Dao.DaoMovimiento;
import com.rosetas.modelo.Movimiento;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MovimientoService {

    private final DaoMovimiento daoMovimiento;

    public MovimientoService(DaoMovimiento daoMovimiento) {
        this.daoMovimiento = daoMovimiento;
    }

    public double obtenerGananciaTotal() {

        return daoMovimiento.obtenerTodos()
                .stream()
                .mapToDouble(Movimiento::getSaldoFinal)
                .sum();
    }

    public double obtenerTotalIngresos() {

        return daoMovimiento.obtenerTodos()
                .stream()
                .filter(m ->
                        m.getTipo() != null &&
                        m.getTipo().equalsIgnoreCase("INGRESO"))
                .mapToDouble(Movimiento::getMonto)
                .sum();
    }

    public double obtenerTotalEgresos() {

        return daoMovimiento.obtenerTodos()
                .stream()
                .filter(m ->
                        m.getTipo() != null &&
                        m.getTipo().equalsIgnoreCase("EGRESO"))
                .mapToDouble(Movimiento::getMonto)
                .sum();
    }

    public Map<String, Double> obtenerGananciaPorProducto() {

        Map<String, Double> resultado = new HashMap<>();

        daoMovimiento.obtenerTodos().forEach(m -> {

            if (m.getProducto() == null) {
                return;
            }

            String nombre = m.getProducto().getNombre();

            if (nombre == null) {
                nombre = "SIN PRODUCTO";
            }

            double ganancia = m.getSaldoFinal();

            resultado.put(
                    nombre,
                    resultado.getOrDefault(nombre, 0.0) + ganancia
            );
        });

        return resultado;
    }

    public List<Movimiento> obtenerTodos() {
        return daoMovimiento.obtenerTodos();
    }

    public Optional<Movimiento> buscarPorId(int id) {
        return daoMovimiento.buscarPorId(id);
    }

    public void eliminarMovimiento(int id) {
        daoMovimiento.eliminar(id);
    }

    public void registrarMovimiento(Movimiento movimiento) {

        if (movimiento == null) {
            throw new IllegalArgumentException(
                    "El movimiento no puede ser nulo"
            );
        }

        if (movimiento.getProducto() == null) {
            throw new IllegalArgumentException(
                    "Debe seleccionar un producto"
            );
        }

        validarMovimiento(movimiento);

        daoMovimiento.guardar(movimiento);
    }
    public void actualizarMovimiento(
        Movimiento movimiento) {

    validarMovimiento(movimiento);

    daoMovimiento.actualizar(movimiento);
}
    private void validarMovimiento(Movimiento movimiento) {

        if (movimiento.getMonto() <= 0) {
            throw new IllegalArgumentException(
                    "El monto debe ser mayor que cero"
            );
        }

        if (movimiento.getCantidad() <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor que cero"
            );
        }

        if (movimiento.getPrecioVenta() <= 0) {
            throw new IllegalArgumentException(
                    "El precio de venta debe ser mayor que cero"
            );
        }

        if (movimiento.getTipo() == null ||
                movimiento.getTipo().isBlank()) {

            throw new IllegalArgumentException(
                    "Debe indicar INGRESO o EGRESO"
            );
        }

        if (!movimiento.getTipo().equalsIgnoreCase("INGRESO") &&
            !movimiento.getTipo().equalsIgnoreCase("EGRESO")) {

            throw new IllegalArgumentException(
                    "El tipo debe ser INGRESO o EGRESO"
            );
        }
    }

    public Movimiento obtenerPorId(int id) {
        return daoMovimiento.buscarPorId(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Movimiento no encontrado con ID: " + id
                        )
                );
    }
}