package com.rosetas.Dao;


import com.rosetas.modelo.Cliente;
import com.rosetas.modelo.Producto;
import com.rosetas.config.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;
public class DaoCliente {

    
    public void guardar(Cliente cliente) {
        

        String sql = "INSERT INTO cliente (nombre, apellido, telefono, id_producto) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getTelefono());
            ps.setInt(4, cliente.getProducto().getId_Producto());

            ps.executeUpdate();

            System.out.println("Cliente guardado en BD");

        } catch (Exception e) {
            System.out.println(" Error al guardar cliente: " + e.getMessage());
        }
    }

    //
    public Cliente buscar(int id_Cliente) {

        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id_Cliente);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                //Solo traemos el id del producto (puedes mejorarlo luego con JOIN)
                Producto producto = new Producto(
                        rs.getInt("id_producto"),
                        "Producto desconocido",
                        "",
                        0
                );
                return new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono"),
                        producto
                );

               
            }

        } catch (Exception e) {
            System.out.println(" Error al buscar cliente: " + e.getMessage());
        }

        return null;
    }

    public List<Cliente> listar() {

        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Producto producto = new Producto(
                        rs.getInt("id_producto"),
                        "Producto desconocido",
                        "",
                        0
                );

                Cliente cliente = new Cliente(
                        rs.getInt("id_cliente"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono"),
                        producto
                );

                lista.add(cliente);
            }

        } catch (Exception e) {
            System.out.println("Error al listar clientes: " + e.getMessage());
        }

        return lista;
    }

    public void eliminar(int idCliente) {

        String sql = "DELETE FROM cliente WHERE id_cliente = ?";

        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ps.executeUpdate();

            System.out.println(" Cliente eliminado");

        } catch (Exception e) {
            System.out.println(" Error al eliminar: " + e.getMessage());
        }
    }
}
