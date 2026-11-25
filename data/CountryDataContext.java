
/**
 * Copyright (c) 2026.
 * Proyecto final de Lenguaje de Programación II.
 *
 * Clase encargada de administrar el acceso a los datos
 * de la tabla country de la base de datos Sakila.
 *
 * @author Samuel Elias Perez Ramirez
 * @version 1.0
 */
package com.sakila.data;

import com.sakila.models.Country;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DataContext concreto encargado de gestionar los países.
 *
 * Permite insertar, actualizar, buscar, listar y eliminar
 * registros de la tabla country.
 */
public final class CountryDataContext extends DataContext<Country> {

    /**
     * Constructor que recibe la conexión con la base de datos.
     *
     * @param conexion conexión activa con MySQL.
     */
    public CountryDataContext(java.sql.Connection conexion) {
        super(conexion);
    }

    /**
     * Inserta un nuevo país en la tabla country.
     *
     * Recupera el identificador generado automáticamente por MySQL
     * y lo asigna al objeto Country.
     *
     * @param pais objeto que contiene el nombre del país.
     * @throws SQLException si ocurre un error durante la inserción.
     */
    @Override
    protected void insertar(Country pais) throws SQLException {

        String sql = "INSERT INTO country (country) VALUES (?)";

        try (PreparedStatement sentencia = conexion.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            sentencia.setString(1, pais.getCountry());

            sentencia.executeUpdate();

            try (ResultSet resultado = sentencia.getGeneratedKeys()) {

                if (resultado.next()) {
                    pais.setCountryId(resultado.getInt(1));
                }
            }
        }
    }

    /**
     * Actualiza el nombre de un país existente.
     *
     * @param pais objeto que contiene el identificador y el nuevo nombre.
     * @throws SQLException si ocurre un error al actualizar el registro.
     */
    @Override
    protected void actualizar(Country pais) throws SQLException {

        String sql = "UPDATE country SET country = ? WHERE country_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, pais.getCountry());
            sentencia.setInt(2, pais.getCountryId());

            sentencia.executeUpdate();
        }
    }

    /**
     * Busca un país mediante su identificador.
     *
     * @param id identificador del país que se desea buscar.
     * @return objeto Country encontrado o null si no existe.
     * @throws SQLException si ocurre un error durante la consulta.
     */
    @Override
    protected Country buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM country WHERE country_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {
                    return convertirCountry(resultado);
                }
            }
        }

        return null;
    }

    /**
     * Recupera todos los países registrados en la base de datos.
     *
     * @return lista de objetos Country.
     * @throws SQLException si ocurre un error durante la consulta.
     */
    @Override
    protected List<Country> listar() throws SQLException {

        List<Country> paises = new ArrayList<>();

        String sql = "SELECT * FROM country";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                paises.add(convertirCountry(resultado));
            }
        }

        return paises;
    }

    /**
     * Elimina un país utilizando su identificador.
     *
     * @param id identificador del país que se desea eliminar.
     * @throws SQLException si ocurre un error al eliminar el registro,
     *                      por ejemplo, por una restricción de clave foránea.
     */
    @Override
    protected void eliminar(int id) throws SQLException {

        String sql = "DELETE FROM country WHERE country_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            sentencia.executeUpdate();
        }
    }

    /**
     * Convierte una fila del resultado SQL en un objeto Country.
     *
     * @param resultado resultado obtenido de la consulta SQL.
     * @return objeto Country con los datos recuperados.
     * @throws SQLException si ocurre un error al leer los campos.
     */
    private Country convertirCountry(ResultSet resultado) throws SQLException {

        Country pais = new Country();

        pais.setCountryId(resultado.getInt("country_id"));
        pais.setCountry(resultado.getString("country"));
        pais.setLastUpdate(resultado.getTimestamp("last_update"));

        return pais;
    }
}

