
package com.sakila.controlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sakila.data.Conexion;
import com.sakila.data.LanguageDataContext;
import com.sakila.models.Language;

public class PruebaCRUDCompletoLanguage {

    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion == null) {
            System.out.println("No se pudo conectar con Sakila.");
            return;
        }

        LanguageDataContext contexto =
                new LanguageDataContext(conexion);

        LanguageController controlador =
                new LanguageController(contexto);

        int languageId = 0;
        boolean insertado = false;

        String nombrePrueba =
                "TEST" + System.currentTimeMillis();
        try {

            // CREAR idioma temporal
            Language idioma = new Language();

            idioma.setName(nombrePrueba);

            // INSERTAR
            controlador.registrarIdioma(idioma);

            insertado = true;

            System.out.println("INSERTAR: correcto.");

            // RECUPERAR EL ID GENERADO
            String sql =
                    "SELECT language_id FROM language WHERE name = ?";

            try (PreparedStatement sentencia =
                    conexion.prepareStatement(sql)) {

                sentencia.setString(1, nombrePrueba);

                try (ResultSet resultado =
                        sentencia.executeQuery()) {

                    if (resultado.next()) {
                        languageId =
                                resultado.getInt("language_id");
                    }
                }
            }

            if (languageId == 0) {
                throw new RuntimeException(
                        "No se pudo recuperar el ID del idioma.");
            }

            System.out.println("ID generado: " + languageId);

            // ACTUALIZAR
            idioma.setLanguageId(languageId);
            idioma.setName("TEST ACTUALIZADO");

            controlador.actualizarIdioma(idioma);

            System.out.println("ACTUALIZAR: correcto.");

            // BUSCAR
            Language encontrado =
                    controlador.buscarIdioma(languageId);

            if (encontrado != null) {

                System.out.println("BUSCAR: correcto.");
                System.out.println(encontrado);

            } else {
                throw new RuntimeException(
                        "No se encontro el idioma.");
            }

            // ELIMINAR
            controlador.eliminarIdioma(languageId);

            insertado = false;

            System.out.println("ELIMINAR: correcto.");

            // VERIFICAR ELIMINACION
            Language verificacion =
                    controlador.buscarIdioma(languageId);

            if (verificacion == null) {
                System.out.println(
                        "VERIFICACION: idioma eliminado.");
            } else {
                System.out.println(
                        "ADVERTENCIA: el idioma todavia existe.");
            }

        } catch (Exception e) {

            System.out.println(
                    "Ocurrio un error: " + e.getMessage());

        } finally {

            // LIMPIAR SI OCURRE UN ERROR
            if (insertado && languageId != 0) {

                try {
                    controlador.eliminarIdioma(languageId);

                    System.out.println(
                            "Se limpio el idioma de prueba.");

                } catch (Exception e) {

                    System.out.println(
                            "No se pudo limpiar el idioma: "
                            + e.getMessage());
                }
            }

            try {
                conexion.close();

                System.out.println("Conexion cerrada.");

            } catch (SQLException e) {

                System.out.println(
                        "Error al cerrar la conexion: "
                        + e.getMessage());
            }
        }
    }
}
