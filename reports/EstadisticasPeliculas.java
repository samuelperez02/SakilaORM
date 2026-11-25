
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.reports;

import java.util.List;
import com.sakila.models.Film;

/**
 * Clase encargada de generar estadísticas relacionadas
 * con las películas registradas en la base de datos Sakila.
 *
 * Permite calcular la cantidad de películas, la duración
 * promedio, la duración mayor y la duración menor.
 *
 * También controla los registros que no tienen una
 * duración disponible.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class EstadisticasPeliculas {

    /**
     * Calcula y muestra las estadísticas de duración
     * de las películas registradas.
     *
     * @param peliculas lista de películas registradas.
     */
    public void mostrarEstadisticas(List<Film> peliculas) {

        if (peliculas == null || peliculas.isEmpty()) {
            System.out.println("No hay peliculas para calcular.");
            return;
        }

        int totalPeliculas = peliculas.size();

        int sumaDuraciones = 0;
        int cantidadDuraciones = 0;

        int duracionMayor = Integer.MIN_VALUE;
        int duracionMenor = Integer.MAX_VALUE;

        for (Film pelicula : peliculas) {

            Integer duracion = pelicula.getLength();

            if (duracion != null) {

                sumaDuraciones += duracion;
                cantidadDuraciones++;

                if (duracion > duracionMayor) {
                    duracionMayor = duracion;
                }

                if (duracion < duracionMenor) {
                    duracionMenor = duracion;
                }
            }
        }

        System.out.println("\n==================================");
        System.out.println("   ESTADISTICAS DE PELICULAS");
        System.out.println("==================================");

        System.out.println("Total de peliculas: " + totalPeliculas);

        if (cantidadDuraciones > 0) {

            double promedio = (double) sumaDuraciones / cantidadDuraciones;

            System.out.println("Duracion promedio: "
                    + String.format("%.2f", promedio) + " minutos");

            System.out.println("Duracion mayor: "
                    + duracionMayor + " minutos");

            System.out.println("Duracion menor: "
                    + duracionMenor + " minutos");

        } else {
            System.out.println("No hay duraciones disponibles.");
        }

        System.out.println("==================================");
    }
}

