

/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.models;

import java.sql.Timestamp;

/**
 * Representa una ciudad registrada en la base de datos Sakila.
 *
 * Contiene el identificador de la ciudad, su nombre,
 * el identificador del país y la fecha de última actualización.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class City {

    /** Identificador único de la ciudad. */
    private int cityId;

    /** Nombre de la ciudad. */
    private String city;

    /** Identificador del país al que pertenece la ciudad. */
    private int countryId;

    /** Fecha y hora de la última actualización. */
    private Timestamp lastUpdate;

    /**
     * Constructor vacío de la clase City.
     */
    public City() {

    }

    /**
     * Construye una ciudad con todos sus datos.
     *
     * @param cityId identificador de la ciudad.
     * @param city nombre de la ciudad.
     * @param countryId identificador del país.
     * @param lastUpdate fecha y hora de última actualización.
     */
    public City(int cityId, String city, int countryId,
                Timestamp lastUpdate) {

        this.cityId = cityId;
        this.city = city;
        this.countryId = countryId;
        this.lastUpdate = lastUpdate;

    }

    /**
     * Obtiene el identificador de la ciudad.
     *
     * @return identificador de la ciudad.
     */
    public int getCityId() {
        return cityId;
    }

    /**
     * Establece el identificador de la ciudad.
     *
     * @param cityId nuevo identificador de la ciudad.
     */
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    /**
     * Obtiene el nombre de la ciudad.
     *
     * @return nombre de la ciudad.
     */
    public String getCity() {
        return city;
    }

    /**
     * Establece el nombre de la ciudad.
     *
     * @param city nuevo nombre de la ciudad.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Obtiene el identificador del país.
     *
     * @return identificador del país relacionado.
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
     * Devuelve los datos principales de la ciudad en formato de texto.
     *
     * @return cadena con el identificador, nombre de la ciudad
     * y el identificador del país.
     */
    @Override
    public String toString() {
        return "ID: " + cityId
                + " | Ciudad: " + city
                + " | ID del Pais: " + countryId;
    }

}
