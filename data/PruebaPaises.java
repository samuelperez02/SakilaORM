
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import java.sql.Connection;
import java.util.List;

import com.sakila.controlers.CountryController;
import com.sakila.models.Country;

/**
 * Clase encargada de probar la consulta y el listado
 * de registros de la tabla Country.
 *
 * Obtiene los paises almacenados en la base de datos
 * y muestra sus identificadores, nombres y el total
 * de registros encontrados.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaPaises {

    /**
     * Método principal que ejecuta la prueba de consulta
     * y listado de paises.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion != null) {

            CountryDataContext contexto =
                    new CountryDataContext(conexion);

            CountryController controlador =
                    new CountryController(contexto);

            List<Country> paises = controlador.listarPaises();

            System.out.println("LISTADO DE PAISES");
            System.out.println("----------------------------");

            for (Country pais : paises) {

                System.out.println("ID: " + pais.getCountryId());
                System.out.println("Pais: " + pais.getCountry());
                System.out.println("----------------------------");
            }

            System.out.println("Total de paises: " + paises.size());

            try {
                conexion.close();
                System.out.println("Conexion cerrada correctamente.");
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion.");
            }
        }
    }
}
