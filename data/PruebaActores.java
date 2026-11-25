
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import com.sakila.controlers.ActorController;
import com.sakila.models.Actor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Clase encargada de probar la consulta de actores
 * registrados en la base de datos Sakila.
 *
 * Utiliza ActorDataContext y ActorController para
 * obtener los registros y mostrarlos en consola.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaActores {

    /**
     * Método principal que ejecuta la prueba.
     * Establece la conexión, consulta los actores,
     * muestra sus datos y cierra la conexión.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion != null) {

            try {

                ActorDataContext datos =
                        new ActorDataContext(conexion);

                ActorController controlador =
                        new ActorController(datos);

                List<Actor> actores =
                        controlador.listarActores();

                System.out.println("LISTA DE ACTORES DE SAKILA");
                System.out.println("---------------------------");

                for (Actor actor : actores) {

                    System.out.println(
                            actor.getActorId() + " - "
                            + actor.getFirstName() + " "
                            + actor.getLastName()
                    );
                }

                System.out.println("---------------------------");
                System.out.println("Total de actores: "
                        + actores.size());

            } finally {

                try {
                    conexion.close();
                    System.out.println("Conexion cerrada.");

                } catch (SQLException e) {
                    System.out.println("Error al cerrar conexion.");
                }
            }
        }
    }
}

