<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mascotaspino.model.Producto"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Producto Registrado</title>
</head>
<body>
    <%
        Producto prod = (Producto) request.getAttribute("productoRegistrado");
        String mensaje = (String) request.getAttribute("mensaje");
    %>

    <h2>Resumen del Producto Procesado</h2>
    
    <% if (mensaje != null) { %>
        <p style="color: green; font-weight: bold;"><%= mensaje %></p>
    <% } %>

    <% if (prod != null) { %>
        <table border="1" cellpadding="8">
            <tr>
                <th>Código</th>
                <th>Nombre</th>
                <th>Categoría</th>
                <th>Precio</th>
            </tr>
            <tr>
                <td><%= prod.getCodigo() %></td>
                <td><%= prod.getNombre() %></td>
                <td><%= prod.getCategoria() %></td>
                <td>$<%= String.format("%.2f", prod.getPrecio()) %></td>
            </tr>
        </table>
    <% } else { %>
        <p>No se encontraron datos del producto.</p>
    <% } %>

    <br>
    <a href="registroProducto.jsp">Registrar otro producto</a> | 
    <a href="index.jsp">Ir al Inicio</a>
</body>
</html>
