
package com.sakila.controlers;

import java.sql.Connection;
import java.sql.SQLException;

import com.sakila.data.ActorDataContext;
import com.sakila.data.Conexion;
import com.sakila.models.Actor;

public class PruebaCRUDActor {

    public static void main(String[] args) {

        // Conectamos con la base de datos
        Connection conexion = Conexion.conectar();

        if (conexion == null) {
            System.out.println("No se pudo conectar con MySQL.");
            return;
        }

        // Creamos el contexto y el controlador
        ActorDataContext datos = new ActorDataContext(conexion);
        ActorController controlador = new ActorController(datos);

        try {

            // Buscamos el actor de prueba
            Actor actorEncontrado = null;

            for (Actor actor : controlador.listarActores()) {

                if (actor.getFirstName().equals("PRUEBA")
                        && actor.getLastName().equals("CRUD_SAKILA")) {

                    actorEncontrado = actor;
                    break;
                }
            }

            // Si no existe, lo registramos
            if (actorEncontrado == null) {

                Actor nuevoActor = new Actor();

                nuevoActor.setFirstName("PRUEBA");
                nuevoActor.setLastName("CRUD_SAKILA");

                controlador.registrarActor(nuevoActor);

                System.out.println("Actor registrado correctamente.");

                // Buscamos el registro que acabamos de insertar
                for (Actor actor : controlador.listarActores()) {

                    if (actor.getFirstName().equals("PRUEBA")
                            && actor.getLastName().equals("CRUD_SAKILA")) {

                        actorEncontrado = actor;
                        break;
                    }
                }

            } else {

                System.out.println("El actor de prueba ya existe.");
            }

            // Actualizamos el actor
            if (actorEncontrado != null) {

                actorEncontrado.setFirstName("SAMUEL");
                actorEncontrado.setLastName("ACTUALIZADO");

                controlador.actualizarActor(actorEncontrado);

                System.out.println("\nActor actualizado correctamente.");

                // Consultamos el actor actualizado por su ID
                Actor actorVerificado =
                        controlador.buscarActor(actorEncontrado.getActorId());

                if (actorVerificado != null) {

                    System.out.println("\nDATOS ACTUALIZADOS:");

                    System.out.println("ID: "
                            + actorVerificado.getActorId());

                    System.out.println("Nombre: "
                            + actorVerificado.getFirstName());

                    System.out.println("Apellido: "
                            + actorVerificado.getLastName());
                }
            }

        } finally {

            // Cerramos la conexión
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

