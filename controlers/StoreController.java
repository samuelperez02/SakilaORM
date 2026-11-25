
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.controlers;

import java.util.List;

import com.sakila.data.StoreDataContext;
import com.sakila.models.Store;

/**
 * Controlador encargado de gestionar las operaciones
 * relacionadas con las tiendas de la base de datos Sakila.
 *
 * Se comunica con StoreDataContext para registrar,
 * actualizar, buscar, listar y eliminar tiendas.
 *
 * @author Proyecto SakilaORM
 * @version 1.0
 */
public class StoreController {

    /** Contexto encargado de acceder a los datos de las tiendas. */
    private StoreDataContext contexto;

    /**
     * Construye el controlador de tiendas.
     *
     * @param contexto contexto utilizado para gestionar los datos de las tiendas.
     */
    public StoreController(StoreDataContext contexto) {
        this.contexto = contexto;
    }

    /**
     * Registra una nueva tienda.
     *
     * @param tienda objeto Store que contiene los datos de la tienda.
     */
    public void registrarTienda(Store tienda) {
        contexto.Post(tienda);
    }

    /**
     * Actualiza los datos de una tienda existente.
     *
     * @param tienda objeto Store con los datos que se desean actualizar.
     */
    public void actualizarTienda(Store tienda) {
        contexto.Put(tienda);
    }

    /**
     * Busca una tienda utilizando su identificador.
     *
     * @param id identificador de la tienda.
     * @return tienda encontrada o null si no existe.
     */
    public Store buscarTienda(int id) {
        return contexto.Get(id);
    }

    /**
     * Obtiene todas las tiendas registradas.
     *
     * @return lista de tiendas almacenadas en la base de datos.
     */
    public List<Store> listarTiendas() {
        return contexto.Get();
    }

    /**
     * Elimina una tienda utilizando su identificador.
     *
     * @param id identificador de la tienda que se desea eliminar.
     */
    public void eliminarTienda(int id) {
        contexto.Delete(id);
    }
}
