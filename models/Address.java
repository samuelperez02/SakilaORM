
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.models;

import java.sql.Timestamp;

/**
 * Representa una dirección registrada en la base de datos Sakila.
 *
 * Contiene información de la dirección, distrito, ciudad,
 * código postal, teléfono y ubicación geográfica.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class Address {

    /** Identificador único de la dirección. */
    private int addressId;

    /** Dirección principal. */
    private String address;

    /** Información adicional de la dirección. */
    private String address2;

    /** Distrito al que pertenece la dirección. */
    private String district;

    /** Identificador de la ciudad relacionada. */
    private int cityId;

    /** Código postal de la dirección. */
    private String postalCode;

    /** Número telefónico asociado a la dirección. */
    private String phone;

    /** Datos binarios de la ubicación geográfica. */
    private byte[] location;

    /** Fecha y hora de la última actualización. */
    private Timestamp lastUpdate;

    /**
     * Constructor vacío de la clase Address.
     */
    public Address() {

    }

    /**
     * Construye una dirección con todos sus datos.
     *
     * @param addressId identificador de la dirección.
     * @param address dirección principal.
     * @param address2 información adicional de la dirección.
     * @param district distrito de la dirección.
     * @param cityId identificador de la ciudad.
     * @param postalCode código postal.
     * @param phone número telefónico.
     * @param location ubicación geográfica en formato binario.
     * @param lastUpdate fecha y hora de última actualización.
     */
    public Address(int addressId, String address, String address2,
                   String district, int cityId, String postalCode,
                   String phone, byte[] location, Timestamp lastUpdate) {

        this.addressId = addressId;
        this.address = address;
        this.address2 = address2;
        this.district = district;
        this.cityId = cityId;
        this.postalCode = postalCode;
        this.phone = phone;
        this.location = location;
        this.lastUpdate = lastUpdate;
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
     * @param addressId nuevo identificador.
     */
    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    /**
     * Obtiene la dirección principal.
     *
     * @return dirección principal.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Establece la dirección principal.
     *
     * @param address nueva dirección.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Obtiene la información adicional de la dirección.
     *
     * @return información adicional.
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Establece la información adicional de la dirección.
     *
     * @param address2 nueva información adicional.
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * Obtiene el distrito de la dirección.
     *
     * @return nombre del distrito.
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Establece el distrito de la dirección.
     *
     * @param district nuevo distrito.
     */
    public void setDistrict(String district) {
        this.district = district;
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
     * @param cityId nuevo identificador de ciudad.
     */
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    /**
     * Obtiene el código postal.
     *
     * @return código postal.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Establece el código postal.
     *
     * @param postalCode nuevo código postal.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Obtiene el número telefónico.
     *
     * @return número telefónico.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Establece el número telefónico.
     *
     * @param phone nuevo número telefónico.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Obtiene los datos binarios de la ubicación geográfica.
     *
     * @return arreglo de bytes con la ubicación.
     */
    public byte[] getLocation() {
        return location;
    }

    /**
     * Establece los datos binarios de la ubicación geográfica.
     *
     * @param location arreglo de bytes con la ubicación.
     */
    public void setLocation(byte[] location) {
        this.location = location;
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
     * Devuelve los datos principales de la dirección en formato de texto.
     *
     * @return cadena con la información de la dirección.
     */
    @Override
    public String toString() {
        return "ID: " + addressId
                + " | Direccion: " + address
                + " | Direccion 2: " + address2
                + " | Distrito: " + district
                + " | ID Ciudad: " + cityId
                + " | Codigo Postal: " + postalCode
                + " | Telefono: " + phone;
    }
}

