
package com.sakila.controlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sakila.data.Conexion;
import com.sakila.data.CityDataContext;
import com.sakila.models.City;

public class PruebaCRUDCompletoCity {

    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion == null) {
            System.out.println("No se pudo conectar con Sakila.");
            return;
        }

        CityDataContext contexto = new CityDataContext(conexion);
        CityController controlador = new CityController(contexto);

        int cityId = 0;
        boolean insertado = false;

        String nombrePrueba =
                "TEST" + System.currentTimeMillis();

        try {

            // COMPROBAR QUE EXISTE EL PAIS CON ID 1
            String consultaPais =
                    "SELECT COUNT(*) FROM country WHERE country_id = 1";

            try (PreparedStatement sentencia =
                    conexion.prepareStatement(consultaPais);
                 ResultSet resultado = sentencia.executeQuery()) {

                resultado.next();

                if (resultado.getInt(1) == 0) {
                    throw new RuntimeException(
                            "No existe el pais con ID 1.");
                }
            }

            // CREAR CIUDAD TEMPORAL
            City ciudad = new City();

            ciudad.setCity(nombrePrueba);
            ciudad.setCountryId(1);

            // INSERTAR
            controlador.registrarCiudad(ciudad);

            insertado = true;

            System.out.println("INSERTAR: correcto.");

            // RECUPERAR EL ID GENERADO
            String sql =
                    "SELECT city_id FROM city WHERE city = ?";

            try (PreparedStatement sentencia =
                    conexion.prepareStatement(sql)) {

                sentencia.setString(1, nombrePrueba);

                try (ResultSet resultado = sentencia.executeQuery()) {

                    if (resultado.next()) {
                        cityId = resultado.getInt("city_id");
                    }
                }
            }

            if (cityId == 0) {
                throw new RuntimeException(
                        "No se pudo recuperar el ID de la ciudad.");
            }

            System.out.println("ID generado: " + cityId);

            // ACTUALIZAR
            ciudad.setCityId(cityId);
            ciudad.setCity("TEST ACTUALIZADO");

            controlador.actualizarCiudad(ciudad);

            System.out.println("ACTUALIZAR: correcto.");

            // BUSCAR
            City encontrada = controlador.buscarCiudad(cityId);

            if (encontrada != null) {

                System.out.println("BUSCAR: correcto.");
                System.out.println(encontrada);

            } else {
                throw new RuntimeException(
                        "No se encontro la ciudad.");
            }

            // ELIMINAR
            controlador.eliminarCiudad(cityId);

            insertado = false;

            System.out.println("ELIMINAR: correcto.");

            // VERIFICAR ELIMINACION
            City verificacion = controlador.buscarCiudad(cityId);

            if (verificacion == null) {
                System.out.println(
                        "VERIFICACION: ciudad eliminada.");
            } else {
                System.out.println(
                        "ADVERTENCIA: la ciudad todavia existe.");
            }

        } catch (Exception e) {

            System.out.println(
                    "Ocurrio un error: " + e.getMessage());

        } finally {

            // LIMPIAR SI OCURRE UN ERROR
            if (insertado && cityId != 0) {

                try {
                    controlador.eliminarCiudad(cityId);

                    System.out.println(
                            "Se limpio la ciudad de prueba.");

                } catch (Exception e) {

                    System.out.println(
                            "No se pudo limpiar la ciudad: "
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
