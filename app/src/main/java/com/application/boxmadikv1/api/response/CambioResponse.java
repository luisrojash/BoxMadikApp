package com.application.boxmadikv1.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CambioResponse {
    @SerializedName("USD_PEN")
    @Expose
    private String message;

    public CambioResponse() {
    }

    public CambioResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
