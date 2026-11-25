
/**
 * Copyright (c) 2026.
 * Proyecto final de Lenguaje de Programacion II.
 *
 * Clase abstracta generica que centraliza las operaciones
 * CRUD utilizadas por los DataContexts del proyecto SakilaORM.
 */

package com.sakila.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Proporciona la estructura comun para administrar
 * los registros de las tablas de la base de datos.
 *
 * @param <T> tipo de objeto que administra la clase.
 */
public abstract class DataContext<T> implements iDatapost<T> {

    /**
     * Conexion utilizada para comunicarse con MySQL.
     */
    protected final Connection conexion;

    /**
     * Constructor que recibe la conexion a la base de datos.
     *
     * @param conexion conexion JDBC.
     */
    public DataContext(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     * Inserta un objeto en la base de datos.
     *
     * @param objeto objeto que se desea insertar.
     * @throws SQLException si ocurre un error en MySQL.
     */
    protected abstract void insertar(T objeto) throws SQLException;

    /**
     * Actualiza un objeto existente.
     *
     * @param objeto objeto con los datos actualizados.
     * @throws SQLException si ocurre un error en MySQL.
     */
    protected abstract void actualizar(T objeto) throws SQLException;

    /**
     * Busca un registro utilizando su identificador.
     *
     * @param id identificador del registro.
     * @return objeto encontrado o null.
     * @throws SQLException si ocurre un error en MySQL.
     */
    protected abstract T buscarPorId(int id) throws SQLException;

    /**
     * Obtiene todos los registros de la tabla.
     *
     * @return lista de objetos.
     * @throws SQLException si ocurre un error en MySQL.
     */
    protected abstract List<T> listar() throws SQLException;

    /**
     * Elimina un registro utilizando su identificador.
     *
     * @param id identificador del registro.
     * @throws SQLException si ocurre un error en MySQL.
     */
    protected abstract void eliminar(int id) throws SQLException;

    /**
     * Ejecuta la insercion de un objeto.
     *
     * @param objeto objeto que se va a insertar.
     */
    @Override
    public final void Post(T objeto) {
        try {
            insertar(objeto);
        } catch (SQLException e) {
            throw new RuntimeException("Error al insertar", e);
        }
    }

    /**
     * Ejecuta la actualizacion de un objeto.
     *
     * @param objeto objeto que se va a actualizar.
     */
    @Override
    public final void Put(T objeto) {
        try {
            actualizar(objeto);
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar", e);
        }
    }

    /**
     * Busca un objeto por su identificador.
     *
     * @param id identificador del registro.
     * @return objeto encontrado.
     */
    @Override
    public final T Get(int id) {
        try {
            return buscarPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar", e);
        }
    }

    /**
     * Obtiene todos los objetos registrados.
     *
     * @return lista de registros.
     */
    @Override
    public final List<T> Get() {
        try {
            return listar();
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar", e);
        }
    }

    /**
     * Elimina un objeto por su identificador.
     *
     * @param id identificador del registro.
     */
    @Override
    public final void Delete(int id) {
        try {
            eliminar(id);
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar", e);
        }
    }
}
