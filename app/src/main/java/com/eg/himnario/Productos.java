package com.eg.himnario;

public class Productos {
    int codigo;
    String letra;
    String Actor;
    String genero;
    String imagen;

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Productos(int codigo, String letra,String actor, String genero, String imagen) {
        this.codigo = codigo;
        this.letra= descripcion;
        Actor = actor;
        this.genero = genero;
    }
}
