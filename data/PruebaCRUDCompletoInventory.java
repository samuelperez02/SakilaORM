
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sakila.controlers.InventoryController;
import com.sakila.models.Inventory;

/**
 * Clase encargada de probar las operaciones CRUD completas
 * de la tabla Inventory.
 *
 * Comprueba la inserción, actualización, búsqueda y eliminación
 * de registros de inventario, además de verificar su relación
 * con las tablas Film, Store y Rental.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaCRUDCompletoInventory {

    /**
     * Método principal que ejecuta las pruebas CRUD de Inventory.
     * Utiliza películas y una tienda existentes para crear
     * un registro temporal de inventario.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion == null) {
            System.out.println("No se pudo conectar.");
            return;
        }

        InventoryController controlador =
                new InventoryController(
                        new InventoryDataContext(conexion));

        int idInventario = 0;

        try {

            // 1. Obtener una pelicula y una tienda existentes
            int filmId;
            int filmIdActualizado;
            int storeId;

            try (PreparedStatement sentencia =
                    conexion.prepareStatement(
                            "SELECT film_id FROM film LIMIT 1");
                 ResultSet resultado = sentencia.executeQuery()) {

                if (!resultado.next()) {
                    throw new Exception("No hay peliculas disponibles.");
                }

                filmId = resultado.getInt("film_id");
            }

            // Obtener otra pelicula diferente para la actualizacion
            try (PreparedStatement sentencia =
                    conexion.prepareStatement(
                            "SELECT film_id FROM film "
                            + "WHERE film_id <> ? LIMIT 1")) {

                sentencia.setInt(1, filmId);

                try (ResultSet resultado = sentencia.executeQuery()) {

                    if (!resultado.next()) {
                        throw new Exception(
                                "No hay otra pelicula disponible para actualizar.");
                    }

                    filmIdActualizado =
                            resultado.getInt("film_id");
                }
            }

            try (PreparedStatement sentencia =
                    conexion.prepareStatement(
                            "SELECT store_id FROM store LIMIT 1");
                 ResultSet resultado = sentencia.executeQuery()) {

                if (!resultado.next()) {
                    throw new Exception("No hay tiendas disponibles.");
                }

                storeId = resultado.getInt("store_id");
            }

            // 2. INSERTAR inventario
            Inventory inventario = new Inventory();
            inventario.setFilmId(filmId);
            inventario.setStoreId(storeId);

            controlador.registrar(inventario);

            // Recuperar directamente el ID generado por MySQL
            idInventario = inventario.getInventoryId();

            if (idInventario <= 0) {
                throw new Exception(
                        "No se pudo recuperar el ID del inventario creado.");
            }

            System.out.println("INSERTAR: correcto.");
            System.out.println("ID generado: " + idInventario);

            // 3. ACTUALIZAR inventario
            inventario.setFilmId(filmIdActualizado);
            inventario.setStoreId(storeId);

            controlador.actualizar(inventario);

            System.out.println("ACTUALIZAR: correcto.");

            // 4. BUSCAR inventario
            Inventory encontrado =
                    controlador.buscar(idInventario);

            if (encontrado != null
                    && encontrado.getFilmId() == filmIdActualizado) {

                System.out.println("BUSCAR: correcto.");
                System.out.println(encontrado);

            } else {
                throw new Exception(
                        "No se encontro el inventario actualizado correctamente.");
            }

            // 5. COMPROBAR que no tenga alquileres
            String sqlAlquileres =
                    "SELECT COUNT(*) FROM rental "
                    + "WHERE inventory_id = ?";

            try (PreparedStatement sentencia =
                    conexion.prepareStatement(sqlAlquileres)) {

                sentencia.setInt(1, idInventario);

                try (ResultSet resultado = sentencia.executeQuery()) {

                    if (resultado.next()
                            && resultado.getInt(1) != 0) {

                        throw new Exception(
                                "El inventario tiene alquileres relacionados.");
                    }
                }
            }

            System.out.println(
                    "RELACIONES: no tiene alquileres relacionados.");

            // 6. ELIMINAR inventario
            controlador.eliminar(idInventario);

            System.out.println("ELIMINAR: correcto.");

            // 7. VERIFICAR eliminacion
            Inventory verificacion =
                    controlador.buscar(idInventario);

            if (verificacion == null) {

                System.out.println(
                        "VERIFICACION: inventario eliminado.");

            } else {
                throw new Exception(
                        "El inventario no fue eliminado.");
            }

            // Indicar que la prueba termino correctamente
            idInventario = 0;

            System.out.println("PRUEBA COMPLETADA.");

        } catch (Exception e) {

            System.out.println(
                    "ERROR EN LA PRUEBA: " + e.getMessage());

            e.printStackTrace();

        } finally {

            // Limpiar el registro temporal si la prueba fallo
            if (idInventario > 0) {

                try {

                    String sqlAlquileres =
                            "SELECT COUNT(*) FROM rental "
                            + "WHERE inventory_id = ?";

                    try (PreparedStatement sentencia =
                            conexion.prepareStatement(sqlAlquileres)) {

                        sentencia.setInt(1, idInventario);

                        try (ResultSet resultado =
                                sentencia.executeQuery()) {

                            if (resultado.next()
                                    && resultado.getInt(1) == 0) {

                                controlador.eliminar(idInventario);

                                System.out.println(
                                        "LIMPIEZA: registro temporal eliminado.");

                            } else {

                                System.out.println(
                                        "LIMPIEZA: no se elimino el registro "
                                        + "porque tiene alquileres relacionados.");
                            }
                        }
                    }

                } catch (Exception e) {

                    System.out.println(
                            "Error durante la limpieza: " + e.getMessage());
                }
            }

            try {
                conexion.close();
                System.out.println("Conexion cerrada.");
            } catch (Exception e) {
                System.out.println(
                        "Error al cerrar conexion: " + e.getMessage());
            }
        }
    }
}

