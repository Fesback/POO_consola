package com.zephyr.entity;

public class Boleto {
    private int id;
    private Pasajero pasajero;
    private Vuelo vuelo;
    private String asiento;

    // Constructor vacio
    public Boleto() {
    }

    public Boleto(Pasajero pasajero, Vuelo vuelo, String asiento) {
        this.pasajero = pasajero;
        this.vuelo = vuelo;
        this.asiento = asiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    @Override
    public String toString() {
        return "Boleto [" +
                "ID: " + id +
                ", Pasajero: " + pasajero.getNombre() + " " + pasajero.getApellido() +
                ", Vuelo: " + vuelo.getCodigoVuelo() +
                ", Asiento: '" + asiento + '\'' +
                ']';
    }
}
