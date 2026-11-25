
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import java.sql.Connection;
import java.util.List;

import com.sakila.controlers.CustomerController;
import com.sakila.models.Customer;

/**
 * Clase encargada de probar la consulta de clientes
 * registrados en la base de datos Sakila.
 *
 * Utiliza CustomerDataContext y CustomerController
 * para obtener y mostrar los datos de los clientes.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaClientes {

    /**
     * Método principal que ejecuta la prueba.
     * Establece la conexión, consulta los clientes,
     * muestra sus datos y cierra la conexión.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion != null) {

            CustomerDataContext contexto =
                    new CustomerDataContext(conexion);

            CustomerController controlador =
                    new CustomerController(contexto);

            List<Customer> clientes =
                    controlador.listarClientes();

            System.out.println("===== LISTADO DE CLIENTES =====");

            for (Customer cliente : clientes) {

                System.out.println(
                        "ID: " + cliente.getCustomerId()
                        + " | Nombre: " + cliente.getFirstName()
                        + " | Apellido: " + cliente.getLastName()
                        + " | Email: " + cliente.getEmail()
                        + " | Tienda: " + cliente.getStoreId()
                        + " | Direccion: " + cliente.getAddressId()
                        + " | Activo: " + cliente.isActive()
                );
            }

            System.out.println("--------------------------------");
            System.out.println("Total de clientes: " + clientes.size());

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
