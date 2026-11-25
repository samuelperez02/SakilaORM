
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import com.sakila.models.Staff;
import com.sakila.models.Store;
import com.sakila.controlers.StaffController;
import com.sakila.controlers.StoreController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Clase encargada de probar las operaciones CRUD completas
 * de la tabla Store y su relación con la tabla Staff.
 *
 * Crea un empleado temporal, lo asigna como administrador
 * de una tienda, actualiza los datos y comprueba las operaciones
 * de búsqueda y eliminación.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaCRUDCompletoStore {

    /**
     * Método principal que ejecuta las pruebas CRUD de Store.
     * Crea registros temporales de empleado y tienda para
     * comprobar su relación y las operaciones correspondientes.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion == null) {
            System.out.println("No se pudo conectar.");
            return;
        }

        StaffController empleadoController =
                new StaffController(new StaffDataContext(conexion));

        StoreController tiendaController =
                new StoreController(new StoreDataContext(conexion));

        int idEmpleado = 0;
        int idTienda = 0;

        try {

            // 1. VERIFICAR que las direcciones existan
            String sqlDirecciones =
                    "SELECT COUNT(*) FROM address WHERE address_id IN (1, 2)";

            try (PreparedStatement sentencia =
                    conexion.prepareStatement(sqlDirecciones);
                 ResultSet resultado = sentencia.executeQuery()) {

                if (!resultado.next() || resultado.getInt(1) != 2) {
                    throw new Exception(
                            "No existen las direcciones 1 y 2 necesarias para la prueba.");
                }
            }

            System.out.println("DIRECCIONES: verificadas.");

            // 2. CREAR un empleado temporal
            String usuario = "t" +
                    (System.currentTimeMillis() % 1000000000L);

            Staff empleado = new Staff();
            empleado.setFirstName("TEST");
            empleado.setLastName("EMPLEADO TIENDA");
            empleado.setAddressId(1);
            empleado.setPicture(null);
            empleado.setEmail(usuario + "@test.com");
            empleado.setStoreId(1);
            empleado.setActive(true);
            empleado.setUsername(usuario);
            empleado.setPassword("12345");

            empleadoController.registrarEmpleado(empleado);

            // Recuperar directamente el ID generado por MySQL
            idEmpleado = empleado.getStaffId();

            if (idEmpleado <= 0) {
                throw new Exception(
                        "No se pudo recuperar el ID del empleado creado.");
            }

            System.out.println("EMPLEADO TEMPORAL: " + idEmpleado);

            // 3. VERIFICAR que el empleado se haya registrado
            Staff empleadoEncontrado =
                    empleadoController.buscarEmpleado(idEmpleado);

            if (empleadoEncontrado == null) {
                throw new Exception(
                        "No se encontro el empleado temporal.");
            }

            System.out.println("BUSCAR EMPLEADO: correcto.");

            // 4. CREAR una tienda utilizando ese empleado
            Store tienda = new Store();
            tienda.setManagerStaffId(idEmpleado);
            tienda.setAddressId(1);

            tiendaController.registrarTienda(tienda);

            // Recuperar directamente el ID generado por MySQL
            idTienda = tienda.getStoreId();

            if (idTienda <= 0) {
                throw new Exception(
                        "No se pudo recuperar el ID de la tienda creada.");
            }

            System.out.println("INSERTAR TIENDA: correcto.");
            System.out.println("ID de la tienda: " + idTienda);

            // 5. ACTUALIZAR la tienda
            tienda.setAddressId(2);

            tiendaController.actualizarTienda(tienda);

            System.out.println("ACTUALIZAR: correcto.");

            // 6. BUSCAR la tienda y comprobar la actualizacion
            Store encontrada =
                    tiendaController.buscarTienda(idTienda);

            if (encontrada == null) {
                throw new Exception(
                        "No se encontro la tienda despues de actualizarla.");
            }

            if (encontrada.getAddressId() != 2) {
                throw new Exception(
                        "La direccion de la tienda no se actualizo correctamente.");
            }

            System.out.println("BUSCAR TIENDA: correcto.");
            System.out.println(encontrada);

            // 7. ELIMINAR primero la tienda
            tiendaController.eliminarTienda(idTienda);

            // Verificar que la tienda haya sido eliminada
            Store verificacionTienda =
                    tiendaController.buscarTienda(idTienda);

            if (verificacionTienda != null) {
                throw new Exception(
                        "La tienda no fue eliminada correctamente.");
            }

            idTienda = 0;

            System.out.println("ELIMINAR TIENDA: correcto.");

            // 8. ELIMINAR después el empleado temporal
            empleadoController.eliminarEmpleado(idEmpleado);

            // Verificar que el empleado haya sido eliminado
            Staff verificacionEmpleado =
                    empleadoController.buscarEmpleado(idEmpleado);

            if (verificacionEmpleado != null) {
                throw new Exception(
                        "El empleado temporal no fue eliminado correctamente.");
            }

            idEmpleado = 0;

            System.out.println("ELIMINAR EMPLEADO: correcto.");

            System.out.println("PRUEBA COMPLETADA.");

        } catch (Exception e) {

            System.out.println("ERROR EN LA PRUEBA: " + e.getMessage());
            e.printStackTrace();

        } finally {

            // 9. LIMPIAR la tienda temporal si la prueba fallo
            if (idTienda > 0) {

                try {
                    tiendaController.eliminarTienda(idTienda);

                    System.out.println(
                            "LIMPIEZA: tienda temporal eliminada.");

                } catch (Exception e) {

                    System.out.println(
                            "Error al limpiar la tienda: " + e.getMessage());
                }
            }

            // 10. LIMPIAR el empleado temporal si la prueba fallo
            if (idEmpleado > 0) {

                try {
                    empleadoController.eliminarEmpleado(idEmpleado);

                    System.out.println(
                            "LIMPIEZA: empleado temporal eliminado.");

                } catch (Exception e) {

                    System.out.println(
                            "Error al limpiar el empleado: " + e.getMessage());
                }
            }

            // 11. CERRAR la conexion
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
