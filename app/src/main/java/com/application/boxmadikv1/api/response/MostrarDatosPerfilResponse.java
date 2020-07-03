package com.application.boxmadikv1.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MostrarDatosPerfilResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("datosusuario")
    @Expose
    private MostrarDataPerfil datosusuario;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MostrarDataPerfil getDatosusuario() {
        return datosusuario;
    }

    public void setDatosusuario(MostrarDataPerfil datosusuario) {
        this.datosusuario = datosusuario;
    }

    public MostrarDatosPerfilResponse() {
    }

    public class MostrarDataPerfil{

        String Usu_Codigo;
        String Usu_Email;
        String Usu_Nom1;
        String Usu_Ape_Pat_Mate;
        String Usu_Celular;
        String Usu_Foto;
        String TDoc_Desc_corta;
        String Pais_Nombre;


        public MostrarDataPerfil(String usu_Codigo, String usu_Email, String usu_Nom1, String usu_Ape_Pat_Mate, String usu_Celular, String usu_Foto, String TDoc_Desc_corta, String pais_Nombre) {
            Usu_Codigo = usu_Codigo;
            Usu_Email = usu_Email;
            Usu_Nom1 = usu_Nom1;
            Usu_Ape_Pat_Mate = usu_Ape_Pat_Mate;
            Usu_Celular = usu_Celular;
            Usu_Foto = usu_Foto;
            this.TDoc_Desc_corta = TDoc_Desc_corta;
            Pais_Nombre = pais_Nombre;
        }

        public String getUsu_Codigo() {
            return Usu_Codigo;
        }

        public void setUsu_Codigo(String usu_Codigo) {
            Usu_Codigo = usu_Codigo;
        }

        public String getUsu_Email() {
            return Usu_Email;
        }

        public void setUsu_Email(String usu_Email) {
            Usu_Email = usu_Email;
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

        public String getUsu_Foto() {
            return Usu_Foto;
        }

        public void setUsu_Foto(String usu_Foto) {
            Usu_Foto = usu_Foto;
        }

        public String getTDoc_Desc_corta() {
            return TDoc_Desc_corta;
        }

        public void setTDoc_Desc_corta(String TDoc_Desc_corta) {
            this.TDoc_Desc_corta = TDoc_Desc_corta;
        }

        public String getPais_Nombre() {
            return Pais_Nombre;
        }

        public void setPais_Nombre(String pais_Nombre) {
            Pais_Nombre = pais_Nombre;
        }
    }
}
