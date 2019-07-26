package br.com.digitalhouse.bclip.model;

import java.io.Serializable;

public class Preferencia implements Serializable {

    private String preferencia;
    private Boolean ativado;

    public Preferencia(String preferencia, Boolean ativado) {
        this.preferencia = preferencia;
        this.ativado = ativado;
    }

    public String getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(String preferencia) {
        this.preferencia = preferencia;
    }

    public Boolean getAtivado() {
        return ativado;
    }

    public void setAtivado(Boolean ativado) {
        this.ativado = ativado;
    }
}
