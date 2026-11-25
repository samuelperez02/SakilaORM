
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.controlers;

import java.util.List;

import com.sakila.data.CustomerDataContext;
import com.sakila.models.Customer;

/**
 * Controlador encargado de gestionar las operaciones
 * relacionadas con los clientes de la base de datos Sakila.
 *
 * Se comunica con CustomerDataContext para registrar,
 * actualizar, buscar, listar y eliminar clientes.
 *
 * @author Proyecto SakilaORM
 * @version 1.0
 */
public class CustomerController {

    /** Contexto encargado de acceder a los datos de los clientes. */
    private CustomerDataContext contexto;

    /**
     * Construye el controlador de clientes.
     *
     * @param contexto objeto utilizado para gestionar los datos de los clientes.
     */
    public CustomerController(CustomerDataContext contexto) {
        this.contexto = contexto;
    }

    /**
     * Registra un nuevo cliente.
     *
     * @param cliente objeto Customer que contiene los datos del cliente.
     */
    public void registrarCliente(Customer cliente) {
        contexto.Post(cliente);
    }

    /**
     * Actualiza los datos de un cliente existente.
     *
     * @param cliente objeto Customer con los datos que se desean actualizar.
     */
    public void actualizarCliente(Customer cliente) {
        contexto.Put(cliente);
    }

    /**
     * Busca un cliente utilizando su identificador.
     *
     * @param id identificador del cliente.
     * @return cliente encontrado o null si no existe.
     */
    public Customer buscarCliente(int id) {
        return contexto.Get(id);
    }

    /**
     * Obtiene todos los clientes registrados.
     *
     * @return lista de clientes almacenados en la base de datos.
     */
    public List<Customer> listarClientes() {
        return contexto.Get();
    }

    /**
     * Elimina un cliente utilizando su identificador.
     *
     * @param id identificador del cliente que se desea eliminar.
     */
    public void eliminarCliente(int id) {
        contexto.Delete(id);
    }
}
