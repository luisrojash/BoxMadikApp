package com.application.boxmadikv1.modelo;

import com.application.boxmadikv1.BoxDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = BoxDB.class)
public class Provincia extends BaseModel {
    @Column
    @PrimaryKey
    private int Provi_codigo;
    @Column
    private int Prov_cod;
    @Column
    private String Prov_Desc;
    @Column
    private String Prov_Estado;
    @Column
    @PrimaryKey
    private int departamento_Depa_Cod;
    @Column
    @PrimaryKey
    private int departamento_pais_Pais_Codigo;

    public Provincia() {
    }

    public int getProv_cod() {
        return Prov_cod;
    }

    public void setProv_cod(int prov_cod) {
        Prov_cod = prov_cod;
    }

    public String getProv_Desc() {
        return Prov_Desc;
    }

    public void setProv_Desc(String prov_Desc) {
        Prov_Desc = prov_Desc;
    }

    public String getProv_Estado() {
        return Prov_Estado;
    }

    public void setProv_Estado(String prov_Estado) {
        Prov_Estado = prov_Estado;
    }

    public int getDepartamento_Depa_Cod() {
        return departamento_Depa_Cod;
    }

    public void setDepartamento_Depa_Cod(int departamento_Depa_Cod) {
        this.departamento_Depa_Cod = departamento_Depa_Cod;
    }

    public int getDepartamento_pais_Pais_Codigo() {
        return departamento_pais_Pais_Codigo;
    }

    public void setDepartamento_pais_Pais_Codigo(int departamento_pais_Pais_Codigo) {
        this.departamento_pais_Pais_Codigo = departamento_pais_Pais_Codigo;
    }

    public int getProvi_codigo() {
        return Provi_codigo;
    }

    public void setProvi_codigo(int provi_codigo) {
        Provi_codigo = provi_codigo;
    }
}

