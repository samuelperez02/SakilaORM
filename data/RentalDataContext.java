

/*
 * Copyright (c) 2026
 * Proyecto Final - SakilaORM
 * Lenguaje de Programación II
 */

package com.sakila.data;

import com.sakila.models.Rental;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de administrar las operaciones de acceso a datos
 * de la tabla rental de la base de datos Sakila.
 *
 * Permite insertar, actualizar, buscar, listar y eliminar
 * los registros de alquileres de peliculas.
 *
 * Tambien permite consultar los alquileres agrupados
 * por cliente, tienda, ciudad y pais.
 *
 * @author Samuel Elias Perez Ramirez
 * @version 1.0
 */
public final class RentalDataContext extends DataContext<Rental> {

    public RentalDataContext(java.sql.Connection conexion) {
        super(conexion);
    }

    @Override
    protected void insertar(Rental alquiler) throws SQLException {

        String sql = "INSERT INTO rental "
                + "(rental_date, inventory_id, customer_id, "
                + "return_date, staff_id) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement sentencia = conexion.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            sentencia.setTimestamp(1, alquiler.getRentalDate());
            sentencia.setInt(2, alquiler.getInventoryId());
            sentencia.setInt(3, alquiler.getCustomerId());
            sentencia.setTimestamp(4, alquiler.getReturnDate());
            sentencia.setInt(5, alquiler.getStaffId());

            sentencia.executeUpdate();

            try (ResultSet resultado = sentencia.getGeneratedKeys()) {
                if (resultado.next()) {
                    alquiler.setRentalId(resultado.getInt(1));
                }
            }
        }
    }

    @Override
    protected void actualizar(Rental alquiler) throws SQLException {

        String sql = "UPDATE rental SET rental_date = ?, "
                + "inventory_id = ?, customer_id = ?, "
                + "return_date = ?, staff_id = ? "
                + "WHERE rental_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setTimestamp(1, alquiler.getRentalDate());
            sentencia.setInt(2, alquiler.getInventoryId());
            sentencia.setInt(3, alquiler.getCustomerId());
            sentencia.setTimestamp(4, alquiler.getReturnDate());
            sentencia.setInt(5, alquiler.getStaffId());
            sentencia.setInt(6, alquiler.getRentalId());

            sentencia.executeUpdate();
        }
    }

    @Override
    protected Rental buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM rental WHERE rental_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {
                    return convertirRental(resultado);
                }
            }
        }

        return null;
    }

    @Override
    protected List<Rental> listar() throws SQLException {

        List<Rental> alquileres = new ArrayList<>();

        String sql = "SELECT * FROM rental";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                alquileres.add(convertirRental(resultado));
            }
        }

        return alquileres;
    }

    /**
     * Obtiene la cantidad de alquileres agrupados por cliente.
     *
     * @return lista con el ID del cliente y la cantidad de alquileres
     * @throws SQLException si ocurre un error durante la consulta
     */
    public List<Object[]> listarAlquileresPorClienteGroupBy()
            throws SQLException {

        List<Object[]> resultados = new ArrayList<>();

        String sql = "SELECT customer_id, COUNT(*) AS cantidad "
                + "FROM rental "
                + "GROUP BY customer_id "
                + "ORDER BY customer_id";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                int customerId = resultado.getInt("customer_id");
                int cantidad = resultado.getInt("cantidad");

                resultados.add(new Object[]{customerId, cantidad});
            }
        }

        return resultados;
    }

    /**
     * Obtiene la cantidad de alquileres realizados en cada tienda.
     *
     * @return lista con el ID de la tienda y la cantidad de alquileres
     * @throws SQLException si ocurre un error durante la consulta
     */
    public List<Object[]> listarAlquileresPorTiendaGroupBy()
            throws SQLException {

        List<Object[]> resultados = new ArrayList<>();

        String sql = "SELECT inventory.store_id, "
                + "COUNT(*) AS cantidad "
                + "FROM rental "
                + "INNER JOIN inventory "
                + "ON rental.inventory_id = inventory.inventory_id "
                + "GROUP BY inventory.store_id "
                + "ORDER BY inventory.store_id";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                int storeId = resultado.getInt("store_id");
                int cantidad = resultado.getInt("cantidad");

                resultados.add(new Object[]{storeId, cantidad});
            }
        }

        return resultados;
    }

    /**
     * Obtiene la cantidad de alquileres agrupados por ciudad y país
     * según la dirección registrada para cada cliente.
     *
     * @return lista con la ciudad, el país y la cantidad de alquileres
     * @throws SQLException si ocurre un error durante la consulta
     */
    public List<Object[]> listarAlquileresPorCiudadPaisGroupBy()
            throws SQLException {

        List<Object[]> resultados = new ArrayList<>();

        String sql = "SELECT city.city, country.country, "
                + "COUNT(rental.rental_id) AS cantidad "
                + "FROM rental "
                + "INNER JOIN customer "
                + "ON rental.customer_id = customer.customer_id "
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
                int cantidad = resultado.getInt("cantidad");

                resultados.add(
                        new Object[]{ciudad, pais, cantidad}
                );
            }
        }

        return resultados;
    }

    /**
     * Obtiene el aging de los alquileres vencidos que todavia
     * no han sido devueltos.
     *
     * Calcula la fecha limite utilizando rental_date y
     * rental_duration de la pelicula.
     *
     * Clasifica los alquileres en cuatro rangos:
     * 1 a 30 dias, 31 a 60 dias, 61 a 90 dias
     * y mas de 90 dias.
     *
     * @return arreglo con las cantidades de cada rango
     *         y el total de alquileres vencidos
     * @throws SQLException si ocurre un error durante la consulta
     */
    public Object[] obtenerAgingAlquileresVencidos()
            throws SQLException {

        String sql = "SELECT "
                + "COALESCE(SUM(CASE "
                + "WHEN DATEDIFF(CURRENT_DATE, "
                + "DATE_ADD(DATE(r.rental_date), "
                + "INTERVAL f.rental_duration DAY)) "
                + "BETWEEN 1 AND 30 THEN 1 ELSE 0 END), 0) "
                + "AS dias_1_30, "

                + "COALESCE(SUM(CASE "
                + "WHEN DATEDIFF(CURRENT_DATE, "
                + "DATE_ADD(DATE(r.rental_date), "
                + "INTERVAL f.rental_duration DAY)) "
                + "BETWEEN 31 AND 60 THEN 1 ELSE 0 END), 0) "
                + "AS dias_31_60, "

                + "COALESCE(SUM(CASE "
                + "WHEN DATEDIFF(CURRENT_DATE, "
                + "DATE_ADD(DATE(r.rental_date), "
                + "INTERVAL f.rental_duration DAY)) "
                + "BETWEEN 61 AND 90 THEN 1 ELSE 0 END), 0) "
                + "AS dias_61_90, "

                + "COALESCE(SUM(CASE "
                + "WHEN DATEDIFF(CURRENT_DATE, "
                + "DATE_ADD(DATE(r.rental_date), "
                + "INTERVAL f.rental_duration DAY)) "
                + "> 90 THEN 1 ELSE 0 END), 0) "
                + "AS mas_de_90, "

                + "COUNT(*) AS total_vencidos "

                + "FROM rental r "

                + "INNER JOIN inventory i "
                + "ON r.inventory_id = i.inventory_id "

                + "INNER JOIN film f "
                + "ON i.film_id = f.film_id "

                + "WHERE r.return_date IS NULL "

                + "AND DATE_ADD(DATE(r.rental_date), "
                + "INTERVAL f.rental_duration DAY) < CURRENT_DATE";

        try (PreparedStatement sentencia =
                     conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            if (resultado.next()) {

                int dias1a30 = resultado.getInt("dias_1_30");

                int dias31a60 = resultado.getInt("dias_31_60");

                int dias61a90 = resultado.getInt("dias_61_90");

                int masDe90 = resultado.getInt("mas_de_90");

                int totalVencidos =
                        resultado.getInt("total_vencidos");

                return new Object[]{
                        dias1a30,
                        dias31a60,
                        dias61a90,
                        masDe90,
                        totalVencidos
                };
            }
        }

        return new Object[]{0, 0, 0, 0, 0};
    }

    @Override
    protected void eliminar(int id) throws SQLException {

        String sql = "DELETE FROM rental WHERE rental_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            sentencia.executeUpdate();
        }
    }

    private Rental convertirRental(ResultSet resultado)
            throws SQLException {

        Rental alquiler = new Rental();

        alquiler.setRentalId(resultado.getInt("rental_id"));
        alquiler.setRentalDate(resultado.getTimestamp("rental_date"));
        alquiler.setInventoryId(resultado.getInt("inventory_id"));
        alquiler.setCustomerId(resultado.getInt("customer_id"));
        alquiler.setReturnDate(resultado.getTimestamp("return_date"));
        alquiler.setStaffId(resultado.getInt("staff_id"));
        alquiler.setLastUpdate(resultado.getTimestamp("last_update"));

        return alquiler;
    }
}
