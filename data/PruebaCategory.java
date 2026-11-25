
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import java.sql.Connection;
import java.util.List;

import com.sakila.controlers.CategoryController;
import com.sakila.models.Category;

/**
 * Clase encargada de probar la consulta de categorías
 * registradas en la base de datos Sakila.
 *
 * Utiliza CategoryDataContext y CategoryController
 * para obtener y mostrar las categorías en consola.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaCategory {

    /**
     * Método principal que ejecuta la prueba.
     * Establece la conexión, consulta las categorías,
     * muestra sus identificadores y nombres, y cierra
     * la conexión con la base de datos.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion != null) {

            CategoryDataContext contexto =
                    new CategoryDataContext(conexion);

            CategoryController controlador =
                    new CategoryController(contexto);

            List<Category> categorias =
                    controlador.listarCategorias();

            System.out.println("===== LISTADO DE CATEGORIAS =====");

            for (Category categoria : categorias) {

                System.out.println(
                        "ID: " + categoria.getCategoryId()
                        + " | Nombre: " + categoria.getName()
                );
            }

            System.out.println("--------------------------------");
            System.out.println("Total de categorias: " + categorias.size());

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
