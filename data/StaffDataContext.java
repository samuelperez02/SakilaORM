/*
 * Copyright (c) 2026
 * Proyecto Final - SakilaORM
 * Lenguaje de Programación II
 */

package com.sakila.data;

import com.sakila.models.Staff;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de administrar las operaciones de acceso a datos
 * de la tabla staff de la base de datos Sakila.
 *
 * Permite insertar, actualizar, buscar, listar y eliminar
 * los registros de empleados del sistema.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public final class StaffDataContext extends DataContext<Staff> {

    /**
     * Construye el contexto de acceso a datos para la tabla staff.
     *
     * @param conexion conexion activa con la base de datos
     */
    public StaffDataContext(java.sql.Connection conexion) {
        super(conexion);
    }

    /**
     * Inserta un nuevo empleado en la tabla staff.
     *
     * La base de datos genera automaticamente el staff_id.
     * El identificador generado se asigna al objeto Staff.
     *
     * @param empleado objeto Staff que contiene los datos
     *                 del empleado que sera almacenado
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected void insertar(Staff empleado) throws SQLException {

        String sql = "INSERT INTO staff "
                + "(first_name, last_name, address_id, picture, "
                + "email, store_id, active, username, password) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement sentencia = conexion.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            sentencia.setString(1, empleado.getFirstName());
            sentencia.setString(2, empleado.getLastName());
            sentencia.setInt(3, empleado.getAddressId());
            sentencia.setBytes(4, empleado.getPicture());
            sentencia.setString(5, empleado.getEmail());
            sentencia.setInt(6, empleado.getStoreId());
            sentencia.setBoolean(7, empleado.isActive());
            sentencia.setString(8, empleado.getUsername());
            sentencia.setString(9, empleado.getPassword());

            sentencia.executeUpdate();

            try (ResultSet resultado = sentencia.getGeneratedKeys()) {
                if (resultado.next()) {
                    empleado.setStaffId(resultado.getInt(1));
                }
            }
        }
    }

    /**
     * Actualiza los datos de un empleado existente.
     *
     * @param empleado objeto Staff que contiene el identificador
     *                 y los nuevos datos del empleado
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected void actualizar(Staff empleado) throws SQLException {

        String sql = "UPDATE staff SET first_name = ?, "
                + "last_name = ?, address_id = ?, picture = ?, "
                + "email = ?, store_id = ?, active = ?, "
                + "username = ?, password = ? "
                + "WHERE staff_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, empleado.getFirstName());
            sentencia.setString(2, empleado.getLastName());
            sentencia.setInt(3, empleado.getAddressId());
            sentencia.setBytes(4, empleado.getPicture());
            sentencia.setString(5, empleado.getEmail());
            sentencia.setInt(6, empleado.getStoreId());
            sentencia.setBoolean(7, empleado.isActive());
            sentencia.setString(8, empleado.getUsername());
            sentencia.setString(9, empleado.getPassword());
            sentencia.setInt(10, empleado.getStaffId());

            sentencia.executeUpdate();
        }
    }

    /**
     * Busca un empleado utilizando su identificador.
     *
     * @param id identificador del empleado que se desea buscar
     * @return objeto Staff encontrado o null si no existe
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected Staff buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM staff WHERE staff_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {
                    return convertirStaff(resultado);
                }
            }
        }

        return null;
    }

    /**
     * Obtiene todos los empleados registrados en la tabla staff.
     *
     * @return lista de objetos Staff encontrados en la base de datos
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected List<Staff> listar() throws SQLException {

        List<Staff> empleados = new ArrayList<>();

        String sql = "SELECT * FROM staff";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                empleados.add(convertirStaff(resultado));
            }
        }

        return empleados;
    }

    /**
     * Elimina un empleado utilizando su identificador.
     *
     * @param id identificador del empleado que se desea eliminar
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected void eliminar(int id) throws SQLException {

        String sql = "DELETE FROM staff WHERE staff_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            sentencia.executeUpdate();
        }
    }

    /**
     * Convierte los datos obtenidos desde MySQL en un objeto Staff.
     *
     * @param resultado conjunto de resultados obtenido mediante
     *                  una consulta SQL
     * @return objeto Staff con los datos obtenidos de la base de datos
     * @throws SQLException si ocurre un error al leer los datos
     */
    private Staff convertirStaff(ResultSet resultado)
            throws SQLException {

        Staff empleado = new Staff();

        empleado.setStaffId(resultado.getInt("staff_id"));
        empleado.setFirstName(resultado.getString("first_name"));
        empleado.setLastName(resultado.getString("last_name"));
        empleado.setAddressId(resultado.getInt("address_id"));
        empleado.setPicture(resultado.getBytes("picture"));
        empleado.setEmail(resultado.getString("email"));
        empleado.setStoreId(resultado.getInt("store_id"));
        empleado.setActive(resultado.getBoolean("active"));
        empleado.setUsername(resultado.getString("username"));
        empleado.setPassword(resultado.getString("password"));
        empleado.setLastUpdate(resultado.getTimestamp("last_update"));

        return empleado;
    }
}
