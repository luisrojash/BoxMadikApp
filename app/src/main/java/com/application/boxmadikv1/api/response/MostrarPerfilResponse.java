package com.application.boxmadikv1.api.response;

import com.google.gson.annotations.SerializedName;

public class MostrarPerfilResponse {
    @SerializedName("error")
    private Boolean error;
    @SerializedName("message")
    private String mensaje;
    @SerializedName("usuario")
    private MostrarUsuResponse mostrarUsuResponse;

    public MostrarPerfilResponse(Boolean error, String mensaje, MostrarUsuResponse mostrarUsuResponse) {
        this.error = error;
        this.mensaje = mensaje;
        this.mostrarUsuResponse = mostrarUsuResponse;
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

    public MostrarUsuResponse getMostrarUsuResponse() {
        return mostrarUsuResponse;
    }

    public void setMostrarUsuResponse(MostrarUsuResponse mostrarUsuResponse) {
        this.mostrarUsuResponse = mostrarUsuResponse;
    }

    public class MostrarUsuResponse {
        private String codigoDepartamento;
        private String nombreDepartamento;
        private String codigoProvincia;
        private String nombreProvincia;
        private String codigoDistrito;
        private String nombreDistrito;
        private String descripcionDireccion;
        private String usuLatitud;
        private String usuLongitud;

        public MostrarUsuResponse(String codigoDepartamento, String nombreDepartamento, String codigoProvincia, String nombreProvincia, String codigoDistrito, String nombreDistrito, String descripcionDireccion, String usuLatitud, String usuLongitud) {
            this.codigoDepartamento = codigoDepartamento;
            this.nombreDepartamento = nombreDepartamento;
            this.codigoProvincia = codigoProvincia;
            this.nombreProvincia = nombreProvincia;
            this.codigoDistrito = codigoDistrito;
            this.nombreDistrito = nombreDistrito;
            this.descripcionDireccion = descripcionDireccion;
            this.usuLatitud = usuLatitud;
            this.usuLongitud = usuLongitud;
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

        public String getDescripcionDireccion() {
            return descripcionDireccion;
        }

        public void setDescripcionDireccion(String descripcionDireccion) {
            this.descripcionDireccion = descripcionDireccion;
        }

        public String getUsuLatitud() {
            return usuLatitud;
        }

        public void setUsuLatitud(String usuLatitud) {
            this.usuLatitud = usuLatitud;
        }

        public String getUsuLongitud() {
            return usuLongitud;
        }

        public void setUsuLongitud(String usuLongitud) {
            this.usuLongitud = usuLongitud;
        }
    }

}
