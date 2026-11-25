

/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.reports;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sakila.models.Payment;

/**
 * Clase encargada de generar estadísticas relacionadas
 * con los pagos realizados en el sistema Sakila.
 *
 * Permite calcular el total recaudado, el promedio por pago,
 * la cantidad de pagos y la recaudación por cliente.
 *
 * Utiliza BigDecimal para los cálculos monetarios
 * y HashMap para organizar los pagos por cliente.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class EstadisticasPagos {

    /**
     * Calcula y muestra las estadísticas generales de los pagos.
     *
     * @param pagos lista de pagos registrados.
     */
    public void mostrarEstadisticas(List<Payment> pagos) {

        if (pagos == null || pagos.isEmpty()) {
            System.out.println("No hay pagos para calcular.");
            return;
        }

        BigDecimal totalRecaudado = BigDecimal.ZERO;

        Map<Integer, BigDecimal> pagosPorCliente = new HashMap<>();

        for (Payment pago : pagos) {

            BigDecimal importe = pago.getAmount();

            if (importe != null) {

                totalRecaudado = totalRecaudado.add(importe);

                int idCliente = pago.getCustomerId();

                BigDecimal acumulado = pagosPorCliente.getOrDefault(
                        idCliente, BigDecimal.ZERO);

                pagosPorCliente.put(
                        idCliente, acumulado.add(importe));
            }
        }

        BigDecimal promedio = totalRecaudado.divide(
                BigDecimal.valueOf(pagos.size()),
                2,
                RoundingMode.HALF_UP);

        System.out.println("\n==================================");
        System.out.println("       ESTADISTICAS DE PAGOS");
        System.out.println("==================================");

        System.out.println("Cantidad de pagos: " + pagos.size());

        System.out.println("Total recaudado: RD$ "
                + totalRecaudado);

        System.out.println("Promedio por pago: RD$ "
                + promedio);

        System.out.println("----------------------------------");
        System.out.println("RECAUDACION POR CLIENTE");

        for (Map.Entry<Integer, BigDecimal> entrada
                : pagosPorCliente.entrySet()) {

            System.out.println(
                    "Cliente ID: " + entrada.getKey()
                    + " | Total pagado: RD$ " + entrada.getValue());
        }

        System.out.println("==================================");
    }
}
