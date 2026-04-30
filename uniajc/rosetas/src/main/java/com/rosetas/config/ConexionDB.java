package com.rosetas.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/rosetas";
    private static final String USER = "root";
    private static final String PASSWORD = "Dampro01";
    public static Connection conectar() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado correctamente");
            return con;
        } catch (SQLException e) {
            System.out.println(" Error de conexión: " + e.getMessage());
            return null;
        }
    }
}