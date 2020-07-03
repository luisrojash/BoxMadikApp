package com.application.boxmadikv1.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DefaultResponseEstadosLastId {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("estado")
    @Expose
    private String estado;
    @SerializedName("lastid")
    @Expose
    private String lastid;

    public DefaultResponseEstadosLastId() {
    }

    public DefaultResponseEstadosLastId(Boolean error, String message, String estado, String lastid) {
        this.error = error;
        this.message = message;
        this.estado = estado;
        this.lastid = lastid;
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

    public String getLastid() {
        return lastid;
    }

    public void setLastid(String lastid) {
        this.lastid = lastid;
    }
}
