package com.application.boxmadikv1.api.response.especialista;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MostrarCentroEstudiosResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String mensaje;

    @SerializedName("listaCentroEstudios")
    @Expose
    List<MostrarCentroEstudiosResp> estudiosRespList;

    public MostrarCentroEstudiosResponse(Boolean error, String mensaje, List<MostrarCentroEstudiosResp> estudiosRespList) {
        this.error = error;
        this.mensaje = mensaje;
        this.estudiosRespList = estudiosRespList;
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

    public List<MostrarCentroEstudiosResp> getEstudiosRespList() {
        return estudiosRespList;
    }

    public void setEstudiosRespList(List<MostrarCentroEstudiosResp> estudiosRespList) {
        this.estudiosRespList = estudiosRespList;
    }

    public class MostrarCentroEstudiosResp {
        private String codigoEspeEstudios;
        private String nombreCurso;
        private String codigoTipoEstudios;
        private String nombreTipoEstudios;
        private String codigoCentroEstudios;
        private String nombreCentroEstudios;
        private String mesInicio;
        private String anioInicio;
        private String mesFin;
        private String anioFin;
        private String fechaInicio;
        private String fechaFin;


        public MostrarCentroEstudiosResp(String codigoEspeEstudios, String nombreCurso, String codigoTipoEstudios, String nombreTipoEstudios, String codigoCentroEstudios, String nombreCentroEstudios, String mesInicio, String anioInicio, String mesFin, String anioFin, String fechaInicio, String fechaFin) {
            this.codigoEspeEstudios = codigoEspeEstudios;
            this.nombreCurso = nombreCurso;
            this.codigoTipoEstudios = codigoTipoEstudios;
            this.nombreTipoEstudios = nombreTipoEstudios;
            this.codigoCentroEstudios = codigoCentroEstudios;
            this.nombreCentroEstudios = nombreCentroEstudios;
            this.mesInicio = mesInicio;
            this.anioInicio = anioInicio;
            this.mesFin = mesFin;
            this.anioFin = anioFin;
            this.fechaInicio = fechaInicio;
            this.fechaFin = fechaFin;
        }

        public String getCodigoEspeEstudios() {
            return codigoEspeEstudios;
        }

        public void setCodigoEspeEstudios(String codigoEspeEstudios) {
            this.codigoEspeEstudios = codigoEspeEstudios;
        }

        public String getNombreCurso() {
            return nombreCurso;
        }

        public void setNombreCurso(String nombreCurso) {
            this.nombreCurso = nombreCurso;
        }

        public String getCodigoTipoEstudios() {
            return codigoTipoEstudios;
        }

        public void setCodigoTipoEstudios(String codigoTipoEstudios) {
            this.codigoTipoEstudios = codigoTipoEstudios;
        }

        public String getNombreTipoEstudios() {
            return nombreTipoEstudios;
        }

        public void setNombreTipoEstudios(String nombreTipoEstudios) {
            this.nombreTipoEstudios = nombreTipoEstudios;
        }

        public String getCodigoCentroEstudios() {
            return codigoCentroEstudios;
        }

        public void setCodigoCentroEstudios(String codigoCentroEstudios) {
            this.codigoCentroEstudios = codigoCentroEstudios;
        }

        public String getNombreCentroEstudios() {
            return nombreCentroEstudios;
        }

        public void setNombreCentroEstudios(String nombreCentroEstudios) {
            this.nombreCentroEstudios = nombreCentroEstudios;
        }

        public String getMesInicio() {
            return mesInicio;
        }

        public void setMesInicio(String mesInicio) {
            this.mesInicio = mesInicio;
        }

        public String getAnioInicio() {
            return anioInicio;
        }

        public void setAnioInicio(String anioInicio) {
            this.anioInicio = anioInicio;
        }

        public String getMesFin() {
            return mesFin;
        }

        public void setMesFin(String mesFin) {
            this.mesFin = mesFin;
        }

        public String getAnioFin() {
            return anioFin;
        }

        public void setAnioFin(String anioFin) {
            this.anioFin = anioFin;
        }

        public String getFechaInicio() {
            return fechaInicio;
        }

        public void setFechaInicio(String fechaInicio) {
            this.fechaInicio = fechaInicio;
        }

        public String getFechaFin() {
            return fechaFin;
        }

        public void setFechaFin(String fechaFin) {
            this.fechaFin = fechaFin;
        }
    }
}
