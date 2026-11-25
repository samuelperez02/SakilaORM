
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.controlers;

import java.util.List;

import com.sakila.data.AddressDataContext;
import com.sakila.models.Address;

/**
 * Controlador encargado de gestionar las operaciones
 * relacionadas con las direcciones de la base de datos Sakila.
 *
 * Se comunica con AddressDataContext para realizar las
 * operaciones de registro, actualización, búsqueda,
 * listado y eliminación de direcciones.
 *
 * @author Proyecto SakilaORM
 * @version 1.0
 */
public class AddressController {

    /** Contexto encargado de acceder a los datos de las direcciones. */
    private AddressDataContext contexto;

    /**
     * Construye el controlador de direcciones.
     *
     * @param contexto objeto utilizado para gestionar los datos de direcciones.
     */
    public AddressController(AddressDataContext contexto) {
        this.contexto = contexto;
    }

    /**
     * Registra una nueva dirección en la base de datos.
     *
     * @param direccion objeto Address que contiene los datos de la dirección.
     */
    public void registrarDireccion(Address direccion) {
        contexto.Post(direccion);
    }

    /**
     * Actualiza los datos de una dirección existente.
     *
     * @param direccion objeto Address con los datos que se desean actualizar.
     */
    public void actualizarDireccion(Address direccion) {
        contexto.Put(direccion);
    }

    /**
     * Busca una dirección utilizando su identificador.
     *
     * @param id identificador de la dirección.
     * @return objeto Address encontrado o null si no existe.
     */
    public Address buscarDireccion(int id) {
        return contexto.Get(id);
    }

    /**
     * Obtiene todas las direcciones registradas.
     *
     * @return lista de direcciones almacenadas en la base de datos.
     */
    public List<Address> listarDirecciones() {
        return contexto.Get();
    }

    /**
     * Elimina una dirección utilizando su identificador.
     *
     * @param id identificador de la dirección que se desea eliminar.
     */
    public void eliminarDireccion(int id) {
        contexto.Delete(id);
    }
}
