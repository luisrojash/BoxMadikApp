package com.application.boxmadikv1.api.response;

import com.application.boxmadikv1.modelo.PropuestaEspecialidad;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListaPropuestaEspecialidadResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String mensaje;

    @SerializedName("propuestaEspecialidad")
    @Expose
    private List<PropuestaEspecialidad> propuestaEspecialidad;

    public ListaPropuestaEspecialidadResponse(Boolean error, String mensaje, List<PropuestaEspecialidad> propuestaEspecialidad) {
        this.error = error;
        this.mensaje = mensaje;
        this.propuestaEspecialidad = propuestaEspecialidad;
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

    public List<PropuestaEspecialidad> getPropuestaEspecialidad() {
        return propuestaEspecialidad;
    }

    public void setPropuestaEspecialidad(List<PropuestaEspecialidad> propuestaEspecialidad) {
        this.propuestaEspecialidad = propuestaEspecialidad;
    }
}
