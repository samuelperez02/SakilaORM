
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.reports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sakila.models.Rental;

/**
 * Clase encargada de generar estadísticas sobre los alquileres
 * registrados en la base de datos Sakila.
 *
 * Permite calcular el total de alquileres, los devueltos,
 * los pendientes y el promedio de alquileres por cliente.
 *
 * Utiliza HashMap para agrupar los alquileres por identificador
 * de cliente.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class EstadisticasAlquileres {

    /**
     * Calcula y muestra las estadísticas generales de los alquileres
     * y la cantidad de alquileres realizados por cada cliente.
     *
     * @param alquileres lista de alquileres registrados.
     */
    public void mostrarEstadisticas(List<Rental> alquileres) {

        if (alquileres == null || alquileres.isEmpty()) {
            System.out.println("No hay alquileres para calcular.");
            return;
        }

        int totalAlquileres = alquileres.size();
        int devueltos = 0;
        int pendientes = 0;

        Map<Integer, Integer> alquileresPorCliente = new HashMap<>();

        for (Rental alquiler : alquileres) {

            if (alquiler.getReturnDate() == null) {
                pendientes++;
            } else {
                devueltos++;
            }

            int idCliente = alquiler.getCustomerId();

            int cantidad = alquileresPorCliente.getOrDefault(
                    idCliente, 0);

            alquileresPorCliente.put(idCliente, cantidad + 1);
        }

        double promedio = (double) totalAlquileres
                / alquileresPorCliente.size();

        System.out.println("\n==================================");
        System.out.println("    ESTADISTICAS DE ALQUILERES");
        System.out.println("==================================");

        System.out.println("Total de alquileres: " + totalAlquileres);

        System.out.println("Alquileres devueltos: " + devueltos);

        System.out.println("Alquileres pendientes: " + pendientes);

        System.out.println("Promedio de alquileres por cliente: "
                + String.format("%.2f", promedio));

        System.out.println("----------------------------------");
        System.out.println("ALQUILERES POR CLIENTE");

        for (Map.Entry<Integer, Integer> entrada
                : alquileresPorCliente.entrySet()) {

            System.out.println(
                    "Cliente ID: " + entrada.getKey()
                    + " | Cantidad de alquileres: " + entrada.getValue());
        }

        System.out.println("==================================");
    }
}
