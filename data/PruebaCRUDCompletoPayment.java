
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.sakila.controlers.PaymentController;
import com.sakila.models.Payment;

/**
 * Clase encargada de probar las operaciones CRUD completas
 * de la tabla Payment.
 *
 * Comprueba la inserción, actualización, búsqueda y eliminación
 * de pagos, incluyendo el manejo de rental_id con valor null.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaCRUDCompletoPayment {

    /**
     * Método principal que ejecuta las pruebas CRUD de Payment.
     * Obtiene un cliente y un empleado existentes para registrar
     * un pago temporal y comprobar las operaciones correspondientes.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion == null) {
            System.out.println("No se pudo conectar.");
            return;
        }

        PaymentController controlador =
                new PaymentController(new PaymentDataContext(conexion));

        int idPago = 0;

        try {

            // 1. Obtener identificadores validos
            int customerId;
            int staffId;

            try (PreparedStatement sentencia = conexion.prepareStatement(
                    "SELECT customer_id FROM customer LIMIT 1");
                 ResultSet resultado = sentencia.executeQuery()) {

                if (!resultado.next()) {
                    throw new Exception("No hay clientes disponibles.");
                }

                customerId = resultado.getInt("customer_id");
            }

            try (PreparedStatement sentencia = conexion.prepareStatement(
                    "SELECT staff_id FROM staff LIMIT 1");
                 ResultSet resultado = sentencia.executeQuery()) {

                if (!resultado.next()) {
                    throw new Exception("No hay empleados disponibles.");
                }

                staffId = resultado.getInt("staff_id");
            }

            // 2. INSERTAR pago
            Timestamp fechaActual = Timestamp.valueOf(
                    java.time.LocalDateTime.now().withNano(0));

            Payment pago = new Payment();
            pago.setCustomerId(customerId);
            pago.setStaffId(staffId);
            pago.setRentalId(null);
            pago.setAmount(new BigDecimal("10.00"));
            pago.setPaymentDate(fechaActual);

            controlador.registrar(pago);

            // Recuperar directamente el ID generado por MySQL
            idPago = pago.getPaymentId();

            if (idPago <= 0) {
                throw new Exception(
                        "No se pudo recuperar el ID del pago creado.");
            }

            System.out.println("INSERTAR: correcto.");
            System.out.println("ID generado: " + idPago);

            // 3. ACTUALIZAR pago
            pago.setAmount(new BigDecimal("15.50"));

            controlador.actualizar(pago);

            System.out.println("ACTUALIZAR: correcto.");

            // 4. BUSCAR pago y comprobar los datos actualizados
            Payment encontrado = controlador.buscar(idPago);

            if (encontrado == null) {
                throw new Exception(
                        "No se encontro el pago despues de actualizarlo.");
            }

            if (encontrado.getAmount() == null
                    || encontrado.getAmount().compareTo(
                            new BigDecimal("15.50")) != 0) {

                throw new Exception(
                        "El monto del pago no fue actualizado correctamente.");
            }

            if (encontrado.getRentalId() != null) {
                throw new Exception(
                        "El campo rental_id debia conservar el valor NULL.");
            }

            System.out.println("BUSCAR: correcto.");
            System.out.println(encontrado);

            // 5. ELIMINAR pago
            controlador.eliminar(idPago);

            System.out.println("ELIMINAR: correcto.");

            // 6. VERIFICAR eliminacion
            Payment verificacion = controlador.buscar(idPago);

            if (verificacion == null) {
                System.out.println("VERIFICACION: pago eliminado.");
            } else {
                throw new Exception("El pago no fue eliminado.");
            }

            // Indicar que la prueba termino correctamente
            idPago = 0;

            System.out.println("PRUEBA COMPLETADA.");

        } catch (Exception e) {

            System.out.println("ERROR EN LA PRUEBA: " + e.getMessage());
            e.printStackTrace();

        } finally {

            // Limpiar el pago temporal si la prueba fallo
            if (idPago > 0) {

                try {

                    controlador.eliminar(idPago);

                    System.out.println(
                            "LIMPIEZA: pago temporal eliminado.");

                } catch (Exception e) {

                    System.out.println(
                            "Error durante la limpieza del pago: "
                            + e.getMessage());
                }
            }

            try {
                conexion.close();
                System.out.println("Conexion cerrada.");
            } catch (Exception e) {
                System.out.println(
                        "Error al cerrar conexion: " + e.getMessage());
            }
        }
    }
}

