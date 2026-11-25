
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import java.sql.Connection;
import java.util.List;

import com.sakila.controlers.FilmController;
import com.sakila.models.Film;

/**
 * Clase encargada de probar la consulta y el listado
 * de registros de la tabla Film.
 *
 * Obtiene las peliculas almacenadas en la base de datos
 * y muestra sus identificadores, titulos, duraciones
 * y el total de registros encontrados.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaPeliculas {

    /**
     * Método principal que ejecuta la prueba de consulta
     * y listado de peliculas.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion != null) {

            FilmDataContext contexto = new FilmDataContext(conexion);

            FilmController controlador = new FilmController(contexto);

            List<Film> peliculas = controlador.listarPeliculas();

            System.out.println("LISTADO DE PELICULAS");
            System.out.println("----------------------------");

            for (Film pelicula : peliculas) {

                System.out.println("ID: " + pelicula.getFilmId());
                System.out.println("Titulo: " + pelicula.getTitle());
                System.out.println("Duracion: " + pelicula.getLength());
                System.out.println("----------------------------");
            }

            System.out.println("Total de peliculas: " + peliculas.size());

            try {
                conexion.close();
                System.out.println("Conexion cerrada correctamente.");
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion.");
            }
        }
    }
}

