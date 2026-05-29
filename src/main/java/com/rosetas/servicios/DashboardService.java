package com.rosetas.servicios;

import com.rosetas.Dao.DaoMovimiento;
import java.util.Map;

public class DashboardService {

    private DaoMovimiento dao;

    public DashboardService(DaoMovimiento dao) {
        this.dao = dao;
    }

    public double obtenerGananciaTotal() {
        return dao.obtenerTodos()
                  .stream()
                  .mapToDouble(m -> m.getSaldoFinal())
                  .sum();
    }

    public double obtenerIngresos() {
        return dao.obtenerTodos()
                  .stream()
                  .filter(m -> m.getTipo().equals("INGRESO"))
                  .mapToDouble(m -> m.getMonto())
                  .sum();
    }

    public double obtenerEgresos() {
        return dao.obtenerTodos()
                  .stream()
                  .filter(m -> m.getTipo().equals("EGRESO"))
                  .mapToDouble(m -> m.getMonto())
                  .sum();
    }

    public Map<String, Double> porProducto() {
        return dao.obtenerMovimientosPorProducto();
    }
}