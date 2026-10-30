<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro de Producto - Mascotas Pino</title>
</head>
<body>
    <h2>Formulario de Registro de Producto</h2>
    
    <form action="ProductoServlet" method="POST">
        <div>
            <label for="codigo">Código del Producto:</label><br>
            <input type="text" id="codigo" name="codigo" required>
        </div>
        <br>
        <div>
            <label for="nombre">Nombre del Producto:</label><br>
            <input type="text" id="nombre" name="nombre" required>
        </div>
        <br>
        <div>
            <label for="categoria">Categoría:</label><br>
            <select id="categoria" name="categoria">
                <option value="Alimento">Alimento</option>
                <option value="Accesorios">Accesorios</option>
                <option value="Aseo">Aseo y Cuidado</option>
                <option value="Juguetes">Juguetes</option>
            </select>
        </div>
        <br>
        <div>
            <label for="precio">Precio ($):</label><br>
            <input type="number" step="0.01" id="precio" name="precio" required>
        </div>
        <br>
        <button type="submit">Guardar Producto</button>
    </form>
    
    <br>
    <a href="index.jsp">Volver al Inicio</a>
</body>
</html>
