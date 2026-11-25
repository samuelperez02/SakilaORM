
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.models;

import java.sql.Timestamp;

/**
 * Representa un idioma registrado en la base de datos Sakila.
 *
 * Contiene el identificador del idioma, su nombre
 * y la fecha de última actualización.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class Language {

    /** Identificador único del idioma. */
    private int languageId;

    /** Nombre del idioma. */
    private String name;

    /** Fecha y hora de la última actualización. */
    private Timestamp lastUpdate;

    /**
     * Constructor vacío de la clase Language.
     */
    public Language() {

    }

    /**
     * Construye un objeto con los datos de un idioma.
     *
     * @param languageId identificador del idioma.
     * @param name nombre del idioma.
     * @param lastUpdate fecha y hora de última actualización.
     */
    public Language(int languageId, String name, Timestamp lastUpdate) {

        this.languageId = languageId;
        this.name = name;
        this.lastUpdate = lastUpdate;

    }

    /**
     * Obtiene el identificador del idioma.
     *
     * @return identificador del idioma.
     */
    public int getLanguageId() {
        return languageId;
    }

    /**
     * Establece el identificador del idioma.
     *
     * @param languageId nuevo identificador del idioma.
     */
    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    /**
     * Obtiene el nombre del idioma.
     *
     * @return nombre del idioma.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del idioma.
     *
     * @param name nuevo nombre del idioma.
     */
    public void setName(String name) {
        this.name = name;
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
     * Devuelve los datos principales del idioma.
     *
     * @return cadena con el identificador y nombre del idioma.
     */
    @Override
    public String toString() {
        return "ID Idioma: " + languageId
                + " | Nombre: " + name;
    }

}

