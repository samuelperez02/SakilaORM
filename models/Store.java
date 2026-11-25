
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.models;

import java.sql.Timestamp;

/**
 * Representa una tienda de alquiler de películas.
 *
 * Contiene el identificador de la tienda, el empleado encargado,
 * la dirección y la fecha de última actualización.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class Store {

    /** Identificador único de la tienda. */
    private int storeId;

    /** Identificador del empleado encargado de la tienda. */
    private int managerStaffId;

    /** Identificador de la dirección de la tienda. */
    private int addressId;

    /** Fecha y hora de la última actualización. */
    private Timestamp lastUpdate;

    /**
     * Constructor vacío de la clase Store.
     */
    public Store() {

    }

    /**
     * Construye un objeto con los datos de una tienda.
     *
     * @param storeId identificador de la tienda.
     * @param managerStaffId identificador del encargado.
     * @param addressId identificador de la dirección.
     * @param lastUpdate fecha y hora de última actualización.
     */
    public Store(int storeId, int managerStaffId,
                 int addressId, Timestamp lastUpdate) {

        this.storeId = storeId;
        this.managerStaffId = managerStaffId;
        this.addressId = addressId;
        this.lastUpdate = lastUpdate;

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
     * Obtiene el identificador del encargado.
     *
     * @return identificador del empleado encargado.
     */
    public int getManagerStaffId() {
        return managerStaffId;
    }

    /**
     * Establece el identificador del encargado.
     *
     * @param managerStaffId nuevo identificador del encargado.
     */
    public void setManagerStaffId(int managerStaffId) {
        this.managerStaffId = managerStaffId;
    }

    /**
     * Obtiene el identificador de la dirección.
     *
     * @return identificador de la dirección.
     */
    public int getAddressId() {
        return addressId;
    }

    /**
     * Establece el identificador de la dirección.
     *
     * @param addressId nuevo identificador de la dirección.
     */
    public void setAddressId(int addressId) {
        this.addressId = addressId;
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
     * Devuelve los datos principales de la tienda.
     *
     * @return cadena con el identificador de la tienda,
     * su encargado y su dirección.
     */
    @Override
    public String toString() {
        return "ID Tienda: " + storeId
                + " | ID Encargado: " + managerStaffId
                + " | ID Direccion: " + addressId;
    }

}
