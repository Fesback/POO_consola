package com.zephyr.entity;

public class Rol {
    private int idRol;
    private String nomberRol;
    private String descripcion;

    public Rol(int idRol, String nomberRol, String descripcion) {
        this.idRol = idRol;
        this.nomberRol = nomberRol;
        this.descripcion = descripcion;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNomberRol() {
        return nomberRol;
    }

    public void setNomberRol(String nomberRol) {
        this.nomberRol = nomberRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // imprimir rol en consola

    @Override
    public String toString(){
        return this.nomberRol;
    }
}
