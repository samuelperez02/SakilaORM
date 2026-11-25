
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.controlers;

import java.util.List;

import com.sakila.data.CityDataContext;
import com.sakila.models.City;

/**
 * Controlador encargado de gestionar las operaciones
 * relacionadas con las ciudades de la base de datos Sakila.
 *
 * Se comunica con CityDataContext para registrar,
 * actualizar, buscar, listar y eliminar ciudades.
 *
 * @author Proyecto SakilaORM
 * @version 1.0
 */
public class CityController {

    /** Contexto encargado de acceder a los datos de las ciudades. */
    private CityDataContext contexto;

    /**
     * Construye el controlador de ciudades.
     *
     * @param contexto objeto utilizado para gestionar los datos de las ciudades.
     */
    public CityController(CityDataContext contexto) {
        this.contexto = contexto;
    }

    /**
     * Registra una nueva ciudad.
     *
     * @param ciudad objeto City que contiene los datos de la ciudad.
     */
    public void registrarCiudad(City ciudad) {
        contexto.Post(ciudad);
    }

    /**
     * Actualiza los datos de una ciudad existente.
     *
     * @param ciudad objeto City con los datos que se desean actualizar.
     */
    public void actualizarCiudad(City ciudad) {
        contexto.Put(ciudad);
    }

    /**
     * Busca una ciudad utilizando su identificador.
     *
     * @param id identificador de la ciudad.
     * @return ciudad encontrada o null si no existe.
     */
    public City buscarCiudad(int id) {
        return contexto.Get(id);
    }

    /**
     * Obtiene todas las ciudades registradas.
     *
     * @return lista de ciudades almacenadas en la base de datos.
     */
    public List<City> listarCiudades() {
        return contexto.Get();
    }

    /**
     * Elimina una ciudad utilizando su identificador.
     *
     * @param id identificador de la ciudad que se desea eliminar.
     */
    public void eliminarCiudad(int id) {
        contexto.Delete(id);
    }
}
