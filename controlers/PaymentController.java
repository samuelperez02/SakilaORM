
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.controlers;

import com.sakila.data.PaymentDataContext;
import com.sakila.models.Payment;

import java.sql.SQLException;
import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones
 * relacionadas con los pagos de la base de datos Sakila.
 *
 * Se comunica con PaymentDataContext para registrar,
 * actualizar, buscar, listar, eliminar y consultar
 * estadísticas de pagos por cliente, tienda, ciudad y país.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PaymentController {

    /** Contexto encargado de acceder a los datos de los pagos. */
    private PaymentDataContext contexto;

    public PaymentController(PaymentDataContext contexto) {
        this.contexto = contexto;
    }

    public void registrar(Payment pago) {
        contexto.Post(pago);
    }

    public void actualizar(Payment pago) {
        contexto.Put(pago);
    }

    public Payment buscar(int id) {
        return contexto.Get(id);
    }

    public List<Payment> listar() {
        return contexto.Get();
    }

    /**
     * Obtiene el total pagado por cada cliente
     * utilizando GROUP BY y SUM en MySQL.
     *
     * @return lista con el ID del cliente y el total pagado.
     */
    public List<Object[]> listarPagosPorClienteGroupBy() {
        try {
            return contexto.listarPagosPorClienteGroupBy();
        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error al consultar los pagos agrupados por cliente.", e);
        }
    }

    /**
     * Obtiene el total de pagos realizados en cada tienda
     * utilizando GROUP BY y SUM en MySQL.
     *
     * @return lista con el ID de la tienda y el total pagado.
     */
    public List<Object[]> listarPagosPorTiendaGroupBy() {
        try {
            return contexto.listarPagosPorTiendaGroupBy();
        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error al consultar los pagos agrupados por tienda.", e);
        }
    }

    /**
     * Obtiene el total de pagos agrupados por ciudad y país
     * utilizando GROUP BY y SUM en MySQL.
     *
     * @return lista con la ciudad, el país y el total pagado.
     */
    public List<Object[]> listarPagosPorCiudadPaisGroupBy() {
        try {
            return contexto.listarPagosPorCiudadPaisGroupBy();
        } catch (SQLException e) {
            throw new RuntimeException(
                    "Error al consultar los pagos agrupados por ciudad y país.", e);
        }
    }

    public void eliminar(int id) {
        contexto.Delete(id);
    }
}


