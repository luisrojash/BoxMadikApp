package com.application.boxmadikv1.api.response.especialista;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListaZonaTrabajoResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String mensaje;
    @SerializedName("listaZonaTrabajos")
    @Expose
    private List<ListZonaTrabajoRes> listZonaTrabajoRes;

    public ListaZonaTrabajoResponse(Boolean error, String mensaje, List<ListZonaTrabajoRes> listZonaTrabajoRes) {
        this.error = error;
        this.mensaje = mensaje;
        this.listZonaTrabajoRes = listZonaTrabajoRes;
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

    public List<ListZonaTrabajoRes> getListZonaTrabajoRes() {
        return listZonaTrabajoRes;
    }

    public void setListZonaTrabajoRes(List<ListZonaTrabajoRes> listZonaTrabajoRes) {
        this.listZonaTrabajoRes = listZonaTrabajoRes;
    }

    public class ListZonaTrabajoRes{
        private String codigoDepartamento;
        private String  nombreDepartamento;
        private String codigoProvincia;
        private String nombreProvincia;
        private String codigoDistrito;
        private String nombreDistrito;

        public ListZonaTrabajoRes(String codigoDepartamento, String nombreDepartamento, String codigoProvincia, String nombreProvincia, String codigoDistrito, String nombreDistrito) {
            this.codigoDepartamento = codigoDepartamento;
            this.nombreDepartamento = nombreDepartamento;
            this.codigoProvincia = codigoProvincia;
            this.nombreProvincia = nombreProvincia;
            this.codigoDistrito = codigoDistrito;
            this.nombreDistrito = nombreDistrito;
        }

        public String getCodigoDepartamento() {
            return codigoDepartamento;
        }

        public void setCodigoDepartamento(String codigoDepartamento) {
            this.codigoDepartamento = codigoDepartamento;
        }

        public String getNombreDepartamento() {
            return nombreDepartamento;
        }

        public void setNombreDepartamento(String nombreDepartamento) {
            this.nombreDepartamento = nombreDepartamento;
        }

        public String getCodigoProvincia() {
            return codigoProvincia;
        }

        public void setCodigoProvincia(String codigoProvincia) {
            this.codigoProvincia = codigoProvincia;
        }

        public String getNombreProvincia() {
            return nombreProvincia;
        }

        public void setNombreProvincia(String nombreProvincia) {
            this.nombreProvincia = nombreProvincia;
        }

        public String getCodigoDistrito() {
            return codigoDistrito;
        }

        public void setCodigoDistrito(String codigoDistrito) {
            this.codigoDistrito = codigoDistrito;
        }

        public String getNombreDistrito() {
            return nombreDistrito;
        }

        public void setNombreDistrito(String nombreDistrito) {
            this.nombreDistrito = nombreDistrito;
        }
    }
}
