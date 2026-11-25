
/**
 * Copyright (c) 2026.
 * Proyecto final de Lenguaje de Programacion II.
 *
 * Interfaz para administrar registros que utilizan
 * claves primarias compuestas.
 */

package com.sakila.data;

/**
 * Define operaciones CRUD que necesitan dos identificadores.
 *
 * @param <T> Tipo de objeto que se va a administrar.
 */
public interface IDataPostCompuesto<T> {

    /**
     * Busca un registro utilizando dos identificadores.
     *
     * @param id1 primer identificador.
     * @param id2 segundo identificador.
     * @return objeto encontrado o null si no existe.
     */
    T Get(int id1, int id2);

    /**
     * Elimina un registro utilizando dos identificadores.
     *
     * @param id1 primer identificador.
     * @param id2 segundo identificador.
     */
    void Delete(int id1, int id2);
}
