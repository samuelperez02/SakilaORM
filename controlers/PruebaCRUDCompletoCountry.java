
package com.sakila.controlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sakila.data.Conexion;
import com.sakila.data.CountryDataContext;
import com.sakila.models.Country;

public class PruebaCRUDCompletoCountry {

    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion == null) {
            System.out.println("No se pudo conectar con Sakila.");
            return;
        }

        CountryDataContext contexto =
                new CountryDataContext(conexion);

        CountryController controlador =
                new CountryController(contexto);

        int countryId = 0;
        boolean insertado = false;

        String nombrePrueba =
                "TEST" + System.currentTimeMillis();

        try {

            // CREAR pais temporal
            Country pais = new Country();

            pais.setCountry(nombrePrueba);

            // INSERTAR
            controlador.registrarPais(pais);

            insertado = true;

            System.out.println("INSERTAR: correcto.");

            // RECUPERAR EL ID GENERADO
            String sql =
                    "SELECT country_id FROM country WHERE country = ?";

            try (PreparedStatement sentencia =
                    conexion.prepareStatement(sql)) {

                sentencia.setString(1, nombrePrueba);

                try (ResultSet resultado =
                        sentencia.executeQuery()) {

                    if (resultado.next()) {
                        countryId =
                                resultado.getInt("country_id");
                    }
                }
            }

            if (countryId == 0) {
                throw new RuntimeException(
                        "No se pudo recuperar el ID del pais.");
            }

            System.out.println("ID generado: " + countryId);

            // ACTUALIZAR
            pais.setCountryId(countryId);
            pais.setCountry("TEST ACTUALIZADO");

            controlador.actualizarPais(pais);

            System.out.println("ACTUALIZAR: correcto.");

            // BUSCAR
            Country encontrado =
                    controlador.buscarPais(countryId);

            if (encontrado != null) {

                System.out.println("BUSCAR: correcto.");
                System.out.println(encontrado);

            } else {
                throw new RuntimeException(
                        "No se encontro el pais.");
            }

            // COMPROBAR SI TIENE CIUDADES RELACIONADAS
            String consulta =
                    "SELECT COUNT(*) FROM city WHERE country_id = ?";

            int cantidadCiudades = 0;

            try (PreparedStatement sentencia =
                    conexion.prepareStatement(consulta)) {

                sentencia.setInt(1, countryId);

                try (ResultSet resultado =
                        sentencia.executeQuery()) {

                    if (resultado.next()) {
                        cantidadCiudades = resultado.getInt(1);
                    }
                }
            }

            if (cantidadCiudades == 0) {

                // ELIMINAR
                controlador.eliminarPais(countryId);

                insertado = false;

                System.out.println("ELIMINAR: correcto.");

                // VERIFICAR ELIMINACION
                Country verificacion =
                        controlador.buscarPais(countryId);

                if (verificacion == null) {
                    System.out.println(
                            "VERIFICACION: pais eliminado.");
                } else {
                    System.out.println(
                            "ADVERTENCIA: el pais todavia existe.");
                }

            } else {

                System.out.println(
                        "No se eliminara: el pais tiene ciudades relacionadas.");
            }

        } catch (Exception e) {

            System.out.println(
                    "Ocurrio un error: " + e.getMessage());

        } finally {

            // LIMPIAR SI OCURRE UN ERROR
            if (insertado && countryId != 0) {

                try {

                    String consulta =
                            "SELECT COUNT(*) FROM city WHERE country_id = ?";

                    int cantidadCiudades = 0;

                    try (PreparedStatement sentencia =
                            conexion.prepareStatement(consulta)) {

                        sentencia.setInt(1, countryId);

                        try (ResultSet resultado =
                                sentencia.executeQuery()) {

                            if (resultado.next()) {
                                cantidadCiudades = resultado.getInt(1);
                            }
                        }
                    }

                    if (cantidadCiudades == 0) {
                        controlador.eliminarPais(countryId);
                        System.out.println(
                                "Se limpio el pais de prueba.");
                    }

                } catch (Exception e) {

                    System.out.println(
                            "No se pudo limpiar el pais: "
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
