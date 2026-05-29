package com.rosetas.vista;

import javax.swing.*;
import java.awt.*;

public class VistaLogin extends JPanel {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegistrar;

    public VistaLogin() {

        setLayout(new BorderLayout());
        setBackground(new Color(236,240,241)); // fondo general

        // =========================
        // PANEL CENTRAL (CARD)
        // =========================
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(350, 300));
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200,200,200)),
                BorderFactory.createEmptyBorder(20,20,20,20)
        ));

        // CONTENEDOR PARA CENTRAR
        JPanel contenedor = new JPanel(new GridBagLayout());
        contenedor.setBackground(new Color(236,240,241));
        contenedor.add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // =========================
        // TÍTULO
        // =========================
        JLabel titulo = new JLabel("ROSETAS SYSTEM");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(new Color(153,95,46));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titulo, gbc);

        gbc.gridwidth = 1;

        // =========================
        // USUARIO
        // =========================
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel("Usuario:"), gbc);

        gbc.gridx = 1;
        txtUsuario = new JTextField(15);
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(txtUsuario, gbc);

        // =========================
        // PASSWORD
        // =========================
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        txtPassword = new JPasswordField(15);
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(txtPassword, gbc);

        // =========================
        // BOTÓN LOGIN
        // =========================
        gbc.gridx = 0;
        gbc.gridy++;
        btnLogin = new JButton("Ingresar");
        btnLogin.setBackground(new Color(41,128,185));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        panel.add(btnLogin, gbc);

        // =========================
        // BOTÓN REGISTRO
        // =========================
        gbc.gridx = 1;
        btnRegistrar = new JButton("Registrarse");
        btnRegistrar.setFocusPainted(false);
        panel.add(btnRegistrar, gbc);

        add(contenedor, BorderLayout.CENTER);
    }

    // =========================
    // GETTERS
    // =========================
    public String getUsuario() {
        return txtUsuario.getText();
    }

    public String getPassword() {
        return new String(txtPassword.getPassword());
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void limpiarCampos() {
        txtUsuario.setText("");
        txtPassword.setText("");
    }
}