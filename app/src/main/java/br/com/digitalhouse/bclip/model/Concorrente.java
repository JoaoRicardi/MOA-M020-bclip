package br.com.digitalhouse.bclip.model;

import java.io.Serializable;

public class Concorrente implements Serializable {

    private String nomeConcorrente;

    public Concorrente(String nomeConcorrente) {
        this.nomeConcorrente = nomeConcorrente;
    }

    public String getNomeConcorrente() {
        return nomeConcorrente;
    }

    public void setNomeConcorrente(String nomeConcorrente) {
        this.nomeConcorrente = nomeConcorrente;
    }
}
