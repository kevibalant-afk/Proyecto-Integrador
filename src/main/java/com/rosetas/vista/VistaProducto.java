package com.rosetas.vista;

import com.rosetas.modelo.Producto;
import java.util.List;
import java.util.Scanner;

public class VistaProducto {

        private final Scanner scanner;
    

    public VistaProducto() {    
    this.scanner = new Scanner(System.in);
    }
    public Producto solicitarDatosProducto(){
    System.out.println("Ingrese el nombre del produto:");
    String nombreProducto = scanner.nextLine();
    
    System.out.println("Ingrese el tipo de Producto");
    String tipo = scanner.nextLine();

    System.out.println("Ingrese el precio de producto ");
    int precio = scanner.nextInt();

    return new Producto ( 0, nombreProducto, tipo, precio ); 
    }
   public void mostrarProducto(Producto producto){
    System.out.println("\n------ PRODUCTO ------");
        System.out.println("ID: " + producto.getId());
        System.out.println("NombreProducto: " + producto.getNombre());
        System.out.println("Tipo " + producto.getTipo());
        System.out.println("Precio " + producto.getPrecio());
    }
    public void mostrarTodosLosProductos(List<Producto> productos) {
        System.out.println("\n==== LISTA DE PRODUCTOS ====");

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        for (Producto producto : productos) {
            mostrarProducto(producto);
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    

  
}
