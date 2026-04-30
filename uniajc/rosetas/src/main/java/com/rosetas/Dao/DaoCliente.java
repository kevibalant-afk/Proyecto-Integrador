package com.rosetas.Dao;

import com.rosetas.modelo.Cliente;
import com.rosetas.modelo.Producto;
import com.rosetas.config.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoCliente {

    private Connection conn;

    public DaoCliente() {
        conn = ConexionDB.conectar();
    }

    // ✅ GUARDAR CLIENTE
    public void guardar(Cliente cliente) {

        String sql = "INSERT INTO cliente (nombre, apellido, telefono, id_producto) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getTelefono());

            if (cliente.getProducto() != null) {
                ps.setInt(4, cliente.getProducto().getId_Producto());
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> listar() {

        List<Cliente> lista = new ArrayList<>();

        String sql = "SELECT c.*, p.nombre AS nombre_producto " +
                     "FROM cliente c " +
                     "LEFT JOIN producto p ON c.id_producto = p.id_producto";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Producto producto = null;

                int idProducto = rs.getInt("id_producto");

                if (idProducto != 0) {
                    producto = new Producto(idProducto, sql, sql, idProducto);
                    producto.setId_Producto(idProducto);
                    producto.setNombre(rs.getString("nombre_producto"));
                }

                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono"),
                        producto
                );

                lista.add(cliente);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Cliente buscar(int id) {

        String sql = "SELECT c.*, p.nombre AS nombre_producto " +
                     "FROM cliente c " +
                     "LEFT JOIN producto p ON c.id_producto = p.id_producto " +
                     "WHERE c.id_cliente = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Producto producto = null;

                int idProducto = rs.getInt("id_producto");

                if (idProducto != 0) {
                    producto = new Producto(idProducto, sql, sql, idProducto);
                    producto.setId_Producto(idProducto);
                    producto.setNombre(rs.getString("nombre_producto"));
                }

                return new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono"),
                        producto
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ✅ ELIMINAR CLIENTE
    public void eliminar(int id) {

        String sql = "DELETE FROM cliente WHERE id_cliente = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
