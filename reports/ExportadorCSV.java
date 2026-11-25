
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.reports;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Exporta listas de objetos a archivos en formato CSV.
 * Utiliza reflexión para obtener los atributos de los objetos
 * y escribirlos como columnas del archivo.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class ExportadorCSV {

    /**
     * Exporta una lista de objetos a un archivo CSV.
     * La primera fila contiene los nombres de los atributos
     * y las siguientes filas contienen los valores de cada objeto.
     *
     * @param datos lista de objetos que se desea exportar
     * @param ruta ubicación y nombre del archivo CSV
     */
    public void exportar(List<?> datos, String ruta) {

        if (datos == null || datos.isEmpty()) {
            System.out.println("No hay datos para exportar.");
            return;
        }

        try (BufferedWriter escritor =
                new BufferedWriter(new FileWriter(ruta))) {

            // Obtener los campos del primer objeto
            Field[] campos = datos.get(0).getClass().getDeclaredFields();

            // Escribir los nombres de las columnas
            for (int i = 0; i < campos.length; i++) {

                escritor.write(campos[i].getName());

                if (i < campos.length - 1) {
                    escritor.write(",");
                }
            }

            escritor.newLine();

            // Escribir los datos
            for (Object objeto : datos) {

                for (int i = 0; i < campos.length; i++) {

                    campos[i].setAccessible(true);

                    Object valor = campos[i].get(objeto);

                    String texto = valor == null
                            ? ""
                            : valor.toString();

                    // Evitar problemas con comas y comillas
                    texto = texto.replace("\"", "\"\"");

                    escritor.write("\"" + texto + "\"");

                    if (i < campos.length - 1) {
                        escritor.write(",");
                    }
                }

                escritor.newLine();
            }

            System.out.println("Archivo CSV exportado correctamente.");
            System.out.println("Ubicacion: " + ruta);

        } catch (IOException | IllegalAccessException e) {

            System.out.println("Error al exportar CSV: "
                    + e.getMessage());
        }
    }
}
