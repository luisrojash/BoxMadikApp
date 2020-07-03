package com.application.boxmadikv1.modelo;

import com.application.boxmadikv1.BoxDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
@Table(database = BoxDB.class)
public class RangoTurno extends BaseModel {
    @Column
    @PrimaryKey
    private int Rat_Item;
    @Column
    private String Rat_descripcion;
    @Column
    private String Rat_Estado;

    public RangoTurno() {
    }

    public int getRat_Item() {
        return Rat_Item;
    }

    public void setRat_Item(int rat_Item) {
        Rat_Item = rat_Item;
    }

    public String getRat_descripcion() {
        return Rat_descripcion;
    }

    public void setRat_descripcion(String rat_descripcion) {
        Rat_descripcion = rat_descripcion;
    }

    public String getRat_Estado() {
        return Rat_Estado;
    }

    public void setRat_Estado(String rat_Estado) {
        Rat_Estado = rat_Estado;
    }
}
