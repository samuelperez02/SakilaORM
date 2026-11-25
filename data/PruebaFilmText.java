
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import java.sql.Connection;
import java.util.List;

import com.sakila.controlers.FilmTextController;
import com.sakila.models.FilmText;

/**
 * Clase encargada de probar la consulta y el listado
 * de registros de la tabla FilmText.
 *
 * Obtiene los registros mediante FilmTextController
 * y muestra hasta 20 peliculas, incluyendo su identificador
 * y titulo, junto con el total de registros encontrados.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaFilmText {

    /**
     * Método principal que ejecuta la prueba de consulta
     * y listado de registros de FilmText.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion != null) {

            FilmTextDataContext contexto =
                    new FilmTextDataContext(conexion);

            FilmTextController controlador =
                    new FilmTextController(contexto);

            List<FilmText> peliculas = controlador.listar();

            System.out.println("===== LISTADO DE FILM TEXT =====");

            int contador = 0;

            for (FilmText pelicula : peliculas) {

                if (contador < 20) {

                    System.out.println(
                            "ID: " + pelicula.getFilmId()
                            + " | Titulo: " + pelicula.getTitle()
                    );

                    contador++;
                }
            }

            System.out.println("--------------------------------");
            System.out.println("Total de registros: " + peliculas.size());

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
