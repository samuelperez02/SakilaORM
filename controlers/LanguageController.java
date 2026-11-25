
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.controlers;

import java.util.List;

import com.sakila.data.LanguageDataContext;
import com.sakila.models.Language;

/**
 * Controlador encargado de gestionar las operaciones
 * relacionadas con los idiomas de la base de datos Sakila.
 *
 * Se comunica con LanguageDataContext para registrar,
 * actualizar, buscar, listar y eliminar idiomas.
 *
 * @author Proyecto SakilaORM
 * @version 1.0
 */
public class LanguageController {

    /** Contexto encargado de acceder a los datos de los idiomas. */
    private LanguageDataContext contexto;

    /**
     * Construye el controlador de idiomas.
     *
     * @param contexto contexto utilizado para gestionar los datos de los idiomas.
     */
    public LanguageController(LanguageDataContext contexto) {
        this.contexto = contexto;
    }

    /**
     * Registra un nuevo idioma.
     *
     * @param idioma objeto Language que contiene los datos del idioma.
     */
    public void registrarIdioma(Language idioma) {
        contexto.Post(idioma);
    }

    /**
     * Actualiza los datos de un idioma existente.
     *
     * @param idioma objeto Language con los datos que se desean actualizar.
     */
    public void actualizarIdioma(Language idioma) {
        contexto.Put(idioma);
    }

    /**
     * Busca un idioma utilizando su identificador.
     *
     * @param id identificador del idioma.
     * @return idioma encontrado o null si no existe.
     */
    public Language buscarIdioma(int id) {
        return contexto.Get(id);
    }

    /**
     * Obtiene todos los idiomas registrados.
     *
     * @return lista de idiomas almacenados en la base de datos.
     */
    public List<Language> listarIdiomas() {
        return contexto.Get();
    }

    /**
     * Elimina un idioma utilizando su identificador.
     *
     * @param id identificador del idioma que se desea eliminar.
     */
    public void eliminarIdioma(int id) {
        contexto.Delete(id);
    }
}
