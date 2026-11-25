
/**
 * Copyright (c) 2026.
 * Proyecto final de Lenguaje de Programacion II.
 *
 * Clase encargada de realizar las operaciones CRUD
 * de la tabla actor de la base de datos Sakila.
 */

package com.sakila.data;

import com.sakila.models.Actor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Administra los registros de actores mediante JDBC.
 *
 * Extiende DataContext para utilizar las operaciones CRUD comunes.
 */
public final class ActorDataContext extends DataContext<Actor> {

    /**
     * Constructor que recibe la conexion con MySQL.
     *
     * @param conexion conexion a la base de datos.
     */
    public ActorDataContext(java.sql.Connection conexion) {
        super(conexion);
    }

    /**
     * Inserta un nuevo actor en la tabla actor.
     *
     * @param actor objeto con los datos del actor.
     * @throws SQLException si ocurre un error al insertar.
     */
    @Override
    protected void insertar(Actor actor) throws SQLException {

        String sql = "INSERT INTO actor (first_name, last_name) VALUES (?, ?)";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, actor.getFirstName());
            sentencia.setString(2, actor.getLastName());

            sentencia.executeUpdate();
        }
    }

    /**
     * Actualiza el nombre y apellido de un actor.
     *
     * @param actor objeto con los datos actualizados.
     * @throws SQLException si ocurre un error al actualizar.
     */
    @Override
    protected void actualizar(Actor actor) throws SQLException {

        String sql = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, actor.getFirstName());
            sentencia.setString(2, actor.getLastName());
            sentencia.setInt(3, actor.getActorId());

            sentencia.executeUpdate();
        }
    }

    /**
     * Busca un actor mediante su identificador.
     *
     * @param id identificador del actor.
     * @return objeto Actor encontrado o null si no existe.
     * @throws SQLException si ocurre un error en la consulta.
     */
    @Override
    protected Actor buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM actor WHERE actor_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {

                    return new Actor(
                            resultado.getInt("actor_id"),
                            resultado.getString("first_name"),
                            resultado.getString("last_name"),
                            resultado.getTimestamp("last_update")
                    );
                }
            }
        }

        return null;
    }

    /**
     * Obtiene todos los actores registrados.
     *
     * @return lista de actores encontrados.
     * @throws SQLException si ocurre un error en la consulta.
     */
    @Override
    protected List<Actor> listar() throws SQLException {

        List<Actor> actores = new ArrayList<>();

        String sql = "SELECT * FROM actor";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                Actor actor = new Actor(
                        resultado.getInt("actor_id"),
                        resultado.getString("first_name"),
                        resultado.getString("last_name"),
                        resultado.getTimestamp("last_update")
                );

                actores.add(actor);
            }
        }

        return actores;
    }

    /**
     * Elimina un actor mediante su identificador.
     *
     * @param id identificador del actor que se eliminará.
     * @throws SQLException si ocurre un error al eliminar.
     */
    @Override
    protected void eliminar(int id) throws SQLException {

        String sql = "DELETE FROM actor WHERE actor_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            sentencia.executeUpdate();
        }
    }
}
