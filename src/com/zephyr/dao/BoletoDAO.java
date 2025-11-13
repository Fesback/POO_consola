package com.zephyr.dao;

import com.zephyr.core.Crud;
import com.zephyr.entity.Boleto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class BoletoDAO implements Crud<Boleto> {

    private List<Boleto> dbBoletos = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);


    @Override
    public void create(Boleto obj) {
        obj.setId(idCounter.getAndIncrement());
        dbBoletos.add(obj);
    }

    @Override
    public Optional<Boleto> read(int id) {
        for (Boleto b : dbBoletos) {
            if (b.getId() == id) {
                return Optional.of(b);
            }
        }
        return Optional.empty();
    }

    @Override
    public void update(Boleto obj) {
        for (int i = 0; i < dbBoletos.size(); i++) {
            if (dbBoletos.get(i).getId() == obj.getId()) {
                dbBoletos.set(i, obj);
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        dbBoletos.removeIf(boleto -> boleto.getId() == id);
    }

    @Override
    public LinkedList<Boleto> list() {
        return new LinkedList<>(this.dbBoletos);
    }

    public LinkedList<Boleto> findByVueloId(int idVuelo) {
        LinkedList<Boleto> manifiesto = new LinkedList<>();
        for (Boleto b : dbBoletos) {
            if (b.getVuelo().getId() == idVuelo) {
                manifiesto.add(b);
            }
        }
        return manifiesto;
    }

    public boolean isAsientoOcupado(int idVuelo, String asiento) {
        for (Boleto b : dbBoletos) {
            if (b.getVuelo().getId() == idVuelo &&
                    b.getAsiento().equalsIgnoreCase(asiento)) {
                return true;
            }
        }
        return false;
    }
}

