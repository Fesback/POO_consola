package com.zephyr.service;

import com.zephyr.entity.Boleto;

import java.util.LinkedList;

public interface EmbarqueService {
    boolean asignarPasajeroAVuelo(int idPasajero, int idVuelo, String asiento);
    LinkedList<Boleto> obtenerManifiestoVuelo(int idVuelo);

}
