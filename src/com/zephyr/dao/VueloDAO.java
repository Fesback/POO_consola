package com.zephyr.dao;

import com.zephyr.core.Crud;
import com.zephyr.entity.Vuelo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class VueloDAO implements Crud<Vuelo> {

    private List<Vuelo> dbVuelos = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);


    @Override
    public void create(Vuelo obj) {
        obj.setId(idCounter.getAndIncrement());
        dbVuelos.add(obj);
    }

    @Override
    public Optional<Vuelo> read(int id) {
        for (Vuelo v :dbVuelos){
            if (v.getId() == id) {
                return Optional.of(v);
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(Vuelo obj) {
        for (int i = 0; i < dbVuelos.size(); i++) {
            if (dbVuelos.get(i).getId() == obj.getId()) {
                dbVuelos.set(i, obj);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        dbVuelos.removeIf(vuelo -> vuelo.getId() == id);
    }

    @Override
    public LinkedList<Vuelo> list() {
        return new LinkedList<>(this.dbVuelos);
    }

    public Optional<Vuelo> findByCodigo(String codigoVuelo) {
        for (Vuelo v : dbVuelos) {
            if (v.getCodigoVuelo().equalsIgnoreCase(codigoVuelo)) {
                return Optional.of(v);
            }
        }
        return Optional.empty();
    }
}
