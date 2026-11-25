
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.sakila.controlers.RentalController;
import com.sakila.models.Rental;

/**
 * Clase encargada de probar las operaciones CRUD completas
 * de la tabla Rental.
 *
 * Comprueba la inserción, actualización, búsqueda y eliminación
 * de alquileres, además de verificar que no existan pagos
 * relacionados antes de eliminar un registro.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaCRUDCompletoRental {

    /**
     * Método principal que ejecuta las pruebas CRUD de Rental.
     * Obtiene registros existentes de inventario, clientes y empleados
     * para crear un alquiler temporal y comprobar sus operaciones.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion == null) {
            System.out.println("No se pudo conectar.");
            return;
        }

        RentalController controlador =
                new RentalController(new RentalDataContext(conexion));

        int idAlquiler = 0;

        try {

            // 1. Obtener identificadores validos
            int inventoryId;
            int customerId;
            int staffId;

            try (PreparedStatement sentencia = conexion.prepareStatement(
                    "SELECT inventory_id FROM inventory LIMIT 1");
                 ResultSet resultado = sentencia.executeQuery()) {

                if (!resultado.next()) {
                    throw new Exception("No hay inventario disponible.");
                }

                inventoryId = resultado.getInt("inventory_id");
            }

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

            // 2. INSERTAR alquiler
            Timestamp fechaActual =
                    Timestamp.valueOf(
                            java.time.LocalDateTime.now().withNano(0));

            Rental alquiler = new Rental();
            alquiler.setRentalDate(fechaActual);
            alquiler.setInventoryId(inventoryId);
            alquiler.setCustomerId(customerId);
            alquiler.setReturnDate(null);
            alquiler.setStaffId(staffId);

            controlador.registrarAlquiler(alquiler);

            // Recuperar directamente el ID generado por MySQL
            idAlquiler = alquiler.getRentalId();

            if (idAlquiler <= 0) {
                throw new Exception(
                        "No se pudo recuperar el ID del alquiler creado.");
            }

            System.out.println("INSERTAR: correcto.");
            System.out.println("ID generado: " + idAlquiler);

            // 3. ACTUALIZAR alquiler
            Timestamp nuevaFecha =
                    new Timestamp(System.currentTimeMillis() + 1000);

            alquiler.setRentalDate(nuevaFecha);
            alquiler.setReturnDate(
                    new Timestamp(System.currentTimeMillis() + 86400000L));

            controlador.actualizarAlquiler(alquiler);

            System.out.println("ACTUALIZAR: correcto.");

            // 4. BUSCAR alquiler y verificar que exista
            Rental encontrado =
                    controlador.buscarAlquiler(idAlquiler);

            if (encontrado == null) {
                throw new Exception(
                        "No se encontro el alquiler despues de actualizarlo.");
            }

            System.out.println("BUSCAR: correcto.");
            System.out.println(encontrado);

            // 5. COMPROBAR que no tenga pagos relacionados
            String sqlPagos =
                    "SELECT COUNT(*) FROM payment WHERE rental_id = ?";

            try (PreparedStatement sentencia =
                    conexion.prepareStatement(sqlPagos)) {

                sentencia.setInt(1, idAlquiler);

                try (ResultSet resultado = sentencia.executeQuery()) {

                    if (resultado.next() && resultado.getInt(1) != 0) {
                        throw new Exception(
                                "El alquiler tiene pagos relacionados.");
                    }
                }
            }

            System.out.println(
                    "RELACIONES: no tiene pagos relacionados.");

            // 6. ELIMINAR alquiler
            controlador.eliminarAlquiler(idAlquiler);

            System.out.println("ELIMINAR: correcto.");

            // 7. VERIFICAR que haya sido eliminado
            Rental verificacion =
                    controlador.buscarAlquiler(idAlquiler);

            if (verificacion == null) {

                System.out.println(
                        "VERIFICACION: alquiler eliminado.");

                // Evitar intentar eliminarlo nuevamente
                idAlquiler = 0;

            } else {

                throw new Exception(
                        "El alquiler no fue eliminado.");
            }

            System.out.println("PRUEBA COMPLETADA.");

        } catch (Exception e) {

            System.out.println("ERROR EN LA PRUEBA: " + e.getMessage());
            e.printStackTrace();

        } finally {

            // 8. LIMPIAR el alquiler temporal si la prueba fallo
            if (idAlquiler > 0) {

                try {

                    String sqlPagos =
                            "SELECT COUNT(*) FROM payment WHERE rental_id = ?";

                    boolean tienePagos = false;

                    try (PreparedStatement sentencia =
                            conexion.prepareStatement(sqlPagos)) {

                        sentencia.setInt(1, idAlquiler);

                        try (ResultSet resultado = sentencia.executeQuery()) {

                            if (resultado.next()) {
                                tienePagos = resultado.getInt(1) > 0;
                            }
                        }
                    }

                    if (!tienePagos) {

                        controlador.eliminarAlquiler(idAlquiler);

                        System.out.println(
                                "LIMPIEZA: alquiler temporal eliminado.");

                    } else {

                        System.out.println(
                                "LIMPIEZA: no se elimino porque tiene pagos relacionados.");
                    }

                } catch (Exception e) {

                    System.out.println(
                            "Error durante la limpieza: " + e.getMessage());
                }
            }

            // 9. CERRAR conexion
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


