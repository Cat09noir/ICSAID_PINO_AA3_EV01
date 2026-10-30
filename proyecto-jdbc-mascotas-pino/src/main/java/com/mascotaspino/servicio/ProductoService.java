package com.mascotaspino.servicio;

import com.mascotaspino.dao.ProductoDAO;
import com.mascotaspino.dao.ProductoDAOImpl;
import com.mascotaspino.modelo.Producto;

import java.sql.SQLException;
import java.util.List;

public class ProductoService {

    private final ProductoDAO productoDAO;

    public ProductoService() {
        this.productoDAO = new ProductoDAOImpl();
    }

    public boolean registrarProducto(Producto producto) throws SQLException {
        validarProducto(producto);
        return productoDAO.insertar(producto);
    }

    public List<Producto> listarProductos() throws SQLException {
        return productoDAO.consultarTodos();
    }

    public boolean modificarProducto(Producto producto) throws SQLException {
        validarProducto(producto);
        return productoDAO.actualizar(producto);
    }

    public boolean retirarProducto(int idProducto) throws SQLException {
        if (idProducto <= 0) {
            throw new IllegalArgumentException("El ID debe ser mayor que cero.");
        }
        return productoDAO.eliminar(idProducto);
    }

    private void validarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (producto.getIdCategoria() <= 0) {
            throw new IllegalArgumentException("La categoría es obligatoria.");
        }
        if (producto.getPrecio() == null || producto.getPrecio().signum() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        if (producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
    }
}
