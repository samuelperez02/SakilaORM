
/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila.controlers;

import java.util.List;

import com.sakila.data.StaffDataContext;
import com.sakila.models.Staff;

/**
 * Controlador encargado de gestionar las operaciones
 * relacionadas con los empleados de la base de datos Sakila.
 *
 * Se comunica con StaffDataContext para registrar,
 * actualizar, buscar, listar y eliminar empleados.
 *
 * @author Proyecto SakilaORM
 * @version 1.0
 */
public class StaffController {

    /** Contexto encargado de acceder a los datos de los empleados. */
    private StaffDataContext contexto;

    /**
     * Construye el controlador de empleados.
     *
     * @param contexto contexto utilizado para gestionar los datos de los empleados.
     */
    public StaffController(StaffDataContext contexto) {
        this.contexto = contexto;
    }

    /**
     * Registra un nuevo empleado.
     *
     * @param empleado objeto Staff que contiene los datos del empleado.
     */
    public void registrarEmpleado(Staff empleado) {
        contexto.Post(empleado);
    }

    /**
     * Actualiza los datos de un empleado existente.
     *
     * @param empleado objeto Staff con los datos que se desean actualizar.
     */
    public void actualizarEmpleado(Staff empleado) {
        contexto.Put(empleado);
    }

    /**
     * Busca un empleado utilizando su identificador.
     *
     * @param id identificador del empleado.
     * @return empleado encontrado o null si no existe.
     */
    public Staff buscarEmpleado(int id) {
        return contexto.Get(id);
    }

    /**
     * Obtiene todos los empleados registrados.
     *
     * @return lista de empleados almacenados en la base de datos.
     */
    public List<Staff> listarEmpleados() {
        return contexto.Get();
    }

    /**
     * Elimina un empleado utilizando su identificador.
     *
     * @param id identificador del empleado que se desea eliminar.
     */
    public void eliminarEmpleado(int id) {
        contexto.Delete(id);
    }
}
