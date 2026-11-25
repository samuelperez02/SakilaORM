
package com.sakila.controlers;

import java.sql.Connection;
import java.sql.SQLException;

import com.sakila.data.ActorDataContext;
import com.sakila.data.Conexion;
import com.sakila.models.Actor;

public class PruebaEliminarActor {

    public static void main(String[] args) {

        // Conectamos con MySQL
        Connection conexion = Conexion.conectar();

        if (conexion == null) {
            System.out.println("No se pudo conectar con MySQL.");
            return;
        }

        ActorDataContext datos = new ActorDataContext(conexion);
        ActorController controlador = new ActorController(datos);

        try {

            // Eliminamos el actor con ID 201
            Actor actor201 = controlador.buscarActor(201);

            if (actor201 != null) {

                controlador.eliminarActor(201);

                System.out.println("Actor 201 eliminado.");
            } else {
                System.out.println("El actor 201 no existe.");
            }

            // Eliminamos el actor con ID 202
            Actor actor202 = controlador.buscarActor(202);

            if (actor202 != null) {

                controlador.eliminarActor(202);

                System.out.println("Actor 202 eliminado.");
            } else {
                System.out.println("El actor 202 no existe.");
            }

            // Verificamos que ya no existan
            System.out.println("\nVERIFICACION:");

            if (controlador.buscarActor(201) == null) {
                System.out.println("Actor 201: eliminado correctamente.");
            }

            if (controlador.buscarActor(202) == null) {
                System.out.println("Actor 202: eliminado correctamente.");
            }

        } finally {

            try {
                conexion.close();
                System.out.println("\nConexion cerrada.");

            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion: "
                        + e.getMessage());
            }
        }
    }
}
