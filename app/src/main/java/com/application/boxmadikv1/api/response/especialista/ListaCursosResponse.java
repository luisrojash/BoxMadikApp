package com.application.boxmadikv1.api.response.especialista;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListaCursosResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String mensaje;
    @SerializedName("listaCursos")
    @Expose
    private List<ListaCurResponse> listaCurResponses;

    public ListaCursosResponse(Boolean error, String mensaje, List<ListaCurResponse> listaCurResponses) {
        this.error = error;
        this.mensaje = mensaje;
        this.listaCurResponses = listaCurResponses;
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

    public List<ListaCurResponse> getListaCurResponses() {
        return listaCurResponses;
    }

    public void setListaCurResponses(List<ListaCurResponse> listaCurResponses) {
        this.listaCurResponses = listaCurResponses;
    }

    public class ListaCurResponse {
        private String codigoEspeEstudios;
        private String estadoValidacion;
        private String tipoEstudioNombre;
        private String nombreEspeEstudios;
        private String nombreCentroEstu;
        private String anoInicioEspeEstudios;
        private String anoFinEspeEstudios;
        private String mesInicioEspeEstudios;
        private String mesFinEspeEstudios;

        public ListaCurResponse(String codigoEspeEstudios, String estadoValidacion,
                                String tipoEstudioNombre, String nombreEspeEstudios,
                                String nombreCentroEstu, String anoInicioEspeEstudios,
                                String anoFinEspeEstudios, String mesInicioEspeEstudios,
                                String mesFinEspeEstudios) {
            this.codigoEspeEstudios = codigoEspeEstudios;
            this.estadoValidacion = estadoValidacion;
            this.tipoEstudioNombre = tipoEstudioNombre;
            this.nombreEspeEstudios = nombreEspeEstudios;
            this.nombreCentroEstu = nombreCentroEstu;
            this.anoInicioEspeEstudios = anoInicioEspeEstudios;
            this.anoFinEspeEstudios = anoFinEspeEstudios;
            this.mesInicioEspeEstudios = mesInicioEspeEstudios;
            this.mesFinEspeEstudios = mesFinEspeEstudios;
        }

        public String getCodigoEspeEstudios() {
            return codigoEspeEstudios;
        }

        public void setCodigoEspeEstudios(String codigoEspeEstudios) {
            this.codigoEspeEstudios = codigoEspeEstudios;
        }

        public String getEstadoValidacion() {
            return estadoValidacion;
        }

        public void setEstadoValidacion(String estadoValidacion) {
            this.estadoValidacion = estadoValidacion;
        }

        public String getTipoEstudioNombre() {
            return tipoEstudioNombre;
        }

        public void setTipoEstudioNombre(String tipoEstudioNombre) {
            this.tipoEstudioNombre = tipoEstudioNombre;
        }

        public String getNombreEspeEstudios() {
            return nombreEspeEstudios;
        }

        public void setNombreEspeEstudios(String nombreEspeEstudios) {
            this.nombreEspeEstudios = nombreEspeEstudios;
        }

        public String getNombreCentroEstu() {
            return nombreCentroEstu;
        }

        public void setNombreCentroEstu(String nombreCentroEstu) {
            this.nombreCentroEstu = nombreCentroEstu;
        }

        public String getAnoInicioEspeEstudios() {
            return anoInicioEspeEstudios;
        }

        public void setAnoInicioEspeEstudios(String anoInicioEspeEstudios) {
            this.anoInicioEspeEstudios = anoInicioEspeEstudios;
        }

        public String getAnoFinEspeEstudios() {
            return anoFinEspeEstudios;
        }

        public void setAnoFinEspeEstudios(String anoFinEspeEstudios) {
            this.anoFinEspeEstudios = anoFinEspeEstudios;
        }

        public String getMesInicioEspeEstudios() {
            return mesInicioEspeEstudios;
        }

        public void setMesInicioEspeEstudios(String mesInicioEspeEstudios) {
            this.mesInicioEspeEstudios = mesInicioEspeEstudios;
        }

        public String getMesFinEspeEstudios() {
            return mesFinEspeEstudios;
        }

        public void setMesFinEspeEstudios(String mesFinEspeEstudios) {
            this.mesFinEspeEstudios = mesFinEspeEstudios;
        }
    }
}
