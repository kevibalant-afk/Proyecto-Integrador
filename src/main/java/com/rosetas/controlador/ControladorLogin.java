package com.rosetas.controlador;

import javax.swing.JOptionPane;
import com.rosetas.Sesion;
import com.rosetas.servicios.UsuarioService;
import com.rosetas.vista.VistaLogin;

public class ControladorLogin {

    private VistaLogin vista;
    private UsuarioService service;
    private Runnable onLoginSuccess;

    public ControladorLogin(VistaLogin vista,
                            UsuarioService service,
                            Runnable onLoginSuccess) {

        this.vista = vista;
        this.service = service;
        this.onLoginSuccess = onLoginSuccess;

        init();
    }

    private void init() {
        vista.getBtnLogin().addActionListener(e -> login());
        vista.getBtnRegistrar().addActionListener(e -> registrar());
    }

    private void login() {

        String user = vista.getUsuario();
        String pass = vista.getPassword();

       if (service.login(user, pass)) {

    Sesion.login(user); 

    JOptionPane.showMessageDialog(null, "Bienvenido " + user);
    onLoginSuccess.run();
}
    }

    private void registrar() {
        try {
            service.registrar(vista.getUsuario(), vista.getPassword());
            JOptionPane.showMessageDialog(null, "Usuario registrado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}