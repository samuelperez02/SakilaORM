
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.models;

import java.sql.Timestamp;

/**
 * Representa un registro del inventario de películas.
 *
 * Contiene el identificador del inventario, la película,
 * la tienda y la fecha de última actualización.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class Inventory {

    /** Identificador único del inventario. */
    private int inventoryId;

    /** Identificador de la película. */
    private int filmId;

    /** Identificador de la tienda. */
    private int storeId;

    /** Fecha y hora de la última actualización. */
    private Timestamp lastUpdate;

    /**
     * Constructor vacío de la clase Inventory.
     */
    public Inventory() {

    }

    /**
     * Construye un objeto con los datos del inventario.
     *
     * @param inventoryId identificador del inventario.
     * @param filmId identificador de la película.
     * @param storeId identificador de la tienda.
     * @param lastUpdate fecha y hora de última actualización.
     */
    public Inventory(int inventoryId, int filmId,
                     int storeId, Timestamp lastUpdate) {

        this.inventoryId = inventoryId;
        this.filmId = filmId;
        this.storeId = storeId;
        this.lastUpdate = lastUpdate;

    }

    /**
     * Obtiene el identificador del inventario.
     *
     * @return identificador del inventario.
     */
    public int getInventoryId() {
        return inventoryId;
    }

    /**
     * Establece el identificador del inventario.
     *
     * @param inventoryId nuevo identificador del inventario.
     */
    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
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
     * Obtiene el identificador de la tienda.
     *
     * @return identificador de la tienda.
     */
    public int getStoreId() {
        return storeId;
    }

    /**
     * Establece el identificador de la tienda.
     *
     * @param storeId nuevo identificador de la tienda.
     */
    public void setStoreId(int storeId) {
        this.storeId = storeId;
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
     * Devuelve los datos principales del inventario.
     *
     * @return cadena con los identificadores del inventario,
     * la película y la tienda.
     */
    @Override
    public String toString() {
        return "ID Inventario: " + inventoryId
                + " | ID Pelicula: " + filmId
                + " | ID Tienda: " + storeId;
    }

}
