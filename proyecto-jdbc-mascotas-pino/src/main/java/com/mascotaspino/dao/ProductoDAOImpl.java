package com.mascotaspino.dao;

import com.mascotaspino.conexion.ConexionBD;
import com.mascotaspino.modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** Implementa las operaciones CRUD de productos usando JDBC. */
public class ProductoDAOImpl implements ProductoDAO {

    // Se trabaja directamente con la tabla productos para mantener los IDs reales.
    private static final String INSERTAR_SQL =
            "INSERT INTO productos (imagen, nombre, id_categoria, descripcion, precio, stock) "
            + "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String CONSULTAR_SQL =
            "SELECT p.id, p.imagen, p.nombre, p.id_categoria, c.nombre AS categoria_nombre, "
            + "p.descripcion, p.precio, p.stock FROM productos p "
            + "LEFT JOIN categoria c ON c.id = p.id_categoria ORDER BY p.id";
    private static final String ACTUALIZAR_SQL =
            "UPDATE productos SET imagen = ?, nombre = ?, id_categoria = ?, descripcion = ?, "
            + "precio = ?, stock = ? WHERE id = ?";
    private static final String ELIMINAR_SQL = "DELETE FROM productos WHERE id = ?";

    @Override
    public boolean insertar(Producto producto) throws SQLException {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(INSERTAR_SQL)) {
            sentencia.setString(1, producto.getImagen());
            sentencia.setString(2, producto.getNombre());
            sentencia.setInt(3, producto.getIdCategoria());
            sentencia.setString(4, producto.getDescripcion());
            sentencia.setBigDecimal(5, producto.getPrecio());
            sentencia.setInt(6, producto.getStock());
            return sentencia.executeUpdate() == 1;
        }
    }

    @Override
    public List<Producto> consultarTodos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(CONSULTAR_SQL);
             ResultSet resultado = sentencia.executeQuery()) {
            while (resultado.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(resultado.getInt("id"));
                producto.setImagen(resultado.getString("imagen"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setIdCategoria(resultado.getInt("id_categoria"));
                producto.setCategoriaNombre(resultado.getString("categoria_nombre"));
                producto.setDescripcion(resultado.getString("descripcion"));
                producto.setPrecio(resultado.getBigDecimal("precio"));
                producto.setStock(resultado.getInt("stock"));
                productos.add(producto);
            }
        }
        return productos;
    }

    @Override
    public boolean actualizar(Producto producto) throws SQLException {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(ACTUALIZAR_SQL)) {
            sentencia.setString(1, producto.getImagen());
            sentencia.setString(2, producto.getNombre());
            sentencia.setInt(3, producto.getIdCategoria());
            sentencia.setString(4, producto.getDescripcion());
            sentencia.setBigDecimal(5, producto.getPrecio());
            sentencia.setInt(6, producto.getStock());
            sentencia.setInt(7, producto.getIdProducto());
            return sentencia.executeUpdate() == 1;
        }
    }

    @Override
    public boolean eliminar(int idProducto) throws SQLException {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(ELIMINAR_SQL)) {
            sentencia.setInt(1, idProducto);
            return sentencia.executeUpdate() == 1;
        }
    }
}
