package com.eg.himnario;

public class Productos {
    int codigo;
    String letra;
    String autor;
    String nombre;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    String imagen;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Productos(int codigo, String letra, String autor, String nombre) {
        this.codigo = codigo;
        this.letra = letra;
        this.autor = autor;
        this.nombre = nombre;
    }
}