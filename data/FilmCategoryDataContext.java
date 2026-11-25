
/*
 * Copyright (c) 2026
 * Proyecto Final - SakilaORM
 * Lenguaje de Programación II
 */

package com.sakila.data;

import com.sakila.models.FilmCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de administrar las operaciones de acceso a datos
 * de la tabla film_category de la base de datos Sakila.
 *
 * Esta tabla representa la relacion entre peliculas y categorias.
 * Utiliza una clave primaria compuesta por film_id y category_id.
 *
 * @author
 * @version 1.0
 */
public final class FilmCategoryDataContext
        extends DataContext<FilmCategory>
        implements IDataPostCompuesto<FilmCategory> {

    /**
     * Construye el contexto de acceso a datos para las relaciones
     * entre peliculas y categorias.
     *
     * @param conexion conexion activa con la base de datos
     */
    public FilmCategoryDataContext(java.sql.Connection conexion) {
        super(conexion);
    }

    /**
     * Inserta una nueva relacion entre una pelicula y una categoria.
     *
     * @param relacion objeto FilmCategory que contiene los identificadores
     *                 de la pelicula y la categoria
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected void insertar(FilmCategory relacion) throws SQLException {

        String sql = "INSERT INTO film_category "
                + "(film_id, category_id) VALUES (?, ?)";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, relacion.getFilmId());
            sentencia.setInt(2, relacion.getCategoryId());

            sentencia.executeUpdate();
        }
    }

    /**
     * Actualiza la fecha de modificacion de una relacion existente.
     *
     * @param relacion objeto FilmCategory que identifica la relacion
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected void actualizar(FilmCategory relacion) throws SQLException {

        String sql = "UPDATE film_category "
                + "SET last_update = CURRENT_TIMESTAMP "
                + "WHERE film_id = ? AND category_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, relacion.getFilmId());
            sentencia.setInt(2, relacion.getCategoryId());

            sentencia.executeUpdate();
        }
    }

    /**
     * Impide realizar busquedas utilizando solamente un identificador,
     * debido a que esta tabla utiliza una clave primaria compuesta.
     *
     * @param id identificador individual que no permite localizar
     *           una relacion de forma unica
     * @return no retorna un objeto
     * @throws UnsupportedOperationException cuando se intenta utilizar
     *         una sola clave
     * @throws SQLException si ocurre un error relacionado con la consulta
     */
    @Override
    protected FilmCategory buscarPorId(int id) throws SQLException {

        throw new UnsupportedOperationException(
                "Utilice Get con filmId y categoryId.");
    }

    /**
     * Busca una relacion utilizando los identificadores de pelicula
     * y categoria.
     *
     * @param filmId identificador de la pelicula
     * @param categoryId identificador de la categoria
     * @return objeto FilmCategory encontrado o null si no existe
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    public FilmCategory buscarPorClave(int filmId, int categoryId)
            throws SQLException {

        String sql = "SELECT * FROM film_category "
                + "WHERE film_id = ? AND category_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, filmId);
            sentencia.setInt(2, categoryId);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {
                    return convertirFilmCategory(resultado);
                }
            }
        }

        return null;
    }

    /**
     * Implementa la busqueda de una relacion mediante dos identificadores.
     *
     * @param id1 identificador de la pelicula
     * @param id2 identificador de la categoria
     * @return objeto FilmCategory encontrado o null si no existe
     * @throws RuntimeException si ocurre un error durante la busqueda
     */
    @Override
    public FilmCategory Get(int id1, int id2) {

        try {
            return buscarPorClave(id1, id2);

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error al buscar la relacion entre pelicula y categoria.", e);
        }
    }

    /**
     * Obtiene todas las relaciones registradas en la tabla film_category.
     *
     * @return lista con todas las relaciones entre peliculas y categorias
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected List<FilmCategory> listar() throws SQLException {

        List<FilmCategory> relaciones = new ArrayList<>();

        String sql = "SELECT * FROM film_category";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                relaciones.add(convertirFilmCategory(resultado));
            }
        }

        return relaciones;
    }

    /**
     * Impide eliminar una relacion utilizando solamente un identificador,
     * porque se necesitan las dos partes de la clave primaria compuesta.
     *
     * @param id identificador individual que no permite localizar
     *           una relacion de forma unica
     * @throws UnsupportedOperationException cuando se intenta eliminar
     *         utilizando una sola clave
     * @throws SQLException si ocurre un error relacionado con la consulta
     */
    @Override
    protected void eliminar(int id) throws SQLException {

        throw new UnsupportedOperationException(
                "Utilice Delete con filmId y categoryId.");
    }

    /**
     * Elimina una relacion utilizando los identificadores de pelicula
     * y categoria.
     *
     * @param filmId identificador de la pelicula
     * @param categoryId identificador de la categoria
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    public void eliminarPorClave(int filmId, int categoryId)
            throws SQLException {

        String sql = "DELETE FROM film_category "
                + "WHERE film_id = ? AND category_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, filmId);
            sentencia.setInt(2, categoryId);

            sentencia.executeUpdate();
        }
    }

    /**
     * Implementa la eliminacion de una relacion mediante dos identificadores.
     *
     * @param id1 identificador de la pelicula
     * @param id2 identificador de la categoria
     * @throws RuntimeException si ocurre un error durante la eliminacion
     */
    @Override
    public void Delete(int id1, int id2) {

        try {
            eliminarPorClave(id1, id2);

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error al eliminar la relacion entre pelicula y categoria.", e);
        }
    }

    /**
     * Convierte los datos obtenidos de MySQL en un objeto FilmCategory.
     *
     * @param resultado conjunto de resultados devuelto por la consulta SQL
     * @return objeto FilmCategory con sus datos correspondientes
     * @throws SQLException si ocurre un error al leer los resultados
     */
    private FilmCategory convertirFilmCategory(ResultSet resultado)
            throws SQLException {

        FilmCategory relacion = new FilmCategory();

        relacion.setFilmId(resultado.getInt("film_id"));
        relacion.setCategoryId(resultado.getInt("category_id"));
        relacion.setLastUpdate(resultado.getTimestamp("last_update"));

        return relacion;
    }
}

