
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.controlers;

import java.util.List;

import com.sakila.data.FilmDataContext;
import com.sakila.models.Film;

/**
 * Controlador encargado de gestionar las operaciones
 * relacionadas con las películas de la base de datos Sakila.
 *
 * Se comunica con FilmDataContext para registrar,
 * actualizar, buscar, listar y eliminar películas.
 *
 * @author Proyecto SakilaORM
 * @version 1.0
 */
public class FilmController {

    /** Contexto encargado de acceder a los datos de las películas. */
    private FilmDataContext contexto;

    /**
     * Construye el controlador de películas.
     *
     * @param contexto objeto utilizado para gestionar los datos de las películas.
     */
    public FilmController(FilmDataContext contexto) {
        this.contexto = contexto;
    }

    /**
     * Registra una nueva película.
     *
     * @param film objeto Film que contiene los datos de la película.
     */
    public void registrarPelicula(Film film) {
        contexto.Post(film);
    }

    /**
     * Actualiza los datos de una película existente.
     *
     * @param film objeto Film con los datos que se desean actualizar.
     */
    public void actualizarPelicula(Film film) {
        contexto.Put(film);
    }

    /**
     * Busca una película utilizando su identificador.
     *
     * @param id identificador de la película.
     * @return película encontrada o null si no existe.
     */
    public Film buscarPelicula(int id) {
        return contexto.Get(id);
    }

    /**
     * Obtiene todas las películas registradas.
     *
     * @return lista de películas almacenadas en la base de datos.
     */
    public List<Film> listarPeliculas() {
        return contexto.Get();
    }

    /**
     * Elimina una película utilizando su identificador.
     *
     * @param id identificador de la película que se desea eliminar.
     */
    public void eliminarPelicula(int id) {
        contexto.Delete(id);
    }
}

