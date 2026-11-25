
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.models;

import java.util.regex.Pattern;

/**
 * Clase encargada de validar los datos del sistema Sakila ORM
 * mediante expresiones regulares.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class Validaciones {

    /**
     * Valida el formato de un correo electronico.
     *
     * @param correo correo que se desea comprobar.
     * @return true si el correo tiene un formato valido.
     */
    public static boolean validarCorreo(String correo) {

        String expresion = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        return correo != null && Pattern.matches(expresion, correo);
    }

    /**
     * Valida nombres y apellidos.
     *
     * Permite letras, espacios, acentos y guiones.
     *
     * @param nombre texto que se desea comprobar.
     * @return true si el texto tiene un formato valido.
     */
    public static boolean validarNombre(String nombre) {

        String expresion = "^[\\p{L}]+([ '-][\\p{L}]+)*$";

        return nombre != null && Pattern.matches(expresion, nombre.trim());
    }

    /**
     * Valida un numero de telefono de entre 7 y 15 digitos.
     *
     * @param telefono numero que se desea comprobar.
     * @return true si el telefono tiene un formato valido.
     */
    public static boolean validarTelefono(String telefono) {

        String expresion = "^\\d{7,15}$";

        return telefono != null && Pattern.matches(expresion, telefono);
    }
}
