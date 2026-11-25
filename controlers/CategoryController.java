
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.controlers;

import java.util.List;

import com.sakila.data.CategoryDataContext;
import com.sakila.models.Category;

/**
 * Controlador encargado de gestionar las operaciones
 * relacionadas con las categorías de películas de Sakila.
 *
 * Se comunica con CategoryDataContext para registrar,
 * actualizar, buscar, listar y eliminar categorías.
 *
 * @author Proyecto SakilaORM
 * @version 1.0
 */
public class CategoryController {

    /** Contexto encargado de acceder a los datos de las categorías. */
    private CategoryDataContext contexto;

    /**
     * Construye el controlador de categorías.
     *
     * @param contexto objeto utilizado para gestionar los datos de categorías.
     */
    public CategoryController(CategoryDataContext contexto) {
        this.contexto = contexto;
    }

    /**
     * Registra una nueva categoría.
     *
     * @param categoria objeto Category que contiene los datos de la categoría.
     */
    public void registrarCategoria(Category categoria) {
        contexto.Post(categoria);
    }

    /**
     * Actualiza los datos de una categoría existente.
     *
     * @param categoria objeto Category con los datos que se desean actualizar.
     */
    public void actualizarCategoria(Category categoria) {
        contexto.Put(categoria);
    }

    /**
     * Busca una categoría utilizando su identificador.
     *
     * @param id identificador de la categoría.
     * @return categoría encontrada o null si no existe.
     */
    public Category buscarCategoria(int id) {
        return contexto.Get(id);
    }

    /**
     * Obtiene todas las categorías registradas.
     *
     * @return lista de categorías almacenadas en la base de datos.
     */
    public List<Category> listarCategorias() {
        return contexto.Get();
    }

    /**
     * Elimina una categoría utilizando su identificador.
     *
     * @param id identificador de la categoría que se desea eliminar.
     */
    public void eliminarCategoria(int id) {
        contexto.Delete(id);
    }
}

