/*
 * Copyright (c) 2026
 * Proyecto Final - SakilaORM
 * Lenguaje de Programación II
 */

package com.sakila.data;

import com.sakila.models.FilmText;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de administrar las operaciones de acceso a datos
 * de la tabla film_text de la base de datos Sakila.
 *
 * Esta clase permite insertar, actualizar, buscar, listar y eliminar
 * registros relacionados con el texto de las peliculas.
 *
 * @author
 * @version 1.0
 */
public final class FilmTextDataContext extends DataContext<FilmText> {

    /**
     * Construye el contexto de acceso a datos para la tabla film_text.
     *
     * @param conexion conexion activa con la base de datos
     */
    public FilmTextDataContext(java.sql.Connection conexion) {
        super(conexion);
    }

    /**
     * Inserta un nuevo registro en la tabla film_text.
     *
     * @param pelicula objeto FilmText que contiene los datos que seran
     *                 almacenados en la base de datos
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected void insertar(FilmText pelicula) throws SQLException {

        String sql = "INSERT INTO film_text "
                + "(film_id, title, description) "
                + "VALUES (?, ?, ?)";

        try (PreparedStatement sentencia =
                conexion.prepareStatement(sql)) {

            sentencia.setInt(1, pelicula.getFilmId());
            sentencia.setString(2, pelicula.getTitle());
            sentencia.setString(3, pelicula.getDescription());

            sentencia.executeUpdate();
        }
    }

    /**
     * Actualiza el titulo y la descripcion de una pelicula
     * existente en la tabla film_text.
     *
     * @param pelicula objeto FilmText que contiene los nuevos datos
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected void actualizar(FilmText pelicula) throws SQLException {

        String sql = "UPDATE film_text SET "
                + "title = ?, description = ? "
                + "WHERE film_id = ?";

        try (PreparedStatement sentencia =
                conexion.prepareStatement(sql)) {

            sentencia.setString(1, pelicula.getTitle());
            sentencia.setString(2, pelicula.getDescription());
            sentencia.setInt(3, pelicula.getFilmId());

            sentencia.executeUpdate();
        }
    }

    /**
     * Busca un registro de film_text utilizando su identificador.
     *
     * @param id identificador de la pelicula que se desea buscar
     * @return objeto FilmText encontrado o null si no existe
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected FilmText buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM film_text WHERE film_id = ?";

        try (PreparedStatement sentencia =
                conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {
                    return convertirFilmText(resultado);
                }
            }
        }

        return null;
    }

    /**
     * Obtiene todos los registros almacenados en la tabla film_text.
     *
     * @return lista de objetos FilmText encontrados en la base de datos
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected List<FilmText> listar() throws SQLException {

        List<FilmText> peliculas = new ArrayList<>();

        String sql = "SELECT * FROM film_text";

        try (PreparedStatement sentencia =
                conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                peliculas.add(convertirFilmText(resultado));
            }
        }

        return peliculas;
    }

    /**
     * Elimina un registro de la tabla film_text utilizando
     * el identificador de la pelicula.
     *
     * @param id identificador de la pelicula que se desea eliminar
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected void eliminar(int id) throws SQLException {

        String sql = "DELETE FROM film_text WHERE film_id = ?";

        try (PreparedStatement sentencia =
                conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            sentencia.executeUpdate();
        }
    }

    /**
     * Convierte los datos obtenidos desde MySQL en un objeto FilmText.
     *
     * @param resultado conjunto de resultados obtenido mediante
     *                  una consulta SQL
     * @return objeto FilmText con los datos obtenidos de la base de datos
     * @throws SQLException si ocurre un error al leer los datos
     */
    private FilmText convertirFilmText(ResultSet resultado)
            throws SQLException {

        FilmText pelicula = new FilmText();

        pelicula.setFilmId(resultado.getInt("film_id"));
        pelicula.setTitle(resultado.getString("title"));
        pelicula.setDescription(resultado.getString("description"));

        return pelicula;
    }
}
