package com.eg.himnario;

public class Productos {
    int codigo;
    String descripcion;
    String Actor;
    String genero;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getActor() {
        return Actor;
    }

    public void setActor(String actor) {
        Actor = actor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Productos(int codigo, String descripcion, String actor, String genero) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        Actor = actor;
        this.genero = genero;
    }
}
