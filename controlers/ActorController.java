
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.controlers;

import com.sakila.data.ActorDataContext;
import com.sakila.models.Actor;

import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones
 * relacionadas con los actores de la base de datos Sakila.
 *
 * Se comunica con ActorDataContext para realizar las
 * operaciones de registro, actualización, búsqueda,
 * listado y eliminación de actores.
 *
 * @author Proyecto SakilaORM
 * @version 1.0
 */
public class ActorController {

    /** Objeto encargado de acceder a los datos de los actores. */
    private final ActorDataContext datos;

    /**
     * Construye el controlador de actores.
     *
     * @param datos contexto de datos utilizado para gestionar actores.
     */
    public ActorController(ActorDataContext datos) {
        this.datos = datos;
    }

    /**
     * Registra un nuevo actor.
     *
     * @param actor objeto Actor que se desea registrar.
     */
    public void registrarActor(Actor actor) {
        datos.Post(actor);
    }

    /**
     * Actualiza la información de un actor existente.
     *
     * @param actor objeto Actor con los datos actualizados.
     */
    public void actualizarActor(Actor actor) {
        datos.Put(actor);
    }

    /**
     * Busca un actor utilizando su identificador.
     *
     * @param id identificador del actor.
     * @return actor encontrado o null si no existe.
     */
    public Actor buscarActor(int id) {
        return datos.Get(id);
    }

    /**
     * Obtiene la lista de todos los actores registrados.
     *
     * @return lista de actores.
     */
    public List<Actor> listarActores() {
        return datos.Get();
    }

    /**
     * Elimina un actor utilizando su identificador.
     *
     * @param id identificador del actor que se desea eliminar.
     */
    public void eliminarActor(int id) {
        datos.Delete(id);
    }

}
