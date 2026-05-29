package com.rosetas.vista;

import com.rosetas.controlador.ControladorMovimiento;
import com.rosetas.modelo.Movimiento;
import com.rosetas.modelo.Producto;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.List;

public class VistaMovimientoSwing extends JPanel {

    private ControladorMovimiento controlador;

    public void setControlador(ControladorMovimiento controlador) {
        this.controlador = controlador;
    }
    private static final Color COLOR_PRIMARY = new Color(153,95,46);
    private static final Color COLOR_SECONDARY = new Color(123,65,16);
    private static final Color COLOR_ACENTO = new Color(231, 76, 60);
    private static final Color COLOR_EXITO = new Color(46, 204, 113);
    private static final Color BLUE = new Color(52, 152, 219);
    private static final String[] COLUMNAS = {
            "ID", "SALDO ANTERIOR", "MONTO", "SALDO FINAL", "TIPO", "CANTIDAD", "PRECIO VENTA", "COSTO UNITARIO", "PRODUCTO"
    };

    private JTable tablaMovimientos;
    private DefaultTableModel modeloTabla;
    private JTextField txtBuscar;

    private JDialog dialogo;

    private JTextField txtSaldoAnterior;
    private JTextField txtMonto;
    private JTextField txtSaldoFinal;
    private JTextField txttipo;
    private JTextField txtProductoId;

    private Movimiento movimientoSeleccionado;
    private boolean editar = false;

    public VistaMovimientoSwing() {
        initComponents();
    }

    private void initComponents() {

        setLayout(new BorderLayout(10, 10));
        setBackground(COLOR_SECONDARY);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        add(panelBusqueda(), BorderLayout.NORTH);
        add(panelTabla(), BorderLayout.CENTER);
        add(panelBotones(), BorderLayout.SOUTH);
    }
    private JComboBox<Producto> comboProductos;
    private JTextField txtPrecioVenta;
    private JTextField txtCantidad;
    private JTextField txtCostoUnitario;
    private JButton botonGuardar;
    public JComboBox<Producto> getComboProductos() { return comboProductos; }
    public JTextField getTxtPrecioVenta() { return txtPrecioVenta; }
    public JTextField getTxtCantidad() { return txtCantidad; }
    public JButton getBotonGuardar() { return botonGuardar; }
    

    private JPanel panelBusqueda() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBackground(COLOR_SECONDARY);

        JLabel labelBuscar = new JLabel("Buscar:");
        labelBuscar.setForeground(COLOR_PRIMARY);

        txtBuscar = new JTextField(25);
        txtBuscar.setPreferredSize(new Dimension(200, 30));

        JButton botonBuscar = crearBoton("Buscar", COLOR_PRIMARY);
        botonBuscar.addActionListener(e -> buscarMovimientos());

        JButton botonLimpiar = crearBoton("Limpiar", new Color(149, 165, 166));
        botonLimpiar.addActionListener(e -> {
            txtBuscar.setText("");
            cargarMovimientos();
        });

        panel.add(labelBuscar);
        panel.add(txtBuscar);
        panel.add(botonBuscar);
        panel.add(botonLimpiar);

        return panel;
    }

    private JPanel panelTabla() {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_SECONDARY);

        modeloTabla = new DefaultTableModel(COLUMNAS, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        tablaMovimientos = new JTable(modeloTabla);
        tablaMovimientos.setRowHeight(25);
        tablaMovimientos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaMovimientos.setSelectionBackground(COLOR_PRIMARY);
        tablaMovimientos.setSelectionForeground(Color.WHITE);

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < tablaMovimientos.getColumnCount(); i++) {
            tablaMovimientos.getColumnModel().getColumn(i).setCellRenderer(center);
        }

        JScrollPane scroll = new JScrollPane(tablaMovimientos);
        scroll.setBorder(BorderFactory.createLineBorder(COLOR_PRIMARY, 2));

        panel.add(scroll);

        return panel;
    }

    private JPanel panelBotones() {

        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(COLOR_SECONDARY);

        JButton registrar = crearBoton("Registrar", COLOR_EXITO);
        registrar.addActionListener(e -> { editar = false;
            mostrarDialogo();
        });

        JButton eliminar = crearBoton("Eliminar", COLOR_ACENTO);
        eliminar.addActionListener(e -> eliminarMovimiento());

        JButton actualizar = crearBoton("Actualizar", COLOR_PRIMARY);
        actualizar.addActionListener(e -> cargarMovimientos());

        JButton editar = crearBoton("Editar", BLUE);
        editar.addActionListener(e -> editarMovimiento());
        
        panel.add(registrar);
        panel.add(eliminar);
        panel.add(actualizar);
        panel.add(editar);
        return panel;
    }

    private JButton crearBoton(String txt, Color c) {
        JButton b = new JButton(txt);
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setPreferredSize(new Dimension(120, 35));
        return b;
    }

    
    public void cargarMovimientos() {
        if (controlador != null) {
            mostrarMovimientos(controlador.obtenerTodos());
        }
    }
public void mostrarMovimientos(List<Movimiento> lista) {

    modeloTabla.setRowCount(0);

    if (lista == null) {
        return;
    }

    for (Movimiento m : lista) {

        modeloTabla.addRow(new Object[]{
                m.getId_Movimiento(),
                m.getSaldoAnterior(),
                m.getMonto(),
                m.getSaldoFinal(),
                m.getTipo(),
                m.getCantidad(),
                m.getPrecioVenta(),
                m.getCostoUnitario(),
                m.getProducto() != null
                        ? m.getProducto().getNombre()
                        : ""
        });
    }
}private void editarMovimiento() {

    int fila = tablaMovimientos.getSelectedRow();

    if (fila < 0) {
        JOptionPane.showMessageDialog(this,
                "Seleccione un movimiento");
        return;
    }

    int id = (int) modeloTabla.getValueAt(fila, 0);

    String cantidad = JOptionPane.showInputDialog(
            this,
            "Nueva cantidad:",
            modeloTabla.getValueAt(fila, 5)
    );

    String precio = JOptionPane.showInputDialog(
            this,
            "Nuevo precio:",
            modeloTabla.getValueAt(fila, 6)
    );

    if (cantidad == null || precio == null) {
        return;
    }

    controlador.editarMovimiento(
            id,
            Double.parseDouble(cantidad),
            Double.parseDouble(precio)
    );
}

    private void buscarMovimientos() {

    String texto = txtBuscar.getText().toLowerCase();

    List<Movimiento> lista =
            controlador.obtenerTodos()
            .stream()
            .filter(m ->
                    m.getProducto() != null
                    && m.getProducto().getNombre() != null
                    && m.getProducto()
                         .getNombre()
                         .toLowerCase()
                         .contains(texto))
            .toList();

    mostrarMovimientos(lista);
}
   private void mostrarDialogo() {

    dialogo = new JDialog(
            (JFrame) SwingUtilities.getWindowAncestor(this),
            true
    );

        dialogo = new JDialog( (JFrame) SwingUtilities .getWindowAncestor(this), editar
                                ? "Editar movimiento"
                                : "Registrar movimiento",
                        true);
    dialogo.setSize(500, 450);
    dialogo.setLocationRelativeTo(this);

    JPanel panel = new JPanel(
            new GridLayout(9, 2, 10, 10)
    );

    txtSaldoAnterior = new JTextField();
    txtMonto = new JTextField();
    txtSaldoFinal = new JTextField();
    txttipo = new JTextField();
    txtProductoId = new JTextField();

    txtCantidad = new JTextField();
    txtPrecioVenta = new JTextField();
    txtCostoUnitario = new JTextField();
    


    panel.add(new JLabel("Saldo Anterior"));
    panel.add(txtSaldoAnterior);

    panel.add(new JLabel("Monto"));
    panel.add(txtMonto);

    panel.add(new JLabel("Saldo Final"));
    panel.add(txtSaldoFinal);

    panel.add(new JLabel("Tipo"));
    panel.add(txttipo);

    panel.add(new JLabel("Cantidad"));
    panel.add(txtCantidad);

    panel.add(new JLabel("Precio Venta"));
    panel.add(txtPrecioVenta);

    panel.add(new JLabel("Costo Unitario"));
    panel.add(txtCostoUnitario);

    panel.add(new JLabel("ID Producto"));
    panel.add(txtProductoId);

    JButton guardar = crearBoton(
            "Guardar",
            COLOR_EXITO
    );

    guardar.addActionListener(
            e -> guardarMovimiento()
    );

    panel.add(new JLabel());
    panel.add(guardar);

    dialogo.add(panel);

    dialogo.setVisible(true);
}
    private void guardarMovimiento() {

    try {

        double monto =
                Double.parseDouble(txtMonto.getText());

        double saldoFinal =
                Double.parseDouble(txtSaldoFinal.getText());

        double saldoAnterior =
                Double.parseDouble(txtSaldoAnterior.getText());
        double cantidad =
                Double.parseDouble(txtCantidad.getText());
        double precioVenta =
                Double.parseDouble(txtPrecioVenta.getText());
        double costoUnitario =
                Double.parseDouble(txtCostoUnitario.getText());

        String tipo =
                txttipo.getText().toUpperCase();

        int idProducto =
                Integer.parseInt(txtProductoId.getText());

        Producto producto = new Producto(
                idProducto,
                null,
                null,
                0
        );
        

        
            controlador.guardarMovimiento(
                    saldoAnterior,
                    monto,
                    saldoFinal,
                    cantidad,
                    precioVenta,
                    costoUnitario,
                    tipo,
                    producto
            );
        

        dialogo.dispose();

        cargarMovimientos();

        JOptionPane.showMessageDialog(
                this,
                "Guardado correctamente"
        );

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Error: " + e.getMessage()
        );

        e.printStackTrace();
    }
}
 
    private void eliminarMovimiento() {

        int fila = tablaMovimientos.getSelectedRow();

        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione uno");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {

            int id = (int) modeloTabla.getValueAt(fila, 0);

            controlador.eliminarMovimiento(id);

            cargarMovimientos();

            JOptionPane.showMessageDialog(this, "Eliminado");
        }
    }
    

    public void mostrarMensaje(String mensaje) {
    JOptionPane.showMessageDialog(this, mensaje);
}
}