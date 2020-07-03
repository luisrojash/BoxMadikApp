package com.application.boxmadikv1.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListaRubrosEspecResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("listaRubros")
    @Expose
    private List<ListaRubrosEspec> listaRubros;

    public ListaRubrosEspecResponse() {
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

    public List<ListaRubrosEspec> getListaRubros() {
        return listaRubros;
    }

    public void setListaRubros(List<ListaRubrosEspec> listaRubros) {
        this.listaRubros = listaRubros;
    }

    public class ListaRubrosEspec{
        private String Uru_codigo;
        private String rubro_Rub_Codigo;

        public ListaRubrosEspec() {
        }

        public String getUru_codigo() {
            return Uru_codigo;
        }

        public void setUru_codigo(String uru_codigo) {
            Uru_codigo = uru_codigo;
        }

        public String getRubro_Rub_Codigo() {
            return rubro_Rub_Codigo;
        }

        public void setRubro_Rub_Codigo(String rubro_Rub_Codigo) {
            this.rubro_Rub_Codigo = rubro_Rub_Codigo;
        }
    }
}
