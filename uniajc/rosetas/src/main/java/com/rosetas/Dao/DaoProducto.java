package com.rosetas.Dao;

import com.rosetas.modelo.Producto;
import com.rosetas.config.ConexionDB;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class DaoProducto {

    public void guardar(Producto p) {
        String sql = "INSERT INTO producto VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, p.getId_Producto());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getTipo());
            ps.setDouble(4, p.getPrecio());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al guardar producto: " + e.getMessage());
        }
    }

    public Producto buscar(int id) {
        String sql = "SELECT * FROM producto WHERE id_producto = ?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getString("tipo"),
                        rs.getDouble("precio")
                );
            }

        } catch (Exception e) {
            System.out.println("Error al buscar producto: " + e.getMessage());
        }

        return null;
    }

    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto";

        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getString("tipo"),
                        rs.getDouble("precio")
                ));
            }

        } catch (Exception e) {
            System.out.println("Error al listar: " + e.getMessage());
        }

        return lista;
    }
}