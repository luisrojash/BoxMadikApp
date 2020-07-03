package com.application.boxmadikv1.modelo;

import com.application.boxmadikv1.BoxDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = BoxDB.class)
public class TipoCambio extends BaseModel {
    @Column
    @PrimaryKey
    private String Tc_Item;
    @Column
    private String Tc_Fecha;
    @Column
    private double Tc_Valor;
    @Column
    private String pais_Pais_Codigo;

    public TipoCambio() {
    }

    public String getTc_Item() {
        return Tc_Item;
    }

    public void setTc_Item(String tc_Item) {
        Tc_Item = tc_Item;
    }

    public String getTc_Fecha() {
        return Tc_Fecha;
    }

    public void setTc_Fecha(String tc_Fecha) {
        Tc_Fecha = tc_Fecha;
    }

    public double getTc_Valor() {
        return Tc_Valor;
    }

    public void setTc_Valor(double tc_Valor) {
        Tc_Valor = tc_Valor;
    }

    public String getPais_Pais_Codigo() {
        return pais_Pais_Codigo;
    }

    public void setPais_Pais_Codigo(String pais_Pais_Codigo) {
        this.pais_Pais_Codigo = pais_Pais_Codigo;
    }
}
