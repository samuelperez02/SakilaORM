
package com.sakila.controlers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sakila.data.Conexion;
import com.sakila.data.StaffDataContext;
import com.sakila.models.Staff;

public class PruebaCRUDCompletoStaff {

    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion == null) {
            System.out.println("No se pudo conectar con Sakila.");
            return;
        }

        StaffDataContext contexto =
                new StaffDataContext(conexion);

        StaffController controlador =
                new StaffController(contexto);

        int staffId = 0;
        boolean insertado = false;

        String usuario =
                "t" + (System.currentTimeMillis() % 1000000000L);

        String correo = usuario + "@test.com";

        try {

            // CREAR EMPLEADO TEMPORAL
            Staff empleado = new Staff();

            empleado.setFirstName("TEST");
            empleado.setLastName("EMPLEADO");
            empleado.setAddressId(1);
            empleado.setPicture(null);
            empleado.setEmail(correo);
            empleado.setStoreId(1);
            empleado.setActive(true);
            empleado.setUsername(usuario);
            empleado.setPassword("test123");

            // INSERTAR
            controlador.registrarEmpleado(empleado);

            insertado = true;

            System.out.println("INSERTAR: correcto.");

            // RECUPERAR ID GENERADO
            String sql =
                    "SELECT staff_id FROM staff WHERE username = ?";

            try (PreparedStatement sentencia =
                    conexion.prepareStatement(sql)) {

                sentencia.setString(1, usuario);

                try (ResultSet resultado = sentencia.executeQuery()) {

                    if (resultado.next()) {
                        staffId = resultado.getInt("staff_id");
                    }
                }
            }

            if (staffId == 0) {
                throw new RuntimeException(
                        "No se pudo recuperar el ID del empleado.");
            }

            System.out.println("ID generado: " + staffId);

            // ACTUALIZAR
            empleado.setStaffId(staffId);
            empleado.setFirstName("TEST ACTUALIZADO");
            empleado.setLastName("EMPLEADO ACTUALIZADO");

            controlador.actualizarEmpleado(empleado);

            System.out.println("ACTUALIZAR: correcto.");

            // BUSCAR
            Staff encontrado =
                    controlador.buscarEmpleado(staffId);

            if (encontrado != null) {

                System.out.println("BUSCAR: correcto.");
                System.out.println(encontrado);

            } else {
                throw new RuntimeException(
                        "No se encontro el empleado.");
            }

            // ELIMINAR
            controlador.eliminarEmpleado(staffId);

            insertado = false;

            System.out.println("ELIMINAR: correcto.");

            // VERIFICAR ELIMINACION
            Staff verificacion =
                    controlador.buscarEmpleado(staffId);

            if (verificacion == null) {
                System.out.println(
                        "VERIFICACION: empleado eliminado.");
            } else {
                System.out.println(
                        "ADVERTENCIA: el empleado todavia existe.");
            }

        } catch (Exception e) {

            System.out.println(
                    "Ocurrio un error: " + e.getMessage());

        } finally {

            // LIMPIAR SI OCURRE UN ERROR
            if (insertado && staffId != 0) {

                try {
                    controlador.eliminarEmpleado(staffId);

                    System.out.println(
                            "Se limpio el empleado de prueba.");

                } catch (Exception e) {

                    System.out.println(
                            "No se pudo limpiar el empleado: "
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

