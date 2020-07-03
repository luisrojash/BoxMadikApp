package com.application.boxmadikv1.modelo;

import com.application.boxmadikv1.BoxDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = BoxDB.class)
public class RangoDias extends BaseModel {
    @Column
    @PrimaryKey
    private int Rad_Item;
    @Column
    private String Rad_descripcion;
    @Column
    private String Rad_Estado;

    public RangoDias() {
    }

    public int getRad_Item() {
        return Rad_Item;
    }

    public void setRad_Item(int rad_Item) {
        Rad_Item = rad_Item;
    }

    public String getRad_descripcion() {
        return Rad_descripcion;
    }

    public void setRad_descripcion(String rad_descripcion) {
        Rad_descripcion = rad_descripcion;
    }

    public String getRad_Estado() {
        return Rad_Estado;
    }

    public void setRad_Estado(String rad_Estado) {
        Rad_Estado = rad_Estado;
    }
}
