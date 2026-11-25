
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.models;

import java.sql.Timestamp;

/**
 * Representa la relación entre un actor y una película.
 *
 * Esta clase utiliza dos identificadores para representar
 * la clave primaria compuesta de la tabla film_actor:
 * actorId y filmId.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class FilmActor {

    /** Identificador del actor relacionado. */
    private int actorId;

    /** Identificador de la película relacionada. */
    private int filmId;

    /** Fecha y hora de la última actualización. */
    private Timestamp lastUpdate;

    /**
     * Constructor vacío de la clase FilmActor.
     */
    public FilmActor() {

    }

    /**
     * Construye una relación entre un actor y una película.
     *
     * @param actorId identificador del actor.
     * @param filmId identificador de la película.
     * @param lastUpdate fecha y hora de última actualización.
     */
    public FilmActor(int actorId, int filmId,
                     Timestamp lastUpdate) {

        this.actorId = actorId;
        this.filmId = filmId;
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
     * Devuelve los identificadores del actor y la película.
     *
     * @return cadena con el identificador del actor
     * y el identificador de la película.
     */
    @Override
    public String toString() {
        return "ID Actor: " + actorId
                + " | ID Pelicula: " + filmId;
    }

}
