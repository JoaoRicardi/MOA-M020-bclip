package br.com.digitalhouse.bclip.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "preferenciaEmpresas")

public class PreferenciaEmpresas implements Serializable {



    @PrimaryKey(autoGenerate = true)
    private int idPreferenciaEmpresa;

    @ColumnInfo(name = "preferenciaEmpresa" )
    private String preferenciaEmpresa;

    @ColumnInfo(name = "ativado")
    private Boolean ativado;


    public PreferenciaEmpresas(int idPreferenciaEmpresa, String preferenciaEmpresa, Boolean ativado) {
        this.idPreferenciaEmpresa = idPreferenciaEmpresa;
        this.preferenciaEmpresa = preferenciaEmpresa;
        this.ativado = ativado;
    }

    public PreferenciaEmpresas() {

    }


    public String getPreferenciaEmpresa() {
        return preferenciaEmpresa;
    }

    public void setPreferenciaEmpresa(String preferenciaEmpresa) {
        this.preferenciaEmpresa = preferenciaEmpresa;
    }

    public Boolean getAtivado() {
        return ativado;
    }

    public void setAtivado(Boolean ativado) {
        this.ativado = ativado;
    }

    public int getIdPreferenciaEmpresa() {
        return idPreferenciaEmpresa;
    }

    public void setIdPreferenciaEmpresa(int idPreferenciaEmpresa) {
        this.idPreferenciaEmpresa = idPreferenciaEmpresa;
    }
}
