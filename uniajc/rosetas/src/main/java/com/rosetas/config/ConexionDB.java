package com.rosetas.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionDB {

    private static final String URL = "jdbc:postgresql://ep-wispy-pine-amxvoawj-pooler.c-5.us-east-1.aws.neon.tech:5432/rosetas?sslmode=require";
    private static final String USER = "neondb_owner";
    private static final String PASSWORD = "npg_vuMyGcbBN90X";

    public static Connection conectar() {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado a Neon correctamente");
            return con;
        } catch (Exception e) {
            System.out.println(" Error de conexión: " + e.getMessage());
            return null;
        }
    }
}