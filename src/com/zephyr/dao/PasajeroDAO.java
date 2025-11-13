package com.zephyr.dao;

import com.zephyr.core.Crud;
import com.zephyr.entity.Pasajero;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class PasajeroDAO implements Crud<Pasajero> {

    private List<Pasajero> dbPasajeros = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);


    @Override
    public void create(Pasajero obj) {
        obj.setId(idCounter.getAndIncrement());
        dbPasajeros.add(obj);
    }

    @Override
    public Optional<Pasajero> read(int id) {
        for (Pasajero p : dbPasajeros){
            if (p.getId() == id){
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(Pasajero obj) {
        for (int i = 0; i < dbPasajeros.size(); i++) {
            if (dbPasajeros.get(i).getId() == obj.getId()) {
                dbPasajeros.set(i, obj);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        dbPasajeros.removeIf(pasajero -> pasajero.getId() == id);

    }

    @Override
    public LinkedList<Pasajero> list() {
        return new LinkedList<>(this.dbPasajeros);
    }

    public Optional<Pasajero> findByNroDocumento(String nroDocumento) {
        for (Pasajero p : dbPasajeros) {
            if (p.getNroDocumento().equalsIgnoreCase(nroDocumento)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }
}
