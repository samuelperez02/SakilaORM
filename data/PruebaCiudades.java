
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import java.sql.Connection;
import java.util.List;

import com.sakila.controlers.CityController;
import com.sakila.models.City;

/**
 * Clase encargada de probar la consulta de ciudades
 * registradas en la base de datos Sakila.
 *
 * Utiliza CityDataContext y CityController para
 * obtener y mostrar la información de las ciudades.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaCiudades {

    /**
     * Método principal que ejecuta la prueba.
     * Establece la conexión, consulta las ciudades,
     * muestra sus datos y cierra la conexión.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion != null) {

            CityDataContext contexto = new CityDataContext(conexion);

            CityController controlador = new CityController(contexto);

            List<City> ciudades = controlador.listarCiudades();

            System.out.println("LISTADO DE CIUDADES");

            System.out.println("----------------------------");

            for (City ciudad : ciudades) {

                System.out.println("ID: " + ciudad.getCityId());

                System.out.println("Ciudad: " + ciudad.getCity());

                System.out.println("ID del pais: " + ciudad.getCountryId());

                System.out.println("----------------------------");

            }

            System.out.println("Total de ciudades: " + ciudades.size());

            try {

                conexion.close();

                System.out.println("Conexion cerrada correctamente.");

            } catch (Exception e) {

                System.out.println("Error al cerrar la conexion.");

            }

        }

    }

}

