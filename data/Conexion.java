
/**
 * Copyright (c) 2026.
 * Proyecto final de Lenguaje de Programacion II.
 *
 * Clase encargada de establecer la conexion
 * entre la aplicacion Java y la base de datos Sakila.
 */
package com.sakila.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:mysql://localhost:3306/sakila";

    private static final String USUARIO = "root";

    // La contraseña se obtiene de una variable de entorno.
    private static final String CLAVE =
            System.getenv("DB_PASSWORD");

    /**
     * Establece una conexion con la base de datos Sakila.
     *
     * @return objeto Connection si la conexion es exitosa;
     *         null si ocurre un error.
     */
    public static Connection conectar() {

        Connection conexion = null;

        if (CLAVE == null || CLAVE.isEmpty()) {
            System.out.println(
                    "Error: No se ha configurado DB_PASSWORD.");
            return null;
        }

        try {
            conexion = DriverManager.getConnection(
                    URL, USUARIO, CLAVE);

            System.out.println("Zona horaria Java: "
                    + java.time.ZoneId.systemDefault());

            System.out.println("Hora Java: "
                    + java.time.LocalDateTime.now());

            System.out.println("Conexion exitosa con Sakila.");

        } catch (SQLException e) {

            System.out.println("Error al conectar con MySQL: "
                    + e.getMessage());
        }

        return conexion;
    }
}
