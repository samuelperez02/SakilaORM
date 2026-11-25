
package com.sakila.controlers;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sakila.data.Conexion;
import com.sakila.data.FilmDataContext;
import com.sakila.models.Film;

public class PruebaCRUDCompletoFilm {

    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion == null) {
            System.out.println("No se pudo conectar con Sakila.");
            return;
        }

        FilmDataContext contexto = new FilmDataContext(conexion);
        FilmController controlador = new FilmController(contexto);

        int filmId = 0;
        boolean insertado = false;

        String tituloPrueba =
                "PRUEBA CRUD " + System.currentTimeMillis();

        try {

            // CREAR pelicula temporal
            Film pelicula = new Film();

            pelicula.setTitle(tituloPrueba);
            pelicula.setDescription("Pelicula temporal para probar el CRUD.");
            pelicula.setReleaseYear(2026);
            pelicula.setLanguageId(1);
            pelicula.setOriginalLanguageId(null);
            pelicula.setRentalDuration(5);
            pelicula.setRentalRate(new BigDecimal("0.99"));
            pelicula.setLength(90);
            pelicula.setReplacementCost(new BigDecimal("19.99"));
            pelicula.setRating("PG");
            pelicula.setSpecialFeatures("Trailers");

            // INSERTAR
            controlador.registrarPelicula(pelicula);
            insertado = true;

            System.out.println("INSERTAR: correcto.");

            // OBTENER EL ID GENERADO POR MYSQL
            String sql = "SELECT film_id FROM film WHERE title = ?";

            try (PreparedStatement sentencia =
                    conexion.prepareStatement(sql)) {

                sentencia.setString(1, tituloPrueba);

                try (ResultSet resultado = sentencia.executeQuery()) {

                    if (resultado.next()) {
                        filmId = resultado.getInt("film_id");
                    }
                }
            }

            if (filmId == 0) {
                throw new RuntimeException(
                        "No se pudo recuperar el ID de la pelicula.");
            }

            System.out.println("ID generado: " + filmId);

            // ACTUALIZAR
            pelicula.setFilmId(filmId);
            pelicula.setTitle("PELICULA CRUD ACTUALIZADA");

            controlador.actualizarPelicula(pelicula);

            System.out.println("ACTUALIZAR: correcto.");

            // BUSCAR
            Film encontrada = controlador.buscarPelicula(filmId);

            if (encontrada != null) {

                System.out.println("BUSCAR: correcto.");
                System.out.println("ID: " + encontrada.getFilmId());
                System.out.println("Titulo: " + encontrada.getTitle());

            } else {
                throw new RuntimeException(
                        "No se encontro la pelicula.");
            }

            // ELIMINAR
            controlador.eliminarPelicula(filmId);
            insertado = false;

            System.out.println("ELIMINAR: correcto.");

            // VERIFICAR ELIMINACION
            Film verificacion = controlador.buscarPelicula(filmId);

            if (verificacion == null) {
                System.out.println("VERIFICACION: pelicula eliminada.");
            } else {
                System.out.println("ADVERTENCIA: la pelicula todavia existe.");
            }

        } catch (Exception e) {

            System.out.println("Ocurrio un error: " + e.getMessage());

        } finally {

            // LIMPIAR si ocurre algun error despues de insertar
            if (insertado && filmId != 0) {

                try {
                    controlador.eliminarPelicula(filmId);
                    System.out.println("Se limpio la pelicula de prueba.");

                } catch (Exception e) {
                    System.out.println(
                            "No se pudo limpiar la pelicula: "
                            + e.getMessage());
                }
            }

            try {
                conexion.close();
                System.out.println("Conexion cerrada.");

            } catch (SQLException e) {
                System.out.println(
                        "Error al cerrar la conexion: " + e.getMessage());
            }
        }
    }
}
