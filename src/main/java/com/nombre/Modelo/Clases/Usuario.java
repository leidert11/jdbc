
package com.nombre.jdbc.Modelo.Clases;

public class Usuario {

    private int idusers;
    private String name;

    public Usuario(int idusers, String name) {
        this.idusers = idusers;
        this.name = name;
    }

    public Usuario(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdusers() {
        return idusers;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idusers=" + idusers + ", name=" + name + '}';
    }

}
