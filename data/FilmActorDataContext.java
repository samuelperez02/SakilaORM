
/**
 * Copyright (c) 2026.
 * Proyecto final de Lenguaje de Programación II.
 *
 * Clase encargada de administrar las relaciones entre actores
 * y películas en la tabla film_actor de la base de datos Sakila.
 *
 * @author Samuel Elias Perez Ramirez
 * @version 1.0
 */
package com.sakila.data;

import com.sakila.models.FilmActor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DataContext concreto encargado de gestionar las relaciones
 * entre actores y películas.
 *
 * Utiliza una clave primaria compuesta por actor_id y film_id.
 * Por esta razón, las búsquedas y eliminaciones individuales
 * requieren ambos identificadores.
 */
public final class FilmActorDataContext
        extends DataContext<FilmActor>
        implements IDataPostCompuesto<FilmActor> {

    /**
     * Constructor que recibe la conexión con la base de datos.
     *
     * @param conexion conexión activa con MySQL.
     */
    public FilmActorDataContext(java.sql.Connection conexion) {
        super(conexion);
    }

    /**
     * Inserta una relación entre un actor y una película.
     *
     * @param relacion objeto que contiene el identificador del actor
     *                 y el identificador de la película.
     * @throws SQLException si ocurre un error durante la inserción,
     *                      incluyendo una relación duplicada o claves foráneas inválidas.
     */
    @Override
    protected void insertar(FilmActor relacion) throws SQLException {

        String sql = "INSERT INTO film_actor (actor_id, film_id) VALUES (?, ?)";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, relacion.getActorId());
            sentencia.setInt(2, relacion.getFilmId());

            sentencia.executeUpdate();
        }
    }

    /**
     * Actualiza la fecha de modificación de una relación.
     *
     * Identifica la relación utilizando actor_id y film_id.
     *
     * @param relacion objeto que contiene las dos claves de la relación.
     * @throws SQLException si ocurre un error durante la actualización.
     */
    @Override
    protected void actualizar(FilmActor relacion) throws SQLException {

        String sql = "UPDATE film_actor SET last_update = CURRENT_TIMESTAMP "
                + "WHERE actor_id = ? AND film_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, relacion.getActorId());
            sentencia.setInt(2, relacion.getFilmId());

            sentencia.executeUpdate();
        }
    }

    /**
     * Impide realizar búsquedas utilizando una sola clave primaria.
     *
     * La tabla film_actor utiliza una clave primaria compuesta.
     *
     * @param id identificador individual que no es suficiente para buscar.
     * @return este método no devuelve un objeto.
     * @throws UnsupportedOperationException siempre, porque se necesitan
     *                                        los dos identificadores.
     */
    @Override
    protected FilmActor buscarPorId(int id) throws SQLException {

        throw new UnsupportedOperationException(
                "Utilice Get con actorId y filmId.");
    }

    /**
     * Busca una relación utilizando los identificadores del actor
     * y de la película.
     *
     * @param actorId identificador del actor.
     * @param filmId identificador de la película.
     * @return relación encontrada o null si no existe.
     * @throws SQLException si ocurre un error durante la consulta.
     */
    public FilmActor buscarPorClave(int actorId, int filmId)
            throws SQLException {

        String sql = "SELECT * FROM film_actor "
                + "WHERE actor_id = ? AND film_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, actorId);
            sentencia.setInt(2, filmId);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {
                    return convertirFilmActor(resultado);
                }
            }
        }

        return null;
    }

    /**
     * Implementa la búsqueda de la interfaz para claves compuestas.
     *
     * Controla las posibles excepciones SQL y las convierte
     * en una excepción de ejecución.
     *
     * @param id1 identificador del actor.
     * @param id2 identificador de la película.
     * @return relación encontrada o null si no existe.
     * @throws RuntimeException si ocurre un error al buscar la relación.
     */
    @Override
    public FilmActor Get(int id1, int id2) {

        try {
            return buscarPorClave(id1, id2);

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error al buscar la relacion entre actor y pelicula.", e);
        }
    }

    /**
     * Recupera todas las relaciones entre actores y películas.
     *
     * @return lista de objetos FilmActor.
     * @throws SQLException si ocurre un error durante la consulta.
     */
    @Override
    protected List<FilmActor> listar() throws SQLException {

        List<FilmActor> relaciones = new ArrayList<>();

        String sql = "SELECT * FROM film_actor";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                relaciones.add(convertirFilmActor(resultado));
            }
        }

        return relaciones;
    }

    /**
     * Impide eliminar una relación utilizando una sola clave.
     *
     * @param id identificador individual que no es suficiente para eliminar.
     * @throws UnsupportedOperationException siempre, porque se necesitan
     *                                        los dos identificadores.
     */
    @Override
    protected void eliminar(int id) throws SQLException {

        throw new UnsupportedOperationException(
                "Utilice Delete con actorId y filmId.");
    }

    /**
     * Elimina una relación utilizando los identificadores del actor
     * y de la película.
     *
     * @param actorId identificador del actor.
     * @param filmId identificador de la película.
     * @throws SQLException si ocurre un error durante la eliminación.
     */
    public void eliminarPorClave(int actorId, int filmId)
            throws SQLException {

        String sql = "DELETE FROM film_actor "
                + "WHERE actor_id = ? AND film_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, actorId);
            sentencia.setInt(2, filmId);

            sentencia.executeUpdate();
        }
    }

    /**
     * Implementa la eliminación de la interfaz para claves compuestas.
     *
     * Controla las posibles excepciones SQL y las convierte
     * en una excepción de ejecución.
     *
     * @param id1 identificador del actor.
     * @param id2 identificador de la película.
     * @throws RuntimeException si ocurre un error al eliminar la relación.
     */
    @Override
    public void Delete(int id1, int id2) {

        try {
            eliminarPorClave(id1, id2);

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Error al eliminar la relacion entre actor y pelicula.", e);
        }
    }

    /**
     * Convierte una fila del resultado SQL en un objeto FilmActor.
     *
     * @param resultado resultado obtenido de la consulta SQL.
     * @return objeto FilmActor con los identificadores y la fecha
     *         de modificación recuperados.
     * @throws SQLException si ocurre un error al leer los campos.
     */
    private FilmActor convertirFilmActor(ResultSet resultado)
            throws SQLException {

        FilmActor relacion = new FilmActor();

        relacion.setActorId(resultado.getInt("actor_id"));
        relacion.setFilmId(resultado.getInt("film_id"));
        relacion.setLastUpdate(resultado.getTimestamp("last_update"));

        return relacion;
    }
}

