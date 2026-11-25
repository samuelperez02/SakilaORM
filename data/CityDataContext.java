
/**
 * Copyright (c) 2026.
 * Proyecto final de Lenguaje de Programación II.
 *
 * Clase encargada de administrar el acceso a los datos
 * de la tabla city de la base de datos Sakila.
 *
 * @author Samuel Elias Perez Ramirez
 * @version 1.0
 */
package com.sakila.data;

import com.sakila.models.City;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DataContext concreto encargado de gestionar las ciudades.
 *
 * Permite insertar, actualizar, buscar, listar y eliminar ciudades.
 * Cada ciudad está relacionada con un país mediante country_id.
 */
public final class CityDataContext extends DataContext<City> {

    /**
     * Construye el contexto de datos utilizando una conexión existente.
     *
     * @param conexion conexión con la base de datos MySQL.
     */
    public CityDataContext(java.sql.Connection conexion) {
        super(conexion);
    }

    /**
     * Inserta una nueva ciudad en la tabla city.
     *
     * Recupera el identificador generado automáticamente por MySQL
     * y lo asigna al objeto City.
     *
     * @param ciudad objeto que contiene el nombre de la ciudad
     *               y el identificador de su país.
     * @throws SQLException si ocurre un error durante la inserción.
     */
    @Override
    protected void insertar(City ciudad) throws SQLException {

        String sql = "INSERT INTO city (city, country_id) VALUES (?, ?)";

        try (PreparedStatement sentencia = conexion.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            sentencia.setString(1, ciudad.getCity());
            sentencia.setInt(2, ciudad.getCountryId());

            sentencia.executeUpdate();

            try (ResultSet resultado = sentencia.getGeneratedKeys()) {

                if (resultado.next()) {
                    ciudad.setCityId(resultado.getInt(1));
                }
            }
        }
    }

    /**
     * Actualiza el nombre de una ciudad y el país al que pertenece.
     *
     * @param ciudad objeto con el identificador y los nuevos datos.
     * @throws SQLException si ocurre un error al actualizar la ciudad,
     *                      incluyendo una posible restricción de clave foránea.
     */
    @Override
    protected void actualizar(City ciudad) throws SQLException {

        String sql = "UPDATE city SET city = ?, country_id = ? "
                + "WHERE city_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, ciudad.getCity());
            sentencia.setInt(2, ciudad.getCountryId());
            sentencia.setInt(3, ciudad.getCityId());

            sentencia.executeUpdate();
        }
    }

    /**
     * Busca una ciudad mediante su identificador.
     *
     * @param id identificador de la ciudad que se desea consultar.
     * @return objeto City encontrado o null si no existe.
     * @throws SQLException si ocurre un error durante la consulta.
     */
    @Override
    protected City buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM city WHERE city_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {
                    return convertirCity(resultado);
                }
            }
        }

        return null;
    }

    /**
     * Recupera todas las ciudades registradas en la base de datos.
     *
     * @return lista de objetos City.
     * @throws SQLException si ocurre un error durante la consulta.
     */
    @Override
    protected List<City> listar() throws SQLException {

        List<City> ciudades = new ArrayList<>();

        String sql = "SELECT * FROM city";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                ciudades.add(convertirCity(resultado));
            }
        }

        return ciudades;
    }

    /**
     * Elimina una ciudad utilizando su identificador.
     *
     * @param id identificador de la ciudad que se desea eliminar.
     * @throws SQLException si ocurre un error al eliminarla,
     *                      por ejemplo, si existen registros relacionados.
     */
    @Override
    protected void eliminar(int id) throws SQLException {

        String sql = "DELETE FROM city WHERE city_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            sentencia.executeUpdate();
        }
    }

    /**
     * Convierte una fila obtenida de MySQL en un objeto City.
     *
     * @param resultado resultado de la consulta SQL.
     * @return objeto City con los datos recuperados.
     * @throws SQLException si ocurre un error al leer los campos.
     */
    private City convertirCity(ResultSet resultado) throws SQLException {

        City ciudad = new City();

        ciudad.setCityId(resultado.getInt("city_id"));
        ciudad.setCity(resultado.getString("city"));
        ciudad.setCountryId(resultado.getInt("country_id"));
        ciudad.setLastUpdate(resultado.getTimestamp("last_update"));

        return ciudad;
    }
}

