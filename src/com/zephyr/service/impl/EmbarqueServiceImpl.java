package com.zephyr.service.impl;

import com.zephyr.dao.BoletoDAO;
import com.zephyr.dao.PasajeroDAO;
import com.zephyr.dao.VueloDAO;
import com.zephyr.entity.Boleto;
import com.zephyr.entity.Pasajero;
import com.zephyr.entity.Vuelo;
import com.zephyr.service.EmbarqueService;

import java.util.LinkedList;
import java.util.Optional;

public class EmbarqueServiceImpl implements EmbarqueService {

    private PasajeroDAO pasajeroDAO;
    private BoletoDAO boletoDAO;
    private VueloDAO vueloDAO;

    public EmbarqueServiceImpl(PasajeroDAO pasajeroDAO, VueloDAO vueloDAO, BoletoDAO boletoDAO) {
        this.pasajeroDAO = pasajeroDAO;
        this.boletoDAO = boletoDAO;
        this.vueloDAO = vueloDAO;
    }

    @Override
    public boolean asignarPasajeroAVuelo(int idPasajero, int idVuelo, String asiento) {
        Optional<Pasajero> optPasajero = pasajeroDAO.read(idPasajero);
        Optional<Vuelo> optVuelo = vueloDAO.read(idVuelo);

        if (optPasajero.isEmpty()) {
            System.out.println("[Error] El pasajero con ID " + idPasajero + " no existe.");
            return false;
        }
        if (optVuelo.isEmpty()) {
            System.out.println("[Error] El vuelo con ID " + idVuelo + " no existe.");
            return false;
        }

        Pasajero pasajero = optPasajero.get();
        Vuelo vuelo = optVuelo.get();

        if (!vuelo.getEstado().equals("PROGRAMADO")) {
            System.out.println("[Error] No se puede asignar pasajero. El vuelo esta " + vuelo.getEstado());
            return false;
        }

        if (boletoDAO.isAsientoOcupado(idVuelo, asiento)) {
            System.out.println("[Error] El asiento " + asiento + " ya esta ocupado en este vuelo.");
            return false;
        }
        System.out.println("[Éxito] Asiento " + asiento + " asignado a " + pasajero.getNombre());
        Boleto nuevoBoleto = new Boleto(pasajero, vuelo, asiento);
        boletoDAO.create(nuevoBoleto);

        return true;
    }

    @Override
    public LinkedList<Boleto> obtenerManifiestoVuelo(int idVuelo) {
        return boletoDAO.findByVueloId(idVuelo);
    }
}
