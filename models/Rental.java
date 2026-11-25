
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.models;

import java.sql.Timestamp;

/**
 * Representa un alquiler de película realizado por un cliente.
 *
 * Contiene los identificadores del alquiler, inventario,
 * cliente y empleado, además de las fechas correspondientes.
 *
 * La fecha de devolución puede ser null cuando el alquiler
 * todavía está pendiente de devolución.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class Rental {

    /** Identificador único del alquiler. */
    private int rentalId;

    /** Fecha y hora en que se realizó el alquiler. */
    private Timestamp rentalDate;

    /** Identificador del registro de inventario. */
    private int inventoryId;

    /** Identificador del cliente que realizó el alquiler. */
    private int customerId;

    /** Fecha y hora de devolución de la película. */
    private Timestamp returnDate;

    /** Identificador del empleado que registró el alquiler. */
    private int staffId;

    /** Fecha y hora de la última actualización. */
    private Timestamp lastUpdate;

    /**
     * Constructor vacío de la clase Rental.
     */
    public Rental() {

    }

    /**
     * Construye un objeto con los datos de un alquiler.
     *
     * @param rentalId identificador del alquiler.
     * @param rentalDate fecha y hora del alquiler.
     * @param inventoryId identificador del inventario.
     * @param customerId identificador del cliente.
     * @param returnDate fecha de devolución, puede ser null.
     * @param staffId identificador del empleado.
     * @param lastUpdate fecha y hora de última actualización.
     */
    public Rental(int rentalId, Timestamp rentalDate,
                  int inventoryId, int customerId,
                  Timestamp returnDate, int staffId,
                  Timestamp lastUpdate) {

        this.rentalId = rentalId;
        this.rentalDate = rentalDate;
        this.inventoryId = inventoryId;
        this.customerId = customerId;
        this.returnDate = returnDate;
        this.staffId = staffId;
        this.lastUpdate = lastUpdate;

    }

    /**
     * Obtiene el identificador del alquiler.
     *
     * @return identificador del alquiler.
     */
    public int getRentalId() {
        return rentalId;
    }

    /**
     * Establece el identificador del alquiler.
     *
     * @param rentalId nuevo identificador del alquiler.
     */
    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    /**
     * Obtiene la fecha del alquiler.
     *
     * @return fecha y hora del alquiler.
     */
    public Timestamp getRentalDate() {
        return rentalDate;
    }

    /**
     * Establece la fecha del alquiler.
     *
     * @param rentalDate nueva fecha del alquiler.
     */
    public void setRentalDate(Timestamp rentalDate) {
        this.rentalDate = rentalDate;
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
     * Obtiene el identificador del cliente.
     *
     * @return identificador del cliente.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Establece el identificador del cliente.
     *
     * @param customerId nuevo identificador del cliente.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Obtiene la fecha de devolución.
     *
     * @return fecha de devolución o null si está pendiente.
     */
    public Timestamp getReturnDate() {
        return returnDate;
    }

    /**
     * Establece la fecha de devolución.
     *
     * @param returnDate nueva fecha de devolución o null.
     */
    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * Obtiene el identificador del empleado.
     *
     * @return identificador del empleado.
     */
    public int getStaffId() {
        return staffId;
    }

    /**
     * Establece el identificador del empleado.
     *
     * @param staffId nuevo identificador del empleado.
     */
    public void setStaffId(int staffId) {
        this.staffId = staffId;
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
     * Devuelve los datos principales del alquiler.
     *
     * @return cadena con los datos del alquiler y su estado
     * de devolución.
     */
    @Override
    public String toString() {
        return "ID Alquiler: " + rentalId
                + " | Fecha Alquiler: " + rentalDate
                + " | ID Inventario: " + inventoryId
                + " | ID Cliente: " + customerId
                + " | Fecha Devolucion: "
                + (returnDate == null ? "Pendiente" : returnDate)
                + " | ID Empleado: " + staffId;

    }

}
