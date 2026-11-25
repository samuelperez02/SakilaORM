
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sakila.controlers.CategoryController;
import com.sakila.models.Category;

/**
 * Clase encargada de probar las operaciones CRUD completas
 * de la tabla Category de la base de datos Sakila.
 *
 * Comprueba la inserción, actualización, búsqueda y eliminación
 * de una categoría temporal.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaCRUDCompletoCategory {

    /**
     * Método principal que ejecuta las pruebas CRUD.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion == null) {
            System.out.println("No se pudo conectar.");
            return;
        }

        CategoryController controlador =
                new CategoryController(new CategoryDataContext(conexion));

        int idCategoria = 0;

        // Nombre único para evitar modificar categorías existentes.
        String sufijo = String.valueOf(
                System.currentTimeMillis() % 1000000000L);

        String nombrePrueba = "TEST_" + sufijo;
        String nombreActualizado = "TEST_UPD_" + sufijo;

        try {

            // 1. INSERTAR una categoría temporal
            Category categoria = new Category();
            categoria.setName(nombrePrueba);

            controlador.registrarCategoria(categoria);

            // 2. RECUPERAR el ID utilizando el nombre único
            String sqlBuscar =
                    "SELECT category_id FROM category WHERE name = ?";

            try (PreparedStatement sentencia =
                    conexion.prepareStatement(sqlBuscar)) {

                sentencia.setString(1, nombrePrueba);

                try (ResultSet resultado = sentencia.executeQuery()) {

                    if (resultado.next()) {
                        idCategoria =
                                resultado.getInt("category_id");
                    }
                }
            }

            if (idCategoria <= 0) {
                throw new Exception(
                        "No se pudo recuperar el ID de la categoría.");
            }

            System.out.println("INSERTAR: correcto.");
            System.out.println("ID generado: " + idCategoria);

            // 3. ACTUALIZAR la categoría
            categoria.setCategoryId(idCategoria);
            categoria.setName(nombreActualizado);

            controlador.actualizarCategoria(categoria);

            System.out.println("ACTUALIZAR: correcto.");

            // 4. BUSCAR y verificar la categoría actualizada
            Category encontrada =
                    controlador.buscarCategoria(idCategoria);

            if (encontrada == null) {
                throw new Exception(
                        "No se encontró la categoría actualizada.");
            }

            if (!nombreActualizado.equals(encontrada.getName())) {
                throw new Exception(
                        "El nombre de la categoría no se actualizó.");
            }

            System.out.println("BUSCAR: correcto.");
            System.out.println(encontrada);

            // 5. COMPROBAR que no tenga películas relacionadas
            String sqlRelacion =
                    "SELECT COUNT(*) FROM film_category "
                    + "WHERE category_id = ?";

            try (PreparedStatement sentencia =
                    conexion.prepareStatement(sqlRelacion)) {

                sentencia.setInt(1, idCategoria);

                try (ResultSet resultado = sentencia.executeQuery()) {

                    if (resultado.next()) {

                        int relaciones = resultado.getInt(1);

                        if (relaciones == 0) {
                            System.out.println(
                                    "RELACIONES: no tiene películas relacionadas.");
                        } else {
                            throw new Exception(
                                    "La categoría tiene películas relacionadas.");
                        }
                    }
                }
            }

            // 6. ELIMINAR la categoría temporal
            controlador.eliminarCategoria(idCategoria);

            // 7. VERIFICAR que haya sido eliminada
            Category verificacion =
                    controlador.buscarCategoria(idCategoria);

            if (verificacion != null) {
                throw new Exception(
                        "La categoría no fue eliminada correctamente.");
            }

            idCategoria = 0;

            System.out.println("ELIMINAR: correcto.");
            System.out.println("VERIFICACION: categoría eliminada.");

            System.out.println("PRUEBA COMPLETADA.");

        } catch (Exception e) {

            System.out.println("ERROR EN LA PRUEBA: " + e.getMessage());
            e.printStackTrace();

        } finally {

            // 8. LIMPIAR la categoría temporal si ocurrió un error
            if (idCategoria > 0) {

                try {

                    // Comprobar que no existan películas relacionadas.
                    String sqlRelacion =
                            "SELECT COUNT(*) FROM film_category "
                            + "WHERE category_id = ?";

                    try (PreparedStatement sentencia =
                            conexion.prepareStatement(sqlRelacion)) {

                        sentencia.setInt(1, idCategoria);

                        try (ResultSet resultado =
                                sentencia.executeQuery()) {

                            if (resultado.next()
                                    && resultado.getInt(1) == 0) {

                                controlador.eliminarCategoria(idCategoria);

                                System.out.println(
                                        "LIMPIEZA: categoría temporal eliminada.");

                            } else {

                                System.out.println(
                                        "LIMPIEZA: no se eliminó porque tiene películas relacionadas.");
                            }
                        }
                    }

                } catch (Exception e) {

                    System.out.println(
                            "Error al limpiar la categoría: " + e.getMessage());
                }
            }

            // 9. CERRAR la conexión
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
