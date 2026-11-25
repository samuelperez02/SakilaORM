
/*
 
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.reports;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sakila.models.Customer;
import com.sakila.models.Rental;
import com.sakila.models.Payment;

/**
 * Clase encargada de generar estadísticas relacionadas
 * con los alquileres y pagos realizados por los clientes.
 *
 * Permite conocer la cantidad de alquileres por cliente
 * y el total de dinero pagado por cada uno.
 *
 * Utiliza HashMap, GROUP BY y BigDecimal para organizar
 * y calcular la información.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class EstadisticasClientes {

    /**
     * Muestra la cantidad de alquileres realizados por cada cliente.
     *
     * @param clientes lista de clientes registrados.
     * @param alquileres lista de alquileres realizados.
     */
    public void mostrarAlquileresPorCliente(
            List<Customer> clientes,
            List<Rental> alquileres) {

        if (clientes == null || alquileres == null) {
            System.out.println("No hay datos disponibles.");
            return;
        }

        Map<Integer, Integer> alquileresPorCliente =
                new HashMap<>();

        for (Customer cliente : clientes) {
            alquileresPorCliente.put(
                    cliente.getCustomerId(), 0);
        }

        for (Rental alquiler : alquileres) {

            int idCliente = alquiler.getCustomerId();

            if (alquileresPorCliente.containsKey(idCliente)) {

                int cantidad =
                        alquileresPorCliente.get(idCliente);

                alquileresPorCliente.put(
                        idCliente, cantidad + 1);
            }
        }

        System.out.println("\n======================================");
        System.out.println("       ALQUILERES POR CLIENTE");
        System.out.println("======================================");

        for (Customer cliente : clientes) {

            int cantidad = alquileresPorCliente.get(
                    cliente.getCustomerId());

            System.out.println(
                    "Cliente: " + cliente.getFirstName()
                    + " " + cliente.getLastName()
                    + " | ID: " + cliente.getCustomerId()
                    + " | Alquileres: " + cantidad);
        }

        System.out.println("--------------------------------------");
        System.out.println("Total de clientes: " + clientes.size());
        System.out.println("Total de alquileres: " + alquileres.size());
        System.out.println("======================================");
    }

    /**
     * Muestra el total de pagos realizados por cada cliente
     * y calcula el total general recaudado.
     *
     * @param clientes lista de clientes registrados.
     * @param pagos lista de pagos realizados.
     */
    public void mostrarPagosPorCliente(
            List<Customer> clientes,
            List<Payment> pagos) {

        if (clientes == null || pagos == null) {
            System.out.println("No hay datos disponibles.");
            return;
        }

        Map<Integer, BigDecimal> pagosPorCliente =
                new HashMap<>();

        for (Customer cliente : clientes) {
            pagosPorCliente.put(
                    cliente.getCustomerId(), BigDecimal.ZERO);
        }

        for (Payment pago : pagos) {

            int idCliente = pago.getCustomerId();

            if (pago.getAmount() == null) {
                continue;
            }

            if (pagosPorCliente.containsKey(idCliente)) {

                BigDecimal total =
                        pagosPorCliente.get(idCliente);

                pagosPorCliente.put(
                        idCliente, total.add(pago.getAmount()));
            }
        }

        System.out.println("\n======================================");
        System.out.println("         PAGOS POR CLIENTE");
        System.out.println("======================================");

        for (Customer cliente : clientes) {

            BigDecimal total = pagosPorCliente.get(
                    cliente.getCustomerId());

            System.out.println(
                    "Cliente: " + cliente.getFirstName()
                    + " " + cliente.getLastName()
                    + " | ID: " + cliente.getCustomerId()
                    + " | Total pagado: RD$ " + total);
        }

        System.out.println("--------------------------------------");
        System.out.println("Total de clientes: " + clientes.size());

        BigDecimal totalGeneral = BigDecimal.ZERO;

        for (BigDecimal cantidad : pagosPorCliente.values()) {
            totalGeneral = totalGeneral.add(cantidad);
        }

        System.out.println(
                "Total general recaudado: RD$ " + totalGeneral);

        System.out.println("======================================");
    }

    /**
     * Muestra la cantidad de alquileres por cliente
     * utilizando una consulta SQL con GROUP BY.
     *
     * @param clientes lista de clientes registrados.
     * @param resultados resultados agrupados desde MySQL.
     */
    public void mostrarAlquileresPorClienteGroupBy(
            List<Customer> clientes,
            List<Object[]> resultados) {

        if (clientes == null || resultados == null) {
            System.out.println("No hay datos disponibles.");
            return;
        }

        Map<Integer, Integer> alquileresPorCliente =
                new HashMap<>();

        for (Customer cliente : clientes) {
            alquileresPorCliente.put(
                    cliente.getCustomerId(), 0);
        }

        for (Object[] fila : resultados) {

            int idCliente = (Integer) fila[0];
            int cantidad = ((Number) fila[1]).intValue();

            alquileresPorCliente.put(idCliente, cantidad);
        }

        System.out.println("\n======================================");
        System.out.println(" ALQUILERES POR CLIENTE - GROUP BY");
        System.out.println("======================================");

        for (Customer cliente : clientes) {

            int cantidad = alquileresPorCliente.get(
                    cliente.getCustomerId());

            System.out.println(
                    "Cliente: " + cliente.getFirstName()
                    + " " + cliente.getLastName()
                    + " | ID: " + cliente.getCustomerId()
                    + " | Alquileres: " + cantidad);
        }

        System.out.println("--------------------------------------");
        System.out.println("Total de clientes: " + clientes.size());

        int totalAlquileres = 0;

        for (int cantidad : alquileresPorCliente.values()) {
            totalAlquileres += cantidad;
        }

        System.out.println("Total de alquileres: " + totalAlquileres);
        System.out.println("======================================");
    }

    /**
     * Muestra el total pagado por cada cliente utilizando
     * los resultados agrupados desde MySQL.
     *
     * @param clientes lista de clientes registrados.
     * @param resultados resultados de GROUP BY y SUM.
     */
    public void mostrarPagosPorClienteGroupBy(
            List<Customer> clientes,
            List<Object[]> resultados) {

        if (clientes == null || resultados == null) {
            System.out.println("No hay datos disponibles.");
            return;
        }

        Map<Integer, BigDecimal> pagosPorCliente =
                new HashMap<>();

        // Inicializar todos los clientes en cero.
        for (Customer cliente : clientes) {
            pagosPorCliente.put(
                    cliente.getCustomerId(), BigDecimal.ZERO);
        }

        // Recibir los resultados agrupados desde MySQL.
        for (Object[] fila : resultados) {

            int idCliente = (Integer) fila[0];
            BigDecimal totalPagado = (BigDecimal) fila[1];

            pagosPorCliente.put(idCliente, totalPagado);
        }

        System.out.println("\n======================================");
        System.out.println(" PAGOS POR CLIENTE - GROUP BY");
        System.out.println("======================================");

        for (Customer cliente : clientes) {

            BigDecimal total = pagosPorCliente.get(
                    cliente.getCustomerId());

            System.out.println(
                    "Cliente: " + cliente.getFirstName()
                    + " " + cliente.getLastName()
                    + " | ID: " + cliente.getCustomerId()
                    + " | Total pagado: RD$ "
                    + total.setScale(2, java.math.RoundingMode.HALF_UP));
        }

        System.out.println("--------------------------------------");
        System.out.println("Total de clientes: " + clientes.size());

        BigDecimal totalGeneral = BigDecimal.ZERO;

        for (BigDecimal cantidad : pagosPorCliente.values()) {
            totalGeneral = totalGeneral.add(cantidad);
        }

        System.out.println(
                "Total general recaudado: RD$ "
                + totalGeneral.setScale(2, java.math.RoundingMode.HALF_UP));

        System.out.println("======================================");
    }
}
