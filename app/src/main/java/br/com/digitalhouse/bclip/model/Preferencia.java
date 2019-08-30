package br.com.digitalhouse.bclip.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "preferenciaTable")

public class Preferencia implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private int idPreferencia;

    @ColumnInfo(name = "preferencia")
    private String preferencia;

    @ColumnInfo(name = "ativado")
    private Boolean ativado;


    @ColumnInfo(name = "label")
    private String label;


    public Preferencia(int idPreferencia, String preferencia, Boolean ativado, String label) {
        this.idPreferencia = idPreferencia;
        this.preferencia = preferencia;
        this.ativado = ativado;
        this.label = label;
    }

    public Preferencia() {
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

    public int getIdPreferencia() {
        return idPreferencia;
    }

    public void setIdPreferencia(int idPreferencia) {
        this.idPreferencia = idPreferencia;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
