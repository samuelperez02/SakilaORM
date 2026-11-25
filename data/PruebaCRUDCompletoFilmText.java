
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import java.math.BigDecimal;
import java.sql.Connection;

import com.sakila.controlers.FilmController;
import com.sakila.controlers.FilmTextController;
import com.sakila.models.Film;
import com.sakila.models.FilmText;

/**
 * Clase encargada de probar las operaciones CRUD completas
 * de la tabla FilmText y su relación con la tabla Film.
 *
 * Comprueba la creación automática de FilmText mediante
 * un trigger y sus operaciones de inserción, actualización,
 * búsqueda y eliminación.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaCRUDCompletoFilmText {

    /**
     * Método principal que ejecuta las pruebas CRUD de FilmText.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion == null) {
            System.out.println("No se pudo conectar con la base de datos.");
            return;
        }

        FilmController filmController =
                new FilmController(new FilmDataContext(conexion));

        FilmTextController filmTextController =
                new FilmTextController(new FilmTextDataContext(conexion));

        int filmId = 0;

        String sufijo = String.valueOf(
                System.currentTimeMillis() % 1000000000L);

        String tituloPelicula = "FILM TEXT TEST " + sufijo;

        try {

            // 1. CREAR UNA PELICULA TEMPORAL
            Film pelicula = new Film();

            pelicula.setTitle(tituloPelicula);
            pelicula.setDescription(
                    "Pelicula temporal para probar FilmText.");
            pelicula.setReleaseYear(2026);
            pelicula.setLanguageId(1);
            pelicula.setOriginalLanguageId(null);
            pelicula.setRentalDuration(3);
            pelicula.setRentalRate(new BigDecimal("4.99"));
            pelicula.setLength(90);
            pelicula.setReplacementCost(new BigDecimal("19.99"));
            pelicula.setRating("G");
            pelicula.setSpecialFeatures("Trailers");

            filmController.registrarPelicula(pelicula);

            // Recuperar directamente el ID generado por MySQL
            filmId = pelicula.getFilmId();

            if (filmId <= 0) {
                throw new Exception(
                        "No se pudo recuperar el ID de la pelicula.");
            }

            System.out.println(
                    "PELICULA TEMPORAL CREADA: " + filmId);

            // 2. COMPROBAR EL FILM TEXT CREADO POR EL TRIGGER
            FilmText filmText =
                    filmTextController.buscar(filmId);

            if (filmText == null) {
                throw new Exception(
                        "El trigger no creo el registro FilmText.");
            }

            System.out.println(
                    "FILM TEXT CREADO POR TRIGGER: correcto.");

            System.out.println(
                    "ID: " + filmText.getFilmId()
                    + " | Titulo: " + filmText.getTitle());

            // 3. ELIMINAR EL FILM TEXT CREADO POR EL TRIGGER
            filmTextController.eliminar(filmId);

            if (filmTextController.buscar(filmId) != null) {
                throw new Exception(
                        "No se pudo eliminar el FilmText inicial.");
            }

            System.out.println(
                    "ELIMINAR FILM TEXT INICIAL: correcto.");

            // 4. INSERTAR UN NUEVO FILM TEXT
            FilmText nuevoFilmText = new FilmText();

            nuevoFilmText.setFilmId(filmId);
            nuevoFilmText.setTitle("FILM TEXT INSERTADO");
            nuevoFilmText.setDescription(
                    "Descripcion insertada mediante FilmTextDataContext.");

            filmTextController.registrar(nuevoFilmText);

            System.out.println(
                    "INSERTAR FILM TEXT: correcto.");

            // 5. ACTUALIZAR EL FILM TEXT
            nuevoFilmText.setTitle("FILM TEXT ACTUALIZADO");
            nuevoFilmText.setDescription(
                    "Descripcion actualizada mediante FilmTextDataContext.");

            filmTextController.actualizar(nuevoFilmText);

            System.out.println(
                    "ACTUALIZAR FILM TEXT: correcto.");

            // 6. BUSCAR Y VERIFICAR LOS DATOS ACTUALIZADOS
            FilmText encontrado =
                    filmTextController.buscar(filmId);

            if (encontrado == null) {
                throw new Exception(
                        "No se encontro el FilmText actualizado.");
            }

            if (!"FILM TEXT ACTUALIZADO".equals(
                    encontrado.getTitle())) {

                throw new Exception(
                        "El titulo de FilmText no se actualizo.");
            }

            if (!"Descripcion actualizada mediante FilmTextDataContext."
                    .equals(encontrado.getDescription())) {

                throw new Exception(
                        "La descripcion de FilmText no se actualizo.");
            }

            System.out.println("BUSCAR: correcto.");

            System.out.println(
                    "ID Film: " + encontrado.getFilmId()
                    + " | Titulo: " + encontrado.getTitle()
                    + " | Descripcion: " + encontrado.getDescription());

            // 7. ELIMINAR EL FILM TEXT
            filmTextController.eliminar(filmId);

            System.out.println(
                    "ELIMINAR FILM TEXT: correcto.");

            // 8. VERIFICAR QUE FILM TEXT YA NO EXISTA
            FilmText verificacion =
                    filmTextController.buscar(filmId);

            if (verificacion != null) {
                throw new Exception(
                        "FilmText todavia existe despues de eliminarlo.");
            }

            System.out.println(
                    "VERIFICACION: FilmText eliminado.");

            // 9. ELIMINAR LA PELICULA TEMPORAL
            filmController.eliminarPelicula(filmId);

            // Verificar que la pelicula ya no exista
            Film peliculaVerificada =
                    filmController.buscarPelicula(filmId);

            if (peliculaVerificada != null) {
                throw new Exception(
                        "La pelicula temporal no fue eliminada.");
            }

            filmId = 0;

            System.out.println(
                    "PELICULA TEMPORAL ELIMINADA: correcto.");

            System.out.println(
                    "PRUEBA CRUD FILM TEXT COMPLETADA.");

        } catch (Exception e) {

            System.out.println(
                    "ERROR DURANTE LA PRUEBA: " + e.getMessage());

            e.printStackTrace();

        } finally {

            // 10. LIMPIAR LOS REGISTROS TEMPORALES SI HUBO UN ERROR
            if (filmId > 0) {

                try {

                    FilmText pendiente =
                            filmTextController.buscar(filmId);

                    if (pendiente != null) {
                        filmTextController.eliminar(filmId);
                    }

                    filmController.eliminarPelicula(filmId);

                    System.out.println(
                            "LIMPIEZA: registros temporales eliminados.");

                } catch (Exception e) {

                    System.out.println(
                            "Error al limpiar los registros temporales: "
                            + e.getMessage());
                }
            }

            // 11. CERRAR LA CONEXION
            try {
                conexion.close();
                System.out.println("Conexion cerrada.");

            } catch (Exception e) {

                System.out.println(
                        "Error al cerrar la conexion.");
            }
        }
    }
}

