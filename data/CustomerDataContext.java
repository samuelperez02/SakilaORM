
/**
 * Copyright (c) 2026.
 * Proyecto final de Lenguaje de Programación II.
 *
 * Clase encargada de administrar el acceso a los datos
 * de la tabla customer de la base de datos Sakila.
 *
 * @author Samuel Elias Perez Ramirez
 * @version 1.0
 */
package com.sakila.data;

import com.sakila.models.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DataContext concreto encargado de gestionar los clientes.
 *
 * Permite insertar, actualizar, buscar, listar y eliminar
 * registros de la tabla customer.
 */
public final class CustomerDataContext extends DataContext<Customer> {

    /**
     * Constructor que recibe la conexión con la base de datos.
     *
     * @param conexion conexión activa con MySQL.
     */
    public CustomerDataContext(java.sql.Connection conexion) {
        super(conexion);
    }

    /**
     * Inserta un nuevo cliente en la tabla customer.
     *
     * Guarda los datos personales, la tienda, la dirección,
     * el estado del cliente y la fecha de creación.
     * Recupera el identificador generado automáticamente por MySQL
     * y lo asigna al objeto Customer.
     *
     * @param cliente objeto que contiene los datos del cliente.
     * @throws SQLException si ocurre un error durante la inserción,
     *                      incluyendo restricciones de claves foráneas.
     */
    @Override
    protected void insertar(Customer cliente) throws SQLException {

        String sql = "INSERT INTO customer "
                + "(store_id, first_name, last_name, email, "
                + "address_id, active, create_date) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement sentencia = conexion.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            sentencia.setInt(1, cliente.getStoreId());
            sentencia.setString(2, cliente.getFirstName());
            sentencia.setString(3, cliente.getLastName());
            sentencia.setString(4, cliente.getEmail());
            sentencia.setInt(5, cliente.getAddressId());
            sentencia.setBoolean(6, cliente.isActive());
            sentencia.setTimestamp(7, cliente.getCreateDate());

            sentencia.executeUpdate();

            try (ResultSet resultado = sentencia.getGeneratedKeys()) {

                if (resultado.next()) {
                    cliente.setCustomerId(resultado.getInt(1));
                }
            }
        }
    }

    /**
     * Actualiza los datos de un cliente existente.
     *
     * Modifica la tienda, el nombre, el apellido, el correo electrónico,
     * la dirección y el estado del cliente.
     *
     * @param cliente objeto con el identificador y los nuevos datos.
     * @throws SQLException si ocurre un error durante la actualización.
     */
    @Override
    protected void actualizar(Customer cliente) throws SQLException {

        String sql = "UPDATE customer SET store_id = ?, "
                + "first_name = ?, last_name = ?, email = ?, "
                + "address_id = ?, active = ? WHERE customer_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, cliente.getStoreId());
            sentencia.setString(2, cliente.getFirstName());
            sentencia.setString(3, cliente.getLastName());
            sentencia.setString(4, cliente.getEmail());
            sentencia.setInt(5, cliente.getAddressId());
            sentencia.setBoolean(6, cliente.isActive());
            sentencia.setInt(7, cliente.getCustomerId());

            sentencia.executeUpdate();
        }
    }

    /**
     * Busca un cliente utilizando su identificador.
     *
     * @param id identificador del cliente que se desea consultar.
     * @return objeto Customer encontrado o null si no existe.
     * @throws SQLException si ocurre un error durante la consulta.
     */
    @Override
    protected Customer buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM customer WHERE customer_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {
                    return convertirCustomer(resultado);
                }
            }
        }

        return null;
    }

    /**
     * Recupera todos los clientes registrados en la base de datos.
     *
     * @return lista de objetos Customer.
     * @throws SQLException si ocurre un error durante la consulta.
     */
    @Override
    protected List<Customer> listar() throws SQLException {

        List<Customer> clientes = new ArrayList<>();

        String sql = "SELECT * FROM customer";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                clientes.add(convertirCustomer(resultado));
            }
        }

        return clientes;
    }

    /**
     * Elimina un cliente utilizando su identificador.
     *
     * @param id identificador del cliente que se desea eliminar.
     * @throws SQLException si ocurre un error al eliminar el registro,
     *                      por ejemplo, debido a registros relacionados.
     */
    @Override
    protected void eliminar(int id) throws SQLException {

        String sql = "DELETE FROM customer WHERE customer_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            sentencia.executeUpdate();
        }
    }

    /**
     * Convierte una fila del resultado SQL en un objeto Customer.
     *
     * Recupera los datos personales, las relaciones con tienda y dirección,
     * el estado del cliente y las fechas almacenadas.
     *
     * @param resultado resultado obtenido de la consulta SQL.
     * @return objeto Customer con los datos recuperados.
     * @throws SQLException si ocurre un error al leer los campos.
     */
    private Customer convertirCustomer(ResultSet resultado)
            throws SQLException {

        Customer cliente = new Customer();

        cliente.setCustomerId(resultado.getInt("customer_id"));
        cliente.setStoreId(resultado.getInt("store_id"));
        cliente.setFirstName(resultado.getString("first_name"));
        cliente.setLastName(resultado.getString("last_name"));
        cliente.setEmail(resultado.getString("email"));
        cliente.setAddressId(resultado.getInt("address_id"));
        cliente.setActive(resultado.getBoolean("active"));
        cliente.setCreateDate(resultado.getTimestamp("create_date"));
        cliente.setLastUpdate(resultado.getTimestamp("last_update"));

        return cliente;
    }
}
