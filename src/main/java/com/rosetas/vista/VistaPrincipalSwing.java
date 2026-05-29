package com.rosetas.vista;

import java.awt.*;
import javax.swing.*;

import com.rosetas.Sesion;
import com.rosetas.Dao.DaoCliente;
import com.rosetas.Dao.DaoMovimiento;
import com.rosetas.Dao.DaoProducto;
import com.rosetas.Dao.DaoUsuario;
import com.rosetas.servicios.ClienteService;
import com.rosetas.servicios.MovimientoService;
import com.rosetas.servicios.ProductoService;
import com.rosetas.servicios.UsuarioService;
import com.rosetas.controlador.ControladorCliente;
import com.rosetas.controlador.ControladorLogin;
import com.rosetas.controlador.ControladorMovimiento;
import com.rosetas.controlador.ControladorProducto;

public class VistaPrincipalSwing extends JFrame {

    private static final Color COLOR_PRIMARY = new Color(153, 95, 46);
    private static final Color COLOR_SIDEBAR = new Color(44, 62, 80);

    private CardLayout cardLayout;
    private JPanel panelContenedor;
    private JPanel sidebar;

    // Vistas
    private VistaLogin vistaLogin;
    private VistaClienteSwing vistaCliente;
    private VistaProductoSwing vistaProducto;
    private VistaMovimientoSwing vistaMovimiento;
    private VistaDashboard vistaDashboard;

    // Servicios
    private MovimientoService movimientoService;
    private ProductoService productoService;
    private ClienteService clienteService;

    public VistaPrincipalSwing() {

        inicializarServicios();
        initUI();
        inicializarMVC();
    }

    private void inicializarServicios() {

        movimientoService =
                new MovimientoService(new DaoMovimiento());

        productoService =
                new ProductoService(new DaoProducto());

        clienteService =
                new ClienteService(new DaoCliente());
    }

    private void initUI() {

        setTitle("Sistema Rosetas");

        ImageIcon icono = new ImageIcon(getClass().getResource("/com/rosetas/img/logo.png"));
        setIconImage(icono.getImage());
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // =====================================
        // HEADER
        // =====================================
       JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(153,95,46));
        headerPanel.setPreferredSize(new Dimension(100,60));

        JLabel lblLogo = new JLabel();

java.net.URL logoURL =
        getClass().getResource("/com/rosetas/img/logo.png");

if (logoURL != null) {

    ImageIcon logo = new ImageIcon(logoURL);

    Image img = logo.getImage().getScaledInstance(
            40,
            40,
            Image.SCALE_SMOOTH);

    lblLogo.setIcon(new ImageIcon(img));

} else {

    System.out.println(
            "No se encontró /com/rosetas/img/logo.png"
    );
}
        lblLogo.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));

        JLabel titulo = new JLabel("ROSETAS");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);

        headerPanel.add(lblLogo, BorderLayout.WEST);
        headerPanel.add(titulo, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);
        // =====================================
        // SIDEBAR
        // =====================================
        sidebar = new JPanel();

        sidebar.setBackground(COLOR_SIDEBAR);

        sidebar.setLayout(
                new GridLayout(6, 1, 10, 10));

        sidebar.setPreferredSize(
                new Dimension(200, 0));

        JButton btnDashboard =
                crearBoton("Dashboard");

        JButton btnClientes =
                crearBoton("Clientes");

        JButton btnProductos =
                crearBoton("Productos");

        JButton btnMovimientos =
                crearBoton("Movimientos");

        JButton btnLogout =
                crearBoton("Cerrar sesión");

        sidebar.add(btnDashboard);
        sidebar.add(btnClientes);
        sidebar.add(btnProductos);
        sidebar.add(btnMovimientos);
        sidebar.add(new JLabel());
        sidebar.add(btnLogout);

        add(sidebar, BorderLayout.WEST);

        // =====================================
        // CONTENEDOR
        // =====================================
        cardLayout = new CardLayout();

        panelContenedor = new JPanel(cardLayout);

        // =====================================
        // VISTAS
        // =====================================
        vistaLogin = new VistaLogin();

        vistaCliente = new VistaClienteSwing();

        vistaProducto = new VistaProductoSwing();

        vistaMovimiento = new VistaMovimientoSwing();

        vistaDashboard =
                new VistaDashboard(movimientoService);

        panelContenedor.add(vistaLogin, "LOGIN");
        panelContenedor.add(vistaDashboard, "DASHBOARD");
        panelContenedor.add(vistaCliente, "CLIENTES");
        panelContenedor.add(vistaProducto, "PRODUCTOS");
        panelContenedor.add(vistaMovimiento, "MOVIMIENTOS");

        add(panelContenedor, BorderLayout.CENTER);

        // =====================================
        // EVENTOS
        // =====================================
        btnDashboard.addActionListener(
                e -> {
                    vistaDashboard.actualizarDashboard();
                    cardLayout.show(
                            panelContenedor,
                            "DASHBOARD");
                });

        btnClientes.addActionListener(
                e -> cardLayout.show(
                        panelContenedor,
                        "CLIENTES"));

        btnProductos.addActionListener(
                e -> cardLayout.show(
                        panelContenedor,
                        "PRODUCTOS"));

        btnMovimientos.addActionListener(
                e -> cardLayout.show(
                        panelContenedor,
                        "MOVIMIENTOS"));

        btnLogout.addActionListener(
                e -> cerrarSesion());

        // =====================================
        // ESTADO INICIAL
        // =====================================
        sidebar.setVisible(false);

        cardLayout.show(
                panelContenedor,
                "LOGIN");
    }

    private JButton crearBoton(String texto) {

        JButton btn = new JButton(texto);

        btn.setFocusPainted(false);

        btn.setForeground(Color.WHITE);

        btn.setBackground(COLOR_SIDEBAR);

        btn.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14));

        btn.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10));

        return btn;
    }

    private void cerrarSesion() {

        int opcion =
                JOptionPane.showConfirmDialog(
                        this,
                        "¿Cerrar sesión?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {

            Sesion.logout();

            vistaLogin.limpiarCampos();

            sidebar.setVisible(false);

            cardLayout.show(
                    panelContenedor,
                    "LOGIN");
        }
    }

    private void inicializarMVC() {

        // LOGIN
        UsuarioService usuarioService =
                new UsuarioService(
                        new DaoUsuario());

        new ControladorLogin(
                vistaLogin,
                usuarioService,
                () -> {

                    sidebar.setVisible(true);

                    vistaDashboard.actualizarDashboard();

                    cardLayout.show(
                            panelContenedor,
                            "DASHBOARD");
                });

        // CLIENTES
        new ControladorCliente(
                vistaCliente,
                clienteService);

        // PRODUCTOS
        new ControladorProducto(
                vistaProducto,
                productoService);

        // MOVIMIENTOS
        new ControladorMovimiento(
                vistaMovimiento,
                movimientoService,
                productoService,
                vistaDashboard);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new VistaPrincipalSwing()
                        .setVisible(true));
    }
}