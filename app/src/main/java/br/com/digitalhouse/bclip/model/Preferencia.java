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


    public Preferencia(int idPreferencia, String preferencia, Boolean ativado) {
        this.idPreferencia = idPreferencia;
        this.preferencia = preferencia;
        this.ativado = ativado;
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
}
