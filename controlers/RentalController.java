
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.controlers;

import java.util.List;

import com.sakila.data.RentalDataContext;
import com.sakila.models.Rental;

/**
 * Controlador encargado de gestionar las operaciones
 * relacionadas con los alquileres de películas de Sakila.
 *
 * Se comunica con RentalDataContext para registrar,
 * actualizar, buscar, listar, eliminar y consultar
 * estadísticas de alquileres por cliente, tienda,
 * ciudad, país y antigüedad de alquileres vencidos.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class RentalController {

    /** Contexto encargado de acceder a los datos de los alquileres. */
    private RentalDataContext contexto;

    public RentalController(RentalDataContext contexto) {
        this.contexto = contexto;
    }

    public void registrarAlquiler(Rental alquiler) {
        contexto.Post(alquiler);
    }

    public void actualizarAlquiler(Rental alquiler) {
        contexto.Put(alquiler);
    }

    public Rental buscarAlquiler(int id) {
        return contexto.Get(id);
    }

    public List<Rental> listarAlquileres() {
        return contexto.Get();
    }

    /**
     * Obtiene la cantidad de alquileres por cliente
     * utilizando GROUP BY en MySQL.
     *
     * @return lista con el ID del cliente y la cantidad de alquileres.
     */
    public List<Object[]> listarAlquileresPorClienteGroupBy() {
        try {
            return contexto.listarAlquileresPorClienteGroupBy();
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(
                    "Error al consultar alquileres por cliente.", e);
        }
    }

    /**
     * Obtiene la cantidad de alquileres por tienda
     * utilizando GROUP BY en MySQL.
     *
     * @return lista con el ID de la tienda y la cantidad de alquileres.
     */
    public List<Object[]> listarAlquileresPorTiendaGroupBy() {
        try {
            return contexto.listarAlquileresPorTiendaGroupBy();
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(
                    "Error al consultar alquileres por tienda.", e);
        }
    }

    /**
     * Obtiene la cantidad de alquileres agrupados por ciudad y país
     * utilizando GROUP BY en MySQL.
     *
     * @return lista con la ciudad, el país y la cantidad de alquileres.
     */
    public List<Object[]> listarAlquileresPorCiudadPaisGroupBy() {
        try {
            return contexto.listarAlquileresPorCiudadPaisGroupBy();
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(
                    "Error al consultar alquileres por ciudad y país.", e);
        }
    }

    /**
     * Obtiene el aging de los alquileres vencidos que todavía
     * no han sido devueltos.
     *
     * @return arreglo con las cantidades de alquileres vencidos
     *         en cada rango y el total general.
     */
    public Object[] obtenerAgingAlquileresVencidos() {
        try {
            return contexto.obtenerAgingAlquileresVencidos();
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(
                    "Error al consultar el aging de alquileres vencidos.", e);
        }
    }

    public void eliminarAlquiler(int id) {
        contexto.Delete(id);
    }
}