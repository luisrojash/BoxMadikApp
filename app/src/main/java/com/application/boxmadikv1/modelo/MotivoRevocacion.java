package com.application.boxmadikv1.modelo;

import com.application.boxmadikv1.BoxDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = BoxDB.class)
public class MotivoRevocacion extends BaseModel {
    @Column
    @PrimaryKey
    private int Mrev_codigo;
    @Column
    private String Mrev_descripcion;
    @Column
    private String Mrev_estado;

    public MotivoRevocacion() {
    }

    public int getMrev_codigo() {
        return Mrev_codigo;
    }

    public void setMrev_codigo(int mrev_codigo) {
        Mrev_codigo = mrev_codigo;
    }

    public String getMrev_descripcion() {
        return Mrev_descripcion;
    }

    public void setMrev_descripcion(String mrev_descripcion) {
        Mrev_descripcion = mrev_descripcion;
    }

    public String getMrev_estado() {
        return Mrev_estado;
    }

    public void setMrev_estado(String mrev_estado) {
        Mrev_estado = mrev_estado;
    }
}
