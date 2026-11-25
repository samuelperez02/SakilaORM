
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import java.sql.Connection;
import java.util.List;

import com.sakila.models.FilmCategory;

/**
 * Clase encargada de probar la consulta de la tabla intermedia
 * FilmCategory, que relaciona las peliculas con sus categorias.
 *
 * Obtiene las relaciones almacenadas y muestra hasta 20 registros,
 * incluyendo los identificadores de la pelicula y la categoria,
 * junto con la fecha de ultima actualizacion.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaFilmCategory {

    /**
     * Método principal que ejecuta la prueba de consulta
     * de las relaciones entre peliculas y categorias.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion != null) {

            FilmCategoryDataContext contexto =
                    new FilmCategoryDataContext(conexion);

            List<FilmCategory> relaciones = contexto.Get();

            System.out.println("Relaciones entre peliculas y categorias:");

            int limite = Math.min(20, relaciones.size());

            for (int i = 0; i < limite; i++) {

                FilmCategory relacion = relaciones.get(i);

                System.out.println(
                    "Pelicula ID: " + relacion.getFilmId()
                    + " | Categoria ID: " + relacion.getCategoryId()
                    + " | Ultima actualizacion: "
                    + relacion.getLastUpdate()
                );
            }

            System.out.println();

            System.out.println(
                    "Total de relaciones: " + relaciones.size());

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
