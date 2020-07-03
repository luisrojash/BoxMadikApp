package com.application.boxmadikv1.modelo;

import com.application.boxmadikv1.BoxDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = BoxDB.class)
public class CentroEstudios extends BaseModel{
    @Column
    @PrimaryKey
    private int Cest_Codigo;
    @Column
    private String Cest_descripcion;
    @Column
    private String Cest_estado;
    @Column
    private String Pais_Codigo;

    public CentroEstudios() {
    }

    public int getCest_Codigo() {
        return Cest_Codigo;
    }

    public void setCest_Codigo(int cest_Codigo) {
        Cest_Codigo = cest_Codigo;
    }

    public String getCest_descripcion() {
        return Cest_descripcion;
    }

    public void setCest_descripcion(String cest_descripcion) {
        Cest_descripcion = cest_descripcion;
    }

    public String getCest_estado() {
        return Cest_estado;
    }

    public void setCest_estado(String cest_estado) {
        Cest_estado = cest_estado;
    }

    public String getPais_Codigo() {
        return Pais_Codigo;
    }

    public void setPais_Codigo(String pais_Codigo) {
        Pais_Codigo = pais_Codigo;
    }
}
