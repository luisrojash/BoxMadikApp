package com.application.boxmadikv1.modelo;

import com.application.boxmadikv1.BoxDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = BoxDB.class)
public class SolucionRevocacion extends BaseModel {
    @Column
    @PrimaryKey
    private int Srev_codigo;
    @Column
    private String Srev_descripcion;
    @Column
    private String Srev_estado;

    public SolucionRevocacion() {
    }

    public int getSrev_codigo() {
        return Srev_codigo;
    }

    public void setSrev_codigo(int srev_codigo) {
        Srev_codigo = srev_codigo;
    }

    public String getSrev_descripcion() {
        return Srev_descripcion;
    }

    public void setSrev_descripcion(String srev_descripcion) {
        Srev_descripcion = srev_descripcion;
    }

    public String getSrev_estado() {
        return Srev_estado;
    }

    public void setSrev_estado(String srev_estado) {
        Srev_estado = srev_estado;
    }
}
