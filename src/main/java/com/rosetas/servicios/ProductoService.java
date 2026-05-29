package com.rosetas.servicios;
import java.util.Optional;
import java.util.List;

import com.rosetas.Dao.DaoProducto;
import com.rosetas.modelo.Producto;

public class ProductoService {

    private final DaoProducto daoProducto;

    public ProductoService(DaoProducto daoproducto){
        this.daoProducto = daoproducto;
    }

    public void registrarProducto(Producto producto){
        if (producto == null){
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }

        validarProducto(producto);
        daoProducto.guardar(producto);
    }

    public List<Producto> obtenerTodosLosProductos(){
        return daoProducto.obtenerTodos();
    }

    public Optional<Producto> buscarProductoPorId(int id){
        return daoProducto.buscarPorid(id);
    }

    public void actualizarProducto(Producto producto){
        if (producto == null){
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }

        if (producto.getId() <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        Optional<Producto> existente = daoProducto.buscarPorid(producto.getId());
        if (existente.isEmpty()) {
            throw new IllegalArgumentException("Producto no encontrado.");
        }

        validarProducto(producto);
        daoProducto.actualizar(producto);
    }

    public void eliminarProducto(int id){
        if (id <= 0) {
            throw new IllegalArgumentException("ID de producto inválido.");
        }

        Optional<Producto> existente = daoProducto.buscarPorid(id);
        if (existente.isEmpty()) {
            throw new IllegalArgumentException("Producto no encontrado con ID: " + id);
        }

        daoProducto.eliminar(id);
    }

    private void validarProducto(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }

        if (producto.getTipo() == null || producto.getTipo().isEmpty()) {
            throw new IllegalArgumentException("El tipo es obligatorio.");
        }

        if (producto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio es obligatorio.");
        }
    }

}