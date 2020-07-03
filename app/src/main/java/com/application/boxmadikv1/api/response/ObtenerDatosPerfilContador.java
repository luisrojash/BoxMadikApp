package com.application.boxmadikv1.api.response;

import com.application.boxmadikv1.api.response.especialista.DatosPerfilResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ObtenerDatosPerfilContador {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String mensaje;

    @SerializedName("datosPerfil")
    @Expose
    private ObtenerDatosPerfilContador.ObtenerDataResponse obtenerDataResponse;

    public ObtenerDatosPerfilContador(Boolean error, String mensaje, ObtenerDataResponse obtenerDataResponse) {
        this.error = error;
        this.mensaje = mensaje;
        this.obtenerDataResponse = obtenerDataResponse;
    }

    public ObtenerDatosPerfilContador() {
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

    public ObtenerDataResponse getObtenerDataResponse() {
        return obtenerDataResponse;
    }

    public void setObtenerDataResponse(ObtenerDataResponse obtenerDataResponse) {
        this.obtenerDataResponse = obtenerDataResponse;
    }

    public class ObtenerDataResponse {
        private String propuestaPendiente;
        private String propuestaFinalizada;
        private String propuestaProceso;
        private String propuestaPagados;
        private String cotiPendiente;
        private String cotiAceptado;
        private String cotiFinalizada;
        private String estrellasUsuario;
        private String usuPresentacion;

        public String getPropuestaPendiente() {
            return propuestaPendiente;
        }

        public void setPropuestaPendiente(String propuestaPendiente) {
            this.propuestaPendiente = propuestaPendiente;
        }

        public String getPropuestaFinalizada() {
            return propuestaFinalizada;
        }

        public void setPropuestaFinalizada(String propuestaFinalizada) {
            this.propuestaFinalizada = propuestaFinalizada;
        }

        public String getPropuestaProceso() {
            return propuestaProceso;
        }

        public void setPropuestaProceso(String propuestaProceso) {
            this.propuestaProceso = propuestaProceso;
        }

        public String getCotiPendiente() {
            return cotiPendiente;
        }

        public void setCotiPendiente(String cotiPendiente) {
            this.cotiPendiente = cotiPendiente;
        }

        public String getCotiAceptado() {
            return cotiAceptado;
        }

        public void setCotiAceptado(String cotiAceptado) {
            this.cotiAceptado = cotiAceptado;
        }

        public String getCotiFinalizada() {
            return cotiFinalizada;
        }

        public void setCotiFinalizada(String cotiFinalizada) {
            this.cotiFinalizada = cotiFinalizada;
        }

        public String getPropuestaPagados() {
            return propuestaPagados;
        }

        public void setPropuestaPagados(String propuestaPagados) {
            this.propuestaPagados = propuestaPagados;
        }

        public String getEstrellasUsuario() {
            return estrellasUsuario;
        }

        public void setEstrellasUsuario(String estrellasUsuario) {
            this.estrellasUsuario = estrellasUsuario;
        }

        public String getUsuPresentacion() {
            return usuPresentacion;
        }

        public void setUsuPresentacion(String usuPresentacion) {
            this.usuPresentacion = usuPresentacion;
        }
    }


}
