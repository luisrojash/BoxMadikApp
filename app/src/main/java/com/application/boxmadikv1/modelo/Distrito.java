package com.application.boxmadikv1.modelo;

import com.application.boxmadikv1.BoxDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = BoxDB.class)
public class Distrito extends BaseModel{
    @Column
    @PrimaryKey
    private int Dist_cod;
    @Column
    private String Dist_Desc;
    @Column
    private String Dist_Estado;
    @Column
    @PrimaryKey
    private int provincia_Prov_cod;
    @Column
    @PrimaryKey
    private int provincia_departamento_Depa_Cod;
    @Column
    @PrimaryKey
    private int provincia_departamento_pais_Pais_Codigo;

    public Distrito() {
    }

    public int getDist_cod() {
        return Dist_cod;
    }

    public void setDist_cod(int dist_cod) {
        Dist_cod = dist_cod;
    }

    public String getDist_Desc() {
        return Dist_Desc;
    }

    public void setDist_Desc(String dist_Desc) {
        Dist_Desc = dist_Desc;
    }

    public String getDist_Estado() {
        return Dist_Estado;
    }

    public void setDist_Estado(String dist_Estado) {
        Dist_Estado = dist_Estado;
    }

    public int getProvincia_Prov_cod() {
        return provincia_Prov_cod;
    }

    public void setProvincia_Prov_cod(int provincia_Prov_cod) {
        this.provincia_Prov_cod = provincia_Prov_cod;
    }

    public int getProvincia_departamento_Depa_Cod() {
        return provincia_departamento_Depa_Cod;
    }

    public void setProvincia_departamento_Depa_Cod(int provincia_departamento_Depa_Cod) {
        this.provincia_departamento_Depa_Cod = provincia_departamento_Depa_Cod;
    }

    public int getProvincia_departamento_pais_Pais_Codigo() {
        return provincia_departamento_pais_Pais_Codigo;
    }

    public void setProvincia_departamento_pais_Pais_Codigo(int provincia_departamento_pais_Pais_Codigo) {
        this.provincia_departamento_pais_Pais_Codigo = provincia_departamento_pais_Pais_Codigo;
    }
}
