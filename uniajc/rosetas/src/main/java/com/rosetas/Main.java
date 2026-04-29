package com.rosetas;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.rosetas.controlador.ControladorCliente;
import com.rosetas.controlador.ControladorMovimiento;
import com.rosetas.vista.VistaMovimientos;
import com.rosetas.controlador.ControladorProducto;
import com.rosetas.modelo.Producto;
import com.rosetas.vista.VistaCliente;
import com.rosetas.vista.VistaProducto;


public class Main {
    /**
     * @param args
     */
    public static void main(final String[] args) {
        System.out.println("Bienvenido a Rosetas");

        try (Scanner sc = new Scanner(System.in)) {
            
            final List<Producto> productos = new ArrayList<>();
            productos.add(new Producto(1, "Torta de Chocolate", "libra baja", 120000));
            productos.add(new Producto(2, "Torta de Vainilla", "libra alta", 110000));
            productos.add(new Producto(3, "Torta de Fresa", "libra baja", 115000));

            System.out.println("Productos disponibles:");
            for (final Producto producto : productos) {
                System.out.println(producto.getId_Producto() + " - " + producto.getNombre());
            }
            final VistaProducto vistaProducto = new VistaProducto();
            final VistaMovimientos vistaMovimientos = new VistaMovimientos();
            final VistaCliente vistaCliente = new VistaCliente();

            final ControladorProducto controladorProducto = new ControladorProducto(vistaProducto, productos);
            final ControladorMovimiento controladorMovimiento = new ControladorMovimiento(vistaMovimientos);
            final ControladorCliente controladorCliente = new ControladorCliente(vistaCliente);
 
            int opcion;

            do {
                System.out.println("\n--- MENÚ ---");
                System.out.println("1. Agregar cliente");
                System.out.println("2. Salir");
                System.out.println("3. Registrar movimiento");
                System.out.print("Seleccione una opción: ");
                opcion = sc.nextInt();
                sc.nextLine(); // limpiar buffer

                switch (opcion) {
                    case 1:
                        
                        System.out.print("Nombre: ");
                        final String nombre = sc.nextLine();

                        System.out.print("Apellido: ");
                        final String apellido = sc.nextLine();

                        System.out.print("Teléfono: ");
                        final String telefono = sc.nextLine();
                        System.out.print("ID del producto que desea comprar: ");
                        final int id_Producto = sc.nextInt();

                        controladorCliente.crearCliente(0, nombre, apellido, telefono, id_Producto);
                        
                        break;
                        
                      
                      
                    

                    case 2:
                        System.out.println("Saliendo del sistema...");
                        break;

                    case 3:
                        System.out.print("idProducto: ");
                        final int idProd = sc.nextInt();
                        sc.nextLine();

                        final Producto prod = controladorProducto.getProducto(idProd);

                        if (prod == null) {
                            System.out.println("Producto no encontrado");
                            break;
                        }

                        System.out.print("Saldo anterior: ");
                        final double saldoAnterior = sc.nextDouble();

                        System.out.print("Monto del movimiento: ");
                        double monto = sc.nextDouble();

                        System.out.print("Tipo (1 = ingreso, 2 = egreso): ");
                        final int tipo = sc.nextInt();
                        sc.nextLine();

                        if (tipo == 2) {
                            monto = -monto; // egreso
                        }

                        controladorMovimiento.crearMovimiento(0, saldoAnterior, monto, prod);

                        break;

                    default:
                        System.out.println("Opción inválida.");
                }

                
            } while (opcion != 2);
        }
        // Scanner automatically closed here
    }
}