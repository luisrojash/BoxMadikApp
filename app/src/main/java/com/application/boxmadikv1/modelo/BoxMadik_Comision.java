package com.application.boxmadikv1.modelo;

import com.application.boxmadikv1.BoxDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = BoxDB.class)
public class BoxMadik_Comision extends BaseModel {
    @Column
    @PrimaryKey
    private int Boxc_item;
    @Column
    private String Boxc_fecha;
    @Column
    private String Boxc_comision;
    @Column
    private String Boxc_estado;
    @Column
    private String Pais_Codigo;

    public BoxMadik_Comision() {
    }

    public int getBoxc_item() {
        return Boxc_item;
    }

    public void setBoxc_item(int boxc_item) {
        Boxc_item = boxc_item;
    }

    public String getBoxc_fecha() {
        return Boxc_fecha;
    }

    public void setBoxc_fecha(String boxc_fecha) {
        Boxc_fecha = boxc_fecha;
    }

    public String getBoxc_comision() {
        return Boxc_comision;
    }

    public void setBoxc_comision(String boxc_comision) {
        Boxc_comision = boxc_comision;
    }

    public String getBoxc_estado() {
        return Boxc_estado;
    }

    public void setBoxc_estado(String boxc_estado) {
        Boxc_estado = boxc_estado;
    }

    public String getPais_Codigo() {
        return Pais_Codigo;
    }

    public void setPais_Codigo(String pais_Codigo) {
        Pais_Codigo = pais_Codigo;
    }
}
