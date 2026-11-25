
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.models;

import java.sql.Timestamp;

/**
 * Representa un empleado de una tienda Sakila.
 *
 * Contiene los datos personales, información de acceso,
 * dirección, tienda asignada y estado del empleado.
 *
 * Algunos atributos, como picture, email y password,
 * pueden contener valores null.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class Staff {

    /** Identificador único del empleado. */
    private int staffId;

    /** Nombre del empleado. */
    private String firstName;

    /** Apellido del empleado. */
    private String lastName;

    /** Identificador de la dirección del empleado. */
    private int addressId;

    /** Imagen del empleado almacenada en bytes. */
    private byte[] picture;

    /** Correo electrónico del empleado. */
    private String email;

    /** Identificador de la tienda donde trabaja. */
    private int storeId;

    /** Indica si el empleado está activo. */
    private boolean active;

    /** Nombre de usuario para acceder al sistema. */
    private String username;

    /** Contraseña del empleado. */
    private String password;

    /** Fecha y hora de la última actualización. */
    private Timestamp lastUpdate;

    /**
     * Constructor vacío de la clase Staff.
     */
    public Staff() {

    }

    /**
     * Construye un objeto con los datos de un empleado.
     *
     * @param staffId identificador del empleado.
     * @param firstName nombre del empleado.
     * @param lastName apellido del empleado.
     * @param addressId identificador de su dirección.
     * @param picture imagen del empleado en bytes.
     * @param email correo electrónico.
     * @param storeId identificador de la tienda.
     * @param active estado del empleado.
     * @param username nombre de usuario.
     * @param password contraseña del empleado.
     * @param lastUpdate fecha y hora de última actualización.
     */
    public Staff(int staffId, String firstName, String lastName,
                 int addressId, byte[] picture, String email,
                 int storeId, boolean active, String username,
                 String password, Timestamp lastUpdate) {

        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressId = addressId;
        this.picture = picture;
        this.email = email;
        this.storeId = storeId;
        this.active = active;
        this.username = username;
        this.password = password;
        this.lastUpdate = lastUpdate;

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
     * Obtiene el nombre del empleado.
     *
     * @return nombre del empleado.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Establece el nombre del empleado.
     *
     * @param firstName nuevo nombre del empleado.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Obtiene el apellido del empleado.
     *
     * @return apellido del empleado.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Establece el apellido del empleado.
     *
     * @param lastName nuevo apellido del empleado.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     * Obtiene la imagen del empleado.
     *
     * @return imagen almacenada en un arreglo de bytes.
     */
    public byte[] getPicture() {
        return picture;
    }

    /**
     * Establece la imagen del empleado.
     *
     * @param picture nueva imagen almacenada en bytes.
     */
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    /**
     * Obtiene el correo electrónico.
     *
     * @return correo electrónico del empleado.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico.
     *
     * @param email nuevo correo electrónico.
     */
    public void setEmail(String email) {
        this.email = email;
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
     * Comprueba si el empleado está activo.
     *
     * @return true si está activo; false si está inactivo.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Establece el estado del empleado.
     *
     * @param active nuevo estado del empleado.
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario.
     *
     * @param username nuevo nombre de usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene la contraseña del empleado.
     *
     * @return contraseña almacenada.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del empleado.
     *
     * @param password nueva contraseña.
     */
    public void setPassword(String password) {
        this.password = password;
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
     * Devuelve los datos principales del empleado.
     *
     * @return cadena con la información principal del empleado.
     */
    @Override
    public String toString() {
        return "ID Empleado: " + staffId
                + " | Nombre: " + firstName
                + " | Apellido: " + lastName
                + " | Correo: " + email
                + " | Usuario: " + username
                + " | ID Tienda: " + storeId
                + " | Estado: " + (active ? "Activo" : "Inactivo");
    }

}

