
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.reports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.sakila.models.Inventory;
import com.sakila.models.Rental;
import com.sakila.models.Payment;

/**
 * Clase encargada de generar estadísticas relacionadas
 * con los alquileres y pagos realizados en las tiendas.
 *
 * Permite conocer la cantidad de alquileres por tienda
 * y el total recaudado por cada establecimiento.
 *
 * Utiliza HashMap y consultas GROUP BY de MySQL
 * para obtener las estadísticas.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class EstadisticasTiendas {

    /**
     * Calcula y muestra la cantidad de alquileres
     * correspondientes a cada tienda.
     *
     * @param inventarios lista de registros del inventario.
     * @param alquileres lista de alquileres realizados.
     */
    public void mostrarAlquileresPorTienda(
            List<Inventory> inventarios,
            List<Rental> alquileres) {

        if (inventarios == null || alquileres == null) {
            System.out.println("No hay datos disponibles.");
            return;
        }

        Map<Integer, Integer> alquileresPorTienda = new HashMap<>();

        for (Inventory inventario : inventarios) {
            alquileresPorTienda.put(inventario.getStoreId(), 0);
        }

        for (Rental alquiler : alquileres) {

            int idInventario = alquiler.getInventoryId();

            for (Inventory inventario : inventarios) {

                if (inventario.getInventoryId() == idInventario) {

                    int idTienda = inventario.getStoreId();

                    int cantidad = alquileresPorTienda.get(idTienda);

                    alquileresPorTienda.put(idTienda, cantidad + 1);

                    break;
                }
            }
        }

        System.out.println("\n==================================");
        System.out.println("   ALQUILERES POR TIENDA");
        System.out.println("==================================");

        for (Map.Entry<Integer, Integer> entrada
                : alquileresPorTienda.entrySet()) {

            System.out.println(
                    "Tienda ID: " + entrada.getKey()
                    + " | Total de alquileres: " + entrada.getValue());
        }

        System.out.println("==================================");
    }
    

    /**
     * Muestra la cantidad de alquileres por tienda
     * obtenida directamente mediante GROUP BY en MySQL.
     *
     * @param resultados lista con el ID de la tienda
     *                   y la cantidad de alquileres.
     */
    public void mostrarAlquileresPorTiendaGroupBy(
            List<Object[]> resultados) {

        if (resultados == null || resultados.isEmpty()) {
            System.out.println("No hay datos disponibles.");
            return;
        }

        int totalGeneral = 0;

        System.out.println("\n==================================");
        System.out.println(" ALQUILERES POR TIENDA - GROUP BY MYSQL");
        System.out.println("==================================");

        for (Object[] fila : resultados) {

            int storeId = (Integer) fila[0];

            int cantidad = (Integer) fila[1];

            totalGeneral += cantidad;

            System.out.println(
                    "Tienda ID: " + storeId
                    + " | Total de alquileres: " + cantidad);
        }

        System.out.println("----------------------------------");

        System.out.println(
                "TOTAL GENERAL DE ALQUILERES: " + totalGeneral);

        System.out.println("==================================");
    }


    /**
     * Calcula y muestra el total de pagos correspondiente
     * a cada tienda utilizando HashMap.
     *
     * @param pagos lista de pagos registrados.
     * @param alquileres lista de alquileres realizados.
     * @param inventarios lista de registros del inventario.
     */
    public void mostrarPagosPorTienda(
            List<Payment> pagos,
            List<Rental> alquileres,
            List<Inventory> inventarios) {

        if (pagos == null || alquileres == null || inventarios == null) {
            System.out.println("No hay datos disponibles.");
            return;
        }

        Map<Integer, BigDecimal> pagosPorTienda = new HashMap<>();

        for (Inventory inventario : inventarios) {
            pagosPorTienda.put(inventario.getStoreId(), BigDecimal.ZERO);
        }

        for (Payment pago : pagos) {

            if (pago.getRentalId() == null || pago.getAmount() == null) {
                continue;
            }

            int idAlquiler = pago.getRentalId();

            for (Rental alquiler : alquileres) {

                if (alquiler.getRentalId() == idAlquiler) {

                    int idInventario = alquiler.getInventoryId();

                    for (Inventory inventario : inventarios) {

                        if (inventario.getInventoryId() == idInventario) {

                            int idTienda = inventario.getStoreId();

                            BigDecimal total = pagosPorTienda.get(idTienda);

                            pagosPorTienda.put(
                                    idTienda,
                                    total.add(pago.getAmount()));

                            break;
                        }
                    }

                    break;
                }
            }
        }

        System.out.println("\n==================================");
        System.out.println("       PAGOS POR TIENDA");
        System.out.println("==================================");

        for (Map.Entry<Integer, BigDecimal> entrada
                : pagosPorTienda.entrySet()) {

            System.out.println(
                    "Tienda ID: " + entrada.getKey()
                    + " | Total recaudado: RD$ "
                    + entrada.getValue().setScale(2, RoundingMode.HALF_UP));
        }

        System.out.println("==================================");
    }

    /**
     * Muestra el total de pagos por tienda obtenido
     * directamente mediante GROUP BY en MySQL.
     *
     * @param resultados lista con el ID de la tienda
     *                   y el total pagado.
     */
    public void mostrarPagosPorTiendaGroupBy(
            List<Object[]> resultados) {

        if (resultados == null || resultados.isEmpty()) {
            System.out.println("No hay datos disponibles.");
            return;
        }

        BigDecimal totalGeneral = BigDecimal.ZERO;

        System.out.println("\n==================================");
        System.out.println(" PAGOS POR TIENDA - GROUP BY MYSQL");
        System.out.println("==================================");

        for (Object[] fila : resultados) {

            int storeId = (Integer) fila[0];

            BigDecimal totalPagado = (BigDecimal) fila[1];

            totalGeneral = totalGeneral.add(totalPagado);

            System.out.println(
                    "Tienda ID: " + storeId
                    + " | Total pagado: RD$ "
                    + totalPagado.setScale(2, RoundingMode.HALF_UP));
        }

        System.out.println("----------------------------------");

        System.out.println(
                "TOTAL GENERAL: RD$ "
                + totalGeneral.setScale(2, RoundingMode.HALF_UP));

        System.out.println("==================================");
    }
}
