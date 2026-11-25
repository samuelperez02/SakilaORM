/*
 * Copyright (c) 2026.
 * Proyecto Final - Lenguaje de Programación II.
 * Sistema de gestión de la base de datos Sakila.
 */

package com.sakila;

import java.sql.Connection;
import java.util.Scanner;
import java.math.BigDecimal;

import com.sakila.controlers.ActorController;
import com.sakila.controlers.FilmController;

import com.sakila.data.ActorDataContext;
import com.sakila.data.FilmDataContext;
import com.sakila.data.Conexion;

import com.sakila.models.Actor;
import com.sakila.models.Film;

import com.sakila.controlers.LanguageController;
import com.sakila.data.LanguageDataContext;
import com.sakila.models.Language;

import com.sakila.controlers.CountryController;
import com.sakila.data.CountryDataContext;
import com.sakila.models.Country;

import com.sakila.controlers.CityController;
import com.sakila.data.CityDataContext;
import com.sakila.models.City;

import com.sakila.controlers.AddressController;
import com.sakila.data.AddressDataContext;
import com.sakila.models.Address;

import com.sakila.controlers.CustomerController;
import com.sakila.data.CustomerDataContext;
import com.sakila.models.Customer;

import com.sakila.controlers.StaffController;
import com.sakila.data.StaffDataContext;
import com.sakila.models.Staff;

import com.sakila.controlers.StoreController;
import com.sakila.data.StoreDataContext;
import com.sakila.models.Store;

import com.sakila.controlers.CategoryController;
import com.sakila.data.CategoryDataContext;
import com.sakila.models.Category;

import com.sakila.controlers.RentalController;
import com.sakila.data.RentalDataContext;
import com.sakila.models.Rental;
import java.util.List;

import com.sakila.controlers.PaymentController;
import com.sakila.data.PaymentDataContext;
import com.sakila.models.Payment;

import com.sakila.controlers.InventoryController;
import com.sakila.data.InventoryDataContext;
import com.sakila.models.Inventory;

import com.sakila.controlers.FilmActorController;
import com.sakila.controlers.FilmCategoryController;

import com.sakila.data.FilmActorDataContext;
import com.sakila.data.FilmCategoryDataContext;

import com.sakila.models.FilmActor;
import com.sakila.models.FilmCategory;

import com.sakila.controlers.FilmTextController;
import com.sakila.data.FilmTextDataContext;
import com.sakila.models.FilmText;
import java.sql.SQLException;
import com.sakila.models.Validaciones;
import com.sakila.reports.EstadisticasActores;
import com.sakila.reports.EstadisticasAlquileres;
import com.sakila.reports.EstadisticasClientes;
import com.sakila.reports.EstadisticasTiendas;
import com.sakila.reports.EstadisticasUbicaciones;
import com.sakila.reports.EstadisticasInventario;
import com.sakila.reports.ReporteTablas;
import com.sakila.reports.ExportadorCSV;
import com.sakila.reports.ExportadorJSON;

/**
 * Clase principal del sistema Sakila ORM.
 *
 * Esta clase permite al usuario interactuar con la base de datos
 * Sakila mediante un menu principal y diferentes opciones de
 * administracion CRUD, reportes y estadisticas.
 *
 * @author Samuel Elias Perez
 * @version 1.0
 */

public class Main {
	
	/**
	 * Inicia la ejecucion del sistema y muestra el menu principal.
	 *
	 * Permite acceder a la administracion CRUD, reportes y estadisticas.
	 *
	 * @param args argumentos recibidos al ejecutar el programa
	 */

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        int opcion;

        do {

            System.out.println("\n==================================");
            System.out.println("          SAKILA ORM");
            System.out.println("==================================");
            System.out.println("1. Administrar registros (CRUD)");
            System.out.println("2. Consultar reportes");
            System.out.println("3. Estadisticas");
            System.out.println("0. Salir");
            System.out.println("==================================");
            System.out.print("Seleccione una opcion: ");

            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {

                case 1:
                    mostrarMenuCRUD(entrada);
                    break;

                case 2:
                	mostrarMenuReportes(entrada);
                    break;

                case 3:
                    mostrarMenuEstadisticas();
                    break;

                case 0:
                    System.out.println("Gracias por utilizar Sakila ORM.");
                    break;

                default:
                    System.out.println("Opcion no valida.");
                    break;
            }

        } while (opcion != 0);

        entrada.close();
    }
    
    /**
     * Muestra el menu general para administrar los registros de Sakila.
     *
     * @param entrada objeto Scanner utilizado para recibir datos del usuario
     */

    // ==========================================
    // MENU PRINCIPAL DE ADMINISTRACION CRUD
    // ==========================================

    public static void mostrarMenuCRUD(Scanner entrada) {

        int opcion;

        do {

            System.out.println("\n==================================");
            System.out.println("       ADMINISTRACION CRUD");
            System.out.println("==================================");
            System.out.println("1. Actores");
            System.out.println("2. Peliculas");
            System.out.println("3. Idiomas");
            System.out.println("4. Paises");
            System.out.println("5. Ciudades");
            System.out.println("6. Direcciones");
            System.out.println("7. Clientes");
            System.out.println("8. Empleados");
            System.out.println("9. Tiendas");
            System.out.println("10. Categorias");
            System.out.println("11. Alquileres");
            System.out.println("12. Pagos");
            System.out.println("13. Inventario");
            System.out.println("14. Relaciones de peliculas");
            System.out.println("15. Texto de peliculas");
            System.out.println("0. Volver al menu principal");
            System.out.println("==================================");
            System.out.print("Seleccione una opcion: ");

            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {

                case 1:
                    mostrarMenuActores(entrada);
                    break;

                case 2:
                    mostrarMenuPeliculas(entrada);
                    break;
                    
                case 3:
                    mostrarMenuIdiomas(entrada);
                    break;
                    
                case 4:
                    mostrarMenuPaises(entrada);
                    break;
                    
                case 5:
                    mostrarMenuCiudades(entrada);
                    break;
                    
                case 6:
                    mostrarMenuDirecciones(entrada);
                    break;
                    
                case 7:
                    mostrarMenuClientes(entrada);
                    break;
                    
                case 8:
                    mostrarMenuEmpleados(entrada);
                    break;
                    
                case 9:
                    mostrarMenuTiendas(entrada);
                    break;
                    
                case 10:
                    mostrarMenuCategorias(entrada);
                    break;
                    
                case 11:
                    mostrarMenuAlquileres(entrada);
                    break;
                    
                case 12:
                    mostrarMenuPagos(entrada);
                    break;
                    
                case 13:
                    mostrarMenuInventario(entrada);
                    break;
                    
                case 14:
                    mostrarMenuRelacionesPeliculas(entrada);
                    break;
                    
                case 15:
                    mostrarMenuFilmText(entrada);
                    break;

                case 0:
                    System.out.println("Regresando al menu principal...");
                    break;

                default:
                    System.out.println("Opcion no valida o modulo pendiente.");
                    break;
            }

        } while (opcion != 0);
    }
    
    /**
     * Muestra las opciones para registrar, buscar, listar, actualizar
     * y eliminar actores.
     *
     * @param entrada objeto Scanner utilizado para recibir datos del usuario
     */

    // ==========================================
    // MENU CRUD DE ACTORES
    // ==========================================

    public static void mostrarMenuActores(Scanner entrada) {

        Connection conexion = Conexion.conectar();

        if (conexion == null) {
            System.out.println("No se pudo conectar con la base de datos.");
            return;
        }

        ActorController controlador =
                new ActorController(new ActorDataContext(conexion));

        int opcion = -1;

        try {

            do {

                System.out.println("\n==================================");
                System.out.println("          CRUD DE ACTORES");
                System.out.println("==================================");
                System.out.println("1. Registrar actor");
                System.out.println("2. Buscar actor");
                System.out.println("3. Listar actores");
                System.out.println("4. Actualizar actor");
                System.out.println("5. Eliminar actor");
                System.out.println("0. Volver");
                System.out.println("==================================");
                System.out.print("Seleccione una opcion: ");

                opcion = entrada.nextInt();
                entrada.nextLine();

                switch (opcion) {

                    case 1:

                        Actor nuevoActor = new Actor();

                        System.out.print("Ingrese el nombre: ");
                        nuevoActor.setFirstName(entrada.nextLine());

                        System.out.print("Ingrese el apellido: ");
                        nuevoActor.setLastName(entrada.nextLine());

                        controlador.registrarActor(nuevoActor);

                        System.out.println("Actor registrado correctamente.");
                        break;

                    case 2:

                        System.out.print("Ingrese el ID del actor: ");
                        int idBuscar = entrada.nextInt();
                        entrada.nextLine();

                        Actor encontrado =
                                controlador.buscarActor(idBuscar);

                        if (encontrado != null) {
                            System.out.println("\nACTOR ENCONTRADO:");
                            System.out.println(encontrado);
                        } else {
                            System.out.println("No existe ese actor.");
                        }

                        break;

                    case 3:

                        System.out.println("\nLISTA DE ACTORES:");

                        for (Actor actor : controlador.listarActores()) {
                            System.out.println(actor);
                        }

                        break;

                    case 4:

                        System.out.print("Ingrese el ID del actor: ");
                        int idActualizar = entrada.nextInt();
                        entrada.nextLine();

                        Actor actorActualizar =
                                controlador.buscarActor(idActualizar);

                        if (actorActualizar != null) {

                            System.out.print("Ingrese el nuevo nombre: ");
                            actorActualizar.setFirstName(entrada.nextLine());

                            System.out.print("Ingrese el nuevo apellido: ");
                            actorActualizar.setLastName(entrada.nextLine());

                            controlador.actualizarActor(actorActualizar);

                            System.out.println("Actor actualizado correctamente.");

                        } else {
                            System.out.println("No existe ese actor.");
                        }

                        break;

                    case 5:

                        System.out.print("Ingrese el ID del actor: ");
                        int idEliminar = entrada.nextInt();
                        entrada.nextLine();

                        Actor actorEliminar =
                                controlador.buscarActor(idEliminar);

                        if (actorEliminar != null) {

                            controlador.eliminarActor(idEliminar);

                            System.out.println("Actor eliminado correctamente.");

                        } else {
                            System.out.println("No existe ese actor.");
                        }

                        break;

                    case 0:
                        System.out.println("Regresando al menu CRUD...");
                        break;

                    default:
                        System.out.println("Opcion no valida.");
                        break;
                }

            } while (opcion != 0);

        } catch (Exception e) {

            System.out.println("Error durante la operacion: " + e.getMessage());

        } finally {

            try {
                conexion.close();
                System.out.println("Conexion cerrada.");
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion.");
            }
        }
    }
    
    /**
     * Permite administrar los idiomas registrados en la base de datos.
     *
     * @param entrada objeto Scanner utilizado para recibir datos del usuario
     */
    
 // ==========================================
 // MENU CRUD DE IDIOMAS
 // ==========================================

 public static void mostrarMenuIdiomas(Scanner entrada) {

     Connection conexion = Conexion.conectar();

     if (conexion == null) {
         System.out.println("No se pudo conectar con la base de datos.");
         return;
     }

     LanguageController controlador =
             new LanguageController(new LanguageDataContext(conexion));

     int opcion = -1;

     try {

         do {

             System.out.println("\n==================================");
             System.out.println("          CRUD DE IDIOMAS");
             System.out.println("==================================");
             System.out.println("1. Registrar idioma");
             System.out.println("2. Buscar idioma");
             System.out.println("3. Listar idiomas");
             System.out.println("4. Actualizar idioma");
             System.out.println("5. Eliminar idioma");
             System.out.println("0. Volver");
             System.out.println("==================================");
             System.out.print("Seleccione una opcion: ");

             opcion = entrada.nextInt();
             entrada.nextLine();

             switch (opcion) {

                 // REGISTRAR IDIOMA
                 case 1:

                     Language nuevoIdioma = new Language();

                     System.out.print("Ingrese el nombre del idioma: ");
                     nuevoIdioma.setName(entrada.nextLine());

                     controlador.registrarIdioma(nuevoIdioma);

                     System.out.println("Idioma registrado correctamente.");
                     System.out.println("ID generado: " + nuevoIdioma.getLanguageId());

                     break;

                 // BUSCAR IDIOMA
                 case 2:

                     System.out.print("Ingrese el ID del idioma: ");
                     int idBuscar = entrada.nextInt();
                     entrada.nextLine();

                     Language encontrado = controlador.buscarIdioma(idBuscar);

                     if (encontrado != null) {
                         System.out.println("\nIDIOMA ENCONTRADO:");
                         System.out.println(encontrado);
                     } else {
                         System.out.println("No existe ese idioma.");
                     }

                     break;

                 // LISTAR IDIOMAS
                 case 3:

                     System.out.println("\nLISTA DE IDIOMAS:");

                     for (Language idioma : controlador.listarIdiomas()) {
                         System.out.println(idioma);
                     }

                     break;

                 // ACTUALIZAR IDIOMA
                 case 4:

                     System.out.print("Ingrese el ID del idioma: ");
                     int idActualizar = entrada.nextInt();
                     entrada.nextLine();

                     Language idiomaActualizar =
                             controlador.buscarIdioma(idActualizar);

                     if (idiomaActualizar != null) {

                         System.out.print("Ingrese el nuevo nombre: ");
                         idiomaActualizar.setName(entrada.nextLine());

                         controlador.actualizarIdioma(idiomaActualizar);

                         System.out.println("Idioma actualizado correctamente.");

                     } else {
                         System.out.println("No existe ese idioma.");
                     }

                     break;

                 // ELIMINAR IDIOMA
                 case 5:

                     System.out.print("Ingrese el ID del idioma: ");
                     int idEliminar = entrada.nextInt();
                     entrada.nextLine();

                     Language idiomaEliminar =
                             controlador.buscarIdioma(idEliminar);

                     if (idiomaEliminar != null) {

                         System.out.println("\nIDIOMA SELECCIONADO:");
                         System.out.println(idiomaEliminar);

                         System.out.print("¿Desea eliminarlo? (S/N): ");
                         String confirmar = entrada.nextLine();

                         if (confirmar.equalsIgnoreCase("S")) {

                             controlador.eliminarIdioma(idEliminar);

                             System.out.println("Idioma eliminado correctamente.");

                         } else {
                             System.out.println("Eliminacion cancelada.");
                         }

                     } else {
                         System.out.println("No existe ese idioma.");
                     }

                     break;

                 case 0:
                     System.out.println("Regresando al menu CRUD...");
                     break;

                 default:
                     System.out.println("Opcion no valida.");
                     break;
             }

         } while (opcion != 0);

     } catch (Exception e) {

         System.out.println("Error durante la operacion: " + e.getMessage());

     } finally {

         try {
             conexion.close();
             System.out.println("Conexion cerrada.");
         } catch (Exception e) {
             System.out.println("Error al cerrar la conexion.");
         }
     }
 }
 
 /**
  * Permite administrar los paises registrados en la base de datos.
  *
  * @param entrada objeto Scanner utilizado para recibir datos del usuario
  */
 
 // ==========================================
 // MENU CRUD DE PAISES
 // ==========================================

public static void mostrarMenuPaises(Scanner entrada) {

    Connection conexion = Conexion.conectar();

    if (conexion == null) {
        System.out.println("No se pudo conectar con la base de datos.");
        return;
    }
    CountryController controlador =
            new CountryController(new CountryDataContext(conexion));

    int opcion;

    try {
        do {
        System.out.println("\n================================");
        System.out.println("       CRUD DE PAISES");
        System.out.println("================================");
        System.out.println("1. Registrar pais");
        System.out.println("2. Buscar pais");
        System.out.println("3. Listar paises");
        System.out.println("4. Actualizar pais");
        System.out.println("5. Eliminar pais");
        System.out.println("0. Volver al menu CRUD");
        System.out.print("Seleccione una opcion: ");

        opcion = Integer.parseInt(entrada.nextLine());

        switch (opcion) {

            case 1: {
                Country nuevoPais = new Country();

                System.out.print("Nombre del pais: ");
                nuevoPais.setCountry(entrada.nextLine());

                controlador.registrarPais(nuevoPais);

                System.out.println("Pais registrado correctamente.");
                System.out.println("ID generado: " + nuevoPais.getCountryId());
                break;
            }

            case 2: {
                System.out.print("Digite el ID del pais: ");
                int id = Integer.parseInt(entrada.nextLine());

                Country pais = controlador.buscarPais(id);

                if (pais != null) {
                    System.out.println(pais);
                } else {
                    System.out.println("No existe ese pais.");
                }
                break;
            }

            case 3: {
                System.out.println("\nLISTA DE PAISES");

                for (Country pais : controlador.listarPaises()) {
                    System.out.println(pais);
                }
                break;
            }

            case 4: {
                System.out.print("Digite el ID del pais que desea actualizar: ");
                int id = Integer.parseInt(entrada.nextLine());

                Country pais = controlador.buscarPais(id);

                if (pais != null) {
                    System.out.println("Pais encontrado: " + pais);

                    System.out.print("Digite el nuevo nombre: ");
                    pais.setCountry(entrada.nextLine());

                    controlador.actualizarPais(pais);

                    System.out.println("Pais actualizado correctamente.");
                } else {
                    System.out.println("No existe ese pais.");
                }
                break;
            }

            case 5: {
                System.out.print("Digite el ID del pais que desea eliminar: ");
                int id = Integer.parseInt(entrada.nextLine());

                Country pais = controlador.buscarPais(id);

                if (pais != null) {
                    System.out.println("Pais encontrado: " + pais);

                    System.out.print("¿Esta seguro de eliminarlo? (S/N): ");
                    String confirmar = entrada.nextLine();

                    if (confirmar.equalsIgnoreCase("S")) {
                        controlador.eliminarPais(id);
                        System.out.println("Pais eliminado correctamente.");
                    } else {
                        System.out.println("Eliminacion cancelada.");
                    }
                } else {
                    System.out.println("No existe ese pais.");
                }
                break;
            }

            case 0:
                System.out.println("Regresando al menu CRUD...");
                break;

            default:
                System.out.println("Opcion no valida.");
                break;
        }

        } while (opcion != 0);

    } finally {
        try {
            conexion.close();
            System.out.println("Conexion cerrada.");
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexion: "
                    + e.getMessage());
        }
    }
}

/**
 * Permite administrar las direcciones y sus datos relacionados.
 *
 * @param entrada objeto Scanner utilizado para recibir datos del usuario
 */

// ==========================================
// MENU CRUD DE CIUDADES
// ==========================================

public static void mostrarMenuCiudades(Scanner entrada) {

    Connection conexion = Conexion.conectar();

    if (conexion == null) {
        System.out.println("No se pudo conectar con la base de datos.");
        return;
    }

    CityController controlador =
            new CityController(new CityDataContext(conexion));

    int opcion;

    do {
        System.out.println("\n================================");
        System.out.println("       CRUD DE CIUDADES");
        System.out.println("================================");
        System.out.println("1. Registrar ciudad");
        System.out.println("2. Buscar ciudad");
        System.out.println("3. Listar ciudades");
        System.out.println("4. Actualizar ciudad");
        System.out.println("5. Eliminar ciudad");
        System.out.println("0. Volver al menu CRUD");
        System.out.print("Seleccione una opcion: ");

        opcion = Integer.parseInt(entrada.nextLine());

        switch (opcion) {

            case 1: {
                City nuevaCiudad = new City();

                System.out.print("Nombre de la ciudad: ");
                nuevaCiudad.setCity(entrada.nextLine());

                System.out.print("ID del pais al que pertenece: ");
                nuevaCiudad.setCountryId(Integer.parseInt(entrada.nextLine()));

                controlador.registrarCiudad(nuevaCiudad);

                System.out.println("Ciudad registrada correctamente.");
                System.out.println("ID generado: " + nuevaCiudad.getCityId());
                break;
            }

            case 2: {
                System.out.print("Digite el ID de la ciudad: ");
                int id = Integer.parseInt(entrada.nextLine());

                City ciudad = controlador.buscarCiudad(id);

                if (ciudad != null) {
                    System.out.println(ciudad);
                } else {
                    System.out.println("No existe esa ciudad.");
                }
                break;
            }

            case 3: {
                System.out.println("\nLISTA DE CIUDADES");

                for (City ciudad : controlador.listarCiudades()) {
                    System.out.println(ciudad);
                }
                break;
            }

            case 4: {
                System.out.print("Digite el ID de la ciudad que desea actualizar: ");
                int id = Integer.parseInt(entrada.nextLine());

                City ciudad = controlador.buscarCiudad(id);

                if (ciudad != null) {
                    System.out.println("Ciudad encontrada: " + ciudad);

                    System.out.print("Digite el nuevo nombre: ");
                    ciudad.setCity(entrada.nextLine());

                    System.out.print("Digite el nuevo ID del pais: ");
                    ciudad.setCountryId(Integer.parseInt(entrada.nextLine()));

                    controlador.actualizarCiudad(ciudad);

                    System.out.println("Ciudad actualizada correctamente.");
                } else {
                    System.out.println("No existe esa ciudad.");
                }
                break;
            }

            case 5: {
                System.out.print("Digite el ID de la ciudad que desea eliminar: ");
                int id = Integer.parseInt(entrada.nextLine());

                City ciudad = controlador.buscarCiudad(id);

                if (ciudad != null) {
                    System.out.println("Ciudad encontrada: " + ciudad);

                    System.out.print("¿Esta seguro de eliminarla? (S/N): ");
                    String confirmar = entrada.nextLine();

                    if (confirmar.equalsIgnoreCase("S")) {
                        controlador.eliminarCiudad(id);
                        System.out.println("Ciudad eliminada correctamente.");
                    } else {
                        System.out.println("Eliminacion cancelada.");
                    }
                } else {
                    System.out.println("No existe esa ciudad.");
                }
                break;
            }

            case 0:
                System.out.println("Regresando al menu CRUD...");
                break;

            default:
                System.out.println("Opcion no valida.");
                break;
        }

    } while (opcion != 0);

    try {
        conexion.close();
        System.out.println("Conexion cerrada.");
    } catch (Exception e) {
        System.out.println("Error al cerrar la conexion: " + e.getMessage());
    }
}

/**
 * Permite administrar los clientes registrados en Sakila.
 *
 * @param entrada objeto Scanner utilizado para recibir datos del usuario
 */

// ==========================================
// MENU CRUD DE DIRECCIONES
// ==========================================

public static void mostrarMenuDirecciones(Scanner entrada) {

    Connection conexion = Conexion.conectar();

    if (conexion == null) {
        System.out.println("No se pudo conectar con la base de datos.");
        return;
    }

    AddressController controlador =
            new AddressController(new AddressDataContext(conexion));

    int opcion;

    do {
        System.out.println("\n================================");
        System.out.println("      CRUD DE DIRECCIONES");
        System.out.println("================================");
        System.out.println("1. Registrar direccion");
        System.out.println("2. Buscar direccion");
        System.out.println("3. Listar direcciones");
        System.out.println("4. Actualizar direccion");
        System.out.println("5. Eliminar direccion");
        System.out.println("0. Volver al menu CRUD");
        System.out.print("Seleccione una opcion: ");

        opcion = Integer.parseInt(entrada.nextLine());

        switch (opcion) {

            case 1: {
                Address nuevaDireccion = new Address();

                System.out.print("Direccion: ");
                nuevaDireccion.setAddress(entrada.nextLine());

                System.out.print("Direccion adicional (puede dejar vacio): ");
                nuevaDireccion.setAddress2(entrada.nextLine());

                System.out.print("Distrito: ");
                nuevaDireccion.setDistrict(entrada.nextLine());

                System.out.print("ID de la ciudad: ");
                nuevaDireccion.setCityId(Integer.parseInt(entrada.nextLine()));

                System.out.print("Codigo postal: ");
                nuevaDireccion.setPostalCode(entrada.nextLine());

                System.out.print("Telefono: ");
                nuevaDireccion.setPhone(entrada.nextLine());

                // El campo location se deja en null.
                // La columna permite guardar este valor.

                controlador.registrarDireccion(nuevaDireccion);

                System.out.println("Direccion registrada correctamente.");
                System.out.println("ID generado: " + nuevaDireccion.getAddressId());
                break;
            }

            case 2: {
                System.out.print("Digite el ID de la direccion: ");
                int id = Integer.parseInt(entrada.nextLine());

                Address direccion = controlador.buscarDireccion(id);

                if (direccion != null) {
                    System.out.println(direccion);
                } else {
                    System.out.println("No existe esa direccion.");
                }
                break;
            }

            case 3: {
                System.out.println("\nLISTA DE DIRECCIONES");

                for (Address direccion : controlador.listarDirecciones()) {
                    System.out.println(direccion);
                }
                break;
            }

            case 4: {
                System.out.print("Digite el ID de la direccion que desea actualizar: ");
                int id = Integer.parseInt(entrada.nextLine());

                Address direccion = controlador.buscarDireccion(id);

                if (direccion != null) {
                    System.out.println("Direccion encontrada: " + direccion);

                    System.out.print("Nueva direccion: ");
                    direccion.setAddress(entrada.nextLine());

                    System.out.print("Nueva direccion adicional: ");
                    direccion.setAddress2(entrada.nextLine());

                    System.out.print("Nuevo distrito: ");
                    direccion.setDistrict(entrada.nextLine());

                    System.out.print("Nuevo ID de ciudad: ");
                    direccion.setCityId(Integer.parseInt(entrada.nextLine()));

                    System.out.print("Nuevo codigo postal: ");
                    direccion.setPostalCode(entrada.nextLine());

                    System.out.print("Nuevo telefono: ");
                    direccion.setPhone(entrada.nextLine());

                    controlador.actualizarDireccion(direccion);

                    System.out.println("Direccion actualizada correctamente.");
                } else {
                    System.out.println("No existe esa direccion.");
                }
                break;
            }

            case 5: {
                System.out.print("Digite el ID de la direccion que desea eliminar: ");
                int id = Integer.parseInt(entrada.nextLine());

                Address direccion = controlador.buscarDireccion(id);

                if (direccion != null) {
                    System.out.println("Direccion encontrada: " + direccion);

                    System.out.print("¿Esta seguro de eliminarla? (S/N): ");
                    String confirmar = entrada.nextLine();

                    if (confirmar.equalsIgnoreCase("S")) {
                        controlador.eliminarDireccion(id);
                        System.out.println("Direccion eliminada correctamente.");
                    } else {
                        System.out.println("Eliminacion cancelada.");
                    }
                } else {
                    System.out.println("No existe esa direccion.");
                }
                break;
            }

            case 0:
                System.out.println("Regresando al menu CRUD...");
                break;

            default:
                System.out.println("Opcion no valida.");
                break;
        }

    } while (opcion != 0);

    try {
        conexion.close();
        System.out.println("Conexion cerrada.");
    } catch (Exception e) {
        System.out.println("Error al cerrar la conexion: " + e.getMessage());
    }
}

// ==========================================
// MENU CRUD DE CLIENTES
// ==========================================

public static void mostrarMenuClientes(Scanner entrada) {

    Connection conexion = Conexion.conectar();

    if (conexion == null) {
        System.out.println("No se pudo conectar con la base de datos.");
        return;
    }

    CustomerController controlador =
            new CustomerController(new CustomerDataContext(conexion));

    int opcion;

    do {
        System.out.println("\n================================");
        System.out.println("        CRUD DE CLIENTES");
        System.out.println("================================");
        System.out.println("1. Registrar cliente");
        System.out.println("2. Buscar cliente");
        System.out.println("3. Listar clientes");
        System.out.println("4. Actualizar cliente");
        System.out.println("5. Eliminar cliente");
        System.out.println("0. Volver al menu CRUD");
        System.out.print("Seleccione una opcion: ");

        opcion = Integer.parseInt(entrada.nextLine());

        switch (opcion) {

            case 1: {
                Customer nuevoCliente = new Customer();

                System.out.print("ID de la tienda: ");
                nuevoCliente.setStoreId(Integer.parseInt(entrada.nextLine()));


String nombre;

do {
    System.out.print("Nombre: ");
    nombre = entrada.nextLine();

    if (!Validaciones.validarNombre(nombre)) {
        System.out.println("Nombre no valido. Intente nuevamente.");
    }

} while (!Validaciones.validarNombre(nombre));

nuevoCliente.setFirstName(nombre.trim());

String apellido;

do {
    System.out.print("Apellido: ");
    apellido = entrada.nextLine();

    if (!Validaciones.validarNombre(apellido)) {
        System.out.println("Apellido no valido. Intente nuevamente.");
    }

} while (!Validaciones.validarNombre(apellido));

nuevoCliente.setLastName(apellido.trim());

String correo;

do {
    System.out.print("Correo electronico: ");
    correo = entrada.nextLine();

    if (!Validaciones.validarCorreo(correo)) {
        System.out.println("Correo no valido. Ejemplo: usuario@gmail.com");
    }

} while (!Validaciones.validarCorreo(correo));

nuevoCliente.setEmail(correo.trim());


                System.out.print("ID de la direccion: ");
                nuevoCliente.setAddressId(Integer.parseInt(entrada.nextLine()));

                nuevoCliente.setActive(true);
                nuevoCliente.setCreateDate(
                        java.sql.Timestamp.valueOf(
                                java.time.LocalDateTime.now().withNano(0)));

                controlador.registrarCliente(nuevoCliente);

                System.out.println("Cliente registrado correctamente.");
                System.out.println("ID generado: " + nuevoCliente.getCustomerId());
                break;
            }

            case 2: {
                System.out.print("Digite el ID del cliente: ");
                int id = Integer.parseInt(entrada.nextLine());

                Customer cliente = controlador.buscarCliente(id);

                if (cliente != null) {
                    System.out.println(cliente);
                } else {
                    System.out.println("No existe ese cliente.");
                }
                break;
            }

            case 3: {
                System.out.println("\nLISTA DE CLIENTES");

                for (Customer cliente : controlador.listarClientes()) {
                    System.out.println(cliente);
                }
                break;
            }

            case 4: {
                System.out.print("Digite el ID del cliente que desea actualizar: ");
                int id = Integer.parseInt(entrada.nextLine());

                Customer cliente = controlador.buscarCliente(id);

                if (cliente != null) {
                    System.out.println("Cliente encontrado: " + cliente);

                    System.out.print("Nuevo ID de la tienda: ");
                    cliente.setStoreId(Integer.parseInt(entrada.nextLine()));


String nuevoNombre;

do {
    System.out.print("Nuevo nombre: ");
    nuevoNombre = entrada.nextLine();

    if (!Validaciones.validarNombre(nuevoNombre)) {
        System.out.println("Nombre no valido. Intente nuevamente.");
    }

} while (!Validaciones.validarNombre(nuevoNombre));

cliente.setFirstName(nuevoNombre.trim());

String nuevoApellido;

do {
    System.out.print("Nuevo apellido: ");
    nuevoApellido = entrada.nextLine();

    if (!Validaciones.validarNombre(nuevoApellido)) {
        System.out.println("Apellido no valido. Intente nuevamente.");
    }

} while (!Validaciones.validarNombre(nuevoApellido));

cliente.setLastName(nuevoApellido.trim());

String nuevoCorreo;

do {
    System.out.print("Nuevo correo electronico: ");
    nuevoCorreo = entrada.nextLine();

    if (!Validaciones.validarCorreo(nuevoCorreo)) {
        System.out.println("Correo no valido. Ejemplo: usuario@gmail.com");
    }

} while (!Validaciones.validarCorreo(nuevoCorreo));

cliente.setEmail(nuevoCorreo.trim());


                    System.out.print("Nuevo ID de la direccion: ");
                    cliente.setAddressId(Integer.parseInt(entrada.nextLine()));

                    controlador.actualizarCliente(cliente);

                    System.out.println("Cliente actualizado correctamente.");
                } else {
                    System.out.println("No existe ese cliente.");
                }
                break;
            }

            case 5: {
                System.out.print("Digite el ID del cliente que desea eliminar: ");
                int id = Integer.parseInt(entrada.nextLine());

                Customer cliente = controlador.buscarCliente(id);

                if (cliente != null) {
                    System.out.println("Cliente encontrado: " + cliente);

                    System.out.print("¿Esta seguro de eliminarlo? (S/N): ");
                    String confirmar = entrada.nextLine();

                    if (confirmar.equalsIgnoreCase("S")) {
                        controlador.eliminarCliente(id);
                        System.out.println("Cliente eliminado correctamente.");
                    } else {
                        System.out.println("Eliminacion cancelada.");
                    }
                } else {
                    System.out.println("No existe ese cliente.");
                }
                break;
            }

            case 0:
                System.out.println("Regresando al menu CRUD...");
                break;

            default:
                System.out.println("Opcion no valida.");
                break;
        }

    } while (opcion != 0);

    try {
        conexion.close();
        System.out.println("Conexion cerrada.");
    } catch (Exception e) {
        System.out.println("Error al cerrar la conexion: " + e.getMessage());
    }
}

// ==========================================
// MENU CRUD DE EMPLEADOS
// ==========================================

public static void mostrarMenuEmpleados(Scanner entrada) {

    Connection conexion = Conexion.conectar();

    if (conexion == null) {
        System.out.println("No se pudo conectar con la base de datos.");
        return;
    }

    StaffController controlador =
            new StaffController(new StaffDataContext(conexion));

    int opcion;

    do {
        System.out.println("\n================================");
        System.out.println("       CRUD DE EMPLEADOS");
        System.out.println("================================");
        System.out.println("1. Registrar empleado");
        System.out.println("2. Buscar empleado");
        System.out.println("3. Listar empleados");
        System.out.println("4. Actualizar empleado");
        System.out.println("5. Eliminar empleado");
        System.out.println("0. Volver al menu CRUD");
        System.out.print("Seleccione una opcion: ");

        opcion = Integer.parseInt(entrada.nextLine());

        switch (opcion) {

            case 1: {
                Staff nuevoEmpleado = new Staff();

                System.out.print("Nombre: ");
                nuevoEmpleado.setFirstName(entrada.nextLine());

                System.out.print("Apellido: ");
                nuevoEmpleado.setLastName(entrada.nextLine());

                System.out.print("ID de la direccion: ");
                nuevoEmpleado.setAddressId(
                        Integer.parseInt(entrada.nextLine()));

                nuevoEmpleado.setPicture(null);

                System.out.print("Correo electronico: ");
                nuevoEmpleado.setEmail(entrada.nextLine());

                System.out.print("ID de la tienda: ");
                nuevoEmpleado.setStoreId(
                        Integer.parseInt(entrada.nextLine()));

                nuevoEmpleado.setActive(true);

                System.out.print("Nombre de usuario: ");
                nuevoEmpleado.setUsername(entrada.nextLine());

                System.out.print("Contrasena: ");
                nuevoEmpleado.setPassword(entrada.nextLine());

                controlador.registrarEmpleado(nuevoEmpleado);

                System.out.println("Empleado registrado correctamente.");
                System.out.println("ID generado: "
                        + nuevoEmpleado.getStaffId());

                break;
            }

            case 2: {
                System.out.print("Digite el ID del empleado: ");
                int id = Integer.parseInt(entrada.nextLine());

                Staff empleado = controlador.buscarEmpleado(id);

                if (empleado != null) {
                    System.out.println(empleado);
                } else {
                    System.out.println("No existe ese empleado.");
                }

                break;
            }

            case 3: {
                System.out.println("\nLISTA DE EMPLEADOS");

                for (Staff empleado : controlador.listarEmpleados()) {
                    System.out.println(empleado);
                }

                break;
            }

            case 4: {
                System.out.print("Digite el ID del empleado que desea actualizar: ");
                int id = Integer.parseInt(entrada.nextLine());

                Staff empleado = controlador.buscarEmpleado(id);

                if (empleado != null) {

                    System.out.println("Empleado encontrado: " + empleado);

                    System.out.print("Nuevo nombre: ");
                    empleado.setFirstName(entrada.nextLine());

                    System.out.print("Nuevo apellido: ");
                    empleado.setLastName(entrada.nextLine());

                    System.out.print("Nuevo ID de la direccion: ");
                    empleado.setAddressId(
                            Integer.parseInt(entrada.nextLine()));

                    System.out.print("Nuevo correo electronico: ");
                    empleado.setEmail(entrada.nextLine());

                    System.out.print("Nuevo ID de la tienda: ");
                    empleado.setStoreId(
                            Integer.parseInt(entrada.nextLine()));

                    System.out.print("Nuevo nombre de usuario: ");
                    empleado.setUsername(entrada.nextLine());

                    System.out.print("Nueva contrasena: ");
                    empleado.setPassword(entrada.nextLine());

                    controlador.actualizarEmpleado(empleado);

                    System.out.println("Empleado actualizado correctamente.");

                } else {
                    System.out.println("No existe ese empleado.");
                }

                break;
            }

            case 5: {
                System.out.print("Digite el ID del empleado que desea eliminar: ");
                int id = Integer.parseInt(entrada.nextLine());

                Staff empleado = controlador.buscarEmpleado(id);

                if (empleado != null) {

                    System.out.println("Empleado encontrado: " + empleado);

                    System.out.print("¿Esta seguro de eliminarlo? (S/N): ");
                    String confirmar = entrada.nextLine();

                    if (confirmar.equalsIgnoreCase("S")) {
                        controlador.eliminarEmpleado(id);
                        System.out.println("Empleado eliminado correctamente.");
                    } else {
                        System.out.println("Eliminacion cancelada.");
                    }

                } else {
                    System.out.println("No existe ese empleado.");
                }

                break;
            }

            case 0:
                System.out.println("Regresando al menu CRUD...");
                break;

            default:
                System.out.println("Opcion no valida.");
                break;
        }

    } while (opcion != 0);

    try {
        conexion.close();
        System.out.println("Conexion cerrada.");
    } catch (Exception e) {
        System.out.println("Error al cerrar la conexion: "
                + e.getMessage());
    }
}

// ==========================================
// MENU CRUD DE TIENDAS
// ==========================================

public static void mostrarMenuTiendas(Scanner entrada) {

    Connection conexion = Conexion.conectar();

    if (conexion == null) {
        System.out.println("No se pudo conectar con la base de datos.");
        return;
    }

    StoreController controlador =
            new StoreController(new StoreDataContext(conexion));

    int opcion;

    do {
        System.out.println("\n================================");
        System.out.println("         CRUD DE TIENDAS");
        System.out.println("================================");
        System.out.println("1. Registrar tienda");
        System.out.println("2. Buscar tienda");
        System.out.println("3. Listar tiendas");
        System.out.println("4. Actualizar tienda");
        System.out.println("5. Eliminar tienda");
        System.out.println("0. Volver al menu CRUD");
        System.out.print("Seleccione una opcion: ");

        opcion = Integer.parseInt(entrada.nextLine());

        switch (opcion) {

            case 1: {
                Store nuevaTienda = new Store();

                System.out.print("ID del empleado encargado: ");
                nuevaTienda.setManagerStaffId(
                        Integer.parseInt(entrada.nextLine()));

                System.out.print("ID de la direccion: ");
                nuevaTienda.setAddressId(
                        Integer.parseInt(entrada.nextLine()));

                controlador.registrarTienda(nuevaTienda);

                System.out.println("Tienda registrada correctamente.");
                System.out.println("ID generado: "
                        + nuevaTienda.getStoreId());

                break;
            }

            case 2: {
                System.out.print("Digite el ID de la tienda: ");
                int id = Integer.parseInt(entrada.nextLine());

                Store tienda = controlador.buscarTienda(id);

                if (tienda != null) {
                    System.out.println(tienda);
                } else {
                    System.out.println("No existe esa tienda.");
                }

                break;
            }

            case 3: {
                System.out.println("\nLISTA DE TIENDAS");

                for (Store tienda : controlador.listarTiendas()) {
                    System.out.println(tienda);
                }

                break;
            }

            case 4: {
                System.out.print("Digite el ID de la tienda que desea actualizar: ");
                int id = Integer.parseInt(entrada.nextLine());

                Store tienda = controlador.buscarTienda(id);

                if (tienda != null) {

                    System.out.println("Tienda encontrada: " + tienda);

                    System.out.print("Nuevo ID del empleado encargado: ");
                    tienda.setManagerStaffId(
                            Integer.parseInt(entrada.nextLine()));

                    System.out.print("Nuevo ID de la direccion: ");
                    tienda.setAddressId(
                            Integer.parseInt(entrada.nextLine()));

                    controlador.actualizarTienda(tienda);

                    System.out.println("Tienda actualizada correctamente.");

                } else {
                    System.out.println("No existe esa tienda.");
                }

                break;
            }

            case 5: {
                System.out.print("Digite el ID de la tienda que desea eliminar: ");
                int id = Integer.parseInt(entrada.nextLine());

                Store tienda = controlador.buscarTienda(id);

                if (tienda != null) {

                    System.out.println("Tienda encontrada: " + tienda);

                    System.out.print("¿Esta seguro de eliminarla? (S/N): ");
                    String confirmar = entrada.nextLine();

                    if (confirmar.equalsIgnoreCase("S")) {
                        controlador.eliminarTienda(id);
                        System.out.println("Tienda eliminada correctamente.");
                    } else {
                        System.out.println("Eliminacion cancelada.");
                    }

                } else {
                    System.out.println("No existe esa tienda.");
                }

                break;
            }

            case 0:
                System.out.println("Regresando al menu CRUD...");
                break;

            default:
                System.out.println("Opcion no valida.");
                break;
        }

    } while (opcion != 0);

    try {
        conexion.close();
        System.out.println("Conexion cerrada.");
    } catch (Exception e) {
        System.out.println("Error al cerrar la conexion: "
                + e.getMessage());
    }
}

// ==========================================
// MENU CRUD DE CATEGORIAS
// ==========================================

public static void mostrarMenuCategorias(Scanner entrada) {

    Connection conexion = Conexion.conectar();

    if (conexion == null) {
        System.out.println("No se pudo conectar con la base de datos.");
        return;
    }

    CategoryController controlador =
            new CategoryController(new CategoryDataContext(conexion));

    int opcion;

    do {
        System.out.println("\n================================");
        System.out.println("       CRUD DE CATEGORIAS");
        System.out.println("================================");
        System.out.println("1. Registrar categoria");
        System.out.println("2. Buscar categoria");
        System.out.println("3. Listar categorias");
        System.out.println("4. Actualizar categoria");
        System.out.println("5. Eliminar categoria");
        System.out.println("0. Volver al menu CRUD");
        System.out.print("Seleccione una opcion: ");

        opcion = Integer.parseInt(entrada.nextLine());

        switch (opcion) {

            case 1: {
                Category nuevaCategoria = new Category();

                System.out.print("Nombre de la categoria: ");
                nuevaCategoria.setName(entrada.nextLine());

                controlador.registrarCategoria(nuevaCategoria);

                System.out.println("Categoria registrada correctamente.");
                System.out.println("ID generado: "
                        + nuevaCategoria.getCategoryId());

                break;
            }

            case 2: {
                System.out.print("Digite el ID de la categoria: ");
                int id = Integer.parseInt(entrada.nextLine());

                Category categoria = controlador.buscarCategoria(id);

                if (categoria != null) {
                    System.out.println("\nCATEGORIA ENCONTRADA:");
                    System.out.println(categoria);
                } else {
                    System.out.println("No existe esa categoria.");
                }

                break;
            }

            case 3: {
                System.out.println("\nLISTA DE CATEGORIAS:");

                for (Category categoria : controlador.listarCategorias()) {
                    System.out.println(categoria);
                }

                break;
            }

            case 4: {
                System.out.print("Digite el ID de la categoria: ");
                int id = Integer.parseInt(entrada.nextLine());

                Category categoria = controlador.buscarCategoria(id);

                if (categoria != null) {

                    System.out.println("Categoria encontrada: " + categoria);

                    System.out.print("Digite el nuevo nombre: ");
                    categoria.setName(entrada.nextLine());

                    controlador.actualizarCategoria(categoria);

                    System.out.println("Categoria actualizada correctamente.");

                } else {
                    System.out.println("No existe esa categoria.");
                }

                break;
            }

            case 5: {
                System.out.print("Digite el ID de la categoria que desea eliminar: ");
                int id = Integer.parseInt(entrada.nextLine());

                Category categoria = controlador.buscarCategoria(id);

                if (categoria != null) {

                    System.out.println("Categoria encontrada: " + categoria);

                    System.out.print("¿Esta seguro de eliminarla? (S/N): ");
                    String confirmar = entrada.nextLine();

                    if (confirmar.equalsIgnoreCase("S")) {

                        controlador.eliminarCategoria(id);

                        System.out.println("Categoria eliminada correctamente.");

                    } else {
                        System.out.println("Eliminacion cancelada.");
                    }

                } else {
                    System.out.println("No existe esa categoria.");
                }

                break;
            }

            case 0:
                System.out.println("Regresando al menu CRUD...");
                break;

            default:
                System.out.println("Opcion no valida.");
                break;
        }

    } while (opcion != 0);

    try {
        conexion.close();
        System.out.println("Conexion cerrada.");
    } catch (Exception e) {
        System.out.println("Error al cerrar la conexion: "
                + e.getMessage());
    }
}

// ==========================================
// MENU CRUD DE ALQUILERES
// ==========================================

private static void mostrarMenuAlquileres(Scanner entrada) {

    java.sql.Connection conexion = Conexion.conectar();

    if (conexion == null) {
        System.out.println("No se pudo conectar con la base de datos.");
        return;
    }

    RentalDataContext contexto = new RentalDataContext(conexion);
    RentalController controlador = new RentalController(contexto);

    int opcion = -1;

    while (opcion != 0) {

        System.out.println("\n================================");
        System.out.println("       CRUD DE ALQUILERES");
        System.out.println("================================");
        System.out.println("1. Registrar alquiler");
        System.out.println("2. Actualizar alquiler");
        System.out.println("3. Buscar alquiler");
        System.out.println("4. Listar alquileres");
        System.out.println("5. Eliminar alquiler");
        System.out.println("0. Volver al menu anterior");
        System.out.println("================================");
        System.out.print("Seleccione una opcion: ");

        try {
            opcion = Integer.parseInt(entrada.nextLine());

            switch (opcion) {

                case 1: {
                    Rental alquiler = new Rental();

                    System.out.print("Fecha del alquiler (AAAA-MM-DD HH:MM:SS): ");
                    alquiler.setRentalDate(
                            java.sql.Timestamp.valueOf(entrada.nextLine()));

                    System.out.print("ID del inventario: ");
                    alquiler.setInventoryId(
                            Integer.parseInt(entrada.nextLine()));

                    System.out.print("ID del cliente: ");
                    alquiler.setCustomerId(
                            Integer.parseInt(entrada.nextLine()));

                    System.out.print("Fecha de devolucion (AAAA-MM-DD HH:MM:SS) o Enter si esta pendiente: ");
                    String devolucion = entrada.nextLine();

                    if (devolucion.trim().isEmpty()) {
                        alquiler.setReturnDate(null);
                    } else {
                        alquiler.setReturnDate(
                                java.sql.Timestamp.valueOf(devolucion));
                    }

                    System.out.print("ID del empleado: ");
                    alquiler.setStaffId(
                            Integer.parseInt(entrada.nextLine()));

                    controlador.registrarAlquiler(alquiler);

                    System.out.println("Alquiler registrado correctamente.");
                    System.out.println("ID generado: " + alquiler.getRentalId());
                    break;
                }

                case 2: {
                    System.out.print("Ingrese el ID del alquiler: ");
                    int id = Integer.parseInt(entrada.nextLine());

                    Rental alquiler = controlador.buscarAlquiler(id);

                    if (alquiler == null) {
                        System.out.println("No existe ese alquiler.");
                    } else {
                        System.out.println("Alquiler encontrado: " + alquiler);

                        System.out.print("Nueva fecha del alquiler (AAAA-MM-DD HH:MM:SS): ");
                        alquiler.setRentalDate(
                                java.sql.Timestamp.valueOf(entrada.nextLine()));

                        System.out.print("Nuevo ID del inventario: ");
                        alquiler.setInventoryId(
                                Integer.parseInt(entrada.nextLine()));

                        System.out.print("Nuevo ID del cliente: ");
                        alquiler.setCustomerId(
                                Integer.parseInt(entrada.nextLine()));

                        System.out.print("Nueva fecha de devolucion o Enter si esta pendiente: ");
                        String devolucion = entrada.nextLine();

                        if (devolucion.trim().isEmpty()) {
                            alquiler.setReturnDate(null);
                        } else {
                            alquiler.setReturnDate(
                                    java.sql.Timestamp.valueOf(devolucion));
                        }

                        System.out.print("Nuevo ID del empleado: ");
                        alquiler.setStaffId(
                                Integer.parseInt(entrada.nextLine()));

                        controlador.actualizarAlquiler(alquiler);

                        System.out.println("Alquiler actualizado correctamente.");
                    }
                    break;
                }

                case 3: {
                    System.out.print("Ingrese el ID del alquiler: ");
                    int id = Integer.parseInt(entrada.nextLine());

                    Rental alquiler = controlador.buscarAlquiler(id);

                    if (alquiler == null) {
                        System.out.println("No existe ese alquiler.");
                    } else {
                        System.out.println("\nALQUILER ENCONTRADO");
                        System.out.println(alquiler);
                    }
                    break;
                }

                case 4: {
                    System.out.println("\nLISTA DE ALQUILERES");

                    List<Rental> alquileres = controlador.listarAlquileres();

                    if (alquileres.isEmpty()) {
                        System.out.println("No hay alquileres registrados.");
                    } else {
                        for (Rental alquiler : alquileres) {
                            System.out.println(alquiler);
                        }

                        System.out.println("Total de alquileres: "
                                + alquileres.size());
                    }
                    break;
                }

                case 5: {
                    System.out.print("Ingrese el ID del alquiler: ");
                    int id = Integer.parseInt(entrada.nextLine());

                    Rental alquiler = controlador.buscarAlquiler(id);

                    if (alquiler == null) {
                        System.out.println("No existe ese alquiler.");
                    } else {
                        System.out.println("Alquiler encontrado: " + alquiler);

                        System.out.print("¿Desea eliminarlo? (S/N): ");
                        String confirmar = entrada.nextLine();

                        if (confirmar.equalsIgnoreCase("S")) {
                            controlador.eliminarAlquiler(id);
                            System.out.println("Alquiler eliminado correctamente.");
                        } else {
                            System.out.println("Eliminacion cancelada.");
                        }
                    }
                    break;
                }

                case 0:
                    System.out.println("Regresando al menu anterior...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Debe introducir un numero valido.");

        } catch (IllegalArgumentException e) {
            System.out.println("Formato de fecha incorrecto. "
                    + "Use AAAA-MM-DD HH:MM:SS.");

        } catch (Exception e) {
            System.out.println("Error al realizar la operacion: "
                    + e.getMessage());
        }
    }

    try {
        conexion.close();
    } catch (java.sql.SQLException e) {
        System.out.println("Error al cerrar la conexion.");
    }
}

// ==========================================
// MENU CRUD DE ADMINISTRACION DE PAGOS
// ==========================================

public static void mostrarMenuPagos(Scanner entrada) {

    int opcion;

    PaymentDataContext contexto = new PaymentDataContext(Conexion.conectar());
    PaymentController controlador = new PaymentController(contexto);

    do {
        System.out.println("\n==================================");
        System.out.println("       ADMINISTRACION DE PAGOS");
        System.out.println("==================================");
        System.out.println("1. Registrar pago");
        System.out.println("2. Actualizar pago");
        System.out.println("3. Buscar pago");
        System.out.println("4. Listar pagos");
        System.out.println("5. Eliminar pago");
        System.out.println("0. Volver al menu CRUD");
        System.out.println("==================================");
        System.out.print("Seleccione una opcion: ");

        opcion = entrada.nextInt();
        entrada.nextLine();

        switch (opcion) {

            case 1: {
                Payment pago = new Payment();

                System.out.print("ID del cliente: ");
                pago.setCustomerId(entrada.nextInt());
                entrada.nextLine();

                System.out.print("ID del empleado: ");
                pago.setStaffId(entrada.nextInt());
                entrada.nextLine();

                System.out.print("ID del alquiler (deje vacio si no aplica): ");
                String alquiler = entrada.nextLine();

                if (alquiler.isEmpty()) {
                    pago.setRentalId(null);
                } else {
                    pago.setRentalId(Integer.parseInt(alquiler));
                }

                System.out.print("Monto del pago: ");
                pago.setAmount(new BigDecimal(entrada.nextLine()));

                System.out.print("Fecha del pago (AAAA-MM-DD HH:MM:SS): ");
                pago.setPaymentDate(
                    java.sql.Timestamp.valueOf(entrada.nextLine())
                );
                
                System.out.println("Alquiler recibido: " + pago.getRentalId());
                controlador.registrar(pago);

                System.out.println("Pago registrado correctamente.");
                System.out.println("ID generado: " + pago.getPaymentId());
                break;
            }

            case 2: {
                System.out.print("ID del pago que desea actualizar: ");
                int id = entrada.nextInt();
                entrada.nextLine();

                Payment pago = controlador.buscar(id);

                if (pago != null) {

                    System.out.print("Nuevo ID del cliente: ");
                    pago.setCustomerId(entrada.nextInt());
                    entrada.nextLine();

                    System.out.print("Nuevo ID del empleado: ");
                    pago.setStaffId(entrada.nextInt());
                    entrada.nextLine();

                    System.out.print("Nuevo ID del alquiler (vacio si no aplica): ");
                    String alquiler = entrada.nextLine();

                    if (alquiler.isEmpty()) {
                        pago.setRentalId(null);
                    } else {
                        pago.setRentalId(Integer.parseInt(alquiler));
                    }

                    System.out.print("Nuevo monto: ");
                    pago.setAmount(new BigDecimal(entrada.nextLine()));

                    System.out.print("Nueva fecha (AAAA-MM-DD HH:MM:SS): ");
                    pago.setPaymentDate(
                        java.sql.Timestamp.valueOf(entrada.nextLine())
                    );

                    controlador.actualizar(pago);

                    System.out.println("Pago actualizado correctamente.");

                } else {
                    System.out.println("No se encontro el pago.");
                }
                break;
            }

            case 3: {
                System.out.print("Ingrese el ID del pago: ");
                int id = entrada.nextInt();
                entrada.nextLine();

                Payment pago = controlador.buscar(id);

                if (pago != null) {
                    System.out.println(pago);
                } else {
                    System.out.println("No se encontro el pago.");
                }
                break;
            }

            case 4: {
                List<Payment> pagos = controlador.listar();

                System.out.println("\nLISTADO DE PAGOS");

                for (Payment pago : pagos) {
                    System.out.println(pago);
                }

                System.out.println("Total de pagos: " + pagos.size());
                break;
            }

            case 5: {
                System.out.print("Ingrese el ID del pago que desea eliminar: ");
                int id = entrada.nextInt();
                entrada.nextLine();

                controlador.eliminar(id);

                System.out.println("Operacion de eliminacion ejecutada.");
                break;
            }

            case 0:
                System.out.println("Regresando al menu CRUD...");
                break;

            default:
                System.out.println("Opcion no valida.");
                break;
        }

    } while (opcion != 0);
}

// ==========================================
// MENU CRUD DE ADMINISTRACION DE INVENTARIO
// ==========================================

public static void mostrarMenuInventario(Scanner entrada) {

    int opcion;

    InventoryDataContext contexto = new InventoryDataContext(Conexion.conectar());
    InventoryController controlador = new InventoryController(contexto);

    do {
        System.out.println("\n==================================");
        System.out.println("       ADMINISTRACION DE INVENTARIO");
        System.out.println("==================================");
        System.out.println("1. Registrar inventario");
        System.out.println("2. Actualizar inventario");
        System.out.println("3. Buscar inventario");
        System.out.println("4. Listar inventario");
        System.out.println("5. Eliminar inventario");
        System.out.println("0. Volver al menu CRUD");
        System.out.println("==================================");
        System.out.print("Seleccione una opcion: ");

        opcion = entrada.nextInt();
        entrada.nextLine();

        switch (opcion) {

            case 1: {
                Inventory inventario = new Inventory();

                System.out.print("ID de la pelicula: ");
                inventario.setFilmId(entrada.nextInt());
                entrada.nextLine();

                System.out.print("ID de la tienda: ");
                inventario.setStoreId(entrada.nextInt());
                entrada.nextLine();

                controlador.registrar(inventario);

                System.out.println("Inventario registrado correctamente.");
                System.out.println("ID generado: " + inventario.getInventoryId());
                break;
            }

            case 2: {
                System.out.print("ID del inventario que desea actualizar: ");
                int id = entrada.nextInt();
                entrada.nextLine();

                Inventory inventario = controlador.buscar(id);

                if (inventario != null) {

                    System.out.print("Nuevo ID de la pelicula: ");
                    inventario.setFilmId(entrada.nextInt());
                    entrada.nextLine();

                    System.out.print("Nuevo ID de la tienda: ");
                    inventario.setStoreId(entrada.nextInt());
                    entrada.nextLine();

                    controlador.actualizar(inventario);

                    System.out.println("Inventario actualizado correctamente.");

                } else {
                    System.out.println("No se encontro el inventario.");
                }
                break;
            }

            case 3: {
                System.out.print("Ingrese el ID del inventario: ");
                int id = entrada.nextInt();
                entrada.nextLine();

                Inventory inventario = controlador.buscar(id);

                if (inventario != null) {
                    System.out.println(inventario);
                } else {
                    System.out.println("No se encontro el inventario.");
                }
                break;
            }

            case 4: {
                List<Inventory> inventarios = controlador.listar();

                System.out.println("\nLISTADO DE INVENTARIO");

                for (Inventory inventario : inventarios) {
                    System.out.println(inventario);
                }

                System.out.println("Total de inventarios: " + inventarios.size());
                break;
            }

            case 5: {
                System.out.print("Ingrese el ID del inventario que desea eliminar: ");
                int id = entrada.nextInt();
                entrada.nextLine();

                controlador.eliminar(id);

                System.out.println("Operacion de eliminacion ejecutada.");
                break;
            }

            case 0:
                System.out.println("Regresando al menu CRUD...");
                break;

            default:
                System.out.println("Opcion no valida.");
                break;
        }

    } while (opcion != 0);
}

// ==========================================
// MENU CRUD DE RELACION DE PELICULAS
// ==========================================

public static void mostrarMenuRelacionesPeliculas(Scanner entrada) {

    int opcion;

    do {
        System.out.println("\n==================================");
        System.out.println("    RELACIONES DE PELICULAS");
        System.out.println("==================================");
        System.out.println("1. Administrar relacion Pelicula-Actor");
        System.out.println("2. Administrar relacion Pelicula-Categoria");
        System.out.println("0. Volver al menu CRUD");
        System.out.println("==================================");
        System.out.print("Seleccione una opcion: ");

        opcion = entrada.nextInt();
        entrada.nextLine();

        switch (opcion) {

        case 1:
            mostrarMenuFilmActor(entrada);
            break;

        case 2:
            mostrarMenuFilmCategory(entrada);
            break;

            case 0:
                System.out.println("Regresando al menu CRUD...");
                break;

            default:
                System.out.println("Opcion no valida.");
                break;
        }

    } while (opcion != 0);
}

// ==========================================
// MENU CRUD DE RELACION PELICULA-ACTOR
// ==========================================

public static void mostrarMenuFilmActor(Scanner entrada) {

    int opcion;

    FilmActorDataContext contexto =
            new FilmActorDataContext(Conexion.conectar());

    FilmActorController controlador =
            new FilmActorController(contexto);

    do {
        System.out.println("\n==================================");
        System.out.println("       RELACION PELICULA-ACTOR");
        System.out.println("==================================");
        System.out.println("1. Registrar relacion");
        System.out.println("2. Actualizar fecha de relacion");
        System.out.println("3. Buscar relacion");
        System.out.println("4. Listar relaciones");
        System.out.println("5. Eliminar relacion");
        System.out.println("0. Volver al menu anterior");
        System.out.println("==================================");
        System.out.print("Seleccione una opcion: ");

        opcion = entrada.nextInt();
        entrada.nextLine();

        switch (opcion) {

            case 1: {
                FilmActor relacion = new FilmActor();

                System.out.print("ID del actor: ");
                relacion.setActorId(entrada.nextInt());
                entrada.nextLine();

                System.out.print("ID de la pelicula: ");
                relacion.setFilmId(entrada.nextInt());
                entrada.nextLine();

                controlador.registrar(relacion);

                System.out.println("Relacion registrada correctamente.");
                break;
            }

            case 2: {
                System.out.print("ID del actor: ");
                int actorId = entrada.nextInt();
                entrada.nextLine();

                System.out.print("ID de la pelicula: ");
                int filmId = entrada.nextInt();
                entrada.nextLine();

                FilmActor relacion = controlador.buscar(actorId, filmId);

                if (relacion != null) {
                    controlador.actualizar(relacion);
                    System.out.println("Fecha de relacion actualizada correctamente.");
                } else {
                    System.out.println("No se encontro la relacion.");
                }
                break;
            }

            case 3: {
                System.out.print("ID del actor: ");
                int actorId = entrada.nextInt();
                entrada.nextLine();

                System.out.print("ID de la pelicula: ");
                int filmId = entrada.nextInt();
                entrada.nextLine();

                FilmActor relacion = controlador.buscar(actorId, filmId);

                if (relacion != null) {
                    System.out.println(relacion);
                } else {
                    System.out.println("No se encontro la relacion.");
                }
                break;
            }

            case 4: {
                List<FilmActor> relaciones = controlador.listar();

                System.out.println("\nLISTADO DE RELACIONES PELICULA-ACTOR");

                for (FilmActor relacion : relaciones) {
                    System.out.println(relacion);
                }

                System.out.println("Total de relaciones: " + relaciones.size());
                break;
            }

            case 5: {
                System.out.print("ID del actor: ");
                int actorId = entrada.nextInt();
                entrada.nextLine();

                System.out.print("ID de la pelicula: ");
                int filmId = entrada.nextInt();
                entrada.nextLine();

                controlador.eliminar(actorId, filmId);

                System.out.println("Operacion de eliminacion ejecutada.");
                break;
            }

            case 0:
                System.out.println("Regresando al menu de relaciones...");
                break;

            default:
                System.out.println("Opcion no valida.");
                break;
        }

    } while (opcion != 0);
}

// ==========================================
// MENU DE RELACION PELICULA-CATEGORIA
// ==========================================

public static void mostrarMenuFilmCategory(Scanner entrada) {

    int opcion;

    FilmCategoryDataContext contexto =
            new FilmCategoryDataContext(Conexion.conectar());

    FilmCategoryController controlador =
            new FilmCategoryController(contexto);

    do {
        System.out.println("\n==================================");
        System.out.println("    RELACION PELICULA-CATEGORIA");
        System.out.println("==================================");
        System.out.println("1. Registrar relacion");
        System.out.println("2. Actualizar fecha de relacion");
        System.out.println("3. Buscar relacion");
        System.out.println("4. Listar relaciones");
        System.out.println("5. Eliminar relacion");
        System.out.println("0. Volver al menu anterior");
        System.out.println("==================================");
        System.out.print("Seleccione una opcion: ");

        opcion = entrada.nextInt();
        entrada.nextLine();

        switch (opcion) {

            case 1: {
                FilmCategory relacion = new FilmCategory();

                System.out.print("ID de la pelicula: ");
                relacion.setFilmId(entrada.nextInt());
                entrada.nextLine();

                System.out.print("ID de la categoria: ");
                relacion.setCategoryId(entrada.nextInt());
                entrada.nextLine();

                controlador.registrar(relacion);

                System.out.println("Relacion registrada correctamente.");
                break;
            }

            case 2: {
                System.out.print("ID de la pelicula: ");
                int filmId = entrada.nextInt();
                entrada.nextLine();

                System.out.print("ID de la categoria: ");
                int categoryId = entrada.nextInt();
                entrada.nextLine();

                FilmCategory relacion =
                        controlador.buscar(filmId, categoryId);

                if (relacion != null) {
                    controlador.actualizar(relacion);
                    System.out.println("Fecha de relacion actualizada correctamente.");
                } else {
                    System.out.println("No se encontro la relacion.");
                }
                break;
            }

            case 3: {
                System.out.print("ID de la pelicula: ");
                int filmId = entrada.nextInt();
                entrada.nextLine();

                System.out.print("ID de la categoria: ");
                int categoryId = entrada.nextInt();
                entrada.nextLine();

                FilmCategory relacion =
                        controlador.buscar(filmId, categoryId);

                if (relacion != null) {
                    System.out.println(relacion);
                } else {
                    System.out.println("No se encontro la relacion.");
                }
                break;
            }

            case 4: {
                List<FilmCategory> relaciones = controlador.listar();

                System.out.println("\nLISTADO DE RELACIONES PELICULA-CATEGORIA");

                for (FilmCategory relacion : relaciones) {
                    System.out.println(relacion);
                }

                System.out.println("Total de relaciones: " + relaciones.size());
                break;
            }

            case 5: {
                System.out.print("ID de la pelicula: ");
                int filmId = entrada.nextInt();
                entrada.nextLine();

                System.out.print("ID de la categoria: ");
                int categoryId = entrada.nextInt();
                entrada.nextLine();

                controlador.eliminar(filmId, categoryId);

                System.out.println("Operacion de eliminacion ejecutada.");
                break;
            }

            case 0:
                System.out.println("Regresando al menu de relaciones...");
                break;

            default:
                System.out.println("Opcion no valida.");
                break;
        }

    } while (opcion != 0);
}

//==========================================
//MENU DE ADMINISTRAR FILM TEXT
//==========================================
public static void mostrarMenuFilmText(Scanner entrada) {

    int opcion;

    FilmTextDataContext contexto =
            new FilmTextDataContext(Conexion.conectar());

    FilmTextController controlador =
            new FilmTextController(contexto);

    do {
        System.out.println("\n==================================");
        System.out.println("       ADMINISTRAR FILM TEXT");
        System.out.println("==================================");
        System.out.println("1. Registrar pelicula");
        System.out.println("2. Actualizar pelicula");
        System.out.println("3. Buscar pelicula");
        System.out.println("4. Listar peliculas");
        System.out.println("5. Eliminar pelicula");
        System.out.println("0. Volver al menu anterior");
        System.out.println("==================================");
        System.out.print("Seleccione una opcion: ");

        opcion = entrada.nextInt();
        entrada.nextLine();

        switch (opcion) {

            case 1: {
                FilmText pelicula = new FilmText();

                System.out.print("ID de la pelicula: ");
                pelicula.setFilmId(entrada.nextInt());
                entrada.nextLine();

                System.out.print("Titulo: ");
                pelicula.setTitle(entrada.nextLine());

                System.out.print("Descripcion: ");
                pelicula.setDescription(entrada.nextLine());

                controlador.registrar(pelicula);

                System.out.println("Pelicula registrada correctamente.");
                break;
            }

            case 2: {
                System.out.print("ID de la pelicula: ");
                int id = entrada.nextInt();
                entrada.nextLine();

                FilmText pelicula = controlador.buscar(id);

                if (pelicula != null) {

                    System.out.print("Nuevo titulo: ");
                    pelicula.setTitle(entrada.nextLine());

                    System.out.print("Nueva descripcion: ");
                    pelicula.setDescription(entrada.nextLine());

                    controlador.actualizar(pelicula);

                    System.out.println("Pelicula actualizada correctamente.");

                } else {
                    System.out.println("No se encontro la pelicula.");
                }

                break;
            }

            case 3: {
                System.out.print("ID de la pelicula: ");
                int id = entrada.nextInt();
                entrada.nextLine();

                FilmText pelicula = controlador.buscar(id);

                if (pelicula != null) {
                    System.out.println(pelicula);
                } else {
                    System.out.println("No se encontro la pelicula.");
                }

                break;
            }

            case 4: {
                List<FilmText> peliculas = controlador.listar();

                System.out.println("\nLISTADO DE PELICULAS");

                for (FilmText pelicula : peliculas) {
                    System.out.println(pelicula);
                }

                System.out.println("Total de peliculas: " + peliculas.size());
                break;
            }

            case 5: {
                System.out.print("ID de la pelicula: ");
                int id = entrada.nextInt();
                entrada.nextLine();

                FilmText pelicula = controlador.buscar(id);

                if (pelicula != null) {
                    controlador.eliminar(id);
                    System.out.println("Pelicula eliminada correctamente.");
                } else {
                    System.out.println("No se encontro la pelicula.");
                }

                break;
            }

            case 0:
                System.out.println("Regresando al menu anterior...");
                break;

            default:
                System.out.println("Opcion no valida.");
                break;
        }

    } while (opcion != 0);
}

    // ==========================================
    // MENU CRUD DE PELICULAS
    // ==========================================

    public static void mostrarMenuPeliculas(Scanner entrada) {

        Connection conexion = Conexion.conectar();

        if (conexion == null) {
            System.out.println("No se pudo conectar con la base de datos.");
            return;
        }

        FilmController controlador =
                new FilmController(new FilmDataContext(conexion));

        int opcion = -1;

        try {

            do {

                System.out.println("\n==================================");
                System.out.println("          CRUD DE PELICULAS");
                System.out.println("==================================");
                System.out.println("1. Registrar pelicula");
                System.out.println("2. Buscar pelicula");
                System.out.println("3. Listar peliculas");
                System.out.println("4. Actualizar pelicula");
                System.out.println("5. Eliminar pelicula");
                System.out.println("0. Volver");
                System.out.println("==================================");
                System.out.print("Seleccione una opcion: ");

                opcion = entrada.nextInt();
                entrada.nextLine();

                switch (opcion) {

                    // REGISTRAR PELICULA
                    case 1:

                        Film nuevaPelicula = new Film();

                        System.out.print("Ingrese el titulo: ");
                        nuevaPelicula.setTitle(entrada.nextLine());

                        System.out.print("Ingrese la descripcion: ");
                        nuevaPelicula.setDescription(entrada.nextLine());

                        System.out.print("Ingrese el año de lanzamiento: ");
                        nuevaPelicula.setReleaseYear(entrada.nextInt());
                        entrada.nextLine();

                        System.out.print("Ingrese el ID del idioma: ");
                        nuevaPelicula.setLanguageId(entrada.nextInt());
                        entrada.nextLine();

                        System.out.print("Ingrese el ID del idioma original (0 si no tiene): ");
                        int idiomaOriginal = entrada.nextInt();
                        entrada.nextLine();

                        if (idiomaOriginal == 0) {
                            nuevaPelicula.setOriginalLanguageId(null);
                        } else {
                            nuevaPelicula.setOriginalLanguageId(idiomaOriginal);
                        }

                        System.out.print("Ingrese la duracion del alquiler: ");
                        nuevaPelicula.setRentalDuration(entrada.nextInt());
                        entrada.nextLine();

                        System.out.print("Ingrese la tarifa de alquiler (ejemplo 4.99): ");
                        nuevaPelicula.setRentalRate(
                                new BigDecimal(entrada.nextLine()));

                        System.out.print("Ingrese la duracion de la pelicula en minutos: ");
                        nuevaPelicula.setLength(entrada.nextInt());
                        entrada.nextLine();

                        System.out.print("Ingrese el costo de reemplazo (ejemplo 19.99): ");
                        nuevaPelicula.setReplacementCost(
                                new BigDecimal(entrada.nextLine()));

                        System.out.print("Ingrese la clasificacion (G, PG, PG-13, R, NC-17): ");
                        nuevaPelicula.setRating(entrada.nextLine());

                        System.out.println("Ejemplo de caracteristicas: Trailers,Commentaries");
                        System.out.print("Ingrese las caracteristicas especiales: ");
                        nuevaPelicula.setSpecialFeatures(entrada.nextLine());

                        controlador.registrarPelicula(nuevaPelicula);

                        System.out.println("Pelicula registrada correctamente.");
                        System.out.println("ID generado: " + nuevaPelicula.getFilmId());

                        break;

                    // BUSCAR PELICULA
                    case 2:

                        System.out.print("Ingrese el ID de la pelicula: ");
                        int idBuscar = entrada.nextInt();
                        entrada.nextLine();

                        Film encontrada = controlador.buscarPelicula(idBuscar);

                        if (encontrada != null) {

                            System.out.println("\nPELICULA ENCONTRADA:");
                            System.out.println(encontrada);
                            System.out.println("Descripcion: " + encontrada.getDescription());
                            System.out.println("ID del idioma: " + encontrada.getLanguageId());
                            System.out.println("Duracion del alquiler: " + encontrada.getRentalDuration());
                            System.out.println("Tarifa: " + encontrada.getRentalRate());
                            System.out.println("Costo de reemplazo: " + encontrada.getReplacementCost());
                            System.out.println("Clasificacion: " + encontrada.getRating());
                            System.out.println("Caracteristicas: " + encontrada.getSpecialFeatures());

                        } else {
                            System.out.println("No existe esa pelicula.");
                        }

                        break;

                    // LISTAR PELICULAS
                    case 3:

                        System.out.println("\nLISTA DE PELICULAS:");

                        for (Film pelicula : controlador.listarPeliculas()) {
                            System.out.println(pelicula);
                        }

                        break;

                    // ACTUALIZAR PELICULA
                    case 4:

                        System.out.print("Ingrese el ID de la pelicula: ");
                        int idActualizar = entrada.nextInt();
                        entrada.nextLine();

                        Film peliculaActualizar =
                                controlador.buscarPelicula(idActualizar);

                        if (peliculaActualizar != null) {

                            System.out.print("Ingrese el nuevo titulo: ");
                            peliculaActualizar.setTitle(entrada.nextLine());

                            System.out.print("Ingrese la nueva descripcion: ");
                            peliculaActualizar.setDescription(entrada.nextLine());

                            System.out.print("Ingrese el nuevo año: ");
                            peliculaActualizar.setReleaseYear(entrada.nextInt());
                            entrada.nextLine();

                            System.out.print("Ingrese el nuevo ID del idioma: ");
                            peliculaActualizar.setLanguageId(entrada.nextInt());
                            entrada.nextLine();

                            System.out.print("Ingrese el ID del idioma original (0 si no tiene): ");
                            int nuevoIdiomaOriginal = entrada.nextInt();
                            entrada.nextLine();

                            if (nuevoIdiomaOriginal == 0) {
                                peliculaActualizar.setOriginalLanguageId(null);
                            } else {
                                peliculaActualizar.setOriginalLanguageId(nuevoIdiomaOriginal);
                            }

                            System.out.print("Ingrese la nueva duracion del alquiler: ");
                            peliculaActualizar.setRentalDuration(entrada.nextInt());
                            entrada.nextLine();

                            System.out.print("Ingrese la nueva tarifa: ");
                            peliculaActualizar.setRentalRate(
                                    new BigDecimal(entrada.nextLine()));

                            System.out.print("Ingrese la nueva duracion en minutos: ");
                            peliculaActualizar.setLength(entrada.nextInt());
                            entrada.nextLine();

                            System.out.print("Ingrese el nuevo costo de reemplazo: ");
                            peliculaActualizar.setReplacementCost(
                                    new BigDecimal(entrada.nextLine()));

                            System.out.print("Ingrese la nueva clasificacion: ");
                            peliculaActualizar.setRating(entrada.nextLine());

                            System.out.print("Ingrese las nuevas caracteristicas especiales: ");
                            peliculaActualizar.setSpecialFeatures(entrada.nextLine());

                            controlador.actualizarPelicula(peliculaActualizar);

                            System.out.println("Pelicula actualizada correctamente.");

                        } else {
                            System.out.println("No existe esa pelicula.");
                        }

                        break;

                    // ELIMINAR PELICULA
                    case 5:

                        System.out.print("Ingrese el ID de la pelicula: ");
                        int idEliminar = entrada.nextInt();
                        entrada.nextLine();

                        Film peliculaEliminar =
                                controlador.buscarPelicula(idEliminar);

                        if (peliculaEliminar != null) {

                            System.out.println("\nPELICULA SELECCIONADA:");
                            System.out.println(peliculaEliminar);

                            System.out.print("¿Desea eliminarla? (S/N): ");
                            String confirmar = entrada.nextLine();

                            if (confirmar.equalsIgnoreCase("S")) {

                                controlador.eliminarPelicula(idEliminar);

                                System.out.println("Pelicula eliminada correctamente.");

                            } else {
                                System.out.println("Eliminacion cancelada.");
                            }

                        } else {
                            System.out.println("No existe esa pelicula.");
                        }

                        break;

                    case 0:
                        System.out.println("Regresando al menu CRUD...");
                        break;

                    default:
                        System.out.println("Opcion no valida.");
                        break;
                }

            } while (opcion != 0);

        } catch (Exception e) {

            System.out.println("Error durante la operacion: " + e.getMessage());

        } finally {

            try {
                conexion.close();
                System.out.println("Conexion cerrada.");
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion.");
            }
        }
    }

    // ==========================================
    // MENU DE REPORTES
    // ==========================================

public static void mostrarMenuReportes(Scanner entrada) {

    int opcion;

    do {

        System.out.println("\n==================================");
        System.out.println("             REPORTES");
        System.out.println("==================================");
        System.out.println("1.  Reporte de Actores");
        System.out.println("2.  Reporte de Peliculas");
        System.out.println("3.  Reporte de Idiomas");
        System.out.println("4.  Reporte de Paises");
        System.out.println("5.  Reporte de Ciudades");
        System.out.println("6.  Reporte de Direcciones");
        System.out.println("7.  Reporte de Clientes");
        System.out.println("8.  Reporte de Empleados");
        System.out.println("9.  Reporte de Tiendas");
        System.out.println("10. Reporte de Categorias");
        System.out.println("11. Reporte de Alquileres");
        System.out.println("12. Reporte de Pagos");
        System.out.println("13. Reporte de Inventario");
        System.out.println("14. Relacion Peliculas y Actores");
        System.out.println("15. Relacion Peliculas y Categorias");
        System.out.println("16. Reporte de Texto de Peliculas");
        System.out.println("0.  Regresar al menu principal");
        System.out.println("==================================");
        System.out.print("Seleccione una opcion: ");

        opcion = entrada.nextInt();
        entrada.nextLine();


switch (opcion) {

    // ==========================================
    // REPORTE DE ACTORES
    // ==========================================
    case 1:

        try (Connection conexion = Conexion.conectar()) {

            if (conexion == null) {
                System.out.println("No se pudo conectar con la base de datos.");
                break;
            }

            ActorDataContext contexto = new ActorDataContext(conexion);
            ActorController controlador = new ActorController(contexto);

            List<Actor> actores = controlador.listarActores();

            mostrarOpcionesReporte(
                    actores,
                    "ACTORES",
                    "actores",
                    entrada);

        } catch (Exception e) {
            System.out.println("Error al generar el reporte de actores: "
                    + e.getMessage());
        }

        break;

    // ==========================================
    // REPORTE DE PELICULAS
    // ==========================================
    case 2:

        try (Connection conexion = Conexion.conectar()) {

            if (conexion == null) {
                System.out.println("No se pudo conectar con la base de datos.");
                break;
            }

            FilmDataContext contexto = new FilmDataContext(conexion);
            FilmController controlador = new FilmController(contexto);

            List<Film> peliculas = controlador.listarPeliculas();

            mostrarOpcionesReporte(
                    peliculas,
                    "PELICULAS",
                    "peliculas",
                    entrada);
        } catch (Exception e) {
            System.out.println("Error al generar el reporte de peliculas: "
                    + e.getMessage());
        }

        break;

    // ==========================================
    // REPORTE DE IDIOMAS
    // ==========================================
    case 3:

        try (Connection conexion = Conexion.conectar()) {

            if (conexion == null) {
                System.out.println("No se pudo conectar con la base de datos.");
                break;
            }

            LanguageDataContext contexto = new LanguageDataContext(conexion);
            LanguageController controlador = new LanguageController(contexto);

            List<Language> idiomas = controlador.listarIdiomas();

            mostrarOpcionesReporte(
                    idiomas,
                    "IDIOMAS",
                    "idiomas",
                    entrada);

        } catch (Exception e) {
            System.out.println("Error al generar el reporte de idiomas: "
                    + e.getMessage());
        }

        break;

    // ==========================================
    // REPORTE DE PAISES
    // ==========================================
    case 4:

        try (Connection conexion = Conexion.conectar()) {

            if (conexion == null) {
                System.out.println("No se pudo conectar con la base de datos.");
                break;
            }

            CountryDataContext contexto = new CountryDataContext(conexion);
            CountryController controlador = new CountryController(contexto);

            List<Country> paises = controlador.listarPaises();

            mostrarOpcionesReporte(
                    paises,
                    "PAISES",
                    "paises",
                    entrada);

        } catch (Exception e) {
            System.out.println("Error al generar el reporte de paises: "
                    + e.getMessage());
        }

        break;

    // ==========================================
    // REPORTE DE CIUDADES
    // ==========================================
    case 5:

        try (Connection conexion = Conexion.conectar()) {

            if (conexion == null) {
                System.out.println("No se pudo conectar con la base de datos.");
                break;
            }

            CityDataContext contexto = new CityDataContext(conexion);
            CityController controlador = new CityController(contexto);

            List<City> ciudades = controlador.listarCiudades();

            mostrarOpcionesReporte(
                    ciudades,
                    "CIUDADES",
                    "ciudades",
                    entrada);

        } catch (Exception e) {
            System.out.println("Error al generar el reporte de ciudades: "
                    + e.getMessage());
        }

        break;

    // ==========================================
    // REPORTE DE DIRECCIONES
    // ==========================================
    case 6:

        try (Connection conexion = Conexion.conectar()) {

            if (conexion == null) {
                System.out.println("No se pudo conectar con la base de datos.");
                break;
            }

            AddressDataContext contexto = new AddressDataContext(conexion);
            AddressController controlador = new AddressController(contexto);

            List<Address> direcciones = controlador.listarDirecciones();

            mostrarOpcionesReporte(
                    direcciones,
                    "DIRECCIONES",
                    "direcciones",
                    entrada);
            
        } catch (Exception e) {
            System.out.println("Error al generar el reporte de direcciones: "
                    + e.getMessage());
        }

        break;

    // ==========================================
    // REPORTE DE CLIENTES
    // ==========================================
    case 7:

        try (Connection conexion = Conexion.conectar()) {

            if (conexion == null) {
                System.out.println("No se pudo conectar con la base de datos.");
                break;
            }

            CustomerDataContext contexto = new CustomerDataContext(conexion);
            CustomerController controlador = new CustomerController(contexto);

            List<Customer> clientes = controlador.listarClientes();

            mostrarOpcionesReporte(
                    clientes,
                    "CLIENTES",
                    "clientes",
                    entrada);

        } catch (Exception e) {
            System.out.println("Error al generar el reporte de clientes: "
                    + e.getMessage());
        }

        break;

    // ==========================================
    // REPORTE DE EMPLEADOS
    // ==========================================
    case 8:

        try (Connection conexion = Conexion.conectar()) {

            if (conexion == null) {
                System.out.println("No se pudo conectar con la base de datos.");
                break;
            }

            StaffDataContext contexto = new StaffDataContext(conexion);
            StaffController controlador = new StaffController(contexto);

            List<Staff> empleados = controlador.listarEmpleados();

            mostrarOpcionesReporte(
                    empleados,
                    "EMPLEADOS",
                    "empleados",
                    entrada);

        } catch (Exception e) {
            System.out.println("Error al generar el reporte de empleados: "
                    + e.getMessage());
        }

        break;

    // ==========================================
    // REPORTE DE TIENDAS
    // ==========================================
    case 9:

        try (Connection conexion = Conexion.conectar()) {

            if (conexion == null) {
                System.out.println("No se pudo conectar con la base de datos.");
                break;
            }

            StoreDataContext contexto = new StoreDataContext(conexion);
            StoreController controlador = new StoreController(contexto);

            List<Store> tiendas = controlador.listarTiendas();

            mostrarOpcionesReporte(
                    tiendas,
                    "TIENDAS",
                    "tiendas",
                    entrada);

        } catch (Exception e) {
            System.out.println("Error al generar el reporte de tiendas: "
                    + e.getMessage());
        }

        break;

    // ==========================================
    // REPORTE DE CATEGORIAS
    // ==========================================
    case 10:

        try (Connection conexion = Conexion.conectar()) {

            if (conexion == null) {
                System.out.println("No se pudo conectar con la base de datos.");
                break;
            }

            CategoryDataContext contexto = new CategoryDataContext(conexion);
            CategoryController controlador = new CategoryController(contexto);

            List<Category> categorias = controlador.listarCategorias();

            mostrarOpcionesReporte(
                    categorias,
                    "CATEGORIAS",
                    "categorias",
                    entrada);

        } catch (Exception e) {
            System.out.println("Error al generar el reporte de categorias: "
                    + e.getMessage());
        }

        break;

    // ==========================================
    // REPORTE DE ALQUILERES
    // ==========================================
    case 11:

        try (Connection conexion = Conexion.conectar()) {

            if (conexion == null) {
                System.out.println("No se pudo conectar con la base de datos.");
                break;
            }

            RentalDataContext contexto = new RentalDataContext(conexion);
            RentalController controlador = new RentalController(contexto);

            List<Rental> alquileres = controlador.listarAlquileres();

            mostrarOpcionesReporte(
                    alquileres,
                    "ALQUILERES",
                    "alquileres",
                    entrada);
            
        } catch (Exception e) {
            System.out.println("Error al generar el reporte de alquileres: "
                    + e.getMessage());
        }

        break;

    // ==========================================
    // REPORTE DE PAGOS
    // ==========================================
    case 12:

        try (Connection conexion = Conexion.conectar()) {

            if (conexion == null) {
                System.out.println("No se pudo conectar con la base de datos.");
                break;
            }

            PaymentDataContext contexto = new PaymentDataContext(conexion);
            PaymentController controlador = new PaymentController(contexto);

            List<Payment> pagos = controlador.listar();

            mostrarOpcionesReporte(
                    pagos,
                    "PAGOS",
                    "pagos",
                    entrada);

        } catch (Exception e) {
            System.out.println("Error al generar el reporte de pagos: "
                    + e.getMessage());
        }

        break;

    // ==========================================
    // REPORTE DE INVENTARIO
    // ==========================================
    case 13:

        try (Connection conexion = Conexion.conectar()) {

            if (conexion == null) {
                System.out.println("No se pudo conectar con la base de datos.");
                break;
            }

            InventoryDataContext contexto = new InventoryDataContext(conexion);
            InventoryController controlador = new InventoryController(contexto);

            List<Inventory> inventario = controlador.listar();

            mostrarOpcionesReporte(
                    inventario,
                    "INVENTARIO",
                    "inventario",
                    entrada);
            
        } catch (Exception e) {
            System.out.println("Error al generar el reporte de inventario: "
                    + e.getMessage());
        }

        break;

    // ==========================================
    // RELACION PELICULAS Y ACTORES
    // ==========================================
    case 14:

        try (Connection conexion = Conexion.conectar()) {

            if (conexion == null) {
                System.out.println("No se pudo conectar con la base de datos.");
                break;
            }

            FilmActorDataContext contexto = new FilmActorDataContext(conexion);
            FilmActorController controlador = new FilmActorController(contexto);

            List<FilmActor> relaciones = controlador.listar();

            mostrarOpcionesReporte(
                    relaciones,
                    "PELICULAS Y ACTORES",
                    "peliculas_actores",
                    entrada);

        } catch (Exception e) {
            System.out.println("Error al generar el reporte de peliculas y actores: "
                    + e.getMessage());
        }

        break;

    // ==========================================
    // RELACION PELICULAS Y CATEGORIAS
    // ==========================================
    case 15:

        try (Connection conexion = Conexion.conectar()) {

            if (conexion == null) {
                System.out.println("No se pudo conectar con la base de datos.");
                break;
            }

            FilmCategoryDataContext contexto =
                    new FilmCategoryDataContext(conexion);

            FilmCategoryController controlador =
                    new FilmCategoryController(contexto);

            List<FilmCategory> relaciones = controlador.listar();

            mostrarOpcionesReporte(
                    relaciones,
                    "PELICULAS Y CATEGORIAS",
                    "peliculas_categorias",
                    entrada);
            
        } catch (Exception e) {
            System.out.println("Error al generar el reporte de peliculas y categorias: "
                    + e.getMessage());
        }

        break;

    // ==========================================
    // REPORTE DE TEXTO DE PELICULAS
    // ==========================================
    case 16:

        try (Connection conexion = Conexion.conectar()) {

            if (conexion == null) {
                System.out.println("No se pudo conectar con la base de datos.");
                break;
            }

            FilmTextDataContext contexto = new FilmTextDataContext(conexion);
            FilmTextController controlador = new FilmTextController(contexto);

            List<FilmText> textos = controlador.listar();

            mostrarOpcionesReporte(
                    textos,
                    "TEXTO DE PELICULAS",
                    "texto_peliculas",
                    entrada);

        } catch (Exception e) {
            System.out.println("Error al generar el reporte de texto de peliculas: "
                    + e.getMessage());
        }

        break;

    // ==========================================
    // REGRESAR AL MENU PRINCIPAL
    // ==========================================
    case 0:

        System.out.println("Regresando al menu principal.");

        break;

    default:

        System.out.println("Opcion no valida.");

        break;

    } // Cierra el switch

} while (opcion != 0); // Cierra el do while

} // Cierra el metodo mostrarMenuReportes


private static void mostrarOpcionesReporte(
        List<?> datos,
        String nombreTabla,
        String nombreArchivo,
        Scanner entrada) {

    int opcionReporte;

    do {

        System.out.println("\n==================================");
        System.out.println("       OPCIONES DEL REPORTE");
        System.out.println("==================================");
        System.out.println("1. Mostrar reporte en pantalla");
        System.out.println("2. Exportar a CSV");
        System.out.println("3. Exportar a JSON");
        System.out.println("0. Regresar");
        System.out.println("==================================");
        System.out.print("Seleccione una opcion: ");

        opcionReporte = entrada.nextInt();
        entrada.nextLine();

        switch (opcionReporte) {

            case 1:

                ReporteTablas reporte = new ReporteTablas();
                reporte.mostrarReporte(nombreTabla, datos);

                break;

            case 2:

                ExportadorCSV exportadorCSV = new ExportadorCSV();

                String rutaCSV = "C:\\Java\\reporte_"
                        + nombreArchivo + ".csv";

                exportadorCSV.exportar(datos, rutaCSV);

                break;

            case 3:

                ExportadorJSON exportadorJSON = new ExportadorJSON();

                String rutaJSON = "C:\\Java\\reporte_"
                        + nombreArchivo + ".json";

                exportadorJSON.exportar(datos, rutaJSON);

                break;

            case 0:

                System.out.println("Regresando al menu de reportes.");

                break;

            default:

                System.out.println("Opcion no valida.");

                break;
        }

    } while (opcionReporte != 0);
}



    // ==========================================
    // MENU DE ESTADISTICAS
    // ==========================================


public static void mostrarMenuEstadisticas() {

    Scanner entrada = new Scanner(System.in);

    int opcion;

    do {

        System.out.println("\n==================================");
        System.out.println("       MENU DE ESTADISTICAS");
        System.out.println("==================================");
        System.out.println("1. Estadisticas de actores");
        System.out.println("2. Estadisticas de alquileres");
        System.out.println("3. Estadisticas de clientes");
        System.out.println("4. Estadisticas de tiendas");
        System.out.println("5. Alquileres por ciudad y pais (GROUP BY MySQL)");
        System.out.println("6. Pagos por ciudad y pais (GROUP BY MySQL)");
        System.out.println("7. Estadisticas de inventario y aging de alquileres");
        System.out.println("0. Volver al menu principal");
        System.out.print("Seleccione una opcion: ");

        opcion = entrada.nextInt();

        switch (opcion) {

            case 1: {

                Connection conexion = Conexion.conectar();

                if (conexion != null) {

                    ActorDataContext datosActores =
                            new ActorDataContext(conexion);

                    FilmActorDataContext datosRelaciones =
                            new FilmActorDataContext(conexion);

                    ActorController actorController =
                            new ActorController(datosActores);

                    FilmActorController filmActorController =
                            new FilmActorController(datosRelaciones);

                    EstadisticasActores estadisticas =
                            new EstadisticasActores();

                    estadisticas.mostrarEstadisticas(
                            actorController.listarActores(),
                            filmActorController.listar()
                    );

                    try {
                        conexion.close();
                    } catch (java.sql.SQLException e) {
                        System.out.println("Error al cerrar la conexion.");
                    }
                }

                break;
            }

            case 2: {

                Connection conexion = Conexion.conectar();

                if (conexion != null) {

                    RentalDataContext datosAlquileres =
                            new RentalDataContext(conexion);

                    RentalController rentalController =
                            new RentalController(datosAlquileres);

                    EstadisticasAlquileres estadisticas =
                            new EstadisticasAlquileres();

                    estadisticas.mostrarEstadisticas(
                            rentalController.listarAlquileres()
                    );

                    try {
                        conexion.close();
                    } catch (java.sql.SQLException e) {
                        System.out.println("Error al cerrar la conexion.");
                    }
                }

                break;
            }

            case 3: {

                Connection conexion = Conexion.conectar();

                if (conexion != null) {

                    CustomerDataContext datosClientes =
                            new CustomerDataContext(conexion);

                    RentalDataContext datosAlquileres =
                            new RentalDataContext(conexion);

                    PaymentDataContext datosPagos =
                            new PaymentDataContext(conexion);

                    CustomerController customerController =
                            new CustomerController(datosClientes);

                    RentalController rentalController =
                            new RentalController(datosAlquileres);

                    PaymentController paymentController =
                            new PaymentController(datosPagos);

                    EstadisticasClientes estadisticas =
                            new EstadisticasClientes();

                    System.out.println("\n==================================");
                    System.out.println("       ESTADISTICAS DE CLIENTES");
                    System.out.println("==================================");
                    System.out.println("1. Alquileres por cliente");
                    System.out.println("2. Pagos por cliente");
                    System.out.println("3. Alquileres por cliente (GROUP BY MySQL)");
                    System.out.println("4. Pagos por cliente (GROUP BY MySQL)");
                    System.out.print("Seleccione una opcion: ");

                    int opcionCliente = entrada.nextInt();

                    switch (opcionCliente) {

                        case 1:

                            estadisticas.mostrarAlquileresPorCliente(
                                    customerController.listarClientes(),
                                    rentalController.listarAlquileres()
                            );

                            break;

                        case 2:

                            estadisticas.mostrarPagosPorCliente(
                                    customerController.listarClientes(),
                                    paymentController.listar()
                            );

                            break;

                        case 3:

                            estadisticas.mostrarAlquileresPorClienteGroupBy(
                                    customerController.listarClientes(),
                                    rentalController.listarAlquileresPorClienteGroupBy()
                            );

                            break;

                        case 4:

                            estadisticas.mostrarPagosPorClienteGroupBy(
                                    customerController.listarClientes(),
                                    paymentController.listarPagosPorClienteGroupBy()
                            );

                            break;

                        default:

                            System.out.println("Opcion no valida.");

                            break;
                    }

                    try {
                        conexion.close();
                    } catch (java.sql.SQLException e) {
                        System.out.println("Error al cerrar la conexion.");
                    }
                }

                break;
            }

            case 4: {

                Connection conexion = Conexion.conectar();

                if (conexion != null) {

                    PaymentDataContext datosPagos =
                            new PaymentDataContext(conexion);

                    PaymentController paymentController =
                            new PaymentController(datosPagos);

                    RentalDataContext datosAlquileres =
                            new RentalDataContext(conexion);

                    RentalController rentalController =
                            new RentalController(datosAlquileres);

                    EstadisticasTiendas estadisticas =
                            new EstadisticasTiendas();

                    System.out.println("\n==================================");
                    System.out.println("       ESTADISTICAS DE TIENDAS");
                    System.out.println("==================================");
                    System.out.println("1. Pagos por tienda (GROUP BY MySQL)");
                    System.out.println("2. Alquileres por tienda (GROUP BY MySQL)");
                    System.out.print("Seleccione una opcion: ");

                    int opcionTienda = entrada.nextInt();

                    switch (opcionTienda) {

                        case 1:

                            estadisticas.mostrarPagosPorTiendaGroupBy(
                                    paymentController.listarPagosPorTiendaGroupBy()
                            );

                            break;

                        case 2:

                            estadisticas.mostrarAlquileresPorTiendaGroupBy(
                                    rentalController.listarAlquileresPorTiendaGroupBy()
                            );

                            break;

                        default:

                            System.out.println("Opcion no valida.");

                            break;
                    }

                    try {
                        conexion.close();
                    } catch (java.sql.SQLException e) {
                        System.out.println("Error al cerrar la conexion.");
                    }
                }

                break;
            }

            case 5: {

                Connection conexion = Conexion.conectar();

                if (conexion != null) {

                    RentalDataContext datosAlquileres =
                            new RentalDataContext(conexion);

                    RentalController rentalController =
                            new RentalController(datosAlquileres);

                    EstadisticasUbicaciones estadisticas =
                            new EstadisticasUbicaciones();

                    estadisticas.mostrarAlquileresPorCiudadPaisGroupBy(
                            rentalController.listarAlquileresPorCiudadPaisGroupBy()
                    );

                    try {
                        conexion.close();
                    } catch (java.sql.SQLException e) {
                        System.out.println("Error al cerrar la conexion.");
                    }
                }

                break;
            }

            case 6: {

                Connection conexion = Conexion.conectar();

                if (conexion != null) {

                    PaymentDataContext datosPagos =
                            new PaymentDataContext(conexion);

                    PaymentController paymentController =
                            new PaymentController(datosPagos);

                    EstadisticasUbicaciones estadisticas =
                            new EstadisticasUbicaciones();

                    estadisticas.mostrarPagosPorCiudadPaisGroupBy(
                            paymentController.listarPagosPorCiudadPaisGroupBy()
                    );

                    try {
                        conexion.close();
                    } catch (java.sql.SQLException e) {
                        System.out.println("Error al cerrar la conexion.");
                    }
                }

                break;
            }

            case 7: {

                Connection conexion = Conexion.conectar();

                if (conexion != null) {

                    // CREAR LOS CONTEXTOS.
                    FilmDataContext datosPeliculas =
                            new FilmDataContext(conexion);

                    InventoryDataContext datosInventario =
                            new InventoryDataContext(conexion);

                    RentalDataContext datosAlquileres =
                            new RentalDataContext(conexion);

                    // CREAR LOS CONTROLADORES.
                    FilmController filmController =
                            new FilmController(datosPeliculas);

                    InventoryController inventoryController =
                            new InventoryController(datosInventario);

                    RentalController rentalController =
                            new RentalController(datosAlquileres);

                    // CREAR LA CLASE DE ESTADISTICAS.
                    EstadisticasInventario estadisticas =
                            new EstadisticasInventario();

                    // MOSTRAR ESTADISTICAS GENERALES.
                    estadisticas.mostrarEstadisticas(
                            filmController.listarPeliculas(),
                            inventoryController.listar()
                    );

                    // MOSTRAR EL RESUMEN GENERAL.
                    estadisticas.mostrarResumenGeneral(
                            inventoryController
                    );

                    // MOSTRAR EL AGING DE ALQUILERES VENCIDOS.
                    estadisticas.mostrarAgingAlquileresVencidos(
                            rentalController
                    );

                    try {
                        conexion.close();
                    } catch (java.sql.SQLException e) {
                        System.out.println("Error al cerrar la conexion.");
                    }
                }

                break;
            }

            case 0:

                System.out.println("Volviendo al menu principal...");

                break;

            default:

                System.out.println("Opcion no valida.");

                break;
        }

    } while (opcion != 0);
  }
}