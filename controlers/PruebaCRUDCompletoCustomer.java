
package com.sakila.controlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.sakila.data.Conexion;
import com.sakila.data.CustomerDataContext;
import com.sakila.models.Customer;

public class PruebaCRUDCompletoCustomer {

    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion == null) {
            System.out.println("No se pudo conectar con Sakila.");
            return;
        }

        CustomerDataContext contexto =
                new CustomerDataContext(conexion);

        CustomerController controlador =
                new CustomerController(contexto);

        int customerId = 0;
        boolean insertado = false;

        String correo =
                "test" + System.currentTimeMillis() + "@test.com";

        try {

            // CREAR CLIENTE TEMPORAL
            Customer cliente = new Customer();

            cliente.setStoreId(1);
            cliente.setFirstName("TEST");
            cliente.setLastName("CLIENTE");
            cliente.setEmail(correo);
            cliente.setAddressId(1);
            cliente.setActive(true);
            cliente.setCreateDate(
                    new Timestamp(System.currentTimeMillis()));

            // INSERTAR
            controlador.registrarCliente(cliente);

            insertado = true;

            System.out.println("INSERTAR: correcto.");

            // RECUPERAR EL ID GENERADO
            String sql =
                    "SELECT customer_id FROM customer WHERE email = ?";

            try (PreparedStatement sentencia =
                    conexion.prepareStatement(sql)) {

                sentencia.setString(1, correo);

                try (ResultSet resultado = sentencia.executeQuery()) {

                    if (resultado.next()) {
                        customerId =
                                resultado.getInt("customer_id");
                    }
                }
            }

            if (customerId == 0) {
                throw new RuntimeException(
                        "No se pudo recuperar el ID del cliente.");
            }

            System.out.println("ID generado: " + customerId);

            // ACTUALIZAR
            cliente.setCustomerId(customerId);
            cliente.setFirstName("TEST ACTUALIZADO");
            cliente.setLastName("CLIENTE ACTUALIZADO");

            controlador.actualizarCliente(cliente);

            System.out.println("ACTUALIZAR: correcto.");

            // BUSCAR
            Customer encontrado =
                    controlador.buscarCliente(customerId);

            if (encontrado != null) {

                System.out.println("BUSCAR: correcto.");
                System.out.println(encontrado);

            } else {
                throw new RuntimeException(
                        "No se encontro el cliente.");
            }

            // ELIMINAR
            controlador.eliminarCliente(customerId);

            insertado = false;

            System.out.println("ELIMINAR: correcto.");

            // VERIFICAR ELIMINACION
            Customer verificacion =
                    controlador.buscarCliente(customerId);

            if (verificacion == null) {
                System.out.println(
                        "VERIFICACION: cliente eliminado.");
            } else {
                System.out.println(
                        "ADVERTENCIA: el cliente todavia existe.");
            }

        } catch (Exception e) {

            System.out.println(
                    "Ocurrio un error: " + e.getMessage());

        } finally {

            // LIMPIAR SI OCURRE UN ERROR
            if (insertado && customerId != 0) {

                try {
                    controlador.eliminarCliente(customerId);

                    System.out.println(
                            "Se limpio el cliente de prueba.");

                } catch (Exception e) {

                    System.out.println(
                            "No se pudo limpiar el cliente: "
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
