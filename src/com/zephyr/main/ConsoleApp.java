package com.zephyr.main;

import com.zephyr.dao.BoletoDAO;
import com.zephyr.dao.PasajeroDAO;
import com.zephyr.dao.PersonalDAO;
import com.zephyr.dao.VueloDAO;
import com.zephyr.entity.*;
import com.zephyr.service.AuthService;
import com.zephyr.service.EmbarqueService;
import com.zephyr.service.impl.AuthServiceImpl;
import com.zephyr.service.impl.EmbarqueServiceImpl;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PersonalDAO personalDAO = new PersonalDAO();
    private static final AuthService authService = new AuthServiceImpl(personalDAO);

    private static final VueloDAO vueloDAO = new VueloDAO();
    private static final PasajeroDAO pasajeroDAO = new PasajeroDAO();
    private static final BoletoDAO boletoDAO = new BoletoDAO();
    private static final EmbarqueService embarqueService = new EmbarqueServiceImpl(pasajeroDAO, vueloDAO, boletoDAO);


    public static void main(String[] args) {

        System.out.println("----- SISTEMA DE GESTIÓN ZEPHYR (Consola T1) -----");

        Optional<Personal> personalLogueado = handleLogin();

        if (personalLogueado.isPresent()) {
            Personal personal = personalLogueado.get();
            System.out.println("\n-------------------------------------");
            System.out.println("Bienvenido, " + personal.getNombres() + "!");
            System.out.println("Tu Rol es: " + personal.getRol().getNomberRol());
            System.out.println("-------------------------------------");

            if (personal.getRol().getNomberRol().equals("Administrador")) {
                mostrarMenuAdmin();
            } else {
                mostrarMenuAgente();
            }

        } else {
            System.out.println("\n-------------------------------------");
            System.out.println("ERROR: Correo o contrasena incorrectos.");
            System.out.println("-------------------------------------");
        }

        scanner.close();
        System.out.println("Saliendo del sistema...");
    }

    private static Optional<Personal> handleLogin() {
        System.out.print("Ingrese su correo: ");
        String correo = scanner.nextLine();
        System.out.print("Ingrese su contrasena: ");
        String contrasena = scanner.nextLine();

        return authService.login(correo, contrasena);
    }

    private static void mostrarMenuAdmin() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n----- Menú de Administrador (CRUD) -----");
            System.out.println("--- Gestion Personal ---");
            System.out.println("1. Listar Personal");
            System.out.println("2. Registrar Agente");
            System.out.println("3. Actualizar Agente");
            System.out.println("4. Eliminar Agente");
            System.out.println("--- Gestión Vuelos ---");
            System.out.println("5. Crear Vuelo");
            System.out.println("6. Listar Vuelos");
            System.out.println("--- Gestión Pasajeros ---");
            System.out.println("7. Crear Pasajero");
            System.out.println("8. Listar Pasajeros");
            System.out.println("--- Servicios de Embarque ---");
            System.out.println("9. Asignar Pasajero a Vuelo");
            System.out.println("10. Ver Manifiesto de Vuelo");
            System.out.println("-------------------------------------------");
            System.out.println("11. Salir");
            System.out.print("Seleccione una opcion: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    listarPersonal();
                    break;
                case "2":
                    registrarAgente();
                    break;
                case "3":
                    actualizarAgente();
                    break;
                case "4":
                    eliminarAgente();
                    break;
                case "5":
                    handleCrearVuelo();
                    break;
                case "6":
                    handleListarVuelos();
                    break;
                case "7":
                    handleCrearPasajero();
                    break;
                case "8":
                    handleListarPasajeros();
                    break;
                case "9":
                    handleAsignarPasajero();
                    break;
                case "10":
                    handleVerManifiesto();
                    break;
                case "11":
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        }
    }

    private static void registrarAgente() {
        System.out.println("\n----- Registro de Nuevo Agente -----");
        System.out.print("Nombres: ");
        String nombres = scanner.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();

        String pass = generarPasswordAleatorio(8);
        Rol rolAgente = new Rol(3, "Agente", "Rol de agente");

        Personal nuevoAgente = new Personal(0, nombres, apellidos, dni, correo, pass, rolAgente);

        personalDAO.create(nuevoAgente);
        System.out.println("\nAgente registrado!");
        System.out.println("La contrasena temporal generada es: " + pass);
    }

    private static void listarPersonal() {
        System.out.println("\n----- Lista de Personal (LinkedList) -----");
        LinkedList<Personal> personalLista = personalDAO.list();

        if (personalLista.isEmpty()) {
            System.out.println("No hay personal registrado.");
            return;
        }

        for (Personal p : personalLista) {
            System.out.println(p);
        }
    }

    private static void actualizarAgente() {
        System.out.println("\n----- Actualizar Agente -----");
        System.out.print("Ingrese el ID del agente a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Optional<Personal> personalOpt = personalDAO.read(id);

        if (personalOpt.isEmpty()) {
            System.out.println("ERROR: No se encontro ningun agente con el ID: " + id);
            return;
        }

        Personal personalParaActualizar = personalOpt.get();
        System.out.println("Actualizando a: " + personalParaActualizar.getNombres());

        System.out.print("Ingrese el nuevo correo: ");
        String nuevoCorreo = scanner.nextLine();
        System.out.print("Ingrese el nuevo DNI: ");
        String nuevoDni = scanner.nextLine();

        personalParaActualizar.setCorreo(nuevoCorreo);
        personalParaActualizar.setDni(nuevoDni);

        personalDAO.update(personalParaActualizar);

        System.out.println("\nAgente actualizado!");
    }

    private static void eliminarAgente() {
        System.out.println("\n----- Eliminar Agente -----");
        System.out.print("Ingrese el ID del agente a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        personalDAO.delete(id);
    }

    private static void mostrarMenuAgente() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n----- Menú de Agente ----");
            System.out.println("1. Asignar Pasajero a Vuelo (Servicio)");
            System.out.println("2. Ver Manifiesto de Vuelo (Servicio)");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    handleAsignarPasajero();
                    break;
                case "2":
                    handleVerManifiesto();
                    break;
                case "3":
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no volida intente de nuevo.");

            }
        }
    }

    private static void handleCrearVuelo() {
        System.out.println("\n----- Crear Nuevo Vuelo -----");
        System.out.print("Codigo (ej. ZEP-001): ");
        String codigo = scanner.nextLine();
        System.out.print("Origen: ");
        String origen = scanner.nextLine();
        System.out.print("Destino: ");
        String destino = scanner.nextLine();

        Vuelo nuevoVuelo = new Vuelo(codigo, origen, destino, null);

        vueloDAO.create(nuevoVuelo);

        System.out.println("Vuelo creado con ID " + nuevoVuelo.getId() + "!");
    }

    private static void handleListarVuelos() {
        System.out.println("\n----- Lista de Vuelos -----");
        LinkedList<Vuelo> vuelos = vueloDAO.list();

        if (vuelos.isEmpty()) {
            System.out.println("No hay vuelos registrados.");
            return;
        }

        for (Vuelo v : vuelos) {
            System.out.println(v.toString());
        }
    }


    private static void handleCrearPasajero() {
        System.out.println("\n----- Crear Nuevo Pasajero -----");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Tipo Documento (DNI/Pasaporte): ");
        String tipoDoc = scanner.nextLine();
        System.out.print("Numero Documento: ");
        String nroDoc = scanner.nextLine();

        Pasajero nuevoPasajero = new Pasajero(nombre, apellido, tipoDoc, nroDoc);
        pasajeroDAO.create(nuevoPasajero);

        System.out.println("Pasajero creado con ID " + nuevoPasajero.getId() + "!");
    }

    private static void handleListarPasajeros() {
        System.out.println("\n----- Lista de Pasajeros -----");

        LinkedList<Pasajero> pasajeros = pasajeroDAO.list();

        if (pasajeros.isEmpty()) {
            System.out.println("No hay pasajeros registrados.");
            return;
        }

        for (Pasajero p : pasajeros) {
            System.out.println(p.toString());
        }
    }

    private static void handleAsignarPasajero() {
        System.out.println("\n----- Asignar Pasajero a Vuelo (Servicio) -----");
        System.out.print("Ingrese ID del Pasajero: ");
        int idPasajero = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese ID del Vuelo: ");
        int idVuelo = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese Asiento (ej. A23): ");
        String asiento = scanner.nextLine();

        boolean exito = embarqueService.asignarPasajeroAVuelo(idPasajero, idVuelo, asiento);

        if (exito) {
            System.out.println("¡Asignacion exitosa!");
        } else {
            System.out.println("No se pudo completar la asignación.");
        }
    }

    private static void handleVerManifiesto() {
        System.out.println("\n----- Ver Manifiesto de Vuelo (Servicio) -----");
        System.out.print("Ingrese ID del Vuelo: ");
        int idVuelo = Integer.parseInt(scanner.nextLine());

        LinkedList<Boleto> manifiesto = embarqueService.obtenerManifiestoVuelo(idVuelo);

        if (manifiesto.isEmpty()) {
            System.out.println("Este vuelo no tiene pasajeros asignados.");
        } else {
            System.out.println("Pasajeros en el vuelo ID " + idVuelo + ":");
            for (Boleto b : manifiesto) {
                System.out.println(b.toString());
            }
        }
    }

    //aleatoriedad
    private static String generarPasswordAleatorio(int longitud) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(longitud);
        java.util.Random random = new java.util.Random();

        for (int i = 0; i < longitud; i++) {
            int index = random.nextInt(caracteres.length());
            sb.append(caracteres.charAt(index));
        }

        return sb.toString();
    }
}
