package com.rosetas.Dao;

import com.rosetas.modelo.Movimiento;
import com.rosetas.modelo.Producto;
import com.rosetas.config.ConexionDB;

import java.sql.*;
import java.util.*;

public class DaoMovimiento {

    public void guardar(Movimiento movimiento) {

      String sql = """
        INSERT INTO movimiento(saldo_anterior, monto, saldo_final, tipo, id_producto, cantidad, precio_venta, costo_unitario)VALUES (?, ?, ?, ?, ?, ?, ?, ?)""";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, movimiento.getSaldoAnterior());
            pstmt.setDouble(2, movimiento.getMonto());
            pstmt.setDouble(3, movimiento.getSaldoFinal());
            pstmt.setString(4, movimiento.getTipo());
            pstmt.setInt(5, movimiento.getProducto().getId());
            pstmt.setDouble(6, movimiento.getCantidad());
            pstmt.setDouble(7, movimiento.getPrecioVenta());
            pstmt.setDouble(8, movimiento.getCostoUnitario());
       

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al guardar movimiento: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<Movimiento> obtenerTodos() {

    List<Movimiento> lista = new ArrayList<>();

    String sql = """
        SELECT
            m.id_movimiento,
            m.saldo_anterior,
            m.monto,
            m.saldo_final,
            m.cantidad,
            m.precio_venta,
            m.costo_unitario,
            m.tipo,
            p.id_producto,
            p.nombre_Producto
        FROM movimiento m
        INNER JOIN producto p
            ON m.id_producto = p.id_producto
        """;

    try (Connection conn = ConexionDB.conectar();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            lista.add(mapearMovimiento(rs));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
}

    public Optional<Movimiento> buscarPorId(int id) {

    String sql = """
        SELECT
            m.id_movimiento,
            m.saldo_anterior,
            m.monto,
            m.saldo_final,
            m.cantidad,
            m.precio_venta,
            m.costo_unitario,
            m.tipo,
            p.id_producto,
            p.nombre_Producto
        FROM movimiento m
        INNER JOIN producto p
            ON m.id_producto = p.id_producto
        WHERE m.id_movimiento = ?
        """;

    try (Connection conn = ConexionDB.conectar();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, id);

        try (ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return Optional.of(mapearMovimiento(rs));
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return Optional.empty();
}

    public void actualizar(Movimiento movimiento) {

    String sql = """
        UPDATE movimiento
        SET saldo_anterior = ?,
            monto = ?,
            saldo_final = ?,
            tipo = ?,
            id_producto = ?,
            cantidad = ?,
            precio_venta = ?,
            costo_unitario = ?
        WHERE id_movimiento = ?
        """;

    try (Connection conn = ConexionDB.conectar();
         PreparedStatement pstmt =
                 conn.prepareStatement(sql)) {

        pstmt.setDouble(1,
                movimiento.getSaldoAnterior());

        pstmt.setDouble(2,
                movimiento.getMonto());

        pstmt.setDouble(3,
                movimiento.getSaldoFinal());

        pstmt.setString(4,
                movimiento.getTipo());

        pstmt.setInt(5,
                movimiento.getProducto().getId());

        pstmt.setDouble(6,
                movimiento.getCantidad());

        pstmt.setDouble(7,
                movimiento.getPrecioVenta());

        pstmt.setDouble(8,
                movimiento.getCostoUnitario());

        pstmt.setInt(9,
                movimiento.getId_Movimiento());
             System.out.println("Actualizando movimiento:");
System.out.println("ID = " + movimiento.getId_Movimiento());
System.out.println("Cantidad = " + movimiento.getCantidad());
System.out.println("Precio = " + movimiento.getPrecioVenta());

int filas = pstmt.executeUpdate();
System.out.println("Filas actualizadas: " + filas);

    } catch (SQLException e) {

        throw new RuntimeException(
                "Error al actualizar movimiento", e);
    }
}
    public void eliminar(int id) {

        String sql = "DELETE FROM movimiento WHERE id_movimiento = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
        }
    }

private Movimiento mapearMovimiento(ResultSet rs) throws SQLException {

    Producto producto = new Producto(
            rs.getInt("id_producto"),
            rs.getString("nombre_Producto"),
            "",
            0
    );

    Movimiento movimiento = new Movimiento();

    movimiento.setId_Movimiento(
            rs.getInt("id_movimiento"));

    movimiento.setSaldoAnterior(
            rs.getDouble("saldo_anterior"));

    movimiento.setMonto(
            rs.getDouble("monto"));

    movimiento.setSaldoFinal(
            rs.getDouble("saldo_final"));

    movimiento.setCantidad(
            rs.getDouble("cantidad"));

    movimiento.setPrecioVenta(
            rs.getDouble("precio_venta"));

    movimiento.setCostoUnitario(
            rs.getDouble("costo_unitario"));

    movimiento.setTipo(
            rs.getString("tipo"));

    movimiento.setProducto(producto);

    return movimiento;
}
    public Map<String, Double> obtenerResumenFinanciero() {

        Map<String, Double> datos = new HashMap<>();

        String sql = """
            SELECT tipo, SUM(saldo_final) as total
            FROM movimiento
            GROUP BY tipo
        """;

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                datos.put(
                    rs.getString("tipo"),
                    rs.getDouble("total")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datos;
    }

    public Map<String, Double> obtenerMovimientosPorProducto() {

    Map<String, Double> datos = new HashMap<>();

    String sql = """
        SELECT
            p.nombre_Producto,
            SUM(m.saldo_final) AS ganancia_total
        FROM movimiento m
        INNER JOIN producto p
            ON m.id_producto = p.id_producto
        GROUP BY p.nombre_Producto
        """;

    try (Connection conn = ConexionDB.conectar();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {

            datos.put(
                    rs.getString("nombre_Producto"),
                    rs.getDouble("ganancia_total")
            );
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return datos;
}

   public Map<String, Integer> obtenerVentasPorProducto() {

    Map<String, Integer> datos = new HashMap<>();

    String sql = """
        SELECT
            p.nombre_Producto,
            COUNT(*) AS total
        FROM movimiento m
        INNER JOIN producto p
            ON m.id_producto = p.id_producto
        WHERE m.tipo = 'INGRESO'
        GROUP BY p.nombre_Producto
        """;

    try (Connection conn = ConexionDB.conectar();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {

            datos.put(
                    rs.getString("nombre_Producto"),
                    rs.getInt("total")
            );
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return datos;
}
}