
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import java.sql.Connection;
import java.util.List;

import com.sakila.models.Payment;

/**
 * Clase encargada de probar la consulta y el listado
 * de registros de la tabla Payment.
 *
 * Obtiene los pagos almacenados en la base de datos
 * y muestra hasta 20 registros con sus identificadores,
 * clientes, empleados, alquileres, montos y fechas.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaPayment {

    /**
     * Método principal que ejecuta la prueba de consulta
     * y listado de pagos.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion != null) {

            PaymentDataContext contexto =
                    new PaymentDataContext(conexion);

            List<Payment> pagos = contexto.Get();

            System.out.println("Primeros pagos:");

            int limite = Math.min(20, pagos.size());

            for (int i = 0; i < limite; i++) {

                Payment pago = pagos.get(i);

                System.out.println(
                    "ID: " + pago.getPaymentId()
                    + " | Cliente: " + pago.getCustomerId()
                    + " | Empleado: " + pago.getStaffId()
                    + " | Rental: " + pago.getRentalId()
                    + " | Monto: " + pago.getAmount()
                    + " | Fecha: " + pago.getPaymentDate()
                );
            }

            System.out.println();
            System.out.println("Total de pagos: " + pagos.size());

            try {
                conexion.close();
                System.out.println("Conexion cerrada correctamente.");
            } catch (Exception e) {
                System.out.println(
                    "Error al cerrar la conexion: "
                    + e.getMessage()
                );
            }
        }
    }
}
