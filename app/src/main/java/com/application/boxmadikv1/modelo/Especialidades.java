package com.application.boxmadikv1.modelo;

import com.application.boxmadikv1.BoxDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = BoxDB.class)
public class Especialidades extends BaseModel {
    @Column
    @PrimaryKey
    private int Espe_codigo;
    @Column
    private String Espe_desc;
    @Column
    private String Espe_Estado;
    @Column
    private int oficio_Ofi_codigo;
    @Column
    private int oficio_rubro_Rub_Codigo;
    @Column
    private int pais_Pais_Codigo;

    public Especialidades() {
    }

    public int getEspe_codigo() {
        return Espe_codigo;
    }

    public void setEspe_codigo(int espe_codigo) {
        Espe_codigo = espe_codigo;
    }

    public String getEspe_desc() {
        return Espe_desc;
    }

    public void setEspe_desc(String espe_desc) {
        Espe_desc = espe_desc;
    }

    public String getEspe_Estado() {
        return Espe_Estado;
    }

    public void setEspe_Estado(String espe_Estado) {
        Espe_Estado = espe_Estado;
    }

    public int getOficio_Ofi_codigo() {
        return oficio_Ofi_codigo;
    }

    public void setOficio_Ofi_codigo(int oficio_Ofi_codigo) {
        this.oficio_Ofi_codigo = oficio_Ofi_codigo;
    }

    public int getOficio_rubro_Rub_Codigo() {
        return oficio_rubro_Rub_Codigo;
    }

    public void setOficio_rubro_Rub_Codigo(int oficio_rubro_Rub_Codigo) {
        this.oficio_rubro_Rub_Codigo = oficio_rubro_Rub_Codigo;
    }

    public int getPais_Pais_Codigo() {
        return pais_Pais_Codigo;
    }

    public void setPais_Pais_Codigo(int pais_Pais_Codigo) {
        this.pais_Pais_Codigo = pais_Pais_Codigo;
    }
}
