package com.application.boxmadikv1.api.response.cliente;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ObtenerRespuestaRevocaEspResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("dataCorrecto")
    @Expose
    private ObtenerRevocaEspResponse obtenerRevocaEspResponse;

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

    public ObtenerRevocaEspResponse getObtenerRevocaEspResponse() {
        return obtenerRevocaEspResponse;
    }

    public void setObtenerRevocaEspResponse(ObtenerRevocaEspResponse obtenerRevocaEspResponse) {
        this.obtenerRevocaEspResponse = obtenerRevocaEspResponse;
    }

    public class ObtenerRevocaEspResponse {
        private String nombreMotivo;
        private String nombreSolucion;
        private String nombreCalidad;
        private String porcentajeTrabajo;
        private String detallesRevocacion;

        public String getNombreMotivo() {
            return nombreMotivo;
        }

        public void setNombreMotivo(String nombreMotivo) {
            this.nombreMotivo = nombreMotivo;
        }

        public String getNombreSolucion() {
            return nombreSolucion;
        }

        public void setNombreSolucion(String nombreSolucion) {
            this.nombreSolucion = nombreSolucion;
        }

        public String getNombreCalidad() {
            return nombreCalidad;
        }

        public void setNombreCalidad(String nombreCalidad) {
            this.nombreCalidad = nombreCalidad;
        }

        public String getPorcentajeTrabajo() {
            return porcentajeTrabajo;
        }

        public void setPorcentajeTrabajo(String porcentajeTrabajo) {
            this.porcentajeTrabajo = porcentajeTrabajo;
        }

        public String getDetallesRevocacion() {
            return detallesRevocacion;
        }

        public void setDetallesRevocacion(String detallesRevocacion) {
            this.detallesRevocacion = detallesRevocacion;
        }
    }
}
