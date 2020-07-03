package com.application.boxmadikv1.api.response.cliente;

import com.application.boxmadikv1.cliente.menu.abstracto.entidad.ClienteEstadosUi;
import com.application.boxmadikv1.cliente.menu.clientePendiente.entidad.ClientePendienteUi;
import com.application.boxmadikv1.modelo.PropuestaInicial;
import com.bumptech.glide.annotation.Excludes;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListaPropuestaPendienteResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("propuestasClientePendientes")
    @Expose
    private List<PropuestaInicial> propuestasClientePendientes;

    private List<ClienteEstadosUi> clienteEstadosUis;

    public ListaPropuestaPendienteResponse(Boolean error, String message, List<PropuestaInicial> propuestasClientePendientes, List<ClienteEstadosUi> clienteEstadosUis) {
        this.error = error;
        this.message = message;
        this.propuestasClientePendientes = propuestasClientePendientes;
        this.clienteEstadosUis = clienteEstadosUis;
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

    public List<PropuestaInicial> getPropuestasClientePendientes() {
        return propuestasClientePendientes;
    }

    public void setPropuestasClientePendientes(List<PropuestaInicial> propuestasClientePendientes) {
        this.propuestasClientePendientes = propuestasClientePendientes;
    }

    public List<ClienteEstadosUi> getClienteEstadosUis() {
        return clienteEstadosUis;
    }

    public void setClienteEstadosUis(List<ClienteEstadosUi> clienteEstadosUis) {
        this.clienteEstadosUis = clienteEstadosUis;
    }
}
