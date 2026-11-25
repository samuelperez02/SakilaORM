
/**
 * Copyright (c) 2026.
 * Proyecto final de Lenguaje de Programacion II.
 *
 * Interfaz generica que define las operaciones CRUD
 * para administrar los registros de la base de datos.
 *
 * @param <T> Tipo de objeto que se va a administrar.
 */

package com.sakila.data;

import java.util.List;

/**
 * Define las operaciones basicas para insertar,
 * actualizar, buscar, listar y eliminar registros.
 *
 * @param <T> Tipo de dato utilizado por la interfaz.
 */
public interface iDatapost<T> {

    /**
     * Inserta un nuevo objeto.
     *
     * @param objeto objeto que se desea insertar.
     */
    void Post(T objeto);

    /**
     * Actualiza un objeto existente.
     *
     * @param objeto objeto con los datos actualizados.
     */
    void Put(T objeto);

    /**
     * Busca un objeto utilizando su identificador.
     *
     * @param id identificador del registro.
     * @return objeto encontrado o null si no existe.
     */
    T Get(int id);

    /**
     * Obtiene todos los registros.
     *
     * @return lista de objetos encontrados.
     */
    List<T> Get();

    /**
     * Elimina un registro mediante su identificador.
     *
     * @param id identificador del registro que se eliminará.
     */
    void Delete(int id);
}
