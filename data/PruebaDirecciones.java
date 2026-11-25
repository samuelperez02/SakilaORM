
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import java.sql.Connection;
import java.util.List;

import com.sakila.controlers.AddressController;
import com.sakila.models.Address;

/**
 * Clase encargada de probar la consulta y el listado
 * de registros de la tabla Address.
 *
 * Obtiene las direcciones almacenadas en la base de datos
 * y muestra sus principales datos, incluyendo el identificador,
 * dirección, distrito, ciudad, código postal y teléfono.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaDirecciones {

    /**
     * Método principal que ejecuta la prueba de listado
     * de direcciones y muestra el total de registros encontrados.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion != null) {

            AddressDataContext contexto =
                    new AddressDataContext(conexion);

            AddressController controlador =
                    new AddressController(contexto);

            List<Address> direcciones = controlador.listarDirecciones();

            System.out.println("LISTADO DE DIRECCIONES");
            System.out.println("----------------------------");

            for (Address direccion : direcciones) {

                System.out.println("ID: " + direccion.getAddressId());
                System.out.println("Direccion: " + direccion.getAddress());
                System.out.println("Direccion 2: " + direccion.getAddress2());
                System.out.println("Distrito: " + direccion.getDistrict());
                System.out.println("ID de ciudad: " + direccion.getCityId());
                System.out.println("Codigo postal: " + direccion.getPostalCode());
                System.out.println("Telefono: " + direccion.getPhone());

                System.out.println("----------------------------");
            }

            System.out.println("Total de direcciones: " + direcciones.size());

            try {
                conexion.close();
                System.out.println("Conexion cerrada correctamente.");
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion.");
            }
        }
    }
}

