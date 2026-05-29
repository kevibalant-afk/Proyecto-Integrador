package com.rosetas.Dao;

import java.sql.*;
import java.util.Optional;

import com.rosetas.config.ConexionDB;
import com.rosetas.modelo.Usuario;

public class DaoUsuario {

    public void guardar(Usuario usuario) {

        String sql = "INSERT INTO usuario (username, password) VALUES (?, ?)";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Usuario> buscarPorUsername(String username) {

        String sql = "SELECT * FROM usuario WHERE username = ?";

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getString("username"),
                        rs.getString("password")
                );
                return Optional.of(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}