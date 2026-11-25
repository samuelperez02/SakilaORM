
package com.sakila.controlers;

import java.sql.Connection;
import java.sql.SQLException;

import com.sakila.data.Conexion;
import com.sakila.data.FilmCategoryDataContext;
import com.sakila.models.FilmCategory;

public class PruebaCRUDCompletoFilmCategory {

    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion == null) {
            System.out.println("No se pudo conectar con Sakila.");
            return;
        }

        FilmCategoryDataContext contexto =
                new FilmCategoryDataContext(conexion);

        FilmCategoryController controlador =
                new FilmCategoryController(contexto);

        int filmId = 0;
        int categoryId = 0;
        boolean insertado = false;

        try {

            // BUSCAR una combinacion que no exista
            boolean encontrada = false;

            for (int pelicula = 1; pelicula <= 1000 && !encontrada; pelicula++) {

                for (int categoria = 1; categoria <= 16; categoria++) {

                    if (controlador.buscar(pelicula, categoria) == null) {
                        filmId = pelicula;
                        categoryId = categoria;
                        encontrada = true;
                        break;
                    }
                }
            }

            if (!encontrada) {
                System.out.println("No se encontro una combinacion disponible.");
                return;
            }

            System.out.println("Combinacion disponible:");
            System.out.println("Film ID: " + filmId);
            System.out.println("Category ID: " + categoryId);

            // INSERTAR
            FilmCategory relacion = new FilmCategory();
            relacion.setFilmId(filmId);
            relacion.setCategoryId(categoryId);

            controlador.registrar(relacion);
            insertado = true;

            System.out.println("INSERTAR: correcto.");

            // ACTUALIZAR
            controlador.actualizar(relacion);

            System.out.println("ACTUALIZAR: correcto.");

            // BUSCAR
            FilmCategory resultado =
                    controlador.buscar(filmId, categoryId);

            if (resultado != null) {
                System.out.println("BUSCAR: correcto.");
                System.out.println("Film ID: " + resultado.getFilmId());
                System.out.println("Category ID: " + resultado.getCategoryId());
            } else {
                System.out.println("No se encontro la relacion insertada.");
            }

            // ELIMINAR
            controlador.eliminar(filmId, categoryId);
            insertado = false;

            System.out.println("ELIMINAR: correcto.");

            // VERIFICAR eliminacion
            FilmCategory verificacion =
                    controlador.buscar(filmId, categoryId);

            if (verificacion == null) {
                System.out.println("VERIFICACION: relacion eliminada.");
            } else {
                System.out.println("ADVERTENCIA: la relacion todavia existe.");
            }

        } catch (Exception e) {

            System.out.println("Ocurrio un error: " + e.getMessage());

        } finally {

            // Limpiar el registro si ocurre algun error
            if (insertado) {
                try {
                    controlador.eliminar(filmId, categoryId);
                    System.out.println("Se limpio el registro de prueba.");
                } catch (Exception e) {
                    System.out.println("No se pudo limpiar el registro: "
                            + e.getMessage());
                }
            }

            try {
                conexion.close();
                System.out.println("Conexion cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion: "
                        + e.getMessage());
            }
        }
    }
}
