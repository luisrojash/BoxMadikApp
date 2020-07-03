package com.application.boxmadikv1.modelo;

import com.application.boxmadikv1.BoxDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = BoxDB.class)
public class CalidadTrabajo extends BaseModel {
    @Column
    @PrimaryKey
    private int Ctra_codigo;
    @Column
    private String Ctra_descripcion;
    @Column
    private String Ctra_estado;

    public CalidadTrabajo() {
    }

    public int getCtra_codigo() {
        return Ctra_codigo;
    }

    public void setCtra_codigo(int ctra_codigo) {
        Ctra_codigo = ctra_codigo;
    }

    public String getCtra_descripcion() {
        return Ctra_descripcion;
    }

    public void setCtra_descripcion(String ctra_descripcion) {
        Ctra_descripcion = ctra_descripcion;
    }

    public String getCtra_estado() {
        return Ctra_estado;
    }

    public void setCtra_estado(String ctra_estado) {
        Ctra_estado = ctra_estado;
    }
}
