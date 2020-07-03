package com.application.boxmadikv1.modelo;

import com.application.boxmadikv1.BoxDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = BoxDB.class)
public class Departamento extends BaseModel {
    @Column
    @PrimaryKey
    private int Depa_Cod;
    @Column
    private String Depa_Desc;
    @Column
    private String Depa_Estado;
    @Column
    private int pais_Pais_Codigo;

    public Departamento() {
    }

    public int getDepa_Cod() {
        return Depa_Cod;
    }

    public void setDepa_Cod(int depa_Cod) {
        Depa_Cod = depa_Cod;
    }

    public String getDepa_Desc() {
        return Depa_Desc;
    }

    public void setDepa_Desc(String depa_Desc) {
        Depa_Desc = depa_Desc;
    }

    public String getDepa_Estado() {
        return Depa_Estado;
    }

    public void setDepa_Estado(String depa_Estado) {
        Depa_Estado = depa_Estado;
    }

    public int getPais_Pais_Codigo() {
        return pais_Pais_Codigo;
    }

    public void setPais_Pais_Codigo(int pais_Pais_Codigo) {
        this.pais_Pais_Codigo = pais_Pais_Codigo;
    }
}
