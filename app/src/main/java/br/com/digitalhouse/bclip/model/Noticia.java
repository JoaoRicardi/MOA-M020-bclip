package br.com.digitalhouse.bclip.model;

import java.io.Serializable;

public class Noticia implements Serializable {

    private int fotoMateria;
    private String tituloMateria;
    private String descricaoMateria;

    public int getFotoMateria() {
        return fotoMateria;
    }

    public void setFotoMateria(int fotoMateria) {
        this.fotoMateria = fotoMateria;
    }

    public String getTituloMateria() {
        return tituloMateria;
    }

    public void setTituloMateria(String tituloMateria) {
        this.tituloMateria = tituloMateria;
    }

    public String getDescricaoMateria() {
        return descricaoMateria;
    }

    public void setDescricaoMateria(String descricaoMateria) {
        this.descricaoMateria = descricaoMateria;
    }

}
