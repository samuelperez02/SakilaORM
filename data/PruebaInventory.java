
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import java.sql.Connection;
import java.util.List;

import com.sakila.models.Inventory;

/**
 * Clase encargada de probar la consulta y el listado
 * de registros de la tabla Inventory.
 *
 * Obtiene los inventarios almacenados en la base de datos
 * y muestra hasta 20 registros con sus identificadores,
 * peliculas, tiendas y fechas de ultima actualizacion.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaInventory {

    /**
     * Método principal que ejecuta la prueba de consulta
     * y listado de inventarios.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion != null) {

            InventoryDataContext contexto =
                    new InventoryDataContext(conexion);

            List<Inventory> inventarios = contexto.Get();

            System.out.println("Primeros registros de inventario:");

            int limite = Math.min(20, inventarios.size());

            for (int i = 0; i < limite; i++) {

                Inventory inventario = inventarios.get(i);

                System.out.println(
                    "ID: " + inventario.getInventoryId()
                    + " | Pelicula: " + inventario.getFilmId()
                    + " | Tienda: " + inventario.getStoreId()
                    + " | Ultima actualizacion: "
                    + inventario.getLastUpdate()
                );
            }

            System.out.println();
            System.out.println(
                    "Total de inventarios: " + inventarios.size());

            try {
                conexion.close();
                System.out.println("Conexion cerrada correctamente.");
            } catch (Exception e) {
                System.out.println(
                    "Error al cerrar la conexion: " + e.getMessage()
                );
            }
        }
    }
}
