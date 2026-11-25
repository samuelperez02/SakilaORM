
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.models;

import java.sql.Timestamp;

/**
 * Representa una categoría de películas en la base de datos Sakila.
 *
 * Almacena el identificador, nombre y fecha de última actualización
 * de cada categoría.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class Category {

    /** Identificador único de la categoría. */
    private int categoryId;

    /** Nombre de la categoría. */
    private String name;

    /** Fecha y hora de la última actualización. */
    private Timestamp lastUpdate;

    /**
     * Constructor vacío de la clase Category.
     */
    public Category() {

    }

    /**
     * Construye una categoría con todos sus datos.
     *
     * @param categoryId identificador de la categoría.
     * @param name nombre de la categoría.
     * @param lastUpdate fecha y hora de última actualización.
     */
    public Category(int categoryId, String name, Timestamp lastUpdate) {

        this.categoryId = categoryId;
        this.name = name;
        this.lastUpdate = lastUpdate;

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
     * @param categoryId nuevo identificador.
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Obtiene el nombre de la categoría.
     *
     * @return nombre de la categoría.
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre de la categoría.
     *
     * @param name nuevo nombre de la categoría.
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
     * Devuelve la información principal de la categoría en formato de texto.
     *
     * @return cadena con el identificador y nombre de la categoría.
     */
    @Override
    public String toString() {
        return "ID Categoria: " + categoryId
                + " | Nombre: " + name;
    }

}
