package com.mascotaspino.web;

import com.mascotaspino.modelo.Producto;
import com.mascotaspino.servicio.ProductoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

/** Controlador web: recibe solicitudes HTTP y delega la lógica al servicio. */
@WebServlet("/productos")
public class ProductoServlet extends HttpServlet {
    private final ProductoService servicio = new ProductoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            request.setAttribute("productos", servicio.listarProductos());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException exception) {
            throw new ServletException("No fue posible consultar los productos. Verifique MySQL.", exception);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");
        try {
            if ("eliminar".equals(accion)) {
                servicio.retirarProducto(Integer.parseInt(request.getParameter("idProducto")));
            } else {
                Producto producto = leerProducto(request);
                if ("actualizar".equals(accion)) {
                    producto.setIdProducto(Integer.parseInt(request.getParameter("idProducto")));
                    servicio.modificarProducto(producto);
                } else {
                    servicio.registrarProducto(producto);
                }
            }
            // Redirección después del POST para evitar duplicar operaciones al recargar.
            response.sendRedirect(request.getContextPath() + "/productos?estado=ok");
        } catch (SQLException | IllegalArgumentException exception) {
            request.setAttribute("error", "No se pudo procesar la solicitud: " + exception.getMessage());
            doGet(request, response);
        }
    }

    private Producto leerProducto(HttpServletRequest request) {
        String imagen = request.getParameter("imagen");
        String nombre = request.getParameter("nombre");
        int categoria = Integer.parseInt(request.getParameter("idCategoria"));
        String descripcion = request.getParameter("descripcion");
        BigDecimal precio = new BigDecimal(request.getParameter("precio"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        return new Producto(imagen, nombre, categoria, descripcion, precio, stock);
    }
}
