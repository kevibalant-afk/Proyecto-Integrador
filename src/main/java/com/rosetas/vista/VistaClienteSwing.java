package com.rosetas.vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.rosetas.controlador.ControladorCliente;
import com.rosetas.modelo.Cliente;
import com.rosetas.modelo.Producto;

import java.util.List;

public class VistaClienteSwing extends JPanel {

    private static final Color COLOR_PRIMARY = new Color(153,95,46);
    private static final Color COLOR_SECONDARY = new Color(123,65,16);
    private static final Color COLOR_ACENTO = new Color(231, 76, 60);
    private static final Color COLOR_EXITO = new Color(46, 204, 113);

    private static final String[] COLUMNAS = {
            "ID","NOMBRE","APELLIDO","TELÉFONO","PRODUCTO"
    };

    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;

    private JTextField txtBuscar;

    private JDialog dialogo;

    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtProducto;

    private ControladorCliente controlador;

    public VistaClienteSwing() {
        initComponents();
    }

    public void setControlador(ControladorCliente controlador) {
        this.controlador = controlador;
    }

    private void initComponents() {

        setLayout(new BorderLayout(10,10));
        setBackground(COLOR_SECONDARY);
        setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        add(panelBusqueda(), BorderLayout.NORTH);
        add(panelTabla(), BorderLayout.CENTER);
        add(panelBotones(), BorderLayout.SOUTH);
    }

    // ==========================================
    // PANEL BUSQUEDA
    // ==========================================
    private JPanel panelBusqueda() {

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT,10,10));
        panel.setBackground(COLOR_SECONDARY);

        JLabel labelBuscar = new JLabel("Buscar:");
        labelBuscar.setForeground(Color.WHITE);

        txtBuscar = new JTextField(20);

        JButton btnBuscar = crearBoton("Buscar", COLOR_PRIMARY);

        btnBuscar.addActionListener(e -> buscarClientes());

        JButton btnLimpiar = crearBoton("Limpiar", Color.GRAY);

        btnLimpiar.addActionListener(e -> {
            txtBuscar.setText("");
            cargarClientes();
        });

        panel.add(labelBuscar);
        panel.add(txtBuscar);
        panel.add(btnBuscar);
        panel.add(btnLimpiar);

        return panel;
    }

    // ==========================================
    // TABLA
    // ==========================================
    private JPanel panelTabla() {

        JPanel panel = new JPanel(new BorderLayout());

        modeloTabla = new DefaultTableModel(COLUMNAS,0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaClientes = new JTable(modeloTabla);
        tablaClientes.setRowHeight(25);
        tablaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaClientes.setSelectionBackground(COLOR_PRIMARY);

        DefaultTableCellRenderer center =
                new DefaultTableCellRenderer();

        center.setHorizontalAlignment(SwingConstants.CENTER);

        for(int i = 0; i < tablaClientes.getColumnCount(); i++) {

            tablaClientes.getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(center);
        }

        JScrollPane scroll = new JScrollPane(tablaClientes);

        panel.add(scroll);

        return panel;
    }

    // ==========================================
    // BOTONES
    // ==========================================
    private JPanel panelBotones() {

        JPanel panel = new JPanel();

        JButton btnRegistrar =
                crearBoton("Registrar", COLOR_EXITO);

        btnRegistrar.addActionListener(e -> mostrarDialogo());

        JButton btnEliminar =
                crearBoton("Eliminar", COLOR_ACENTO);

        btnEliminar.addActionListener(e -> eliminarCliente());

        JButton btnActualizar =
                crearBoton("Actualizar", COLOR_PRIMARY);

        btnActualizar.addActionListener(e -> cargarClientes());

        panel.add(btnRegistrar);
        panel.add(btnEliminar);
        panel.add(btnActualizar);

        return panel;
    }

    private JButton crearBoton(String texto, Color color) {

        JButton boton = new JButton(texto);

        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);

        boton.setPreferredSize(new Dimension(120,35));

        return boton;
    }

    
    private void mostrarDialogo() {

        dialogo = new JDialog(
                (JFrame) SwingUtilities.getWindowAncestor(this),
                true
        );

        dialogo.setTitle("Registrar Cliente");

        dialogo.setSize(400,300);

        dialogo.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(5,2,10,10));

        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtTelefono = new JTextField();
        txtProducto = new JTextField();

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);

        panel.add(new JLabel("Apellido:"));
        panel.add(txtApellido);

        panel.add(new JLabel("Teléfono:"));
        panel.add(txtTelefono);

        panel.add(new JLabel("ID Producto:"));
        panel.add(txtProducto);

        JButton guardar =
                crearBoton("Guardar", COLOR_EXITO);

        guardar.addActionListener(e -> guardarCliente());

        panel.add(guardar);

        dialogo.add(panel);

        dialogo.setVisible(true);
    }

    // ==========================================
    // GUARDAR
    // ==========================================
    private void guardarCliente() {

        try {

            String nombre = txtNombre.getText();

            String apellido = txtApellido.getText();

            String telefono = getTelefono();

            int id_Producto =
                    Integer.parseInt(txtProducto.getText());

            Producto producto = new Producto(id_Producto, null, null, 0);

            producto.setPrecio(id_Producto);

            Cliente cliente = new Cliente(
                    0,
                    nombre,
                    apellido,
                    telefono,
                    producto
            );

            controlador.registrarCliente(cliente);

            dialogo.dispose();

            cargarClientes();

            mostrarMensaje("Cliente guardado");

        } catch(Exception e) {

            mostrarMensaje("Error: " + e.getMessage());
        }
    }

    // ==========================================
    // MOSTRAR CLIENTES
    // ==========================================
    public void mostrarClientes(List<Cliente> lista) {

        modeloTabla.setRowCount(0);

        for(Cliente c : lista) {

            modeloTabla.addRow(new Object[]{

                    c.getId(),
                    c.getName(),
                    c.getLastname(),
                    c.getTelefono(),

                    c.getProducto() != null
                            ? c.getProducto().getNombre()
                            : "N/A"
            });
        }
    }

    public void cargarClientes() {

        if(controlador != null) {

            mostrarClientes(controlador.obtenerTodoslosClientes());
        }
    }

    // ==========================================
    // BUSCAR
    // ==========================================
    private void buscarClientes() {

        String texto =
                txtBuscar.getText().toLowerCase();

        List<Cliente> lista = controlador
                .obtenerTodoslosClientes()
                .stream()
                .filter(c ->
                        c.getName().toLowerCase().contains(texto)
                )
                .toList();

        mostrarClientes(lista);
    }

    // ==========================================
    // ELIMINAR
    // ==========================================
    private void eliminarCliente() {

        int fila = tablaClientes.getSelectedRow();

        if(fila < 0) {

            mostrarMensaje("Seleccione un cliente");
            return;
        }

        int id =
                (int) modeloTabla.getValueAt(fila,0);

        controlador.eliminarCliente(id);

        cargarClientes();

        mostrarMensaje("Cliente eliminado");
    }

    // ==========================================
    // MENSAJES
    // ==========================================
    public void mostrarMensaje(String mensaje) {

        JOptionPane.showMessageDialog(this,mensaje);
    }

    // ==========================================
    // VALIDACION TELEFONO
    // ==========================================
    public String getTelefono() {

        String telefono =
                txtTelefono.getText().trim();

        if(!telefono.matches("\\d{7,15}")) {

            throw new IllegalArgumentException(
                    "Teléfono inválido"
            );
        }

        return telefono;
    }

    // ==========================================
    // LIMPIAR
    // ==========================================
    public void limpiarCampos() {

        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtProducto.setText("");
    }
}