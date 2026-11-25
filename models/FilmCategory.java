
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.models;

import java.sql.Timestamp;

/**
 * Representa la relación entre una película y una categoría.
 *
 * Esta clase utiliza dos identificadores para representar
 * la clave primaria compuesta de la tabla film_category:
 * filmId y categoryId.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class FilmCategory {

    /** Identificador de la película relacionada. */
    private int filmId;

    /** Identificador de la categoría relacionada. */
    private int categoryId;

    /** Fecha y hora de la última actualización. */
    private Timestamp lastUpdate;

    /**
     * Constructor vacío de la clase FilmCategory.
     */
    public FilmCategory() {

    }

    /**
     * Construye una relación entre una película y una categoría.
     *
     * @param filmId identificador de la película.
     * @param categoryId identificador de la categoría.
     * @param lastUpdate fecha y hora de última actualización.
     */
    public FilmCategory(int filmId, int categoryId,
                        Timestamp lastUpdate) {

        this.filmId = filmId;
        this.categoryId = categoryId;
        this.lastUpdate = lastUpdate;

    }

    /**
     * Obtiene el identificador de la película.
     *
     * @return identificador de la película.
     */
    public int getFilmId() {
        return filmId;
    }

    /**
     * Establece el identificador de la película.
     *
     * @param filmId nuevo identificador de la película.
     */
    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    /**
     * Obtiene el identificador de la categoría.
     *
     * @return identificador de la categoría.
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Establece el identificador de la categoría.
     *
     * @param categoryId nuevo identificador de la categoría.
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Obtiene la fecha de última actualización.
     *
     * @return fecha y hora de actualización.
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Establece la fecha de última actualización.
     *
     * @param lastUpdate nueva fecha de actualización.
     */
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Devuelve los identificadores de la película y su categoría.
     *
     * @return cadena con el identificador de la película
     * y el identificador de la categoría.
     */
    @Override
    public String toString() {
        return "ID Pelicula: " + filmId
                + " | ID Categoria: " + categoryId;
    }

}

