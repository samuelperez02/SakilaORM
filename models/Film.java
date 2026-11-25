
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Representa una película registrada en la base de datos Sakila.
 *
 * Almacena información sobre el título, descripción, año de estreno,
 * idiomas, duración, precios, clasificación y características especiales.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class Film {

    /** Identificador único de la película. */
    private int filmId;

    /** Título de la película. */
    private String title;

    /** Descripción o resumen de la película. */
    private String description;

    /** Año de estreno de la película. Puede ser null. */
    private Integer releaseYear;

    /** Identificador del idioma principal de la película. */
    private int languageId;

    /** Identificador del idioma original. Puede ser null. */
    private Integer originalLanguageId;

    /** Cantidad de días permitidos para alquilar la película. */
    private int rentalDuration;

    /** Precio de alquiler de la película. */
    private BigDecimal rentalRate;

    /** Duración de la película en minutos. Puede ser null. */
    private Integer length;

    /** Costo de reposición de la película. */
    private BigDecimal replacementCost;

    /** Clasificación por edades de la película. */
    private String rating;

    /** Características especiales de la película. */
    private String specialFeatures;

    /** Fecha y hora de la última actualización. */
    private Timestamp lastUpdate;

    /**
     * Constructor vacío de la clase Film.
     */
    public Film() {

    }

    /**
     * Construye una película con todos sus datos.
     *
     * @param filmId identificador de la película.
     * @param title título de la película.
     * @param description descripción de la película.
     * @param releaseYear año de estreno.
     * @param languageId identificador del idioma principal.
     * @param originalLanguageId identificador del idioma original.
     * @param rentalDuration días permitidos para el alquiler.
     * @param rentalRate precio de alquiler.
     * @param length duración en minutos.
     * @param replacementCost costo de reposición.
     * @param rating clasificación por edades.
     * @param specialFeatures características especiales.
     * @param lastUpdate fecha y hora de última actualización.
     */
    public Film(int filmId, String title, String description,
                Integer releaseYear, int languageId,
                Integer originalLanguageId, int rentalDuration,
                BigDecimal rentalRate, Integer length,
                BigDecimal replacementCost, String rating,
                String specialFeatures, Timestamp lastUpdate) {

        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.languageId = languageId;
        this.originalLanguageId = originalLanguageId;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.specialFeatures = specialFeatures;
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
     * @param filmId nuevo identificador.
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
     * @param title nuevo título.
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
     * @param description nueva descripción.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene el año de estreno.
     *
     * @return año de estreno o null si no está disponible.
     */
    public Integer getReleaseYear() {
        return releaseYear;
    }

    /**
     * Establece el año de estreno.
     *
     * @param releaseYear nuevo año de estreno.
     */
    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Obtiene el identificador del idioma principal.
     *
     * @return identificador del idioma.
     */
    public int getLanguageId() {
        return languageId;
    }

    /**
     * Establece el identificador del idioma principal.
     *
     * @param languageId nuevo identificador del idioma.
     */
    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    /**
     * Obtiene el identificador del idioma original.
     *
     * @return identificador del idioma original o null.
     */
    public Integer getOriginalLanguageId() {
        return originalLanguageId;
    }

    /**
     * Establece el identificador del idioma original.
     *
     * @param originalLanguageId nuevo identificador del idioma original.
     */
    public void setOriginalLanguageId(Integer originalLanguageId) {
        this.originalLanguageId = originalLanguageId;
    }

    /**
     * Obtiene la duración permitida para el alquiler.
     *
     * @return cantidad de días de alquiler.
     */
    public int getRentalDuration() {
        return rentalDuration;
    }

    /**
     * Establece la duración permitida para el alquiler.
     *
     * @param rentalDuration nueva cantidad de días.
     */
    public void setRentalDuration(int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    /**
     * Obtiene el precio de alquiler.
     *
     * @return precio de alquiler.
     */
    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    /**
     * Establece el precio de alquiler.
     *
     * @param rentalRate nuevo precio de alquiler.
     */
    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    /**
     * Obtiene la duración de la película en minutos.
     *
     * @return duración en minutos o null si no está disponible.
     */
    public Integer getLength() {
        return length;
    }

    /**
     * Establece la duración de la película.
     *
     * @param length nueva duración en minutos.
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * Obtiene el costo de reposición de la película.
     *
     * @return costo de reposición.
     */
    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    /**
     * Establece el costo de reposición de la película.
     *
     * @param replacementCost nuevo costo de reposición.
     */
    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    /**
     * Obtiene la clasificación por edades.
     *
     * @return clasificación de la película.
     */
    public String getRating() {
        return rating;
    }

    /**
     * Establece la clasificación por edades.
     *
     * @param rating nueva clasificación.
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * Obtiene las características especiales.
     *
     * @return características especiales de la película.
     */
    public String getSpecialFeatures() {
        return specialFeatures;
    }

    /**
     * Establece las características especiales.
     *
     * @param specialFeatures nuevas características especiales.
     */
    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
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
     * Devuelve los datos principales de la película en formato de texto.
     *
     * @return cadena con el identificador, título, año y duración.
     */
    @Override
    public String toString() {
        return "ID: " + filmId
                + " | Titulo: " + title
                + " | Año: " + releaseYear
                + " | Duracion: " + length + " minutos";
    }
}
