package com.rosetas.Dao;

import com.rosetas.modelo.Cliente;
import com.rosetas.modelo.Producto;
import com.rosetas.config.ConexionDB;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DaoCliente {

    // =========================
    // GUARDAR (CON ID GENERADO)
    // =========================
    public void guardar(Cliente cliente) {

        String sql = "INSERT INTO clientes (nombre, apellido, telefono, id_producto) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, cliente.getName());
            pstmt.setString(2, cliente.getLastname());
            pstmt.setString(3, cliente.getTelefono());

            if (cliente.getProducto() != null) {
                pstmt.setInt(4, cliente.getProducto().getId());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al guardar cliente: " + e.getMessage());
            throw new RuntimeException("Error en base de datos", e);
        }
    }

    // =========================
    // LISTAR TODOS
    // =========================
    public List<Cliente> obtenerTodos() {

        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT c.id_cliente, c.nombre, c.apellido, c.telefono, " +
                     "p.id_producto, p.nombre_Producto, p.tipo, p.precio " +
                     "FROM clientes c " +
                     "LEFT JOIN producto p ON c.id_producto = p.id_producto";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                clientes.add(mapearCliente(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar clientes: " + e.getMessage());
            throw new RuntimeException("Error en base de datos", e);
        }

        return clientes;
    }

    // =========================
    // BUSCAR POR ID
    // =========================
    public Optional<Cliente> buscarPorId(int id) {

        String sql = "SELECT c.id_cliente, c.nombre, c.apellido, c.telefono, " +
                     "p.id_producto, p.nombre_Producto, p.tipo, p.precio " +
                     "FROM clientes c " +
                     "LEFT JOIN producto p ON c.id_producto = p.id_producto " +
                     "WHERE c.id_cliente = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapearCliente(rs));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar cliente por ID: " + e.getMessage());
            throw new RuntimeException("Error en base de datos", e);
        }

        return Optional.empty();
    }

    // =========================
    // ACTUALIZAR
    // =========================
    public void actualizar(Cliente cliente) {

        String sql = "UPDATE clientes SET nombre = ?, apellido = ?, telefono = ?, id_producto = ? WHERE id_cliente = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getName());
            pstmt.setString(2, cliente.getLastname());
            pstmt.setString(3, cliente.getTelefono());

            if (cliente.getProducto() != null) {
                pstmt.setInt(4, cliente.getProducto().getId());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }

            pstmt.setInt(5, cliente.getId());

            int filas = pstmt.executeUpdate();

            if (filas == 0) {
                throw new RuntimeException("Cliente no encontrado con ID: " + cliente.getId());
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            throw new RuntimeException("Error en base de datos", e);
        }
    }

    // =========================
    // ELIMINAR
    // =========================
    public void eliminar(int id) {

        String sql = "DELETE FROM clientes WHERE id_cliente = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int filas = pstmt.executeUpdate();

            if (filas == 0) {
                throw new RuntimeException("Cliente no encontrado con ID: " + id);
            }

        } catch (SQLException e) {
            System.err.println("Error al eliminar cliente: " + e.getMessage());
            throw new RuntimeException("Error en base de datos", e);
        }
    }

    // =========================
    // MAPEAR RESULTSET
    // =========================
    private Cliente mapearCliente(ResultSet rs) throws SQLException {

        Producto producto = null;

        int idProducto = rs.getInt("id_producto");

        if (!rs.wasNull()) {
            producto = new Producto(idProducto, null, null, idProducto);
            producto.setId(idProducto);
            producto.setNombre(rs.getString("nombre_Producto"));
            producto.setTipo(rs.getString("tipo"));
            producto.setPrecio(rs.getDouble("precio"));
        }

        Cliente cliente = new Cliente();
        cliente.setId(rs.getInt("id_cliente"));
        cliente.setName(rs.getString("nombre"));
        cliente.setLastname(rs.getString("apellido"));
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setProducto(producto);

        return cliente;
    }
}