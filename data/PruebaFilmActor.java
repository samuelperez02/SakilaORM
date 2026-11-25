
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import java.sql.Connection;
import java.util.List;

import com.sakila.models.FilmActor;

/**
 * Clase encargada de probar la consulta de la tabla intermedia
 * FilmActor, que relaciona los actores con las peliculas.
 *
 * Obtiene las relaciones almacenadas y muestra hasta 20 registros,
 * incluyendo los identificadores del actor y la pelicula,
 * junto con la fecha de ultima actualizacion.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaFilmActor {

    /**
     * Método principal que ejecuta la prueba de consulta
     * de las relaciones entre actores y peliculas.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion != null) {

            FilmActorDataContext contexto =
                    new FilmActorDataContext(conexion);

            List<FilmActor> relaciones = contexto.Get();

            System.out.println("Relaciones entre actores y peliculas:");

            int limite = Math.min(20, relaciones.size());

            for (int i = 0; i < limite; i++) {

                FilmActor relacion = relaciones.get(i);

                System.out.println(
                    "Actor ID: " + relacion.getActorId()
                    + " | Pelicula ID: " + relacion.getFilmId()
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

