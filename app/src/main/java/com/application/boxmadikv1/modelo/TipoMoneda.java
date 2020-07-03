package com.application.boxmadikv1.modelo;

import com.application.boxmadikv1.BoxDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = BoxDB.class)
public class TipoMoneda extends BaseModel{
    @Column
    @PrimaryKey
    private int TMon_Codigo;
    @Column
    private String TMon_Desc;
    @Column
    private String TMon_Estado;
    @Column
    private String TMon_Simbolo;
    @Column
    private String TMon_ISO;
    @Column
    private int pais_Pais_Codigo;

    public TipoMoneda() {
    }

    public int getTMon_Codigo() {
        return TMon_Codigo;
    }

    public void setTMon_Codigo(int TMon_Codigo) {
        this.TMon_Codigo = TMon_Codigo;
    }

    public String getTMon_Desc() {
        return TMon_Desc;
    }

    public void setTMon_Desc(String TMon_Desc) {
        this.TMon_Desc = TMon_Desc;
    }

    public String getTMon_Estado() {
        return TMon_Estado;
    }

    public void setTMon_Estado(String TMon_Estado) {
        this.TMon_Estado = TMon_Estado;
    }

    public String getTMon_Simbolo() {
        return TMon_Simbolo;
    }

    public void setTMon_Simbolo(String TMon_Simbolo) {
        this.TMon_Simbolo = TMon_Simbolo;
    }

    public String getTMon_ISO() {
        return TMon_ISO;
    }

    public void setTMon_ISO(String TMon_ISO) {
        this.TMon_ISO = TMon_ISO;
    }

    public int getPais_Pais_Codigo() {
        return pais_Pais_Codigo;
    }

    public void setPais_Pais_Codigo(int pais_Pais_Codigo) {
        this.pais_Pais_Codigo = pais_Pais_Codigo;
    }
}
