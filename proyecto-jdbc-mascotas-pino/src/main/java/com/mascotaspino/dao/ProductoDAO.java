package com.mascotaspino.dao;

import com.mascotaspino.modelo.Producto;
import java.sql.SQLException;
import java.util.List;

public interface ProductoDAO {

    boolean insertar(Producto producto) throws SQLException;

    List<Producto> consultarTodos() throws SQLException;

    boolean actualizar(Producto producto) throws SQLException;

    boolean eliminar(int idProducto) throws SQLException;
}
