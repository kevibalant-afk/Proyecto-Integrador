package com.rosetas.Dao;


import com.rosetas.modelo.Movimiento;
import com.rosetas.config.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DaoMovimiento {

    public void guardar(Movimiento m) {
        String sql = "INSERT INTO movimiento (saldo_anterior, monto, saldo_final, id_producto) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, m.getSaldoAnterior());
            ps.setDouble(2, m.getMonto());
            ps.setDouble(3, m.getSaldoFinal());
            ps.setInt(4, m.getProducto().getId_Producto());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al guardar movimiento: " + e.getMessage());
        }
    }
}