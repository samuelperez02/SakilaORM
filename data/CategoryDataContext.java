
/**
 * Copyright (c) 2026.
 * Proyecto final de Lenguaje de Programación II.
 *
 * Clase encargada de realizar las operaciones de acceso a datos
 * de la tabla category de la base de datos Sakila.
 *
 * @author Samuel Elias Perez Ramirez
 * @version 1.0
 */
package com.sakila.data;

import com.sakila.models.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DataContext concreto que administra las categorías de películas.
 *
 * Permite insertar, actualizar, buscar, listar y eliminar registros
 * de la tabla category.
 */
public final class CategoryDataContext extends DataContext<Category> {

    /**
     * Constructor que recibe la conexión con la base de datos.
     *
     * @param conexion conexión activa con MySQL.
     */
    public CategoryDataContext(java.sql.Connection conexion) {
        super(conexion);
    }

    /**
     * Inserta una nueva categoría en la tabla category.
     *
     * Recupera la clave primaria generada por MySQL y la asigna
     * al objeto Category.
     *
     * @param categoria objeto con los datos de la categoría.
     * @throws SQLException si ocurre un error al insertar el registro.
     */
    @Override
    protected void insertar(Category categoria) throws SQLException {

        String sql = "INSERT INTO category (name) VALUES (?)";

        try (PreparedStatement sentencia = conexion.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            sentencia.setString(1, categoria.getName());

            sentencia.executeUpdate();

            try (ResultSet resultado = sentencia.getGeneratedKeys()) {

                if (resultado.next()) {
                    categoria.setCategoryId(resultado.getInt(1));
                }
            }
        }
    }

    /**
     * Actualiza el nombre de una categoría existente.
     *
     * @param categoria objeto con el identificador y el nuevo nombre.
     * @throws SQLException si ocurre un error al actualizar el registro.
     */
    @Override
    protected void actualizar(Category categoria) throws SQLException {

        String sql = "UPDATE category SET name = "
                + "? WHERE category_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, categoria.getName());
            sentencia.setInt(2, categoria.getCategoryId());

            sentencia.executeUpdate();
        }
    }

    /**
     * Busca una categoría utilizando su identificador.
     *
     * @param id identificador de la categoría que se desea buscar.
     * @return objeto Category encontrado o null si no existe.
     * @throws SQLException si ocurre un error durante la consulta.
     */
    @Override
    protected Category buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM category WHERE category_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {
                    return convertirCategory(resultado);
                }
            }
        }

        return null;
    }

    /**
     * Recupera todas las categorías registradas en la base de datos.
     *
     * @return lista con los objetos Category encontrados.
     * @throws SQLException si ocurre un error durante la consulta.
     */
    @Override
    protected List<Category> listar() throws SQLException {

        List<Category> categorias = new ArrayList<>();

        String sql = "SELECT * FROM category";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                categorias.add(convertirCategory(resultado));
            }
        }

        return categorias;
    }

    /**
     * Elimina una categoría utilizando su identificador.
     *
     * @param id identificador de la categoría que se desea eliminar.
     * @throws SQLException si ocurre un error al eliminar el registro,
     *                      por ejemplo, por una restricción de clave foránea.
     */
    @Override
    protected void eliminar(int id) throws SQLException {

        String sql = "DELETE FROM category WHERE category_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            sentencia.executeUpdate();
        }
    }

    /**
     * Convierte una fila del resultado SQL en un objeto Category.
     *
     * @param resultado resultado de la consulta SQL.
     * @return objeto Category con los datos recuperados.
     * @throws SQLException si ocurre un error al leer los datos.
     */
    private Category convertirCategory(ResultSet resultado)
            throws SQLException {

        Category categoria = new Category();

        categoria.setCategoryId(resultado.getInt("category_id"));
        categoria.setName(resultado.getString("name"));
        categoria.setLastUpdate(resultado.getTimestamp("last_update"));

        return categoria;
    }
}

