
/**
 * Copyright (c) 2026.
 * Proyecto final de Lenguaje de Programacion II.
 *
 * Clase encargada de administrar las direcciones
 * de la base de datos Sakila mediante operaciones CRUD.
 */

package com.sakila.data;

import com.sakila.models.Address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Administra los registros de la tabla address.
 *
 * Maneja el campo geografico location utilizando
 * las funciones espaciales de MySQL.
 */
public final class AddressDataContext extends DataContext<Address> {

    /**
     * Constructor que recibe la conexion con MySQL.
     *
     * @param conexion conexion a la base de datos.
     */
    public AddressDataContext(java.sql.Connection conexion) {
        super(conexion);
    }

    /**
     * Inserta una nueva direccion.
     *
     * Si location es null, utiliza POINT(0 0) como valor
     * de sustitucion para cumplir con la restriccion NOT NULL.
     *
     * @param direccion objeto con los datos de la direccion.
     * @throws SQLException si ocurre un error al insertar.
     */
    @Override
    protected void insertar(Address direccion) throws SQLException {

        String sql;

        if (direccion.getLocation() == null) {

            sql = "INSERT INTO address "
                    + "(address, address2, district, city_id, "
                    + "postal_code, phone, location) "
                    + "VALUES (?, ?, ?, ?, ?, ?, "
                    + "ST_GeomFromText('POINT(0 0)'))";

            try (PreparedStatement sentencia = conexion.prepareStatement(
                    sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

                sentencia.setString(1, direccion.getAddress());
                sentencia.setString(2, direccion.getAddress2());
                sentencia.setString(3, direccion.getDistrict());
                sentencia.setInt(4, direccion.getCityId());
                sentencia.setString(5, direccion.getPostalCode());
                sentencia.setString(6, direccion.getPhone());

                sentencia.executeUpdate();

                try (ResultSet resultado = sentencia.getGeneratedKeys()) {

                    if (resultado.next()) {
                        direccion.setAddressId(resultado.getInt(1));
                    }
                }
            }

        } else {

            sql = "INSERT INTO address "
                    + "(address, address2, district, city_id, "
                    + "postal_code, phone, location) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ST_GeomFromWKB(?))";

            try (PreparedStatement sentencia = conexion.prepareStatement(
                    sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

                sentencia.setString(1, direccion.getAddress());
                sentencia.setString(2, direccion.getAddress2());
                sentencia.setString(3, direccion.getDistrict());
                sentencia.setInt(4, direccion.getCityId());
                sentencia.setString(5, direccion.getPostalCode());
                sentencia.setString(6, direccion.getPhone());
                sentencia.setBytes(7, direccion.getLocation());

                sentencia.executeUpdate();

                try (ResultSet resultado = sentencia.getGeneratedKeys()) {

                    if (resultado.next()) {
                        direccion.setAddressId(resultado.getInt(1));
                    }
                }
            }
        }
    }

    /**
     * Actualiza los datos de una direccion existente.
     *
     * Si location es null, utiliza POINT(0 0).
     * Si contiene datos, los convierte desde WKB.
     *
     * @param direccion objeto con los datos actualizados.
     * @throws SQLException si ocurre un error al actualizar.
     */
    @Override
    protected void actualizar(Address direccion) throws SQLException {

        String sql;

        if (direccion.getLocation() == null) {

            sql = "UPDATE address SET address = ?, address2 = ?, "
                    + "district = ?, city_id = ?, postal_code = ?, "
                    + "phone = ?, location = ST_GeomFromText('POINT(0 0)') "
                    + "WHERE address_id = ?";

            try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

                sentencia.setString(1, direccion.getAddress());
                sentencia.setString(2, direccion.getAddress2());
                sentencia.setString(3, direccion.getDistrict());
                sentencia.setInt(4, direccion.getCityId());
                sentencia.setString(5, direccion.getPostalCode());
                sentencia.setString(6, direccion.getPhone());
                sentencia.setInt(7, direccion.getAddressId());

                sentencia.executeUpdate();
            }

        } else {

            sql = "UPDATE address SET address = ?, address2 = ?, "
                    + "district = ?, city_id = ?, postal_code = ?, "
                    + "phone = ?, location = ST_GeomFromWKB(?) "
                    + "WHERE address_id = ?";

            try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

                sentencia.setString(1, direccion.getAddress());
                sentencia.setString(2, direccion.getAddress2());
                sentencia.setString(3, direccion.getDistrict());
                sentencia.setInt(4, direccion.getCityId());
                sentencia.setString(5, direccion.getPostalCode());
                sentencia.setString(6, direccion.getPhone());
                sentencia.setBytes(7, direccion.getLocation());
                sentencia.setInt(8, direccion.getAddressId());

                sentencia.executeUpdate();
            }
        }
    }

    /**
     * Busca una direccion por su identificador.
     *
     * @param id identificador de la direccion.
     * @return direccion encontrada o null si no existe.
     * @throws SQLException si ocurre un error en la consulta.
     */
    @Override
    protected Address buscarPorId(int id) throws SQLException {

        String sql = "SELECT address_id, address, address2, district, "
                + "city_id, postal_code, phone, ST_AsWKB(location) AS location, "
                + "last_update FROM address WHERE address_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {
                    return convertirAddress(resultado);
                }
            }
        }

        return null;
    }

    /**
     * Obtiene todas las direcciones registradas.
     *
     * @return lista de direcciones.
     * @throws SQLException si ocurre un error en la consulta.
     */
    @Override
    protected List<Address> listar() throws SQLException {

        List<Address> direcciones = new ArrayList<>();

        String sql = "SELECT address_id, address, address2, district, "
                + "city_id, postal_code, phone, ST_AsWKB(location) AS location, "
                + "last_update FROM address";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                direcciones.add(convertirAddress(resultado));
            }
        }

        return direcciones;
    }

    /**
     * Elimina una direccion mediante su identificador.
     *
     * @param id identificador de la direccion.
     * @throws SQLException si ocurre un error al eliminar.
     */
    @Override
    protected void eliminar(int id) throws SQLException {

        String sql = "DELETE FROM address WHERE address_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            sentencia.executeUpdate();
        }
    }

    /**
     * Convierte los datos obtenidos de MySQL en un objeto Address.
     *
     * @param resultado resultado de la consulta SQL.
     * @return objeto Address con los datos recuperados.
     * @throws SQLException si ocurre un error al leer los datos.
     */
    private Address convertirAddress(ResultSet resultado) throws SQLException {

        Address direccion = new Address();

        direccion.setAddressId(resultado.getInt("address_id"));
        direccion.setAddress(resultado.getString("address"));
        direccion.setAddress2(resultado.getString("address2"));
        direccion.setDistrict(resultado.getString("district"));
        direccion.setCityId(resultado.getInt("city_id"));
        direccion.setPostalCode(resultado.getString("postal_code"));
        direccion.setPhone(resultado.getString("phone"));
        direccion.setLocation(resultado.getBytes("location"));
        direccion.setLastUpdate(resultado.getTimestamp("last_update"));

        return direccion;
    }
}

