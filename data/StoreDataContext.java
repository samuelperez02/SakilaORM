
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import com.sakila.models.Store;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de realizar las operaciones de acceso a datos
 * correspondientes a la entidad Store (tienda).
 *
 * Hereda de DataContext y utiliza la conexión a MySQL para insertar,
 * actualizar, consultar, listar y eliminar tiendas.
 *
 * @author Proyecto SakilaORM
 * @version 1.0
 */
public final class StoreDataContext extends DataContext<Store> {

    /**
     * Construye el contexto de datos para las tiendas.
     *
     * @param conexion conexión activa con la base de datos Sakila.
     */
    public StoreDataContext(java.sql.Connection conexion) {
        super(conexion);
    }

    /**
     * Inserta una nueva tienda en la base de datos.
     * Recupera y asigna el identificador generado automáticamente.
     *
     * @param tienda objeto Store que contiene los datos de la tienda.
     * @throws SQLException si ocurre un error durante la inserción.
     */
    @Override
    protected void insertar(Store tienda) throws SQLException {

        String sql = "INSERT INTO store "
                + "(manager_staff_id, address_id) "
                + "VALUES (?, ?)";

        try (PreparedStatement sentencia = conexion.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            sentencia.setInt(1, tienda.getManagerStaffId());
            sentencia.setInt(2, tienda.getAddressId());

            sentencia.executeUpdate();

            try (ResultSet resultado = sentencia.getGeneratedKeys()) {
                if (resultado.next()) {
                    tienda.setStoreId(resultado.getInt(1));
                }
            }
        }
    }

    /**
     * Actualiza los datos de una tienda existente.
     *
     * @param tienda objeto Store con los datos modificados.
     * @throws SQLException si ocurre un error durante la actualización.
     */
    @Override
    protected void actualizar(Store tienda) throws SQLException {

        String sql = "UPDATE store SET manager_staff_id = ?, "
                + "address_id = ? WHERE store_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, tienda.getManagerStaffId());
            sentencia.setInt(2, tienda.getAddressId());
            sentencia.setInt(3, tienda.getStoreId());

            sentencia.executeUpdate();
        }
    }

    /**
     * Busca una tienda utilizando su identificador.
     *
     * @param id identificador de la tienda.
     * @return objeto Store encontrado o null si no existe.
     * @throws SQLException si ocurre un error durante la consulta.
     */
    @Override
    protected Store buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM store WHERE store_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {
                    return convertirStore(resultado);
                }
            }
        }

        return null;
    }

    /**
     * Obtiene todas las tiendas registradas en la base de datos.
     *
     * @return lista de objetos Store.
     * @throws SQLException si ocurre un error durante la consulta.
     */
    @Override
    protected List<Store> listar() throws SQLException {

        List<Store> tiendas = new ArrayList<>();

        String sql = "SELECT * FROM store";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                tiendas.add(convertirStore(resultado));
            }
        }

        return tiendas;
    }

    /**
     * Elimina una tienda utilizando su identificador.
     *
     * @param id identificador de la tienda que se desea eliminar.
     * @throws SQLException si ocurre un error durante la eliminación.
     */
    @Override
    protected void eliminar(int id) throws SQLException {

        String sql = "DELETE FROM store WHERE store_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            sentencia.executeUpdate();
        }
    }

    /**
     * Convierte el resultado de una consulta SQL en un objeto Store.
     *
     * @param resultado conjunto de resultados obtenido de MySQL.
     * @return objeto Store con los datos recuperados.
     * @throws SQLException si ocurre un error al leer los resultados.
     */
    private Store convertirStore(ResultSet resultado)
            throws SQLException {

        Store tienda = new Store();

        tienda.setStoreId(resultado.getInt("store_id"));
        tienda.setManagerStaffId(resultado.getInt("manager_staff_id"));
        tienda.setAddressId(resultado.getInt("address_id"));
        tienda.setLastUpdate(resultado.getTimestamp("last_update"));

        return tienda;
    }
}
