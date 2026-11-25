
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import java.sql.Connection;
import java.util.List;

import com.sakila.controlers.RentalController;
import com.sakila.models.Rental;

/**
 * Clase encargada de probar la consulta y el listado
 * de registros de la tabla Rental.
 *
 * Obtiene los alquileres almacenados en la base de datos
 * y muestra hasta 20 registros con sus identificadores,
 * fechas, inventarios, clientes y empleados.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaRental {

    /**
     * Método principal que ejecuta la prueba de consulta
     * y listado de alquileres.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion != null) {

            RentalDataContext contexto =
                    new RentalDataContext(conexion);

            RentalController controlador =
                    new RentalController(contexto);

            List<Rental> alquileres =
                    controlador.listarAlquileres();

            System.out.println("===== LISTADO DE ALQUILERES =====");

            int contador = 0;

            for (Rental alquiler : alquileres) {

                if (contador < 20) {

                    System.out.println(
                            "ID: " + alquiler.getRentalId()
                            + " | Fecha: " + alquiler.getRentalDate()
                            + " | Inventario: " + alquiler.getInventoryId()
                            + " | Cliente: " + alquiler.getCustomerId()
                            + " | Devolucion: " + alquiler.getReturnDate()
                            + " | Empleado: " + alquiler.getStaffId()
                    );
                }

                contador++;
            }

            System.out.println("--------------------------------");
            System.out.println("Registros mostrados: "
                    + Math.min(contador, 20));

            System.out.println("Total de alquileres: "
                    + alquileres.size());

            try {
                conexion.close();
                System.out.println("Conexion cerrada correctamente.");
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion: "
                        + e.getMessage());
            }
        }
    }
}

