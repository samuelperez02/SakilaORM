
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.data;

import java.sql.Connection;
import java.util.List;

import com.sakila.controlers.LanguageController;
import com.sakila.models.Language;

/**
 * Clase encargada de probar la consulta y el listado
 * de registros de la tabla Language.
 *
 * Obtiene los idiomas almacenados en la base de datos
 * y muestra sus identificadores, nombres y el total
 * de registros encontrados.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class PruebaIdiomas {

    /**
     * Método principal que ejecuta la prueba de consulta
     * y listado de idiomas.
     *
     * @param args argumentos recibidos al ejecutar el programa
     */
    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion != null) {

            LanguageDataContext contexto =
                    new LanguageDataContext(conexion);

            LanguageController controlador =
                    new LanguageController(contexto);

            List<Language> idiomas = controlador.listarIdiomas();

            System.out.println("LISTADO DE IDIOMAS");
            System.out.println("----------------------------");

            for (Language idioma : idiomas) {

                System.out.println("ID: " + idioma.getLanguageId());
                System.out.println("Nombre: " + idioma.getName());
                System.out.println("----------------------------");
            }

            System.out.println("Total de idiomas: " + idiomas.size());

            try {
                conexion.close();
                System.out.println("Conexion cerrada correctamente.");
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion.");
            }
        }
    }
}

