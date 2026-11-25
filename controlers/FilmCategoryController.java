
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.controlers;

import com.sakila.data.FilmCategoryDataContext;
import com.sakila.models.FilmCategory;

import java.util.List;

/**
 * Controlador encargado de gestionar las relaciones
 * entre películas y categorías en la base de datos Sakila.
 *
 * Se comunica con FilmCategoryDataContext para registrar,
 * actualizar, buscar, listar y eliminar relaciones.
 *
 * Esta relación utiliza una clave primaria compuesta
 * formada por filmId y categoryId.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */
public class FilmCategoryController {

    /** Contexto encargado de acceder a los datos de las relaciones. */
    private final FilmCategoryDataContext contexto;

    /**
     * Construye el controlador de relaciones entre películas y categorías.
     *
     * @param contexto contexto utilizado para gestionar las relaciones.
     */
    public FilmCategoryController(FilmCategoryDataContext contexto) {
        this.contexto = contexto;
    }

    /**
     * Registra una nueva relación entre una película y una categoría.
     *
     * @param relacion objeto FilmCategory que contiene los identificadores
     *                 de la película y de la categoría.
     */
    public void registrar(FilmCategory relacion) {
        contexto.Post(relacion);
    }

    /**
     * Actualiza una relación existente entre una película y una categoría.
     *
     * @param relacion objeto FilmCategory con los datos de la relación.
     */
    public void actualizar(FilmCategory relacion) {
        contexto.Put(relacion);
    }

    /**
     * Busca una relación utilizando los dos identificadores
     * que forman su clave primaria compuesta.
     *
     * @param filmId identificador de la película.
     * @param categoryId identificador de la categoría.
     * @return relación encontrada o null si no existe.
     */
    public FilmCategory buscar(int filmId, int categoryId) {
        return contexto.Get(filmId, categoryId);
    }

    /**
     * Obtiene todas las relaciones entre películas y categorías.
     *
     * @return lista de relaciones registradas.
     */
    public List<FilmCategory> listar() {
        return contexto.Get();
    }

    /**
     * Elimina una relación utilizando los dos identificadores
     * de su clave primaria compuesta.
     *
     * @param filmId identificador de la película.
     * @param categoryId identificador de la categoría.
     */
    public void eliminar(int filmId, int categoryId) {
        contexto.Delete(filmId, categoryId);
    }
}

