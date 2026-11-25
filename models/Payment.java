

/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Representa un pago realizado por un cliente.
 *
 * Contiene los identificadores del pago, cliente, empleado
 * y alquiler, además del importe y las fechas correspondientes.
 *
 * El identificador del alquiler puede ser null cuando
 * el pago no está asociado a un alquiler.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class Payment {

    /** Identificador único del pago. */
    private int paymentId;

    /** Identificador del cliente que realiza el pago. */
    private int customerId;

    /** Identificador del empleado que registra el pago. */
    private int staffId;

    /** Identificador del alquiler asociado al pago. */
    private Integer rentalId;

    /** Importe monetario del pago. */
    private BigDecimal amount;

    /** Fecha y hora en que se realizó el pago. */
    private Timestamp paymentDate;

    /** Fecha y hora de la última actualización. */
    private Timestamp lastUpdate;

    /**
     * Constructor vacío de la clase Payment.
     */
    public Payment() {

    }

    /**
     * Construye un objeto con los datos de un pago.
     *
     * @param paymentId identificador del pago.
     * @param customerId identificador del cliente.
     * @param staffId identificador del empleado.
     * @param rentalId identificador del alquiler, puede ser null.
     * @param amount importe del pago.
     * @param paymentDate fecha y hora del pago.
     * @param lastUpdate fecha y hora de última actualización.
     */
    public Payment(int paymentId, int customerId,
                   int staffId, Integer rentalId,
                   BigDecimal amount, Timestamp paymentDate,
                   Timestamp lastUpdate) {

        this.paymentId = paymentId;
        this.customerId = customerId;
        this.staffId = staffId;
        this.rentalId = rentalId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.lastUpdate = lastUpdate;
    }

    /**
     * Obtiene el identificador del pago.
     *
     * @return identificador del pago.
     */
    public int getPaymentId() {
        return paymentId;
    }

    /**
     * Establece el identificador del pago.
     *
     * @param paymentId nuevo identificador del pago.
     */
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
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
     * Obtiene el identificador del alquiler.
     *
     * @return identificador del alquiler o null si no está asignado.
     */
    public Integer getRentalId() {
        return rentalId;
    }

    /**
     * Establece el identificador del alquiler.
     *
     * @param rentalId nuevo identificador del alquiler o null.
     */
    public void setRentalId(Integer rentalId) {
        this.rentalId = rentalId;
    }

    /**
     * Obtiene el importe del pago.
     *
     * @return importe monetario.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Establece el importe del pago.
     *
     * @param amount nuevo importe monetario.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Obtiene la fecha del pago.
     *
     * @return fecha y hora del pago.
     */
    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    /**
     * Establece la fecha del pago.
     *
     * @param paymentDate nueva fecha y hora del pago.
     */
    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
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
     * Devuelve los datos principales del pago.
     *
     * @return cadena con los datos del pago.
     */
    @Override
    public String toString() {
        return "ID Pago: " + paymentId
                + " | ID Cliente: " + customerId
                + " | ID Empleado: " + staffId
                + " | ID Alquiler: "
                + (rentalId == null ? "No asignado" : rentalId)
                + " | Importe: RD$ " + amount
                + " | Fecha Pago: " + paymentDate;
    }

}
