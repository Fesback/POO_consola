package com.zephyr.dao;

import com.zephyr.core.Crud;
import com.zephyr.entity.Personal;
import com.zephyr.entity.Rol;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class PersonalDAO implements Crud<Personal> {

    private final List<Personal> baseDeDatosSimulada;
    private int idCounter = 0;

    public PersonalDAO() {
        this.baseDeDatosSimulada = new ArrayList<>();

        // creo roles de prueba
        Rol rolAdmin = new Rol(1, "Administrador", "Rol de admin");
        Rol rolSupervisor = new Rol(2, "Supervisor", "Rol de supervisor");
        Rol rolAgente = new Rol(3, "Agente", "Rol de agente");

        //agreo users de prueb
        this.create(new Personal(0, "Admin", "Zephyr", "12345678", "admin@zephyr.com", "admin123", rolAdmin));
        this.create(new Personal(0, "Supervisor", "Smith", "87654321", "super@zephyr.com", "super123", rolSupervisor));
        this.create(new Personal(0, "Agente", "Jones", "11112222", "agente@zephyr.com", "agente123", rolAgente));
    }

    @Override
    public void create(Personal obj) {
        this.idCounter++;
        obj.setIdPersonal(this.idCounter);
        this.baseDeDatosSimulada.add(obj);
        System.out.println("[DAO] Personal " + obj.getNombres() + " (ID=" + obj.getIdPersonal() + ") guardado.");
    }

    @Override
    public Optional<Personal> read(int id) {
        for (Personal personal : this.baseDeDatosSimulada) {
            if (personal.getIdPersonal() == id) {
                return Optional.of(personal);
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(Personal obj) {
        for (int i = 0; i < this.baseDeDatosSimulada.size(); i++) {
            Personal p = this.baseDeDatosSimulada.get(i);
            if (p.getIdPersonal() == obj.getIdPersonal()) {
                this.baseDeDatosSimulada.set(i, obj);
                System.out.println("[DAO] Personal ID=" + p.getIdPersonal() + " actualizado.");
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        boolean removed = this.baseDeDatosSimulada.removeIf(personal -> personal.getIdPersonal() == id);

        if (removed) {
            System.out.println("[DAO] Personal ID=" + id + " eliminado.");
        } else {
            System.out.println("[DAO] No se encontró Personal ID=" + id + " para eliminar.");
        }
    }

    @Override
    public LinkedList<Personal> list() {
        return new LinkedList<>(this.baseDeDatosSimulada);
    }

    // para el login
    public Optional<Personal> findByCorreo(String correo) {
        for (Personal personal : this.baseDeDatosSimulada) {
            if (personal.getCorreo().equalsIgnoreCase(correo)) {
                return Optional.of(personal);
            }
        }
        return Optional.empty();
    }
}
