
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

import com.sakila.models.Customer;
import com.sakila.models.Payment;
import com.sakila.models.Rental;

/**
 * Clase encargada de generar estadísticas generales
 * relacionadas con los clientes de la base de datos Sakila.
 *
 * Permite identificar al cliente que más ha pagado,
 * al que menos ha pagado, el promedio pagado por cliente
 * y el cliente con mayor cantidad de alquileres.
 *
 * Utiliza HashMap para organizar los datos por cliente
 * y BigDecimal para realizar cálculos monetarios.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class EstadisticasGeneralesClientes {

    /**
     * Calcula y muestra las estadísticas generales de los clientes,
     * tomando en cuenta sus pagos y alquileres.
     *
     * @param clientes lista de clientes registrados.
     * @param pagos lista de pagos realizados por los clientes.
     * @param alquileres lista de alquileres realizados.
     */
    public void mostrarEstadisticas(
            List<Customer> clientes,
            List<Payment> pagos,
            List<Rental> alquileres) {

        if (clientes == null || pagos == null || alquileres == null
                || clientes.isEmpty()) {

            System.out.println("No hay datos disponibles.");
            return;
        }

        // GUARDAR EL TOTAL PAGADO POR CADA CLIENTE.
        Map<Integer, BigDecimal> pagosPorCliente = new HashMap<>();

        // GUARDAR LA CANTIDAD DE ALQUILERES POR CLIENTE.
        Map<Integer, Integer> alquileresPorCliente = new HashMap<>();

        // INICIALIZAR LOS CLIENTES.
        for (Customer cliente : clientes) {

            pagosPorCliente.put(
                    cliente.getCustomerId(), BigDecimal.ZERO);

            alquileresPorCliente.put(
                    cliente.getCustomerId(), 0);
        }

        // SUMAR LOS PAGOS.
        for (Payment pago : pagos) {

            int idCliente = pago.getCustomerId();

            if (pago.getAmount() != null
                    && pagosPorCliente.containsKey(idCliente)) {

                BigDecimal total = pagosPorCliente.get(idCliente);

                pagosPorCliente.put(
                        idCliente, total.add(pago.getAmount()));
            }
        }

        // CONTAR LOS ALQUILERES.
        for (Rental alquiler : alquileres) {

            int idCliente = alquiler.getCustomerId();

            if (alquileresPorCliente.containsKey(idCliente)) {

                int cantidad = alquileresPorCliente.get(idCliente);

                alquileresPorCliente.put(
                        idCliente, cantidad + 1);
            }
        }

        // VARIABLES PARA IDENTIFICAR LOS RESULTADOS.
        Customer clienteMayorPago = null;
        Customer clienteMenorPago = null;
        Customer clienteMayorAlquiler = null;

        BigDecimal mayorPago = null;
        BigDecimal menorPago = null;

        int mayorCantidadAlquileres = -1;

        BigDecimal totalGeneral = BigDecimal.ZERO;

        // BUSCAR LOS CLIENTES CON MAYORES Y MENORES VALORES.
        for (Customer cliente : clientes) {

            int idCliente = cliente.getCustomerId();

            BigDecimal total = pagosPorCliente.get(idCliente);

            int cantidad = alquileresPorCliente.get(idCliente);

            totalGeneral = totalGeneral.add(total);

            if (mayorPago == null
                    || total.compareTo(mayorPago) > 0) {

                mayorPago = total;
                clienteMayorPago = cliente;
            }

            if (menorPago == null
                    || total.compareTo(menorPago) < 0) {

                menorPago = total;
                clienteMenorPago = cliente;
            }

            if (cantidad > mayorCantidadAlquileres) {

                mayorCantidadAlquileres = cantidad;
                clienteMayorAlquiler = cliente;
            }
        }

        // CALCULAR EL PROMEDIO POR CLIENTE.
        BigDecimal promedio = totalGeneral.divide(
                BigDecimal.valueOf(clientes.size()),
                2,
                RoundingMode.HALF_UP);

        // MOSTRAR LOS RESULTADOS.
        System.out.println("\n======================================");
        System.out.println("   ESTADISTICAS GENERALES DE CLIENTES");
        System.out.println("======================================");

        System.out.println(
                "Cliente que mas ha pagado: "
                + clienteMayorPago.getFirstName()
                + " " + clienteMayorPago.getLastName()
                + " | RD$ " + mayorPago);

        System.out.println(
                "Cliente que menos ha pagado: "
                + clienteMenorPago.getFirstName()
                + " " + clienteMenorPago.getLastName()
                + " | RD$ " + menorPago);

        System.out.println(
                "Promedio pagado por cliente: RD$ " + promedio);

        System.out.println(
                "Cliente con mas alquileres: "
                + clienteMayorAlquiler.getFirstName()
                + " " + clienteMayorAlquiler.getLastName()
                + " | Alquileres: " + mayorCantidadAlquileres);

        System.out.println("--------------------------------------");
        System.out.println("Clientes analizados: " + clientes.size());
        System.out.println("Pagos analizados: " + pagos.size());
        System.out.println("Alquileres analizados: " + alquileres.size());
        System.out.println("Total recaudado: RD$ " + totalGeneral);

        System.out.println("======================================");
    }
}
