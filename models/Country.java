
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.models;

import java.sql.Timestamp;

/**
 * Representa un país registrado en la base de datos Sakila.
 *
 * Contiene el identificador del país, su nombre
 * y la fecha de última actualización.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class Country {

    /** Identificador único del país. */
    private int countryId;

    /** Nombre del país. */
    private String country;

    /** Fecha y hora de la última actualización. */
    private Timestamp lastUpdate;

    /**
     * Constructor vacío de la clase Country.
     */
    public Country() {

    }

    /**
     * Construye un país con todos sus datos.
     *
     * @param countryId identificador del país.
     * @param country nombre del país.
     * @param lastUpdate fecha y hora de última actualización.
     */
    public Country(int countryId, String country, Timestamp lastUpdate) {

        this.countryId = countryId;
        this.country = country;
        this.lastUpdate = lastUpdate;

    }

    /**
     * Obtiene el identificador del país.
     *
     * @return identificador del país.
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * Establece el identificador del país.
     *
     * @param countryId nuevo identificador del país.
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * Obtiene el nombre del país.
     *
     * @return nombre del país.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Establece el nombre del país.
     *
     * @param country nuevo nombre del país.
     */
    public void setCountry(String country) {
        this.country = country;
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
     * Devuelve la información principal del país en formato de texto.
     *
     * @return cadena con el identificador y nombre del país.
     */
    @Override
    public String toString() {
        return "ID: " + countryId
                + " | Pais: " + country;
    }

}

