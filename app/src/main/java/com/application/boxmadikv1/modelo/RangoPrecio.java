package com.application.boxmadikv1.modelo;

import com.application.boxmadikv1.BoxDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = BoxDB.class)
public class RangoPrecio extends BaseModel{
    @Column
    @PrimaryKey
    private int Ran_Item;
    @Column
    private String Ran_descripcion;
    @Column
    private String Ran_Estado;
    @Column
    private double Ran_Monto1;
    @Column
    private double Ran_Monto2;
    @Column
    private int pais_Pais_Codigo;
    @Column
    private String TMon_Simbolo;
    @Column
    private String TMon_ISO;

    public RangoPrecio() {
    }

    public int getRan_Item() {
        return Ran_Item;
    }

    public void setRan_Item(int ran_Item) {
        Ran_Item = ran_Item;
    }

    public String getRan_descripcion() {
        return Ran_descripcion;
    }

    public void setRan_descripcion(String ran_descripcion) {
        Ran_descripcion = ran_descripcion;
    }

    public String getRan_Estado() {
        return Ran_Estado;
    }

    public void setRan_Estado(String ran_Estado) {
        Ran_Estado = ran_Estado;
    }

    public double getRan_Monto1() {
        return Ran_Monto1;
    }

    public void setRan_Monto1(double ran_Monto1) {
        Ran_Monto1 = ran_Monto1;
    }

    public double getRan_Monto2() {
        return Ran_Monto2;
    }

    public void setRan_Monto2(double ran_Monto2) {
        Ran_Monto2 = ran_Monto2;
    }

    public int getPais_Pais_Codigo() {
        return pais_Pais_Codigo;
    }

    public void setPais_Pais_Codigo(int pais_Pais_Codigo) {
        this.pais_Pais_Codigo = pais_Pais_Codigo;
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
}
