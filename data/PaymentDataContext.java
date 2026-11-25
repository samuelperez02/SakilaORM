
/*
 * Copyright (c) 2026
 * Proyecto Final - SakilaORM
 * Lenguaje de Programación II
 */

package com.sakila.data;

import com.sakila.models.Payment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

/**
 * Clase encargada de administrar las operaciones de acceso a datos
 * de la tabla payment de la base de datos Sakila.
 *
 * Permite insertar, actualizar, buscar, listar y eliminar pagos
 * registrados en el sistema.
 *
 * Tambien permite consultar los pagos agrupados por cliente,
 * tienda, ciudad y país utilizando GROUP BY y SUM en MySQL.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public final class PaymentDataContext extends DataContext<Payment> {

    public PaymentDataContext(java.sql.Connection conexion) {
        super(conexion);
    }

    @Override
    protected void insertar(Payment pago) throws SQLException {

        String sql = "INSERT INTO payment "
                + "(customer_id, staff_id, rental_id, amount, payment_date) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement sentencia = conexion.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            sentencia.setInt(1, pago.getCustomerId());
            sentencia.setInt(2, pago.getStaffId());

            if (pago.getRentalId() == null) {
                sentencia.setNull(3, java.sql.Types.INTEGER);
            } else {
                sentencia.setInt(3, pago.getRentalId());
            }

            sentencia.setBigDecimal(4, pago.getAmount());
            sentencia.setTimestamp(5, pago.getPaymentDate());

            sentencia.executeUpdate();

            try (ResultSet resultado = sentencia.getGeneratedKeys()) {
                if (resultado.next()) {
                    pago.setPaymentId(resultado.getInt(1));
                }
            }
        }
    }

    @Override
    protected void actualizar(Payment pago) throws SQLException {

        String sql = "UPDATE payment SET customer_id = ?, "
                + "staff_id = ?, rental_id = ?, amount = ?, "
                + "payment_date = ? WHERE payment_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, pago.getCustomerId());
            sentencia.setInt(2, pago.getStaffId());

            if (pago.getRentalId() == null) {
                sentencia.setNull(3, java.sql.Types.INTEGER);
            } else {
                sentencia.setInt(3, pago.getRentalId());
            }

            sentencia.setBigDecimal(4, pago.getAmount());
            sentencia.setTimestamp(5, pago.getPaymentDate());
            sentencia.setInt(6, pago.getPaymentId());

            sentencia.executeUpdate();
        }
    }

    @Override
    protected Payment buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM payment WHERE payment_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {
                    return convertirPayment(resultado);
                }
            }
        }

        return null;
    }

    @Override
    protected List<Payment> listar() throws SQLException {

        List<Payment> pagos = new ArrayList<>();

        String sql = "SELECT * FROM payment";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                pagos.add(convertirPayment(resultado));
            }
        }

        return pagos;
    }

    /**
     * Obtiene el total pagado por cada cliente utilizando GROUP BY.
     *
     * @return lista con el ID del cliente y el total pagado
     * @throws SQLException si ocurre un error SQL
     */
    public List<Object[]> listarPagosPorClienteGroupBy()
            throws SQLException {

        List<Object[]> resultados = new ArrayList<>();

        String sql = "SELECT customer_id, SUM(amount) AS total_pagado "
                + "FROM payment "
                + "GROUP BY customer_id "
                + "ORDER BY customer_id";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                int customerId = resultado.getInt("customer_id");

                BigDecimal totalPagado =
                        resultado.getBigDecimal("total_pagado");

                resultados.add(new Object[]{
                        customerId, totalPagado
                });
            }
        }

        return resultados;
    }

    /**
     * Obtiene el total de pagos realizados en cada tienda.
     *
     * @return lista con el ID de la tienda y el total pagado
     * @throws SQLException si ocurre un error SQL
     */
    public List<Object[]> listarPagosPorTiendaGroupBy()
            throws SQLException {

        List<Object[]> resultados = new ArrayList<>();

        String sql = "SELECT staff.store_id, "
                + "SUM(payment.amount) AS total_pagado "
                + "FROM payment "
                + "INNER JOIN staff "
                + "ON payment.staff_id = staff.staff_id "
                + "GROUP BY staff.store_id "
                + "ORDER BY staff.store_id";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                int storeId = resultado.getInt("store_id");

                BigDecimal totalPagado =
                        resultado.getBigDecimal("total_pagado");

                resultados.add(new Object[]{
                        storeId, totalPagado
                });
            }
        }

        return resultados;
    }

    /**
     * Obtiene el total de pagos agrupados por ciudad y país
     * según la dirección registrada de cada cliente.
     *
     * @return lista con ciudad, país y total pagado
     * @throws SQLException si ocurre un error SQL
     */
    public List<Object[]> listarPagosPorCiudadPaisGroupBy()
            throws SQLException {

        List<Object[]> resultados = new ArrayList<>();

        String sql = "SELECT city.city, country.country, "
                + "SUM(payment.amount) AS total_pagado "
                + "FROM payment "
                + "INNER JOIN customer "
                + "ON payment.customer_id = customer.customer_id "
                + "INNER JOIN address "
                + "ON customer.address_id = address.address_id "
                + "INNER JOIN city "
                + "ON address.city_id = city.city_id "
                + "INNER JOIN country "
                + "ON city.country_id = country.country_id "
                + "GROUP BY city.city, country.country "
                + "ORDER BY country.country, city.city";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                String ciudad = resultado.getString("city");
                String pais = resultado.getString("country");

                BigDecimal totalPagado =
                        resultado.getBigDecimal("total_pagado");

                resultados.add(new Object[]{
                        ciudad, pais, totalPagado
                });
            }
        }

        return resultados;
    }

    @Override
    protected void eliminar(int id) throws SQLException {

        String sql = "DELETE FROM payment WHERE payment_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            sentencia.executeUpdate();
        }
    }

    private Payment convertirPayment(ResultSet resultado)
            throws SQLException {

        Payment pago = new Payment();

        pago.setPaymentId(resultado.getInt("payment_id"));
        pago.setCustomerId(resultado.getInt("customer_id"));
        pago.setStaffId(resultado.getInt("staff_id"));

        int rentalId = resultado.getInt("rental_id");

        if (resultado.wasNull()) {
            pago.setRentalId(null);
        } else {
            pago.setRentalId(rentalId);
        }

        pago.setAmount(resultado.getBigDecimal("amount"));
        pago.setPaymentDate(resultado.getTimestamp("payment_date"));
        pago.setLastUpdate(resultado.getTimestamp("last_update"));

        return pago;
    }
}
