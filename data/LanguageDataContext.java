/*
 * Copyright (c) 2026
 * Proyecto Final - SakilaORM
 * Lenguaje de Programación II
 */

package com.sakila.data;

import com.sakila.models.Language;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de administrar las operaciones de acceso a datos
 * de la tabla language de la base de datos Sakila.
 *
 * Permite insertar, actualizar, buscar, listar y eliminar
 * registros correspondientes a los idiomas utilizados en las peliculas.
 *
 * @author
 * @version 1.0
 */
public final class LanguageDataContext extends DataContext<Language> {

    /**
     * Construye el contexto de acceso a datos para la tabla language.
     *
     * @param conexion conexion activa con la base de datos
     */
    public LanguageDataContext(java.sql.Connection conexion) {
        super(conexion);
    }

    /**
     * Inserta un nuevo idioma en la tabla language.
     *
     * La base de datos genera automaticamente el language_id.
     * El identificador generado se asigna posteriormente al objeto
     * Language.
     *
     * @param idioma objeto Language que contiene el nombre del idioma
     *               que sera almacenado
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected void insertar(Language idioma) throws SQLException {

        String sql = "INSERT INTO language (name) VALUES (?)";

        try (PreparedStatement sentencia = conexion.prepareStatement(
                sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            sentencia.setString(1, idioma.getName());

            sentencia.executeUpdate();

            try (ResultSet resultado = sentencia.getGeneratedKeys()) {

                if (resultado.next()) {
                    idioma.setLanguageId(resultado.getInt(1));
                }
            }
        }
    }

    /**
     * Actualiza el nombre de un idioma existente.
     *
     * @param idioma objeto Language que contiene el identificador
     *               y el nuevo nombre
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected void actualizar(Language idioma) throws SQLException {

        String sql = "UPDATE language SET name = ? WHERE language_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, idioma.getName());
            sentencia.setInt(2, idioma.getLanguageId());

            sentencia.executeUpdate();
        }
    }

    /**
     * Busca un idioma utilizando su identificador.
     *
     * @param id identificador del idioma que se desea buscar
     * @return objeto Language encontrado o null si no existe
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected Language buscarPorId(int id) throws SQLException {

        String sql = "SELECT * FROM language WHERE language_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {
                    return convertirLanguage(resultado);
                }
            }
        }

        return null;
    }

    /**
     * Obtiene todos los idiomas registrados en la tabla language.
     *
     * @return lista de objetos Language encontrados en la base de datos
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected List<Language> listar() throws SQLException {

        List<Language> idiomas = new ArrayList<>();

        String sql = "SELECT * FROM language";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                idiomas.add(convertirLanguage(resultado));
            }
        }

        return idiomas;
    }

    /**
     * Elimina un idioma utilizando su identificador.
     *
     * @param id identificador del idioma que se desea eliminar
     * @throws SQLException si ocurre un error al ejecutar la consulta SQL
     */
    @Override
    protected void eliminar(int id) throws SQLException {

        String sql = "DELETE FROM language WHERE language_id = ?";

        try (PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            sentencia.executeUpdate();
        }
    }

    /**
     * Convierte los datos obtenidos desde MySQL en un objeto Language.
     *
     * @param resultado conjunto de resultados obtenido mediante
     *                  una consulta SQL
     * @return objeto Language con los datos obtenidos de la base de datos
     * @throws SQLException si ocurre un error al leer los datos
     */
    private Language convertirLanguage(ResultSet resultado) throws SQLException {

        Language idioma = new Language();

        idioma.setLanguageId(resultado.getInt("language_id"));
        idioma.setName(resultado.getString("name"));
        idioma.setLastUpdate(resultado.getTimestamp("last_update"));

        return idioma;
    }
}

