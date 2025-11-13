package com.zephyr.entity;

public class Personal {

    private int idPersonal;
    private String nombres;
    private String apellidos;
    private String dni;
    private String correo;
    private String contrasena;

    private Rol rol;

    public Personal(int idPersonal, String nombres, String apellidos, String dni, String correo, String contrasena, Rol rol) {
        this.idPersonal = idPersonal;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public int getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    // para imprimir en consola

    @Override
    public String toString() {
        return "ID: " + idPersonal +
                " | Nombre: " + nombres + " " + apellidos +
                " | Correo: " +  correo +
                " | Rol: " + rol.getNomberRol();
    }
}
