package com.application.boxmadikv1.modelo;

import com.application.boxmadikv1.BoxDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = BoxDB.class)
public class Pais extends BaseModel {
    @Column
    @PrimaryKey
    private int Pais_Codigo;
    @Column
    private String Pais_Nombre;
    @Column
    private String Pais_estado;
    @Column
    private String Pais_Bandera;
    @Column
    private String TMon_simbolo;
    @Column
    private String TMon_iso;
    @Column
    private String Pais_Imagen;

    public Pais() {
    }

    public int getPais_Codigo() {
        return Pais_Codigo;
    }

    public void setPais_Codigo(int pais_Codigo) {
        Pais_Codigo = pais_Codigo;
    }

    public String getPais_Nombre() {
        return Pais_Nombre;
    }

    public void setPais_Nombre(String pais_Nombre) {
        Pais_Nombre = pais_Nombre;
    }

    public String getPais_estado() {
        return Pais_estado;
    }

    public void setPais_estado(String pais_estado) {
        Pais_estado = pais_estado;
    }

    public String getPais_Bandera() {
        return Pais_Bandera;
    }

    public void setPais_Bandera(String pais_Bandera) {
        Pais_Bandera = pais_Bandera;
    }

    public String getTMon_simbolo() {
        return TMon_simbolo;
    }

    public void setTMon_simbolo(String TMon_simbolo) {
        this.TMon_simbolo = TMon_simbolo;
    }

    public String getTMon_iso() {
        return TMon_iso;
    }

    public void setTMon_iso(String TMon_iso) {
        this.TMon_iso = TMon_iso;
    }

    public String getPais_Imagen() {
        return Pais_Imagen;
    }

    public void setPais_Imagen(String pais_Imagen) {
        Pais_Imagen = pais_Imagen;
    }
}
