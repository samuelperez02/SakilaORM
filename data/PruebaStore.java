
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import java.sql.Connection;
import java.util.List;

import com.sakila.controlers.StoreController;
import com.sakila.models.Store;

/**
 * Clase encargada de probar la consulta y el listado
 * de registros de la tabla Store.
 *
 * Obtiene las tiendas almacenadas en la base de datos
 * y muestra sus identificadores, empleados encargados
 * y direcciones, junto con el total de registros.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaStore {

    /**
     * Método principal que ejecuta la prueba de consulta
     * y listado de tiendas.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion != null) {

            StoreDataContext contexto =
                    new StoreDataContext(conexion);

            StoreController controlador =
                    new StoreController(contexto);

            List<Store> tiendas =
                    controlador.listarTiendas();

            System.out.println("===== LISTADO DE TIENDAS =====");

            for (Store tienda : tiendas) {

                System.out.println(
                        "ID: " + tienda.getStoreId()
                        + " | Encargado: " + tienda.getManagerStaffId()
                        + " | Direccion: " + tienda.getAddressId()
                );
            }

            System.out.println("--------------------------------");
            System.out.println("Total de tiendas: " + tiendas.size());

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

