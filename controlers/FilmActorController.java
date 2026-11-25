
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.controlers;

import com.sakila.data.FilmActorDataContext;
import com.sakila.models.FilmActor;

import java.util.List;

/**
 * Controlador encargado de gestionar las relaciones
 * entre actores y películas en la base de datos Sakila.
 *
 * Utiliza FilmActorDataContext para registrar, actualizar,
 * buscar, listar y eliminar relaciones entre actores y películas.
 *
 * Esta relación utiliza una clave primaria compuesta
 * formada por actorId y filmId.
 *
 * @author Proyecto SakilaORM
 * @version 1.0
 */
public class FilmActorController {

    /** Contexto encargado de acceder a los datos de las relaciones. */
    private final FilmActorDataContext contexto;

    /**
     * Construye el controlador de relaciones entre actores y películas.
     *
     * @param contexto contexto utilizado para gestionar las relaciones.
     */
    public FilmActorController(FilmActorDataContext contexto) {
        this.contexto = contexto;
    }

    /**
     * Registra una nueva relación entre un actor y una película.
     *
     * @param relacion objeto FilmActor que contiene los identificadores
     *                 del actor y de la película.
     */
    public void registrar(FilmActor relacion) {
        contexto.Post(relacion);
    }

    /**
     * Actualiza una relación existente entre un actor y una película.
     *
     * @param relacion objeto FilmActor con los datos de la relación.
     */
    public void actualizar(FilmActor relacion) {
        contexto.Put(relacion);
    }

    /**
     * Busca una relación utilizando los dos identificadores
     * que forman su clave primaria compuesta.
     *
     * @param actorId identificador del actor.
     * @param filmId identificador de la película.
     * @return relación encontrada o null si no existe.
     */
    public FilmActor buscar(int actorId, int filmId) {
        return contexto.Get(actorId, filmId);
    }

    /**
     * Obtiene todas las relaciones entre actores y películas.
     *
     * @return lista de relaciones registradas.
     */
    public List<FilmActor> listar() {
        return contexto.Get();
    }

    /**
     * Elimina una relación utilizando los dos identificadores
     * de su clave primaria compuesta.
     *
     * @param actorId identificador del actor.
     * @param filmId identificador de la película.
     */
    public void eliminar(int actorId, int filmId) {
        contexto.Delete(actorId, filmId);
    }
}

