
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.models;

import java.sql.Timestamp;

/**
 * Representa un cliente registrado en la base de datos Sakila.
 *
 * Contiene los datos personales del cliente, su tienda,
 * dirección, estado de actividad y fechas de registro.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class Customer {

    /** Identificador único del cliente. */
    private int customerId;

    /** Identificador de la tienda relacionada con el cliente. */
    private int storeId;

    /** Nombre del cliente. */
    private String firstName;

    /** Apellido del cliente. */
    private String lastName;

    /** Correo electrónico del cliente. */
    private String email;

    /** Identificador de la dirección del cliente. */
    private int addressId;

    /** Indica si el cliente está activo. */
    private boolean active;

    /** Fecha de creación del registro del cliente. */
    private Timestamp createDate;

    /** Fecha y hora de la última actualización. */
    private Timestamp lastUpdate;

    /**
     * Constructor vacío de la clase Customer.
     */
    public Customer() {

    }

    /**
     * Construye un cliente con todos sus datos.
     *
     * @param customerId identificador del cliente.
     * @param storeId identificador de la tienda.
     * @param firstName nombre del cliente.
     * @param lastName apellido del cliente.
     * @param email correo electrónico.
     * @param addressId identificador de la dirección.
     * @param active estado de actividad del cliente.
     * @param createDate fecha de creación del registro.
     * @param lastUpdate fecha y hora de última actualización.
     */
    public Customer(int customerId, int storeId, String firstName,
                    String lastName, String email, int addressId,
                    boolean active, Timestamp createDate,
                    Timestamp lastUpdate) {

        this.customerId = customerId;
        this.storeId = storeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addressId = addressId;
        this.active = active;
        this.createDate = createDate;
        this.lastUpdate = lastUpdate;

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
     * @param customerId nuevo identificador.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
     * @param storeId nuevo identificador de tienda.
     */
    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return nombre del cliente.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Establece el nombre del cliente.
     *
     * @param firstName nuevo nombre del cliente.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Obtiene el apellido del cliente.
     *
     * @return apellido del cliente.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Establece el apellido del cliente.
     *
     * @param lastName nuevo apellido del cliente.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Obtiene el correo electrónico del cliente.
     *
     * @return correo electrónico.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del cliente.
     *
     * @param email nuevo correo electrónico.
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @param addressId nuevo identificador de dirección.
     */
    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    /**
     * Comprueba si el cliente está activo.
     *
     * @return true si está activo; false si está inactivo.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Establece el estado de actividad del cliente.
     *
     * @param active nuevo estado de actividad.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Obtiene la fecha de creación del registro.
     *
     * @return fecha de creación.
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * Establece la fecha de creación del registro.
     *
     * @param createDate nueva fecha de creación.
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
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
     * Devuelve los datos principales del cliente en formato de texto.
     *
     * @return cadena con el identificador, nombre, apellido,
     * correo electrónico y estado del cliente.
     */
    @Override
    public String toString() {
        return "ID: " + customerId
                + " | Nombre: " + firstName
                + " | Apellido: " + lastName
                + " | Correo: " + email
                + " | Estado: " + (active ? "Activo" : "Inactivo");
    }

}
