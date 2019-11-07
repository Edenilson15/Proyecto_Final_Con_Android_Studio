package com.eg.himnario;

public class Dto {

    String autor;
    String letra;
    String genero;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Dto() {
        this.autor = autor;
        this.letra = letra;
        this.genero = genero;
    }
}
