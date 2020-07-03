package com.application.boxmadikv1.modelo;

import com.application.boxmadikv1.BoxDB;
import com.application.boxmadikv1.dao.BaseEntity;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;

@Table(database = BoxDB.class)
public class Usuario extends BaseEntity{
    @Column
    @PrimaryKey
    private String Usu_Codigo;
    @Column
    private String Usu_DNI;
    @Column
    private String Usu_Nom1;
    @Column
    private String Usu_Ape_Pat_Mate;
    @Column
    private String Usu_Celular;
    @Column
    private String Usu_Email;
    @Column
    private String Usu_Foto;
    @Column
    private String pais_Pais_Codigo;
    @Column
    private String TDoc_Codigo;

    public Usuario() {
    }

    public String getUsu_Codigo() {
        return Usu_Codigo;
    }

    public void setUsu_Codigo(String usu_Codigo) {
        Usu_Codigo = usu_Codigo;
    }

    public String getUsu_DNI() {
        return Usu_DNI;
    }

    public void setUsu_DNI(String usu_DNI) {
        Usu_DNI = usu_DNI;
    }

    public String getUsu_Nom1() {
        return Usu_Nom1;
    }

    public void setUsu_Nom1(String usu_Nom1) {
        Usu_Nom1 = usu_Nom1;
    }

    public String getUsu_Ape_Pat_Mate() {
        return Usu_Ape_Pat_Mate;
    }

    public void setUsu_Ape_Pat_Mate(String usu_Ape_Pat_Mate) {
        Usu_Ape_Pat_Mate = usu_Ape_Pat_Mate;
    }

    public String getUsu_Celular() {
        return Usu_Celular;
    }

    public void setUsu_Celular(String usu_Celular) {
        Usu_Celular = usu_Celular;
    }

    public String getUsu_Email() {
        return Usu_Email;
    }

    public void setUsu_Email(String usu_Email) {
        Usu_Email = usu_Email;
    }

    public String getUsu_Foto() {
        return Usu_Foto;
    }

    public void setUsu_Foto(String usu_Foto) {
        Usu_Foto = usu_Foto;
    }

    public String getPais_Pais_Codigo() {
        return pais_Pais_Codigo;
    }

    public void setPais_Pais_Codigo(String pais_Pais_Codigo) {
        this.pais_Pais_Codigo = pais_Pais_Codigo;
    }

    public String getTDoc_Codigo() {
        return TDoc_Codigo;
    }

    public void setTDoc_Codigo(String TDoc_Codigo) {
        this.TDoc_Codigo = TDoc_Codigo;
    }
}
