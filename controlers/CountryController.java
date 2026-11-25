
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.controlers;

import java.util.List;

import com.sakila.data.CountryDataContext;
import com.sakila.models.Country;

/**
 * Controlador encargado de gestionar las operaciones
 * relacionadas con los países de la base de datos Sakila.
 *
 * Se comunica con CountryDataContext para registrar,
 * actualizar, buscar, listar y eliminar países.
 *
 * @author Proyecto SakilaORM
 * @version 1.0
 */
public class CountryController {

    /** Contexto encargado de acceder a los datos de los países. */
    private CountryDataContext contexto;

    /**
     * Construye el controlador de países.
     *
     * @param contexto objeto utilizado para gestionar los datos de los países.
     */
    public CountryController(CountryDataContext contexto) {
        this.contexto = contexto;
    }

    /**
     * Registra un nuevo país.
     *
     * @param pais objeto Country que contiene los datos del país.
     */
    public void registrarPais(Country pais) {
        contexto.Post(pais);
    }

    /**
     * Actualiza los datos de un país existente.
     *
     * @param pais objeto Country con los datos que se desean actualizar.
     */
    public void actualizarPais(Country pais) {
        contexto.Put(pais);
    }

    /**
     * Busca un país utilizando su identificador.
     *
     * @param id identificador del país.
     * @return país encontrado o null si no existe.
     */
    public Country buscarPais(int id) {
        return contexto.Get(id);
    }

    /**
     * Obtiene todos los países registrados.
     *
     * @return lista de países almacenados en la base de datos.
     */
    public List<Country> listarPaises() {
        return contexto.Get();
    }

    /**
     * Elimina un país utilizando su identificador.
     *
     * @param id identificador del país que se desea eliminar.
     */
    public void eliminarPais(int id) {
        contexto.Delete(id);
    }
}
