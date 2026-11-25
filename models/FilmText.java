

/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.models;

/**
 * Representa la información textual de una película.
 *
 * Contiene el identificador, el título y la descripción
 * almacenados en la tabla film_text de la base de datos Sakila.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class FilmText {

    /** Identificador de la película. */
    private int filmId;

    /** Título de la película. */
    private String title;

    /** Descripción de la película. */
    private String description;

    /**
     * Constructor vacío de la clase FilmText.
     */
    public FilmText() {

    }

    /**
     * Construye un objeto con los datos de una película.
     *
     * @param filmId identificador de la película.
     * @param title título de la película.
     * @param description descripción de la película.
     */
    public FilmText(int filmId, String title,
                    String description) {

        this.filmId = filmId;
        this.title = title;
        this.description = description;

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
     * Obtiene el título de la película.
     *
     * @return título de la película.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Establece el título de la película.
     *
     * @param title nuevo título de la película.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Obtiene la descripción de la película.
     *
     * @return descripción de la película.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción de la película.
     *
     * @param description nueva descripción de la película.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Devuelve los datos principales de la película.
     *
     * @return cadena con el identificador, título y descripción.
     */
    @Override
    public String toString() {
        return "ID Pelicula: " + filmId
                + " | Titulo: " + title
                + " | Descripcion: " + description;
    }

}
