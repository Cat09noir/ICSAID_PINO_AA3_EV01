<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mascotaspino.modelo.Producto" %>
<%!
    // Escapa texto antes de mostrar valores almacenados en la base de datos.
    private String escapar(String valor) {
        if (valor == null) return "";
        return valor.replace("&", "&amp;").replace("<", "&lt;")
                    .replace(">", "&gt;").replace("\"", "&quot;")
                    .replace("'", "&#39;");
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Mascotas Pino | Gestión de productos</title>
    <style>
        :root{font-family:Arial,sans-serif;color:#243044;background:#f4f7fb}body{margin:0}header{background:#155e75;color:white;padding:24px max(5%,calc((100% - 1100px)/2))}main{max-width:1100px;margin:28px auto;padding:0 18px}.panel{background:white;border-radius:12px;padding:22px;margin-bottom:22px;box-shadow:0 3px 14px #15324b12}form .grid{display:grid;grid-template-columns:repeat(auto-fit,minmax(190px,1fr));gap:12px}label{display:block;font-weight:bold;margin-bottom:5px}input,select,textarea{box-sizing:border-box;width:100%;padding:10px;border:1px solid #cbd5e1;border-radius:7px}button{border:0;border-radius:7px;padding:10px 14px;background:#0e7490;color:white;cursor:pointer;font-weight:bold}button.danger{background:#b91c1c}table{width:100%;border-collapse:collapse}th,td{text-align:left;padding:10px;border-bottom:1px solid #e2e8f0;vertical-align:top}th{background:#ecfeff}.table-wrap{overflow-x:auto}.error{color:#b91c1c}.success{color:#166534}@media(max-width:600px){header{padding:20px}th,td{padding:7px;font-size:13px}}
    </style>
</head>
<body>
<header><h1>Mascotas Pino</h1><p>Módulo web de administración del catálogo de productos</p></header>
<main>
    <section class="panel">
        <h2>Registrar producto</h2>
        <% if (request.getAttribute("error") != null) { %><p class="error"><%= escapar((String)request.getAttribute("error")) %></p><% } %>
        <% if ("ok".equals(request.getParameter("estado"))) { %><p class="success">Operación procesada. Revise el listado.</p><% } %>
        <form action="<%= request.getContextPath() %>/productos" method="post">
            <input type="hidden" name="accion" value="crear">
            <div class="grid">
                <div><label for="nombre">Nombre *</label><input id="nombre" name="nombre" maxlength="100" required></div>
                <div><label for="idCategoria">Categoría *</label><select id="idCategoria" name="idCategoria" required><option value="">Seleccione</option><option value="1">Ropa</option><option value="2">Collar</option><option value="3">Accesorios</option><option value="4">Cadena</option><option value="5">Camas</option><option value="6">Pecheras</option></select></div>
                <div><label for="precio">Precio (COP) *</label><input id="precio" name="precio" type="number" min="0" step="0.01" required></div>
                <div><label for="stock">Existencias *</label><input id="stock" name="stock" type="number" min="0" step="1" required></div>
                <div><label for="imagen">URL de imagen</label><input id="imagen" name="imagen" type="url" maxlength="255" placeholder="https://..."></div>
                <div><label for="descripcion">Descripción</label><textarea id="descripcion" name="descripcion" rows="2"></textarea></div>
            </div><p><button type="submit">Guardar producto</button></p>
        </form>
    </section>
    <section class="panel">
        <h2>Productos registrados</h2>
        <div class="table-wrap"><table><thead><tr><th>ID</th><th>Producto</th><th>Categoría</th><th>Precio</th><th>Stock</th><th>Actualizar</th><th>Eliminar</th></tr></thead><tbody>
        <% List<Producto> productos = (List<Producto>)request.getAttribute("productos"); %>
        <% if (productos == null || productos.isEmpty()) { %><tr><td colspan="7">No hay productos registrados todavía.</td></tr><% } else { for (Producto p : productos) { %>
            <tr><td><%= p.getIdProducto() %></td><td><strong><%= escapar(p.getNombre()) %></strong><br><small><%= escapar(p.getDescripcion()) %></small></td><td><%= escapar(p.getCategoriaNombre()) %></td><td>$<%= p.getPrecio() %></td><td><%= p.getStock() %></td><td>
            <details><summary>Editar</summary>
            <form action="<%= request.getContextPath() %>/productos" method="post">
                <input type="hidden" name="accion" value="actualizar"><input type="hidden" name="idProducto" value="<%= p.getIdProducto() %>">
                <label>Nombre</label><input name="nombre" value="<%= escapar(p.getNombre()) %>" maxlength="100" required>
                <label>Categoría</label><select name="idCategoria" required>
                <% for (int cat = 1; cat <= 6; cat++) { String[] nombres = {"", "Ropa", "Collar", "Accesorios", "Cadena", "Camas", "Pecheras"}; %>
                    <option value="<%= cat %>" <%= p.getIdCategoria() == cat ? "selected" : "" %>><%= nombres[cat] %></option>
                <% } %></select>
                <label>Precio</label><input name="precio" type="number" min="0" step="0.01" value="<%= p.getPrecio() %>" required>
                <label>Stock</label><input name="stock" type="number" min="0" value="<%= p.getStock() %>" required>
                <label>URL de imagen</label><input name="imagen" type="url" value="<%= escapar(p.getImagen()) %>">
                <label>Descripción</label><textarea name="descripcion"><%= escapar(p.getDescripcion()) %></textarea>
                <button type="submit">Guardar cambios</button>
            </form></details>
            </td><td><form action="<%= request.getContextPath() %>/productos" method="post" onsubmit="return confirm('¿Eliminar este producto?');"><input type="hidden" name="accion" value="eliminar"><input type="hidden" name="idProducto" value="<%= p.getIdProducto() %>"><button class="danger" type="submit">Eliminar</button></form></td></tr>
        <% } } %>
        </tbody></table></div>
        <p><small>El módulo implementa las cuatro operaciones CRUD: registrar, consultar, actualizar y eliminar productos.</small></p>
    </section>
</main>
</body>
</html>
