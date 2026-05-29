package com.rosetas;

public class Sesion {

    private static String usuarioActual;

    public static void login(String username) {
        usuarioActual = username;
    }

    public static void logout() {
        usuarioActual = null;
    }

    public static boolean estaActiva() {
        return usuarioActual != null;
    }

    public static String getUsuario() {
        return usuarioActual;
    }
    
}
