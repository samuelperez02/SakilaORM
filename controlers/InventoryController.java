
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.controlers;

import com.sakila.data.InventoryDataContext;
import com.sakila.models.Inventory;

import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones
 * relacionadas con el inventario de películas de Sakila.
 *
 * Se comunica con InventoryDataContext para registrar,
 * actualizar, buscar, listar y eliminar registros de inventario.
 *
 * @author Proyecto SakilaORM
 * @version 1.0
 */
public class InventoryController {

    /** Contexto encargado de acceder a los datos del inventario. */
    private InventoryDataContext contexto;

    /**
     * Construye el controlador de inventario.
     *
     * @param contexto contexto utilizado para gestionar los registros.
     */
    public InventoryController(InventoryDataContext contexto) {
        this.contexto = contexto;
    }

    /**
     * Registra una nueva unidad de inventario.
     *
     * @param inventario objeto Inventory con los datos del inventario.
     */
    public void registrar(Inventory inventario) {
        contexto.Post(inventario);
    }

    /**
     * Actualiza los datos de un registro de inventario existente.
     *
     * @param inventario objeto Inventory con los datos que se desean actualizar.
     */
    public void actualizar(Inventory inventario) {
        contexto.Put(inventario);
    }

    /**
     * Busca un registro de inventario utilizando su identificador.
     *
     * @param id identificador del inventario.
     * @return registro encontrado o null si no existe.
     */
    public Inventory buscar(int id) {
        return contexto.Get(id);
    }

    /**
     * Obtiene todos los registros de inventario.
     *
     * @return lista de registros almacenados en la base de datos.
     */
    public List<Inventory> listar() {
        return contexto.Get();
    }

    /**
     * Elimina un registro de inventario utilizando su identificador.
     *
     * @param id identificador del registro que se desea eliminar.
     */
    public void eliminar(int id) {
        contexto.Delete(id);
    }
    
    /**
     * Obtiene las estadisticas generales del inventario.
     *
     * @return arreglo con el total de unidades,
     *         peliculas diferentes y promedio.
     */
    public Object[] obtenerEstadisticasGenerales() {
        try {
            return contexto.obtenerEstadisticasGenerales();
        } catch (java.sql.SQLException e) {
            throw new RuntimeException(
                    "Error al consultar las estadisticas del inventario.", e);
        }
    }
}
