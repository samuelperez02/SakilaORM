
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import java.sql.Connection;
import java.util.List;

import com.sakila.controlers.StaffController;
import com.sakila.models.Staff;

/**
 * Clase encargada de probar la consulta y el listado
 * de registros de la tabla Staff.
 *
 * Obtiene los empleados almacenados en la base de datos
 * y muestra sus identificadores, nombres, apellidos,
 * correos, tiendas, direcciones y estados de actividad.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaStaff {

    /**
     * Método principal que ejecuta la prueba de consulta
     * y listado de empleados.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion != null) {

            StaffDataContext contexto =
                    new StaffDataContext(conexion);

            StaffController controlador =
                    new StaffController(contexto);

            List<Staff> empleados =
                    controlador.listarEmpleados();

            System.out.println("===== LISTADO DE EMPLEADOS =====");

            for (Staff empleado : empleados) {

                System.out.println(
                        "ID: " + empleado.getStaffId()
                        + " | Nombre: " + empleado.getFirstName()
                        + " | Apellido: " + empleado.getLastName()
                        + " | Email: " + empleado.getEmail()
                        + " | Tienda: " + empleado.getStoreId()
                        + " | Direccion: " + empleado.getAddressId()
                        + " | Activo: " + empleado.isActive()
                );
            }

            System.out.println("--------------------------------");
            System.out.println("Total de empleados: " + empleados.size());

            try {
                conexion.close();
                System.out.println("Conexion cerrada correctamente.");
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion: "
                        + e.getMessage());
            }
        }
    }
}

