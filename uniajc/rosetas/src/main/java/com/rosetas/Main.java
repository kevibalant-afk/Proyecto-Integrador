package com.rosetas;

import com.rosetas.Dao.DaoProducto;
import com.rosetas.Dao.DaoCliente;
import com.rosetas.controlador.ControladorMovimiento;
import com.rosetas.controlador.ControladorCliente;
import com.rosetas.modelo.Producto;
import com.rosetas.vista.VistaMovimientos;
import com.rosetas.vista.VistaCliente;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== BIENVENIDO A ROSETAS =====");

        Scanner sc = new Scanner(System.in);

        // DAO
        DaoProducto productoDAO = new DaoProducto();

        // Vistas
        VistaMovimientos vistaMovimientos = new VistaMovimientos();
        VistaCliente vistaCliente = new VistaCliente();

        // Controladores
        ControladorMovimiento controladorMovimiento = new ControladorMovimiento(vistaMovimientos);
        ControladorCliente controladorCliente = new ControladorCliente(vistaCliente);

        // Mostrar productos
        List<Producto> productos = productoDAO.listar();

        System.out.println("\nPRODUCTOS DISPONIBLES:");
        for (Producto p : productos) {
            System.out.println(p.getId_Producto() + " - " + p.getNombre() + " ($" + p.getPrecio() + ")");
        }

        // Selección producto
        System.out.print("\nIngrese ID del producto: ");
        int idProducto = sc.nextInt();
        sc.nextLine();

        Producto producto = productoDAO.buscar(idProducto);

        if (producto == null) {
            System.out.println("Producto no encontrado.");
            sc.close();
            return;
        }

        System.out.println("Producto seleccionado: " + producto.getNombre());

        // Datos cliente
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido: ");
        String apellido = sc.nextLine();

        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        // Guardar cliente
        controladorCliente.registrarCliente(0, nombre, apellido, telefono, producto);

        System.out.println("Cliente registrado");

        // Movimiento (compra = egreso)
        double saldoAnterior = producto.getPrecio();
        double monto = -producto.getPrecio(); // egreso

        controladorMovimiento.crearMovimiento(0, saldoAnterior, monto, producto);

        System.out.println("Movimiento registrado correctamente");

        sc.close();
    }
}
