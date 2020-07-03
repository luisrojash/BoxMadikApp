package com.application.boxmadikv1.modelo;

import com.application.boxmadikv1.BoxDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = BoxDB.class)
public class PropuestaRevocacion extends BaseModel {
    @Column
    @PrimaryKey
    private int Prorev_codigo;
    @Column
    private String Prorev_descrip;

    public PropuestaRevocacion() {
    }

    public int getProrev_codigo() {
        return Prorev_codigo;
    }

    public void setProrev_codigo(int prorev_codigo) {
        Prorev_codigo = prorev_codigo;
    }

    public String getProrev_descrip() {
        return Prorev_descrip;
    }

    public void setProrev_descrip(String prorev_descrip) {
        Prorev_descrip = prorev_descrip;
    }
}
