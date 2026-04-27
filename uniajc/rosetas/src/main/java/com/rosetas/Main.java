package com.rosetas;


import com.rosetas.controlador.ControladorMovimiento;
import com.rosetas.vista.VistaMovimientos;
import com.rosetas.controlador.ControladorProducto;
import com.rosetas.modelo.Producto;
import com.rosetas.vista.VistaProducto;
import com.rosetas.controlador.ControladorCliente;
import com.rosetas.vista.VistaCliente;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenido a Rosetas");

        Scanner sc = new Scanner(System.in);
        
        
        List<Producto> productos = new ArrayList<>();
       

        productos.add(new Producto(1, "Torta de Chocolate", "libra baja", 120000));
        productos.add(new Producto(2, "Torta de Vainilla", "libra alta", 110000));
        productos.add(new Producto(3, "Torta de Fresa", "libra baja", 115000));

        System.out.println("Productos disponibles:");
        for (Producto producto : productos) {
            System.out.println(producto.getId_Producto() + " - " + producto.getNombre());
        }
        VistaProducto vistaProducto = new VistaProducto();
        VistaMovimientos vistaMovimientos = new VistaMovimientos();
        VistaCliente vistaCliente = new VistaCliente();

        ControladorProducto controladorProducto = new ControladorProducto(vistaProducto, productos);
        ControladorMovimiento controladorMovimiento = new ControladorMovimiento(vistaMovimientos);
        ControladorCliente controladorCliente = new ControladorCliente(vistaCliente);

        
        
        controladorMovimiento.crearMovimiento(1, 450000, -120000, controladorProducto.getProducto(1));

        int opcion;

        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Apellido: ");
                    String apellido = sc.nextLine();

                    System.out.print("Teléfono: ");
                    String telefono = sc.nextLine();
                    sc.nextLine(); // limpiar buffer

                    System.out.print("Producto (id): ");
                    int idProducto = sc.nextInt();
                    sc.nextLine(); // limpiar buffer

                    Producto producto = controladorProducto.getProducto(idProducto);

                    if (producto != null) {
                        controladorCliente.crearCliente(nombre, apellido, telefono, producto);
                        System.out.println("Cliente agregado correctamente.");
                    } else {
                        System.out.println("El producto no existe.");
                    }
                    break;

                case 2:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 2);

        sc.close();
    }
}