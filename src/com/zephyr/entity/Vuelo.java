package com.zephyr.entity;

import java.time.LocalDateTime;

public class Vuelo {
    private int id;
    private String codigoVuelo;
    private String origen;
    private String destino;
    private LocalDateTime fechaHoraSalida;
    private String estado;

    // vacio
    public Vuelo() {
    }

    public Vuelo(String codigoVuelo, String origen, String destino, LocalDateTime fechaHoraSalida) {
        this.codigoVuelo = codigoVuelo;
        this.origen = origen;
        this.destino = destino;
        this.fechaHoraSalida = fechaHoraSalida;
        this.estado = "PROGRAMADO";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public void setCodigoVuelo(String codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Vuelo [" +
                "ID: " + id +
                ", Codigo: '" + codigoVuelo + '\'' +
                ", Origen: '" + origen + '\'' +
                ", Destino: '" + destino + '\'' +
                ", Salida: " + fechaHoraSalida +
                ", Estado: '" + estado + '\'' +
                ']';
    }
}
