package com.rosetas.Dao;

import com.rosetas.modelo.Producto;
import com.rosetas.config.ConexionDB;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoProducto {

    public void guardar(Producto producto) {
        String sql = "INSERT INTO Producto (nombre_Producto, tipo, precio) VALUES (?, ?, ?)";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, producto.getNombre());
            pstmt.setString(2, producto.getTipo());
            pstmt.setDouble(3, producto.getPrecio());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al guardar producto: " + e.getMessage());
            throw new RuntimeException("Error en base de datos", e);
        }
    }

    public List<Producto> obtenerTodos() {
        List<Producto> productos = new ArrayList<>();

        String sql = "SELECT id_producto, nombre_Producto, tipo, precio FROM Producto";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar productos: " + e.getMessage());
            throw new RuntimeException("Error en base de datos", e);
        }

        return productos;
    }

    public Optional<Producto> buscarPorid(int texto) {
        String sql = "SELECT id_producto, nombre_Producto, tipo, precio FROM Producto WHERE id_producto = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, texto);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapearProducto(rs));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar producto: " + e.getMessage());
            throw new RuntimeException("Error en base de datos", e);
        }

        return Optional.empty();
    }

    public void actualizar(Producto producto) {
        String sql = "UPDATE Producto SET nombre_Producto = ?, tipo = ?, precio = ? WHERE id_producto = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, producto.getNombre());
            pstmt.setString(2, producto.getTipo());
            pstmt.setDouble(3, producto.getPrecio());
            pstmt.setInt(4, producto.getId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar producto: " + e.getMessage());
            throw new RuntimeException("Error en base de datos", e);
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM Producto WHERE id_producto = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int filas = pstmt.executeUpdate();

            if (filas == 0) {
                throw new RuntimeException("No se encontró el producto con ID: " + id);
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            throw new RuntimeException("Error en base de datos", e);
        }
    }

    private Producto mapearProducto(ResultSet rs) throws SQLException {
        return new Producto(
            rs.getInt("id_producto"),
            rs.getString("nombre_Producto"),
            rs.getString("tipo"),
            rs.getDouble("precio")
        );
    }

  
   
}