package com.application.boxmadikv1.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DefaultResponseEstados {

    public static final String RUBRO_USUARIO_EXISTE = "304";
    public static final String RUBRO_USUARIO_NO_EXISTE = "305";
    public static final String CREACION_CORRECTA = "120";
    public static final String CREACION_ERROR = "130";
    /*define('RUBRO_USUARIO_EXISTE',304);
    define('RUBRO_USUARIO_NO_EXISTE',305);*/
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("estado")
    @Expose
    private String estado;

    public DefaultResponseEstados() {
    }

    public DefaultResponseEstados(Boolean error, String message, String estado) {
        this.error = error;
        this.message = message;
        this.estado = estado;
    }

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
