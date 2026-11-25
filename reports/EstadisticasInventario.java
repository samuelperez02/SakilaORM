


/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.reports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sakila.models.Film;
import com.sakila.models.Inventory;
import com.sakila.controlers.RentalController;

/**
 * Clase encargada de generar estadísticas relacionadas
 * con el inventario de películas de las tiendas Sakila
 * y la antigüedad de los alquileres vencidos.
 *
 * Utiliza HashMap para organizar y contar las copias.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class EstadisticasInventario {

    /**
     * Muestra estadísticas generales del inventario.
     *
     * @param peliculas lista de películas registradas.
     * @param inventarios lista de copias registradas.
     */
    public void mostrarEstadisticas(
            List<Film> peliculas,
            List<Inventory> inventarios) {

        if (peliculas == null || inventarios == null) {
            System.out.println("No hay datos disponibles.");
            return;
        }

        Map<Integer, Integer> copiasPorPelicula =
                new HashMap<>();

        Map<Integer, Integer> copiasPorTienda =
                new HashMap<>();

        for (Film pelicula : peliculas) {
            copiasPorPelicula.put(
                    pelicula.getFilmId(), 0);
        }

        for (Inventory inventario : inventarios) {

            int idPelicula = inventario.getFilmId();
            int idTienda = inventario.getStoreId();

            if (copiasPorPelicula.containsKey(idPelicula)) {

                int cantidad =
                        copiasPorPelicula.get(idPelicula);

                copiasPorPelicula.put(
                        idPelicula, cantidad + 1);
            }

            if (copiasPorTienda.containsKey(idTienda)) {

                int cantidad = copiasPorTienda.get(idTienda);

                copiasPorTienda.put(
                        idTienda, cantidad + 1);

            } else {

                copiasPorTienda.put(idTienda, 1);
            }
        }

        Film peliculaMayorInventario = null;
        int mayorCantidad = -1;

        for (Film pelicula : peliculas) {

            int cantidad = copiasPorPelicula.get(
                    pelicula.getFilmId());

            if (cantidad > mayorCantidad) {

                mayorCantidad = cantidad;
                peliculaMayorInventario = pelicula;
            }
        }

        System.out.println("\n======================================");
        System.out.println("       ESTADISTICAS DE INVENTARIO");
        System.out.println("======================================");

        System.out.println(
                "Total de peliculas: " + peliculas.size());

        System.out.println(
                "Total de copias en inventario: "
                + inventarios.size());

        System.out.println("--------------------------------------");

        if (peliculaMayorInventario != null) {

            System.out.println(
                    "Pelicula con mas copias: "
                    + peliculaMayorInventario.getTitle());

            System.out.println(
                    "Cantidad de copias: " + mayorCantidad);
        }

        System.out.println("--------------------------------------");
        System.out.println("COPIAS POR TIENDA");

        for (Map.Entry<Integer, Integer> entrada
                : copiasPorTienda.entrySet()) {

            System.out.println(
                    "Tienda ID: " + entrada.getKey()
                    + " | Total de copias: " + entrada.getValue());
        }

        System.out.println("======================================");
    }

    /**
     * Muestra las copias existentes de todas las películas.
     *
     * @param peliculas lista de películas registradas.
     * @param inventarios lista de copias registradas.
     */
    public void mostrarCopiasPorPelicula(
            List<Film> peliculas,
            List<Inventory> inventarios) {

        if (peliculas == null || inventarios == null) {
            System.out.println("No hay datos disponibles.");
            return;
        }

        Map<Integer, Integer> copiasPorPelicula =
                new HashMap<>();

        for (Film pelicula : peliculas) {

            copiasPorPelicula.put(
                    pelicula.getFilmId(), 0);
        }

        for (Inventory inventario : inventarios) {

            int idPelicula = inventario.getFilmId();

            if (copiasPorPelicula.containsKey(idPelicula)) {

                int cantidad =
                        copiasPorPelicula.get(idPelicula);

                copiasPorPelicula.put(
                        idPelicula, cantidad + 1);
            }
        }

        System.out.println("\n======================================");
        System.out.println("    COPIAS DE TODAS LAS PELICULAS");
        System.out.println("======================================");

        for (Film pelicula : peliculas) {

            int cantidad = copiasPorPelicula.get(
                    pelicula.getFilmId());

            System.out.println(
                    "ID: " + pelicula.getFilmId()
                    + " | Pelicula: " + pelicula.getTitle()
                    + " | Copias: " + cantidad);
        }

        System.out.println("--------------------------------------");
        System.out.println("Total de peliculas: " + peliculas.size());
        System.out.println("Total de copias: " + inventarios.size());
        System.out.println("======================================");
    }

    /**
     * Muestra las estadísticas generales del inventario
     * obtenidas directamente desde la base de datos.
     *
     * @param controlador controlador encargado de consultar el inventario.
     */
    public void mostrarResumenGeneral(
            com.sakila.controlers.InventoryController controlador) {

        Object[] resultados =
                controlador.obtenerEstadisticasGenerales();

        int totalUnidades = (Integer) resultados[0];

        int peliculasDiferentes = (Integer) resultados[1];

        java.math.BigDecimal promedio =
                (java.math.BigDecimal) resultados[2];

        System.out.println("\n======================================");
        System.out.println("       RESUMEN GENERAL INVENTARIO");
        System.out.println("======================================");

        System.out.println(
                "Total de unidades: " + totalUnidades);

        System.out.println(
                "Peliculas diferentes: " + peliculasDiferentes);

        System.out.println(
                "Promedio de unidades por pelicula: "
                + promedio.setScale(
                        2, java.math.RoundingMode.HALF_UP));

        System.out.println("======================================");
    }

    /**
     * Muestra la antigüedad de los alquileres vencidos
     * que todavía no han sido devueltos.
     *
     * @param controlador controlador encargado de consultar los alquileres.
     */
    public void mostrarAgingAlquileresVencidos(
            RentalController controlador) {

        Object[] resultados =
                controlador.obtenerAgingAlquileresVencidos();

        int dias1a30 = (Integer) resultados[0];

        int dias31a60 = (Integer) resultados[1];

        int dias61a90 = (Integer) resultados[2];

        int masDe90 = (Integer) resultados[3];

        int totalVencidos = (Integer) resultados[4];

        System.out.println("\n======================================");
        System.out.println("     AGING DE ALQUILERES VENCIDOS");
        System.out.println("======================================");

        System.out.println(
                "Vencidos de 1 a 30 dias: " + dias1a30);

        System.out.println(
                "Vencidos de 31 a 60 dias: " + dias31a60);

        System.out.println(
                "Vencidos de 61 a 90 dias: " + dias61a90);

        System.out.println(
                "Vencidos hace mas de 90 dias: " + masDe90);

        System.out.println("--------------------------------------");

        System.out.println(
                "TOTAL DE ALQUILERES VENCIDOS: "
                + totalVencidos);

        System.out.println("--------------------------------------");

        System.out.println(
                "Nota: Son alquileres pendientes de devolucion, "
                + "no deudas contables confirmadas.");

        System.out.println("======================================");
    }
}
