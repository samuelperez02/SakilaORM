
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

import com.sakila.models.Store;
import com.sakila.models.Address;
import com.sakila.models.City;
import com.sakila.models.Country;
import com.sakila.models.Inventory;
import com.sakila.models.Rental;
import com.sakila.models.Payment;

/**
 * Clase encargada de generar estadísticas relacionadas
 * con las ubicaciones geográficas de las tiendas Sakila.
 *
 * Permite calcular la cantidad de alquileres y los pagos
 * realizados por ciudad y país.
 *
 * Para obtener estos resultados relaciona las tiendas
 * con sus direcciones, ciudades y países, además de
 * consultar los registros de inventario, alquileres y pagos.
 *
 * Utiliza HashMap para organizar los resultados y
 * BigDecimal para realizar cálculos monetarios.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class EstadisticasUbicaciones {

    /**
     * Calcula y muestra la cantidad de alquileres
     * correspondientes a cada ciudad y país.
     *
     * @param tiendas lista de tiendas registradas.
     * @param direcciones lista de direcciones registradas.
     * @param ciudades lista de ciudades registradas.
     * @param paises lista de países registrados.
     * @param inventarios lista de registros del inventario.
     * @param alquileres lista de alquileres realizados.
     */
    public void mostrarAlquileresPorUbicacion(
            List<Store> tiendas,
            List<Address> direcciones,
            List<City> ciudades,
            List<Country> paises,
            List<Inventory> inventarios,
            List<Rental> alquileres) {

        if (tiendas == null || direcciones == null
                || ciudades == null || paises == null
                || inventarios == null || alquileres == null) {

            System.out.println("No hay datos disponibles.");
            return;
        }

        // Contadores de alquileres por ciudad y pais.
        Map<Integer, Integer> alquileresPorCiudad = new HashMap<>();
        Map<Integer, Integer> alquileresPorPais = new HashMap<>();

        // Guardar los nombres de ciudades y paises.
        Map<Integer, String> nombresCiudades = new HashMap<>();
        Map<Integer, String> nombresPaises = new HashMap<>();

        // Inicializar las ciudades.
        for (City ciudad : ciudades) {
            alquileresPorCiudad.put(ciudad.getCityId(), 0);
            nombresCiudades.put(ciudad.getCityId(), ciudad.getCity());
        }

        // Inicializar los paises.
        for (Country pais : paises) {
            alquileresPorPais.put(pais.getCountryId(), 0);
            nombresPaises.put(pais.getCountryId(), pais.getCountry());
        }

        // Relacionar tiendas con ciudades y paises.
        Map<Integer, Integer> ciudadesPorTienda = new HashMap<>();
        Map<Integer, Integer> paisesPorTienda = new HashMap<>();

        for (Store tienda : tiendas) {

            int idDireccion = tienda.getAddressId();

            for (Address direccion : direcciones) {

                if (direccion.getAddressId() == idDireccion) {

                    int idCiudad = direccion.getCityId();

                    for (City ciudad : ciudades) {

                        if (ciudad.getCityId() == idCiudad) {

                            int idPais = ciudad.getCountryId();

                            ciudadesPorTienda.put(
                                    tienda.getStoreId(), idCiudad);

                            paisesPorTienda.put(
                                    tienda.getStoreId(), idPais);

                            break;
                        }
                    }

                    break;
                }
            }
        }

        // Contar los alquileres por ciudad y pais.
        for (Rental alquiler : alquileres) {

            int idInventario = alquiler.getInventoryId();

            for (Inventory inventario : inventarios) {

                if (inventario.getInventoryId() == idInventario) {

                    int idTienda = inventario.getStoreId();

                    // Contar por ciudad.
                    if (ciudadesPorTienda.containsKey(idTienda)) {

                        int idCiudad = ciudadesPorTienda.get(idTienda);

                        int cantidadCiudad =
                                alquileresPorCiudad.get(idCiudad);

                        alquileresPorCiudad.put(
                                idCiudad, cantidadCiudad + 1);
                    }

                    // Contar por pais.
                    if (paisesPorTienda.containsKey(idTienda)) {

                        int idPais = paisesPorTienda.get(idTienda);

                        int cantidadPais =
                                alquileresPorPais.get(idPais);

                        alquileresPorPais.put(
                                idPais, cantidadPais + 1);
                    }

                    break;
                }
            }
        }

        // MOSTRAR ALQUILERES POR CIUDAD.
        System.out.println("\n======================================");
        System.out.println("       ALQUILERES POR CIUDAD");
        System.out.println("======================================");

        for (Map.Entry<Integer, Integer> entrada
                : alquileresPorCiudad.entrySet()) {

            System.out.println(
                    "Ciudad: " + nombresCiudades.get(entrada.getKey())
                    + " | Total de alquileres: " + entrada.getValue());
        }

        // MOSTRAR ALQUILERES POR PAIS.
        System.out.println("\n======================================");
        System.out.println("        ALQUILERES POR PAIS");
        System.out.println("======================================");

        for (Map.Entry<Integer, Integer> entrada
                : alquileresPorPais.entrySet()) {

            System.out.println(
                    "Pais: " + nombresPaises.get(entrada.getKey())
                    + " | Total de alquileres: " + entrada.getValue());
        }

        System.out.println("======================================");
    }

    /**
     * Calcula y muestra el total de pagos correspondientes
     * a cada ciudad y país.
     *
     * Relaciona los pagos con los alquileres, el inventario,
     * las tiendas y sus respectivas ubicaciones.
     *
     * @param tiendas lista de tiendas registradas.
     * @param direcciones lista de direcciones registradas.
     * @param ciudades lista de ciudades registradas.
     * @param paises lista de países registrados.
     * @param inventarios lista de registros del inventario.
     * @param alquileres lista de alquileres realizados.
     * @param pagos lista de pagos registrados.
     */
    public void mostrarPagosPorUbicacion(
            List<Store> tiendas,
            List<Address> direcciones,
            List<City> ciudades,
            List<Country> paises,
            List<Inventory> inventarios,
            List<Rental> alquileres,
            List<Payment> pagos) {

        if (tiendas == null || direcciones == null
                || ciudades == null || paises == null
                || inventarios == null || alquileres == null
                || pagos == null) {

            System.out.println("No hay datos disponibles.");
            return;
        }

        // Guardar los pagos por ciudad y pais.
        Map<Integer, BigDecimal> pagosPorCiudad = new HashMap<>();
        Map<Integer, BigDecimal> pagosPorPais = new HashMap<>();

        // Guardar los nombres de ciudades y paises.
        Map<Integer, String> nombresCiudades = new HashMap<>();
        Map<Integer, String> nombresPaises = new HashMap<>();

        // Inicializar las ciudades.
        for (City ciudad : ciudades) {
            pagosPorCiudad.put(ciudad.getCityId(), BigDecimal.ZERO);
            nombresCiudades.put(ciudad.getCityId(), ciudad.getCity());
        }

        // Inicializar los paises.
        for (Country pais : paises) {
            pagosPorPais.put(pais.getCountryId(), BigDecimal.ZERO);
            nombresPaises.put(pais.getCountryId(), pais.getCountry());
        }

        // Relacionar tiendas con ciudades y paises.
        Map<Integer, Integer> ciudadesPorTienda = new HashMap<>();
        Map<Integer, Integer> paisesPorTienda = new HashMap<>();

        for (Store tienda : tiendas) {

            for (Address direccion : direcciones) {

                if (direccion.getAddressId() == tienda.getAddressId()) {

                    for (City ciudad : ciudades) {

                        if (ciudad.getCityId() == direccion.getCityId()) {

                            ciudadesPorTienda.put(
                                    tienda.getStoreId(),
                                    ciudad.getCityId());

                            paisesPorTienda.put(
                                    tienda.getStoreId(),
                                    ciudad.getCountryId());

                            break;
                        }
                    }

                    break;
                }
            }
        }

        // Recorrer todos los pagos.
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

                            // Sumar el pago por ciudad.
                            if (ciudadesPorTienda.containsKey(idTienda)) {

                                int idCiudad = ciudadesPorTienda.get(idTienda);

                                BigDecimal totalCiudad =
                                        pagosPorCiudad.get(idCiudad);

                                pagosPorCiudad.put(
                                        idCiudad,
                                        totalCiudad.add(pago.getAmount()));
                            }

                            // Sumar el pago por pais.
                            if (paisesPorTienda.containsKey(idTienda)) {

                                int idPais = paisesPorTienda.get(idTienda);

                                BigDecimal totalPais =
                                        pagosPorPais.get(idPais);

                                pagosPorPais.put(
                                        idPais,
                                        totalPais.add(pago.getAmount()));
                            }

                            break;
                        }
                    }

                    break;
                }
            }
        }

        // MOSTRAR PAGOS POR CIUDAD.
        System.out.println("\n======================================");
        System.out.println("          PAGOS POR CIUDAD");
        System.out.println("======================================");

        for (Map.Entry<Integer, BigDecimal> entrada
                : pagosPorCiudad.entrySet()) {

            if (entrada.getValue().compareTo(BigDecimal.ZERO) > 0) {

                System.out.println(
                        "Ciudad: " + nombresCiudades.get(entrada.getKey())
                        + " | Total recaudado: RD$ "
                        + entrada.getValue());
            }
        }

        // MOSTRAR PAGOS POR PAIS.
        System.out.println("\n======================================");
        System.out.println("           PAGOS POR PAIS");
        System.out.println("======================================");

        for (Map.Entry<Integer, BigDecimal> entrada
                : pagosPorPais.entrySet()) {

            if (entrada.getValue().compareTo(BigDecimal.ZERO) > 0) {

                System.out.println(
                        "Pais: " + nombresPaises.get(entrada.getKey())
                        + " | Total recaudado: RD$ "
                        + entrada.getValue());
            }
        }

        System.out.println("======================================");
    }
    

    /**
     * Muestra la cantidad de alquileres por ciudad y país
     * según la dirección registrada de cada cliente.
     *
     * Los datos son obtenidos mediante GROUP BY en MySQL.
     *
     * @param resultados lista con ciudad, país y cantidad de alquileres.
     */
    public void mostrarAlquileresPorCiudadPaisGroupBy(
            List<Object[]> resultados) {

        if (resultados == null || resultados.isEmpty()) {
            System.out.println("No hay datos disponibles.");
            return;
        }

        int totalGeneral = 0;

        System.out.println("\n======================================");
        System.out.println(" ALQUILERES POR CIUDAD Y PAIS");
        System.out.println("       GROUP BY MYSQL");
        System.out.println("======================================");

        for (Object[] fila : resultados) {

            String ciudad = (String) fila[0];
            String pais = (String) fila[1];
            int cantidad = (Integer) fila[2];

            totalGeneral += cantidad;

            System.out.println(
                    "Ciudad: " + ciudad
                    + " | Pais: " + pais
                    + " | Alquileres: " + cantidad);
        }

        System.out.println("--------------------------------------");
        System.out.println("TOTAL GENERAL DE ALQUILERES: "
                + totalGeneral);
        System.out.println("======================================");
    }
    

    /**
     * Muestra el total de pagos agrupados por ciudad y país
     * según la dirección registrada de cada cliente.
     *
     * Los datos son obtenidos mediante GROUP BY y SUM en MySQL.
     *
     * @param resultados lista con ciudad, país y total pagado.
     */
    public void mostrarPagosPorCiudadPaisGroupBy(
            List<Object[]> resultados) {

        if (resultados == null || resultados.isEmpty()) {
            System.out.println("No hay datos disponibles.");
            return;
        }

        BigDecimal totalGeneral = BigDecimal.ZERO;

        System.out.println("\n======================================");
        System.out.println("     PAGOS POR CIUDAD Y PAIS");
        System.out.println("          GROUP BY MYSQL");
        System.out.println("======================================");

        for (Object[] fila : resultados) {

            String ciudad = (String) fila[0];
            String pais = (String) fila[1];
            BigDecimal totalPagado = (BigDecimal) fila[2];

            totalGeneral = totalGeneral.add(totalPagado);

            System.out.println(
                    "Ciudad: " + ciudad
                    + " | Pais: " + pais
                    + " | Total pagado: RD$ "
                    + totalPagado.setScale(
                            2, java.math.RoundingMode.HALF_UP));
        }

        System.out.println("--------------------------------------");
        System.out.println("TOTAL GENERAL PAGADO: RD$ "
                + totalGeneral.setScale(
                        2, java.math.RoundingMode.HALF_UP));

        System.out.println("======================================");
    }

}
