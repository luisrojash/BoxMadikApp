package com.application.boxmadikv1.api.response;

import com.application.boxmadikv1.modelo.Usuario;
import com.application.boxmadikv1.registraUser.entidad.UserUi;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("error")
    private Boolean error;
    @SerializedName("message")
    private String mensaje;
    @SerializedName("usuario")
    private UsuarioResponse usuario;

    public LoginResponse(Boolean error, String mensaje, UsuarioResponse usuario) {
        this.error = error;
        this.mensaje = mensaje;
        this.usuario = usuario;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public UsuarioResponse getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResponse usuario) {
        this.usuario = usuario;
    }

    public class UsuarioResponse {
        private String Usu_Codigo;
        private String Usu_DNI;
        private String Usu_Nom1;
        private String Usu_Ape_Pat_Mate;
        private String Usu_Celular;
        private String Usu_Email;
        private String Usu_foto;
        private String pais_Pais_Codigo;
        private String TDoc_Codigo;
        private String usu_razon_social;

        public UsuarioResponse(String usu_Codigo, String usu_DNI, String usu_Nom1, String usu_Ape_Pat_Mate, String usu_Celular, String usu_Email, String usu_foto, String pais_Pais_Codigo, String TDoc_Codigo, String usu_razon_social) {
            Usu_Codigo = usu_Codigo;
            Usu_DNI = usu_DNI;
            Usu_Nom1 = usu_Nom1;
            Usu_Ape_Pat_Mate = usu_Ape_Pat_Mate;
            Usu_Celular = usu_Celular;
            Usu_Email = usu_Email;
            Usu_foto = usu_foto;
            this.pais_Pais_Codigo = pais_Pais_Codigo;
            this.TDoc_Codigo = TDoc_Codigo;
            this.usu_razon_social = usu_razon_social;
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

        public String getUsu_foto() {
            return Usu_foto;
        }

        public void setUsu_foto(String usu_foto) {
            Usu_foto = usu_foto;
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

        public String getUsu_razon_social() {
            return usu_razon_social;
        }

        public void setUsu_razon_social(String usu_razon_social) {
            this.usu_razon_social = usu_razon_social;
        }
    }
}
