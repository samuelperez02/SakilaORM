
package com.sakila.controlers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.sakila.data.Conexion;
import com.sakila.data.FilmActorDataContext;
import com.sakila.models.FilmActor;

public class PruebaBuscarFilmActor {

    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        try {
            FilmActorDataContext contexto =
                    new FilmActorDataContext(conexion);

            FilmActorController controlador =
                    new FilmActorController(contexto);

            // Obtener todas las relaciones
            List<FilmActor> relaciones = controlador.listar();

            System.out.println("TOTAL DE RELACIONES: "
                    + relaciones.size());

            // Buscar una relacion existente
            if (!relaciones.isEmpty()) {

                FilmActor primeraRelacion = relaciones.get(0);

                int actorId = primeraRelacion.getActorId();
                int filmId = primeraRelacion.getFilmId();

                FilmActor encontrada =
                        controlador.buscar(actorId, filmId);

                if (encontrada != null) {

                    System.out.println("BUSQUEDA EXITOSA");
                    System.out.println("Actor ID: "
                            + encontrada.getActorId());
                    System.out.println("Film ID: "
                            + encontrada.getFilmId());

                } else {
                    System.out.println("No se encontro la relacion.");
                }
            }

        } finally {

            try {
                if (conexion != null) {
                    conexion.close();
                    System.out.println("Conexion cerrada.");
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar: "
                        + e.getMessage());
            }
        }
    }
}
