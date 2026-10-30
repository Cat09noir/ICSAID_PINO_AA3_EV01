package com.mascotaspino.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la conexión JDBC con MySQL.
 */
public final class ConexionBD {

    private static final String URL =
            "jdbc:mysql://localhost:3306/db_mascotas_pino"
            + "?useSSL=false&serverTimezone=America/Bogota"
            + "&allowPublicKeyRetrieval=true";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    private ConexionBD() {
    }

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}
