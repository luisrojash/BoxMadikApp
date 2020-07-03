package com.application.boxmadikv1.modelo;

import com.application.boxmadikv1.BoxDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = BoxDB.class)
public class TipoEstudios extends BaseModel {
    @Column
    @PrimaryKey
    private int Test_Codigo;
    @Column
    private String Test_descripcion;
    @Column
    private String Test_estado;

    public TipoEstudios() {
    }

    public int getTest_Codigo() {
        return Test_Codigo;
    }

    public void setTest_Codigo(int test_Codigo) {
        Test_Codigo = test_Codigo;
    }

    public String getTest_descripcion() {
        return Test_descripcion;
    }

    public void setTest_descripcion(String test_descripcion) {
        Test_descripcion = test_descripcion;
    }

    public String getTest_estado() {
        return Test_estado;
    }

    public void setTest_estado(String test_estado) {
        Test_estado = test_estado;
    }
}
