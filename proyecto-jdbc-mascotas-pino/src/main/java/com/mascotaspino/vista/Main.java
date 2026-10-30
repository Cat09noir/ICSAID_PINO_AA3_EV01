package com.mascotaspino.vista;

import com.mascotaspino.modelo.Producto;
import com.mascotaspino.servicio.ProductoService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner TECLADO = new Scanner(System.in);
    private static final ProductoService SERVICIO = new ProductoService();

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            try {
                ejecutarOpcion(opcion);
            } catch (SQLException exception) {
                System.out.println("Error de base de datos: " + exception.getMessage());
            } catch (IllegalArgumentException exception) {
                System.out.println("Dato inválido: " + exception.getMessage());
            }
        } while (opcion != 0);

        System.out.println("Programa finalizado.");
    }

    private static void mostrarMenu() {
        System.out.println("\n===== MASCOTAS PINO - CRUD JDBC =====");
        System.out.println("1. Insertar producto");
        System.out.println("2. Consultar productos");
        System.out.println("3. Actualizar producto");
        System.out.println("4. Eliminar producto");
        System.out.println("0. Salir");
    }

    private static void ejecutarOpcion(int opcion) throws SQLException {
        switch (opcion) {
            case 1 -> insertarProducto();
            case 2 -> consultarProductos();
            case 3 -> actualizarProducto();
            case 4 -> eliminarProducto();
            case 0 -> { }
            default -> System.out.println("Opción no válida.");
        }
    }

    private static void insertarProducto() throws SQLException {
        Producto producto = leerProducto();
        boolean registrado = SERVICIO.registrarProducto(producto);
        System.out.println(registrado
                ? "Producto insertado correctamente."
                : "No se pudo insertar el producto.");
    }

    private static void consultarProductos() throws SQLException {
        List<Producto> productos = SERVICIO.listarProductos();

        if (productos.isEmpty()) {
            System.out.println("No existen productos registrados.");
            return;
        }

        System.out.println("\n===== PRODUCTOS =====");
        productos.forEach(System.out::println);
    }

    private static void actualizarProducto() throws SQLException {
        int id = leerEntero("ID del producto a actualizar: ");
        Producto producto = leerProducto();
        producto.setIdProducto(id);

        boolean actualizado = SERVICIO.modificarProducto(producto);
        System.out.println(actualizado
                ? "Producto actualizado correctamente."
                : "No se encontró el producto.");
    }

    private static void eliminarProducto() throws SQLException {
        int id = leerEntero("ID del producto a eliminar: ");
        boolean eliminado = SERVICIO.retirarProducto(id);
        System.out.println(eliminado
                ? "Producto eliminado correctamente."
                : "No se encontró el producto.");
    }

    private static Producto leerProducto() {
        System.out.print("Imagen (URL): ");
        String imagen = TECLADO.nextLine();

        System.out.print("Nombre: ");
        String nombre = TECLADO.nextLine();

        int idCategoria = leerEntero(
                "ID categoría (1 Ropa, 2 Collar, 3 Accesorios, "
                + "4 Cadena, 5 Camas, 6 Pecheras): ");

        System.out.print("Descripción: ");
        String descripcion = TECLADO.nextLine();

        BigDecimal precio = leerDecimal("Precio: ");
        int stock = leerEntero("Stock: ");

        return new Producto(imagen, nombre, idCategoria,
                descripcion, precio, stock);
    }

    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Integer.parseInt(TECLADO.nextLine());
    }

    private static BigDecimal leerDecimal(String mensaje) {
        System.out.print(mensaje);
        return new BigDecimal(TECLADO.nextLine());
    }
}
