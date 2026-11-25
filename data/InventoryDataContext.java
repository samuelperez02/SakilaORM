
/*
 * Copyright (c) 2026
 * Proyecto Final - SakilaORM
 * Lenguaje de Programación II
 */

package com.sakila.data;

import com.sakila.models.Inventory;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de administrar las operaciones de acceso a datos
 * de la tabla inventory de la base de datos Sakila.
 *
 * Permite insertar, actualizar, buscar, listar y eliminar registros
 * del inventario de peliculas.
 *
 * Tambien permite consultar estadisticas generales del inventario.
 *
 * @author Samuel Elias Perez Ramirez
 * @version 1.0
 */
public final class InventoryDataContext extends DataContext<Inventory> {

    /**
     * Construye el contexto de acceso a datos para la tabla inventory.
     *
     * @param conexion conexion activa con la base de datos
     */
    public InventoryDataContext(java.sql.Connection conexion) {
        super(conexion);
    }

    /**
     * Inserta un nuevo registro en la tabla inventory.
     *
     * @param inventario objeto Inventory que contiene los datos
     *                   que seran almacenados
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected void insertar(Inventory inventario) throws SQLException {

        String sql = "INSERT INTO inventory "
                + "(film_id, store_id) "
                + "VALUES (?, ?)";

        try (PreparedStatement sentencia = conexion.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            sentencia.setInt(1, inventario.getFilmId());
            sentencia.setInt(2, inventario.getStoreId());

            sentencia.executeUpdate();

            try (ResultSet resultado = sentencia.getGeneratedKeys()) {

                if (resultado.next()) {
                    inventario.setInventoryId(resultado.getInt(1));
                }
            }
        }
    }

    /**
     * Actualiza los datos de un registro existente en inventory.
     *
     * @param inventario objeto Inventory que contiene los nuevos datos
     *                   y el identificador del registro que se actualizara
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected void actualizar(Inventory inventario) throws SQLException {

        String sql = "UPDATE inventory SET "
                + "film_id = ?, store_id = ? "
                + "WHERE inventory_id = ?";

        try (PreparedStatement sentencia =
                conexion.prepareStatement(sql)) {

            sentencia.setInt(1, inventario.getFilmId());
            sentencia.setInt(2, inventario.getStoreId());
            sentencia.setInt(3, inventario.getInventoryId());

            sentencia.executeUpdate();
        }
    }

    /**
     * Busca un registro de inventory utilizando su identificador.
     *
     * @param id identificador del registro de inventario
     * @return objeto Inventory encontrado o null si no existe
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected Inventory buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM inventory "
                + "WHERE inventory_id = ?";

        try (PreparedStatement sentencia =
                conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            try (ResultSet resultado =
                    sentencia.executeQuery()) {

                if (resultado.next()) {
                    return convertirInventory(resultado);
                }
            }
        }

        return null;
    }

    /**
     * Obtiene todos los registros almacenados en la tabla inventory.
     *
     * @return lista de objetos Inventory encontrados en la base de datos
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected List<Inventory> listar() throws SQLException {

        List<Inventory> inventarios = new ArrayList<>();

        String sql = "SELECT * FROM inventory";

        try (PreparedStatement sentencia =
                conexion.prepareStatement(sql);
             ResultSet resultado =
                    sentencia.executeQuery()) {

            while (resultado.next()) {

                inventarios.add(
                    convertirInventory(resultado)
                );
            }
        }

        return inventarios;
    }

    /**
     * Consulta las estadisticas generales del inventario.
     *
     * Obtiene el total de unidades, la cantidad de peliculas
     * diferentes y el promedio de unidades por pelicula.
     *
     * @return arreglo con total de unidades, peliculas diferentes
     *         y promedio de unidades por pelicula
     * @throws SQLException si ocurre un error durante la consulta
     */
    public Object[] obtenerEstadisticasGenerales() throws SQLException {

        String sql = "SELECT "
                + "COUNT(*) AS total_unidades, "
                + "COUNT(DISTINCT film_id) AS peliculas_diferentes, "
                + "COALESCE("
                + "COUNT(*) / NULLIF(COUNT(DISTINCT film_id), 0), "
                + "0) AS promedio_unidades "
                + "FROM inventory";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            if (resultado.next()) {

                int totalUnidades =
                        resultado.getInt("total_unidades");

                int peliculasDiferentes =
                        resultado.getInt("peliculas_diferentes");

                BigDecimal promedioUnidades =
                        resultado.getBigDecimal("promedio_unidades");

                return new Object[]{
                    totalUnidades,
                    peliculasDiferentes,
                    promedioUnidades
                };
            }
        }

        return new Object[]{
            0,
            0,
            BigDecimal.ZERO
        };
    }

    /**
     * Elimina un registro de inventory utilizando su identificador.
     *
     * @param id identificador del registro que se desea eliminar
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected void eliminar(int id) throws SQLException {

        String sql = "DELETE FROM inventory "
                + "WHERE inventory_id = ?";

        try (PreparedStatement sentencia =
                conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            sentencia.executeUpdate();
        }
    }

    /**
     * Convierte los datos obtenidos desde MySQL en un objeto Inventory.
     *
     * @param resultado conjunto de resultados obtenido mediante
     *                  una consulta SQL
     * @return objeto Inventory con los datos obtenidos de la base de datos
     * @throws SQLException si ocurre un error al leer los datos
     */
    private Inventory convertirInventory(ResultSet resultado)
            throws SQLException {

        Inventory inventario = new Inventory();

        inventario.setInventoryId(
                resultado.getInt("inventory_id"));

        inventario.setFilmId(
                resultado.getInt("film_id"));

        inventario.setStoreId(
                resultado.getInt("store_id"));

        inventario.setLastUpdate(
                resultado.getTimestamp("last_update"));

        return inventario;
    }
}