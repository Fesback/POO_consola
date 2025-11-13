package com.zephyr.entity;

public class Pasajero {
    private int id;
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String nroDocumento;

    // Constructor vacio
    public Pasajero() {
    }

    public Pasajero(String nombre, String apellido, String tipoDocumento, String nroDocumento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    @Override
    public String toString() {
        return "Pasajero [" +
                "ID: " + id +
                ", Nombre: '" + nombre + " " + apellido + '\'' +
                ", Documento: " + tipoDocumento + " " + nroDocumento +
                ']';
    }
}
