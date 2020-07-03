package com.application.boxmadikv1.api.response.especialista;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ObtenerRespuestaRevocaResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String mensaje;
    @SerializedName("dataCorrecto")
    @Expose
    private RespuestaRevocaResponse respuestaRevocaResponse;

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

    public RespuestaRevocaResponse getRespuestaRevocaResponse() {
        return respuestaRevocaResponse;
    }

    public void setRespuestaRevocaResponse(RespuestaRevocaResponse respuestaRevocaResponse) {
        this.respuestaRevocaResponse = respuestaRevocaResponse;
    }

    public class RespuestaRevocaResponse {
        private String respuesta_resp;
        private String descripcion_resp;
        private String observa_resp;

        public String getRespuesta_resp() {
            return respuesta_resp;
        }

        public void setRespuesta_resp(String respuesta_resp) {
            this.respuesta_resp = respuesta_resp;
        }

        public String getDescripcion_resp() {
            return descripcion_resp;
        }

        public void setDescripcion_resp(String descripcion_resp) {
            this.descripcion_resp = descripcion_resp;
        }

        public String getObserva_resp() {
            return observa_resp;
        }

        public void setObserva_resp(String observa_resp) {
            this.observa_resp = observa_resp;
        }
    }
}
