
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.reports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sakila.models.Actor;
import com.sakila.models.FilmActor;

/**
 * Clase encargada de generar estadísticas sobre los actores
 * y su participación en las películas de la base de datos Sakila.
 *
 * Utiliza HashMap para almacenar y contar la cantidad
 * de películas en las que participa cada actor.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class EstadisticasActores {

    /**
     * Muestra la cantidad de películas en las que participa
     * cada actor y calcula el total de participaciones.
     *
     * @param actores lista de actores registrados.
     * @param relaciones lista de relaciones entre actores y películas.
     */
    public void mostrarEstadisticas(
            List<Actor> actores, List<FilmActor> relaciones) {

        if (actores == null || relaciones == null) {
            System.out.println("No hay datos disponibles.");
            return;
        }

        Map<Integer, Integer> cantidadPeliculas = new HashMap<>();

        // Inicializar todos los actores con cero peliculas
        for (Actor actor : actores) {
            cantidadPeliculas.put(actor.getActorId(), 0);
        }

        // Contar las peliculas de cada actor
        for (FilmActor relacion : relaciones) {

            int idActor = relacion.getActorId();

            if (cantidadPeliculas.containsKey(idActor)) {

                int cantidad = cantidadPeliculas.get(idActor);

                cantidadPeliculas.put(idActor, cantidad + 1);
            }
        }

        System.out.println("\n======================================");
        System.out.println(" ESTADISTICAS DE ACTORES Y PELICULAS");
        System.out.println("======================================");

        int totalRelaciones = 0;

        for (Actor actor : actores) {

            int cantidad = cantidadPeliculas.get(actor.getActorId());

            System.out.println(
                    "Actor: " + actor.getFirstName() + " "
                    + actor.getLastName()
                    + " | Peliculas: " + cantidad);

            totalRelaciones += cantidad;
        }

        System.out.println("--------------------------------------");
        System.out.println("Total de actores: " + actores.size());
        System.out.println("Total de participaciones: " + totalRelaciones);
        System.out.println("======================================");
    }
}

