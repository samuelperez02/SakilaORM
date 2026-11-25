
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.controlers;

import java.util.List;

import com.sakila.data.FilmTextDataContext;
import com.sakila.models.FilmText;

/**
 * Controlador encargado de gestionar los textos
 * y las descripciones de las películas de Sakila.
 *
 * Se comunica con FilmTextDataContext para registrar,
 * actualizar, buscar, listar y eliminar registros.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class FilmTextController {

    /** Contexto encargado de acceder a los datos de los textos de películas. */
    private final FilmTextDataContext contexto;

    /**
     * Construye el controlador de textos de películas.
     *
     * @param contexto contexto utilizado para gestionar los textos.
     */
    public FilmTextController(FilmTextDataContext contexto) {
        this.contexto = contexto;
    }

    /**
     * Registra un nuevo texto de película.
     *
     * @param pelicula objeto FilmText que contiene el título y la descripción.
     */
    public void registrar(FilmText pelicula) {
        contexto.Post(pelicula);
    }

    /**
     * Actualiza el texto de una película existente.
     *
     * @param pelicula objeto FilmText con los datos que se desean actualizar.
     */
    public void actualizar(FilmText pelicula) {
        contexto.Put(pelicula);
    }

    /**
     * Busca un texto de película utilizando su identificador.
     *
     * @param id identificador del registro de película.
     * @return texto encontrado o null si no existe.
     */
    public FilmText buscar(int id) {
        return contexto.Get(id);
    }

    /**
     * Obtiene todos los textos de películas registrados.
     *
     * @return lista de textos almacenados en la base de datos.
     */
    public List<FilmText> listar() {
        return contexto.Get();
    }

    /**
     * Elimina un texto de película utilizando su identificador.
     *
     * @param id identificador del registro que se desea eliminar.
     */
    public void eliminar(int id) {
        contexto.Delete(id);
    }
}
