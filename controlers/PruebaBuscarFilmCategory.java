
package com.sakila.controlers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.sakila.data.Conexion;
import com.sakila.data.FilmCategoryDataContext;
import com.sakila.models.FilmCategory;

public class PruebaBuscarFilmCategory {

    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        try {
            FilmCategoryDataContext contexto =
                    new FilmCategoryDataContext(conexion);

            FilmCategoryController controlador =
                    new FilmCategoryController(contexto);

            List<FilmCategory> relaciones = controlador.listar();

            System.out.println("TOTAL DE RELACIONES: "
                    + relaciones.size());

            if (!relaciones.isEmpty()) {

                FilmCategory primeraRelacion = relaciones.get(0);

                int filmId = primeraRelacion.getFilmId();
                int categoryId = primeraRelacion.getCategoryId();

                FilmCategory encontrada =
                        controlador.buscar(filmId, categoryId);

                if (encontrada != null) {

                    System.out.println("BUSQUEDA EXITOSA");
                    System.out.println("Film ID: "
                            + encontrada.getFilmId());
                    System.out.println("Category ID: "
                            + encontrada.getCategoryId());

                } else {
                    System.out.println("No se encontro la relacion.");
                }
            }

        } finally {

            try {
                if (conexion != null) {
                    conexion.close();
                    System.out.println("Conexion cerrada.");
                }
            } catch (SQLException e) {
                System.out.println("Error al cerrar: "
                        + e.getMessage());
            }
        }
    }
}
