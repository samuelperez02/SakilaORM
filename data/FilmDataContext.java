
/**
 * Copyright (c) 2026.
 * Proyecto final de Lenguaje de Programación II.
 *
 * Clase encargada de administrar el acceso a los datos
 * de la tabla film de la base de datos Sakila.
 *
 * @author Samuel Elias Perez Ramirez
 * @version 1.0
 */
package com.sakila.data;

import com.sakila.models.Film;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DataContext concreto encargado de gestionar las películas.
 *
 * Permite insertar, actualizar, buscar, listar y eliminar
 * registros de la tabla film.
 */
public final class FilmDataContext extends DataContext<Film> {

    /**
     * Constructor que recibe la conexión con la base de datos.
     *
     * @param conexion conexión activa con MySQL.
     */
    public FilmDataContext(java.sql.Connection conexion) {
        super(conexion);
    }

    /**
     * Inserta una nueva película en la tabla film.
     *
     * Guarda los datos de la película, incluyendo el título,
     * descripción, idiomas, duración, precios y características.
     * Recupera el identificador generado automáticamente por MySQL
     * y lo asigna al objeto Film.
     *
     * @param film objeto que contiene los datos de la película.
     * @throws SQLException si ocurre un error durante la inserción.
     */
    @Override
    protected void insertar(Film film) throws SQLException {

        String sql = "INSERT INTO film "
                + "(title, description, release_year, language_id, "
                + "original_language_id, rental_duration, rental_rate, "
                + "length, replacement_cost, rating, special_features) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement sentencia =
                conexion.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

            sentencia.setString(1, film.getTitle());
            sentencia.setString(2, film.getDescription());
            sentencia.setObject(3, film.getReleaseYear());
            sentencia.setInt(4, film.getLanguageId());
            sentencia.setObject(5, film.getOriginalLanguageId());
            sentencia.setInt(6, film.getRentalDuration());
            sentencia.setBigDecimal(7, film.getRentalRate());
            sentencia.setObject(8, film.getLength());
            sentencia.setBigDecimal(9, film.getReplacementCost());
            sentencia.setString(10, film.getRating());
            sentencia.setString(11, film.getSpecialFeatures());

            sentencia.executeUpdate();

            try (ResultSet resultado = sentencia.getGeneratedKeys()) {

                if (resultado.next()) {
                    film.setFilmId(resultado.getInt(1));
                }
            }
        }
    }

    /**
     * Actualiza los datos de una película existente.
     *
     * Modifica sus datos descriptivos, idiomas, duración,
     * precios, clasificación y características especiales.
     *
     * @param film objeto que contiene el identificador
     *             y los nuevos datos de la película.
     * @throws SQLException si ocurre un error durante la actualización.
     */
    @Override
    protected void actualizar(Film film) throws SQLException {

        String sql = "UPDATE film SET title = ?, description = ?, "
                + "release_year = ?, language_id = ?, "
                + "original_language_id = ?, rental_duration = ?, "
                + "rental_rate = ?, length = ?, replacement_cost = ?, "
                + "rating = ?, special_features = ? WHERE film_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, film.getTitle());
            sentencia.setString(2, film.getDescription());
            sentencia.setObject(3, film.getReleaseYear());
            sentencia.setInt(4, film.getLanguageId());
            sentencia.setObject(5, film.getOriginalLanguageId());
            sentencia.setInt(6, film.getRentalDuration());
            sentencia.setBigDecimal(7, film.getRentalRate());
            sentencia.setObject(8, film.getLength());
            sentencia.setBigDecimal(9, film.getReplacementCost());
            sentencia.setString(10, film.getRating());
            sentencia.setString(11, film.getSpecialFeatures());
            sentencia.setInt(12, film.getFilmId());

            sentencia.executeUpdate();
        }
    }

    /**
     * Busca una película mediante su identificador.
     *
     * @param id identificador de la película que se desea consultar.
     * @return objeto Film encontrado o null si no existe.
     * @throws SQLException si ocurre un error durante la consulta.
     */
    @Override
    protected Film buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM film WHERE film_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {
                    return convertirFilm(resultado);
                }
            }
        }

        return null;
    }

    /**
     * Recupera todas las películas registradas en la base de datos.
     *
     * @return lista de objetos Film.
     * @throws SQLException si ocurre un error durante la consulta.
     */
    @Override
    protected List<Film> listar() throws SQLException {

        List<Film> peliculas = new ArrayList<>();

        String sql = "SELECT * FROM film";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                peliculas.add(convertirFilm(resultado));
            }
        }

        return peliculas;
    }

    /**
     * Elimina una película utilizando su identificador.
     *
     * @param id identificador de la película que se desea eliminar.
     * @throws SQLException si ocurre un error al eliminar el registro,
     *                      por ejemplo, debido a registros relacionados.
     */
    @Override
    protected void eliminar(int id) throws SQLException {

        String sql = "DELETE FROM film WHERE film_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);
            sentencia.executeUpdate();
        }
    }

    /**
     * Convierte una fila del resultado SQL en un objeto Film.
     *
     * Recupera los campos de la película y conserva los valores null
     * de release_year, original_language_id y length.
     *
     * @param resultado resultado obtenido de la consulta SQL.
     * @return objeto Film con los datos recuperados.
     * @throws SQLException si ocurre un error al leer los campos.
     */
    private Film convertirFilm(ResultSet resultado) throws SQLException {

        Film film = new Film();

        film.setFilmId(resultado.getInt("film_id"));
        film.setTitle(resultado.getString("title"));
        film.setDescription(resultado.getString("description"));

        int anio = resultado.getInt("release_year");
        film.setReleaseYear(resultado.wasNull() ? null : anio);

        film.setLanguageId(resultado.getInt("language_id"));

        int idiomaOriginal = resultado.getInt("original_language_id");
        film.setOriginalLanguageId(
                resultado.wasNull() ? null : idiomaOriginal);

        film.setRentalDuration(resultado.getInt("rental_duration"));
        film.setRentalRate(resultado.getBigDecimal("rental_rate"));

        int duracion = resultado.getInt("length");
        film.setLength(resultado.wasNull() ? null : duracion);

        film.setReplacementCost(
                resultado.getBigDecimal("replacement_cost"));

        film.setRating(resultado.getString("rating"));
        film.setSpecialFeatures(resultado.getString("special_features"));
        film.setLastUpdate(resultado.getTimestamp("last_update"));

        return film;
    }
}

