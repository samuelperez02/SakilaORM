
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.models;

import java.sql.Timestamp;

/**
 * Representa un actor registrado en la base de datos Sakila.
 *
 * Contiene el identificador, nombre, apellido y fecha
 * de última actualización del actor.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class Actor {

    /** Identificador único del actor. */
    private int actorId;

    /** Nombre del actor. */
    private String firstName;

    /** Apellido del actor. */
    private String lastName;

    /** Fecha y hora de la última actualización. */
    private Timestamp lastUpdate;

    /**
     * Constructor vacío de la clase Actor.
     */
    public Actor() {

    }

    /**
     * Construye un actor con todos sus datos.
     *
     * @param actorId identificador del actor.
     * @param firstName nombre del actor.
     * @param lastName apellido del actor.
     * @param lastUpdate fecha y hora de la última actualización.
     */
    public Actor(int actorId, String firstName,
                 String lastName, Timestamp lastUpdate) {

        this.actorId = actorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastUpdate = lastUpdate;
    }

    /**
     * Obtiene el identificador del actor.
     *
     * @return identificador del actor.
     */
    public int getActorId() {
        return actorId;
    }

    /**
     * Establece el identificador del actor.
     *
     * @param actorId nuevo identificador del actor.
     */
    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    /**
     * Obtiene el nombre del actor.
     *
     * @return nombre del actor.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Establece el nombre del actor.
     *
     * @param firstName nuevo nombre del actor.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Obtiene el apellido del actor.
     *
     * @return apellido del actor.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Establece el apellido del actor.
     *
     * @param lastName nuevo apellido del actor.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Obtiene la fecha de última actualización.
     *
     * @return fecha y hora de última actualización.
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
     * Devuelve los datos del actor en formato de texto.
     *
     * @return cadena con el identificador, nombre, apellido
     * y fecha de última actualización.
     */
    @Override
    public String toString() {
        return "ID: " + actorId
                + " | Nombre: " + firstName
                + " | Apellido: " + lastName
                + " | Ultima actualizacion: " + lastUpdate;
    }
}
