package com.application.boxmadikv1.modelo;

import com.application.boxmadikv1.BoxDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = BoxDB.class)
public class Banco extends BaseModel {
    @Column
    @PrimaryKey
    private int Ban_Codigo;
    @Column
    private String Ban_Desc;
    @Column
    private String Ban_Estado;
    @Column
    private int pais_Pais_Codigo;
    @Column
    private String Ban_Desc_corta;

    public Banco() {
    }

    public int getBan_Codigo() {
        return Ban_Codigo;
    }

    public void setBan_Codigo(int ban_Codigo) {
        Ban_Codigo = ban_Codigo;
    }

    public String getBan_Desc() {
        return Ban_Desc;
    }

    public void setBan_Desc(String ban_Desc) {
        Ban_Desc = ban_Desc;
    }

    public String getBan_Estado() {
        return Ban_Estado;
    }

    public void setBan_Estado(String ban_Estado) {
        Ban_Estado = ban_Estado;
    }

    public int getPais_Pais_Codigo() {
        return pais_Pais_Codigo;
    }

    public void setPais_Pais_Codigo(int pais_Pais_Codigo) {
        this.pais_Pais_Codigo = pais_Pais_Codigo;
    }

    public String getBan_Desc_corta() {
        return Ban_Desc_corta;
    }

    public void setBan_Desc_corta(String ban_Desc_corta) {
        Ban_Desc_corta = ban_Desc_corta;
    }
}
