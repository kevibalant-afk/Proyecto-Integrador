package com.rosetas;

import com.rosetas.Dao.DaoProducto;
import com.rosetas.Dao.DaoCliente;
import com.rosetas.Dao.DaoMovimiento;
import com.rosetas.modelo.Cliente;
import com.rosetas.modelo.Producto;
import com.rosetas.controlador.ControladorMovimiento;
import com.rosetas.vista.VistaMovimientos;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Bienvenido a Rosetas");

        Scanner sc = new Scanner(System.in);

        // DAO
        DaoProducto productoDAO = new DaoProducto();
        DaoMovimiento movimientoDAO = new DaoMovimiento();
        DaoCliente clienteDAO = new DaoCliente();

        // Vista y controlador
        VistaMovimientos vistaMovimientos = new VistaMovimientos();
        ControladorMovimiento controladorMovimiento = new ControladorMovimiento(vistaMovimientos);

        // Mostrar productos desde BD
        List<Producto> productos = productoDAO.listar();

        System.out.println("\nProductos disponibles:");
        for (Producto p : productos) {
            System.out.println(p.getId_Producto() + " - " + p.getNombre());
        }

        // Pedir ID producto
        System.out.print("\nIngrese ID del producto: ");
        int idProducto = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        Producto producto = productoDAO.buscar(idProducto);

        if (producto != null) {

            System.out.println("Producto seleccionado: " + producto.getNombre());

            // Crear cliente
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Apellido: ");
            String apellido = sc.nextLine();

            System.out.print("Teléfono: ");
            String telefono = sc.nextLine();

            Cliente cliente = new Cliente(0, nombre, apellido, telefono, producto);
            clienteDAO.guardar(cliente);

            // Crear movimiento
            controladorMovimiento.crearMovimiento(
                    1,
                    0,
                    producto.getPrecio(), // ingreso
                    producto
            );

        } else {
            System.out.println("Producto no encontrado.");
        }

        sc.close();
    }
}


