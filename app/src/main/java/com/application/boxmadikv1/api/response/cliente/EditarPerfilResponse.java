package com.application.boxmadikv1.api.response.cliente;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditarPerfilResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("usuFoto")
    @Expose
    private String usuFoto;

    public EditarPerfilResponse(Boolean error, String message, String usuFoto) {
        this.error = error;
        this.message = message;
        this.usuFoto = usuFoto;
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

    public String getUsuFoto() {
        return usuFoto;
    }

    public void setUsuFoto(String usuFoto) {
        this.usuFoto = usuFoto;
    }
}
