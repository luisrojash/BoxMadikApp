package com.application.boxmadikv1.api.response.especialista;

import com.application.boxmadikv1.modelo.PropuestaInicial;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListaPropuestaTotalResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String mensaje;
    @SerializedName("propuestaTotal")
    @Expose
    private List<PropuestaInicial> propuestaTotal;


    public ListaPropuestaTotalResponse(Boolean error, String mensaje, List<PropuestaInicial> propuestaTotal) {
        this.error = error;
        this.mensaje = mensaje;
        this.propuestaTotal = propuestaTotal;
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

    public List<PropuestaInicial> getPropuestaTotal() {
        return propuestaTotal;
    }

    public void setPropuestaTotal(List<PropuestaInicial> propuestaTotal) {
        this.propuestaTotal = propuestaTotal;
    }
}
