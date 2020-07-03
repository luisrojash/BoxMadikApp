package com.application.boxmadikv1.modelo;

import com.application.boxmadikv1.BoxDB;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = BoxDB.class)
public class TipoDocumento extends BaseModel {
    @Column
    @PrimaryKey
    private int TDoc_Codigo;
    @Column
    private String TDoc_Desc_larga;
    @Column
    private String TDoc_Desc_corta;
    @Column
    private String TDoc_Estado;
    @Column
    private String Pais_Codigo;
    @Column
    private String T_Doc_CantNum;

    public TipoDocumento() {
    }

    public int getTDoc_Codigo() {
        return TDoc_Codigo;
    }

    public void setTDoc_Codigo(int TDoc_Codigo) {
        this.TDoc_Codigo = TDoc_Codigo;
    }

    public String getTDoc_Desc_larga() {
        return TDoc_Desc_larga;
    }

    public void setTDoc_Desc_larga(String TDoc_Desc_larga) {
        this.TDoc_Desc_larga = TDoc_Desc_larga;
    }

    public String getTDoc_Desc_corta() {
        return TDoc_Desc_corta;
    }

    public void setTDoc_Desc_corta(String TDoc_Desc_corta) {
        this.TDoc_Desc_corta = TDoc_Desc_corta;
    }

    public String getTDoc_Estado() {
        return TDoc_Estado;
    }

    public void setTDoc_Estado(String TDoc_Estado) {
        this.TDoc_Estado = TDoc_Estado;
    }

    public String getPais_Codigo() {
        return Pais_Codigo;
    }

    public void setPais_Codigo(String pais_Codigo) {
        Pais_Codigo = pais_Codigo;
    }

    public String getT_Doc_CantNum() {
        return T_Doc_CantNum;
    }

    public void setT_Doc_CantNum(String t_Doc_CantNum) {
        T_Doc_CantNum = t_Doc_CantNum;
    }
}
