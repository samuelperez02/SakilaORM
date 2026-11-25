
package com.sakila.controlers;

import java.sql.Connection;
import java.sql.SQLException;

import com.sakila.data.Conexion;
import com.sakila.data.FilmActorDataContext;
import com.sakila.models.FilmActor;

public class PruebaCRUDCompletoFilmActor {

    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion == null) {
            System.out.println("No se pudo conectar con Sakila.");
            return;
        }

        FilmActorDataContext contexto =
                new FilmActorDataContext(conexion);

        FilmActorController controlador =
                new FilmActorController(contexto);

        int actorId = 0;
        int filmId = 0;
        boolean insertado = false;

        try {
            // BUSCAR una combinacion que todavia no exista
            boolean encontrada = false;

            for (int actor = 1; actor <= 200 && !encontrada; actor++) {

                for (int pelicula = 1; pelicula <= 1000; pelicula++) {

                    if (controlador.buscar(actor, pelicula) == null) {
                        actorId = actor;
                        filmId = pelicula;
                        encontrada = true;
                        break;
                    }
                }
            }

            if (!encontrada) {
                System.out.println("No se encontro una combinacion disponible.");
                return;
            }

            System.out.println("Combinacion disponible:");
            System.out.println("Actor ID: " + actorId);
            System.out.println("Film ID: " + filmId);

            // INSERTAR
            FilmActor relacion = new FilmActor();
            relacion.setActorId(actorId);
            relacion.setFilmId(filmId);

            controlador.registrar(relacion);
            insertado = true;

            System.out.println("INSERTAR: correcto.");

            // ACTUALIZAR
            controlador.actualizar(relacion);

            System.out.println("ACTUALIZAR: correcto.");

            // BUSCAR
            FilmActor resultado = controlador.buscar(actorId, filmId);

            if (resultado != null) {
                System.out.println("BUSCAR: correcto.");
                System.out.println("Actor ID: " + resultado.getActorId());
                System.out.println("Film ID: " + resultado.getFilmId());
            } else {
                System.out.println("No se encontro la relacion insertada.");
            }

            // ELIMINAR
            controlador.eliminar(actorId, filmId);
            insertado = false;

            System.out.println("ELIMINAR: correcto.");

            // VERIFICAR que ya no exista
            FilmActor verificacion = controlador.buscar(actorId, filmId);

            if (verificacion == null) {
                System.out.println("VERIFICACION: relacion eliminada.");
            } else {
                System.out.println("ADVERTENCIA: la relacion todavia existe.");
            }

        } catch (Exception e) {
            System.out.println("Ocurrio un error: " + e.getMessage());

        } finally {

            // Intentar limpiar la relacion si ocurrio un error
            if (insertado) {
                try {
                    controlador.eliminar(actorId, filmId);
                    System.out.println("Se limpio el registro de prueba.");
                } catch (Exception e) {
                    System.out.println("No se pudo limpiar el registro: "
                            + e.getMessage());
                }
            }

            try {
                conexion.close();
                System.out.println("Conexion cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion: "
                        + e.getMessage());
            }
        }
    }
}
