package com.application.boxmadikv1.modelo;

import com.application.boxmadikv1.BoxDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = BoxDB.class)
public class Oficio extends BaseModel{
    @Column
    @PrimaryKey
    private int Ofi_codigo;
    @Column
    private String Ofi_desc;
    @Column
    private String Ofi_Estado;
    @Column
    private int rubro_Rub_Codigo;
    @Column
    private int pais_Pais_Codigo;
    @Column
    private double monto_oficio;

    public Oficio() {
    }

    public int getOfi_codigo() {
        return Ofi_codigo;
    }

    public void setOfi_codigo(int ofi_codigo) {
        Ofi_codigo = ofi_codigo;
    }

    public String getOfi_desc() {
        return Ofi_desc;
    }

    public void setOfi_desc(String ofi_desc) {
        Ofi_desc = ofi_desc;
    }

    public String getOfi_Estado() {
        return Ofi_Estado;
    }

    public void setOfi_Estado(String ofi_Estado) {
        Ofi_Estado = ofi_Estado;
    }

    public int getRubro_Rub_Codigo() {
        return rubro_Rub_Codigo;
    }

    public void setRubro_Rub_Codigo(int rubro_Rub_Codigo) {
        this.rubro_Rub_Codigo = rubro_Rub_Codigo;
    }

    public int getPais_Pais_Codigo() {
        return pais_Pais_Codigo;
    }

    public void setPais_Pais_Codigo(int pais_Pais_Codigo) {
        this.pais_Pais_Codigo = pais_Pais_Codigo;
    }

    public double getMonto_oficio() {
        return monto_oficio;
    }

    public void setMonto_oficio(double monto_oficio) {
        this.monto_oficio = monto_oficio;
    }
}
