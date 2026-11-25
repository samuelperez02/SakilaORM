
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Clase encargada de probar la conexión entre Java y MySQL.
 *
 * Utiliza la clase Conexion para establecer una conexión
 * con la base de datos Sakila y comprobar que funcione
 * correctamente.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaConexion {

    /**
     * Método principal que ejecuta la prueba de conexión.
     * Comprueba si se establece la conexión con MySQL
     * y posteriormente intenta cerrarla.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion != null) {

            System.out.println("Java esta conectado a MySQL.");

            try {
                conexion.close();
                System.out.println("Conexion cerrada correctamente.");

            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion.");
            }

        } else {

            System.out.println("No se pudo establecer la conexion.");
        }
    }
}
