# Mascotas Pino — Evidencia GA7-220501096-AA3-EV01

## Propósito
Módulo web para administrar el catálogo de productos de Mascotas Pino. Se implementa con Java 17, Jakarta Servlet, JSP, JDBC, MySQL/MariaDB y Maven.

## Funcionalidades codificadas
- Registrar productos con nombre, categoría, descripción, precio, stock y URL de imagen.
- Consultar el catálogo almacenado en la base de datos.
- Actualizar los datos de un producto desde la opción **Editar**.
- Eliminar productos con confirmación.
- Validar datos en la capa de servicio y usar `PreparedStatement` para las consultas SQL.
- Separar conexión, modelo, acceso a datos (DAO), servicio y controlador web (Servlet).

## Tecnologías y herramientas
- Java 17.
- Jakarta Servlet 6.0 y JSP, para ejecutar en Apache Tomcat 10.1 o superior compatible con Jakarta.
- JDBC y MySQL Connector/J 8.4.0.
- MySQL/MariaDB (por ejemplo, XAMPP).
- Maven para construir el archivo WAR.
- Git y GitHub para control de versiones.

## Estructura principal
```text
src/main/java/com/mascotaspino/
  conexion/ConexionBD.java       # Conexión JDBC
  modelo/Producto.java           # Entidad de producto
  dao/ProductoDAO.java           # Contrato de operaciones CRUD
  dao/ProductoDAOImpl.java       # SQL y persistencia
  servicio/ProductoService.java  # Validaciones y lógica
  web/ProductoServlet.java       # Controlador HTTP GET/POST
src/main/webapp/index.jsp        # Formulario y listado web
 database/db_mascotas_pino.sql   # Estructura y datos iniciales
 pom.xml                         # Dependencias y construcción WAR
```

## Puesta en marcha
1. Instalar JDK 17, Maven y Apache Tomcat 10.1+.
2. Iniciar MySQL desde XAMPP.
3. En phpMyAdmin, crear una base de datos llamada `db_mascotas_pino`, seleccionarla e importar `database/db_mascotas_pino.sql`. Si ya existen tablas con esos nombres, respaldarlas antes de importar.
4. Revisar `src/main/java/com/mascotaspino/conexion/ConexionBD.java`. Por defecto usa `localhost:3306`, usuario `root` y contraseña vacía; cambiar estos datos si la instalación local es distinta.
5. Desde la carpeta del proyecto, ejecutar `mvn clean package`.
6. Copiar `target/mascotas-pino.war` a la carpeta `webapps` de Tomcat e iniciar Tomcat.
7. Abrir `http://localhost:8080/mascotas-pino/productos`.

## Estándares aplicados
- Paquetes en minúscula; clases en PascalCase; métodos y variables en camelCase.
- Constantes SQL en mayúsculas con guion bajo.
- Sangría consistente, nombres descriptivos y comentarios en las clases y operaciones principales.
- Cierre automático de conexiones, sentencias y resultados con `try-with-resources`.
- Consultas parametrizadas para reducir el riesgo de inyección SQL.
- Patrón MVC por capas: modelo, DAO, servicio, servlet controlador y vista JSP.

## Control de versiones
Repositorio de referencia: https://github.com/Tupaginaweb9/proyecto-jdbc-mascotas-pino

Archivo de enlace: `enlace_repositorio.txt`. Después de incorporar los cambios, ejecutar `git status`, `git add .`, `git commit -m "Codificación módulo web GA7-220501096-AA3-EV01"` y `git push` para publicar esta versión en GitHub. Verificar que el repositorio sea público o accesible para el instructor.

## Relación con artefactos anteriores
El módulo corresponde al proyecto formativo Mascotas Pino y al caso de uso de gestión del catálogo. Las operaciones registrar, consultar, actualizar y eliminar se implementan en el DAO y se exponen en la interfaz web por medio del servlet. Adjuntar en la evidencia los diagramas de clases/casos de uso, historias de usuario, diseño o prototipo y plan técnico elaborados en evidencias anteriores, si la plataforma o el instructor los solicita como soporte.
