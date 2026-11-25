
package com.sakila.controlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sakila.data.Conexion;
import com.sakila.data.AddressDataContext;
import com.sakila.models.Address;

public class PruebaCRUDCompletoAddress {

    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion == null) {
            System.out.println("No se pudo conectar con Sakila.");
            return;
        }

        AddressDataContext contexto =
                new AddressDataContext(conexion);

        AddressController controlador =
                new AddressController(contexto);

        int addressId = 0;
        boolean insertado = false;

        String nombrePrueba =
                "TEST" + System.currentTimeMillis();

        try {

            // OBTENER DATOS GEOGRAFICOS DE UNA DIRECCION EXISTENTE
            byte[] ubicacion = null;

            String consulta =
                    "SELECT ST_AsWKB(location) AS location "
                    + "FROM address WHERE address_id = 1";

            try (PreparedStatement sentencia =
                    conexion.prepareStatement(consulta);
                 ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {
                    ubicacion = resultado.getBytes("location");
                }
            }

            if (ubicacion == null) {
                throw new RuntimeException(
                        "No se pudo obtener la ubicacion geografica.");
            }

            // CREAR DIRECCION TEMPORAL
            Address direccion = new Address();

            direccion.setAddress(nombrePrueba);
            direccion.setAddress2(null);
            direccion.setDistrict("TEST");
            direccion.setCityId(1);
            direccion.setPostalCode("00000");
            direccion.setPhone("0000000000");
            direccion.setLocation(ubicacion);

            // INSERTAR
            controlador.registrarDireccion(direccion);

            insertado = true;

            System.out.println("INSERTAR: correcto.");

            // RECUPERAR EL ID GENERADO
            String sql =
                    "SELECT address_id FROM address WHERE address = ?";

            try (PreparedStatement sentencia =
                    conexion.prepareStatement(sql)) {

                sentencia.setString(1, nombrePrueba);

                try (ResultSet resultado = sentencia.executeQuery()) {

                    if (resultado.next()) {
                        addressId =
                                resultado.getInt("address_id");
                    }
                }
            }

            if (addressId == 0) {
                throw new RuntimeException(
                        "No se pudo recuperar el ID de la direccion.");
            }

            System.out.println("ID generado: " + addressId);

            // ACTUALIZAR
            direccion.setAddressId(addressId);
            direccion.setAddress("TEST ACTUALIZADO");

            controlador.actualizarDireccion(direccion);

            System.out.println("ACTUALIZAR: correcto.");

            // BUSCAR
            Address encontrada =
                    controlador.buscarDireccion(addressId);

            if (encontrada != null) {

                System.out.println("BUSCAR: correcto.");
                System.out.println(encontrada);

            } else {
                throw new RuntimeException(
                        "No se encontro la direccion.");
            }

            // ELIMINAR
            controlador.eliminarDireccion(addressId);

            insertado = false;

            System.out.println("ELIMINAR: correcto.");

            // VERIFICAR ELIMINACION
            Address verificacion =
                    controlador.buscarDireccion(addressId);

            if (verificacion == null) {
                System.out.println(
                        "VERIFICACION: direccion eliminada.");
            } else {
                System.out.println(
                        "ADVERTENCIA: la direccion todavia existe.");
            }

        } catch (Exception e) {

            System.out.println(
                    "Ocurrio un error: " + e.getMessage());

        } finally {

            // LIMPIAR SI OCURRE UN ERROR
            if (insertado && addressId != 0) {

                try {
                    controlador.eliminarDireccion(addressId);

                    System.out.println(
                            "Se limpio la direccion de prueba.");

                } catch (Exception e) {

                    System.out.println(
                            "No se pudo limpiar la direccion: "
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
