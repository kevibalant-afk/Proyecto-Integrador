package com.rosetas;

import javax.swing.SwingUtilities;
import com.rosetas.vista.VistaPrincipalSwing;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new VistaPrincipalSwing().setVisible(true);
        });
    }
}