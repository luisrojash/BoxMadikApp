package com.application.boxmadikv1.modelo;

import com.application.boxmadikv1.BoxDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = BoxDB.class)
public class Rubro extends BaseModel{
    @Column
    @PrimaryKey
    private int Rub_Codigo;
    @Column
    private String Rub_Desc;
    @Column
    private String Rub_Abre;
    @Column
    private String Rub_Estado;
    @Column
    private String Rub_Imagen;
    @Column
    private String Rub_presencial;
    @Column
    private String pais_Pais_Codigo;

    public Rubro() {
    }

    public int getRub_Codigo() {
        return Rub_Codigo;
    }

    public void setRub_Codigo(int rub_Codigo) {
        Rub_Codigo = rub_Codigo;
    }

    public String getRub_Desc() {
        return Rub_Desc;
    }

    public void setRub_Desc(String rub_Desc) {
        Rub_Desc = rub_Desc;
    }

    public String getRub_Abre() {
        return Rub_Abre;
    }

    public void setRub_Abre(String rub_Abre) {
        Rub_Abre = rub_Abre;
    }

    public String getRub_Estado() {
        return Rub_Estado;
    }

    public void setRub_Estado(String rub_Estado) {
        Rub_Estado = rub_Estado;
    }

    public String getRub_Imagen() {
        return Rub_Imagen;
    }

    public void setRub_Imagen(String rub_Imagen) {
        Rub_Imagen = rub_Imagen;
    }

    public String getRub_presencial() {
        return Rub_presencial;
    }

    public void setRub_presencial(String rub_presencial) {
        Rub_presencial = rub_presencial;
    }

    public String getPais_Pais_Codigo() {
        return pais_Pais_Codigo;
    }

    public void setPais_Pais_Codigo(String pais_Pais_Codigo) {
        this.pais_Pais_Codigo = pais_Pais_Codigo;
    }
}
