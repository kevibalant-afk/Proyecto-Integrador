package com.rosetas.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.rosetas.controlador.ControladorProducto;
import com.rosetas.modelo.Producto;

public class VistaProductoSwing extends JPanel {

    // =========================
    // COLORES
    // =========================
  private static final Color COLOR_PRIMARY = new Color(153,95,46);
    

    private static final Color COLOR_SECONDARY =
            new Color(123,65,16);

    private static final Color COLOR_ACENTO =
            new Color(231, 76, 60);

    private static final Color COLOR_EXITO =
            new Color(46, 204, 113);

    // =========================
    // COLUMNAS
    // =========================
    private static final String[] COLUMNAS = {
            "ID",
            "NOMBRE PRODUCTO",
            "TIPO",
            "PRECIO"
    };

    // =========================
    // TABLA
    // =========================
    private JTable tablaProductos;

    private DefaultTableModel modeloTabla;

    // =========================
    // BUSQUEDA
    // =========================
    private JTextField txtBuscar;

    // =========================
    // DIALOGO
    // =========================
    private JDialog dialogo;

    // =========================
    // CAMPOS
    // =========================
    private JTextField txtnombreProducto;

    private JTextField txtTipo;

    private JTextField txtPrecio;

    // =========================
    // VARIABLES
    // =========================
    private Producto productoSeleccionado;

    private boolean modoEdicion = false;

    // =========================
    // CONTROLADOR
    // =========================
    private ControladorProducto controlador;

    // =========================
    // CONSTRUCTOR
    // =========================
    public VistaProductoSwing() {

        initComponents();
    }

    // =========================
    // SET CONTROLADOR
    // =========================
    public void setControlador(
            ControladorProducto controlador) {

        this.controlador = controlador;
    }

    // =========================
    // INIT COMPONENTS
    // =========================
    private void initComponents() {

        setLayout(new BorderLayout(10, 10));

        setBackground(COLOR_SECONDARY);

        setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        add(panelBusqueda(), BorderLayout.NORTH);

        add(panelTabla(), BorderLayout.CENTER);

        add(panelBotones(), BorderLayout.SOUTH);
    }

    // =========================
    // PANEL BUSQUEDA
    // =========================
    private JPanel panelBusqueda() {

        JPanel panel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                10
                        )
                );

        panel.setBackground(COLOR_SECONDARY);

        JLabel labelBuscar =
                new JLabel("Buscar ID:");

        labelBuscar.setForeground(COLOR_PRIMARY);

        txtBuscar = new JTextField(20);

        JButton botonBuscar =
                crearBoton(
                        "Buscar",
                        COLOR_PRIMARY
                );

        botonBuscar.addActionListener(
                e -> buscar()
        );

        JButton botonLimpiar =
                crearBoton(
                        "Limpiar",
                        Color.GRAY
                );

        botonLimpiar.addActionListener(e -> {

            txtBuscar.setText("");

            controlador.actualizarVista();
        });

        panel.add(labelBuscar);

        panel.add(txtBuscar);

        panel.add(botonBuscar);

        panel.add(botonLimpiar);

        return panel;
    }

    // =========================
    // PANEL TABLA
    // =========================
    private JPanel panelTabla() {

        JPanel panel =
                new JPanel(new BorderLayout());

        panel.setBackground(COLOR_SECONDARY);

        modeloTabla =
                new DefaultTableModel(
                        COLUMNAS,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        tablaProductos =
                new JTable(modeloTabla);

        tablaProductos.setRowHeight(25);

        tablaProductos.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        tablaProductos.setSelectionBackground(
                COLOR_PRIMARY
        );

        DefaultTableCellRenderer center =
                new DefaultTableCellRenderer();

        center.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        for (int i = 0;
             i < tablaProductos.getColumnCount();
             i++) {

            tablaProductos
                    .getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(center);
        }

        JScrollPane scroll =
                new JScrollPane(tablaProductos);

        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    // =========================
    // PANEL BOTONES
    // =========================
    private JPanel panelBotones() {

        JPanel panel = new JPanel(new FlowLayout());

        JButton btnRegistrar = crearBoton( "Registrar", COLOR_EXITO); 
        btnRegistrar.addActionListener(e -> { modoEdicion = false;
                 mostrarDialogo(); 
        });

        JButton btnEditar = crearBoton( "Editar", COLOR_PRIMARY);
        btnEditar.addActionListener( e -> editarProducto());

        JButton btnEliminar =
                crearBoton(
                        "Eliminar",
                        COLOR_ACENTO
                );

        btnEliminar.addActionListener(
                e -> eliminarProducto()
        );

        panel.add(btnRegistrar);

        panel.add(btnEditar);

        panel.add(btnEliminar);

        return panel;
    }

    // =========================
    // CREAR BOTON
    // =========================
    private JButton crearBoton(
            String texto,
            Color color) {

        JButton boton =
                new JButton(texto);

        boton.setBackground(color);

        boton.setForeground(Color.WHITE);

        boton.setPreferredSize(
                new Dimension(120, 35)
        );

        return boton;
    }

    // =========================
    // MOSTRAR PRODUCTOS
    // =========================
    public void mostrarTodosLosProductos(
            List<Producto> lista) {

        modeloTabla.setRowCount(0);

        for (Producto producto : lista) {

            modeloTabla.addRow(new Object[]{
                    producto.getId(),
                    producto.getNombre(),
                    producto.getTipo(),
                    producto.getPrecio()
            });
        }
    }

    public void mostrarProducto(
            Producto producto) {

        modeloTabla.setRowCount(0);

        if (producto != null) {

            modeloTabla.addRow(new Object[]{
                    producto.getId(),
                    producto.getNombre(),
                    producto.getTipo(),
                    producto.getPrecio()
            });
        }
    }

  
    private void buscar() {

        try {

            int id =
                    Integer.parseInt(
                            txtBuscar.getText()
                    );

            Producto productoBuscado =
                    controlador.buscarProducto(id);

            mostrarProducto(productoBuscado);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un ID válido"
            );
        }
    }

    // =========================
    // DIALOGO
    // =========================
    private void mostrarDialogo() {

        dialogo = new JDialog( (JFrame) SwingUtilities .getWindowAncestor(this), modoEdicion
                                ? "Editar Producto"
                                : "Registrar Producto",
                        true
                );

        dialogo.setSize(400, 300);

        dialogo.setLocationRelativeTo(this);

        JPanel panel =
                new JPanel(new GridBagLayout());

        GridBagConstraints g =
                new GridBagConstraints();

        g.insets =
                new Insets(10,10,10,10);

        txtnombreProducto =
                new JTextField(15);

        txtTipo =
                new JTextField(15);

        txtPrecio =
                new JTextField(15);

        if (modoEdicion &&
                productoSeleccionado != null) {

            txtnombreProducto.setText(
                    productoSeleccionado.getNombre()
            );

            txtTipo.setText(
                    productoSeleccionado.getTipo()
            );

            txtPrecio.setText(
                    String.valueOf(
                            productoSeleccionado.getPrecio()
                    )
            );
        }

        g.gridx = 0;
        g.gridy = 0;
        panel.add(new JLabel("Nombre:"), g);

        g.gridx = 1;
        panel.add(txtnombreProducto, g);

        g.gridx = 0;
        g.gridy = 1;
        panel.add(new JLabel("Tipo:"), g);

        g.gridx = 1;
        panel.add(txtTipo, g);

        g.gridx = 0;
        g.gridy = 2;
        panel.add(new JLabel("Precio:"), g);

        g.gridx = 1;
        panel.add(txtPrecio, g);

        JButton guardar =
                crearBoton(
                        "Guardar",
                        COLOR_EXITO
                );

        guardar.addActionListener(
                e -> guardarProducto()
        );

        g.gridx = 0;
        g.gridy = 3;
        g.gridwidth = 2;

        panel.add(guardar, g);

        dialogo.add(panel);

        dialogo.setVisible(true);
    }

    // =========================
    // GUARDAR PRODUCTO
    // =========================
    private void guardarProducto() {

        try {

            String nombre =
                    txtnombreProducto.getText();

            String tipo =
                    txtTipo.getText();

            double precio =
                    Double.parseDouble(
                            txtPrecio.getText()
                    );

            if (modoEdicion) {

                productoSeleccionado.setNombre(nombre);

                productoSeleccionado.setTipo(tipo);

                productoSeleccionado.setPrecio(precio);

                controlador.actualizarProducto(
                        productoSeleccionado
                );

            } else {

                Producto producto =
                        new Producto(
                                0,
                                nombre,
                                tipo,
                                precio
                        );

                controlador.registrarProducto(
                        producto
                );
            }

            dialogo.dispose();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + e.getMessage()
            );
        }
    }

    // =========================
    // EDITAR PRODUCTO
    // =========================
    private void editarProducto() {

        int fila =
                tablaProductos.getSelectedRow();

        if (fila < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un producto"
            );

            return;
        }

        int id =
                (int) modeloTabla.getValueAt(fila, 0);

        productoSeleccionado =
                controlador.buscarProducto(id);

        if (productoSeleccionado != null) {

            modoEdicion = true;

            mostrarDialogo();
        }
    }

    // =========================
    // ELIMINAR PRODUCTO
    // =========================
    private void eliminarProducto() {

        int fila =
                tablaProductos.getSelectedRow();

        if (fila < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un producto"
            );

            return;
        }

        int id =
                (int) modeloTabla.getValueAt(
                        fila,
                        0
                );

        controlador.eliminarProducto(id);
    }

    // =========================
    // MENSAJES
    // =========================
    public void mostrarMensaje(String mensaje) {

        JOptionPane.showMessageDialog(
                this,
                mensaje
        );
    }
}