package com.application.boxmadikv1.api.response.especialista;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAceptoCotizacionResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String mensaje;
    @SerializedName("dataAceptaCotiza")
    @Expose
    private GetAceptoCotizaResponse getAceptoCotizaResponse;

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

    public GetAceptoCotizaResponse getGetAceptoCotizaResponse() {
        return getAceptoCotizaResponse;
    }

    public void setGetAceptoCotizaResponse(GetAceptoCotizaResponse getAceptoCotizaResponse) {
        this.getAceptoCotizaResponse = getAceptoCotizaResponse;
    }

    public class GetAceptoCotizaResponse {
        private String paisImage;
        private String propuestaCodigo;
        private String propuestaUsuarioCodigo;
        private String propuestaNumCotizacion;
        private String propuestaPromedioCoti;
        private String rubroDescripcion;
        private String rubroImagen;
        private String cotizacionCodigo;
        private String cotizacionCodigoUsuario;
        private String departamentoNombre;
        private String provinciaNombre;
        private String distritoNombre;
        private String propuestaTitulo;
        private String propuestaFecha;
        private String propuestaDetalle;
        private String propuestaEstado;
        private String rangoDiasDescripcion;
        private String rangoTurnoDescripcion;
        private String cotizacionEstado;
        private String oficioDescripcion;

        public String getPaisImage() {
            return paisImage;
        }

        public void setPaisImage(String paisImage) {
            this.paisImage = paisImage;
        }

        public String getPropuestaCodigo() {
            return propuestaCodigo;
        }

        public void setPropuestaCodigo(String propuestaCodigo) {
            this.propuestaCodigo = propuestaCodigo;
        }

        public String getPropuestaUsuarioCodigo() {
            return propuestaUsuarioCodigo;
        }

        public void setPropuestaUsuarioCodigo(String propuestaUsuarioCodigo) {
            this.propuestaUsuarioCodigo = propuestaUsuarioCodigo;
        }

        public String getPropuestaNumCotizacion() {
            return propuestaNumCotizacion;
        }

        public void setPropuestaNumCotizacion(String propuestaNumCotizacion) {
            this.propuestaNumCotizacion = propuestaNumCotizacion;
        }

        public String getPropuestaPromedioCoti() {
            return propuestaPromedioCoti;
        }

        public void setPropuestaPromedioCoti(String propuestaPromedioCoti) {
            this.propuestaPromedioCoti = propuestaPromedioCoti;
        }

        public String getRubroDescripcion() {
            return rubroDescripcion;
        }

        public void setRubroDescripcion(String rubroDescripcion) {
            this.rubroDescripcion = rubroDescripcion;
        }

        public String getRubroImagen() {
            return rubroImagen;
        }

        public void setRubroImagen(String rubroImagen) {
            this.rubroImagen = rubroImagen;
        }

        public String getCotizacionCodigo() {
            return cotizacionCodigo;
        }

        public void setCotizacionCodigo(String cotizacionCodigo) {
            this.cotizacionCodigo = cotizacionCodigo;
        }

        public String getCotizacionCodigoUsuario() {
            return cotizacionCodigoUsuario;
        }

        public void setCotizacionCodigoUsuario(String cotizacionCodigoUsuario) {
            this.cotizacionCodigoUsuario = cotizacionCodigoUsuario;
        }

        public String getDepartamentoNombre() {
            return departamentoNombre;
        }

        public void setDepartamentoNombre(String departamentoNombre) {
            this.departamentoNombre = departamentoNombre;
        }

        public String getProvinciaNombre() {
            return provinciaNombre;
        }

        public void setProvinciaNombre(String provinciaNombre) {
            this.provinciaNombre = provinciaNombre;
        }

        public String getDistritoNombre() {
            return distritoNombre;
        }

        public void setDistritoNombre(String distritoNombre) {
            this.distritoNombre = distritoNombre;
        }

        public String getPropuestaTitulo() {
            return propuestaTitulo;
        }

        public void setPropuestaTitulo(String propuestaTitulo) {
            this.propuestaTitulo = propuestaTitulo;
        }

        public String getPropuestaFecha() {
            return propuestaFecha;
        }

        public void setPropuestaFecha(String propuestaFecha) {
            this.propuestaFecha = propuestaFecha;
        }

        public String getPropuestaDetalle() {
            return propuestaDetalle;
        }

        public void setPropuestaDetalle(String propuestaDetalle) {
            this.propuestaDetalle = propuestaDetalle;
        }

        public String getPropuestaEstado() {
            return propuestaEstado;
        }

        public void setPropuestaEstado(String propuestaEstado) {
            this.propuestaEstado = propuestaEstado;
        }

        public String getRangoDiasDescripcion() {
            return rangoDiasDescripcion;
        }

        public void setRangoDiasDescripcion(String rangoDiasDescripcion) {
            this.rangoDiasDescripcion = rangoDiasDescripcion;
        }

        public String getRangoTurnoDescripcion() {
            return rangoTurnoDescripcion;
        }

        public void setRangoTurnoDescripcion(String rangoTurnoDescripcion) {
            this.rangoTurnoDescripcion = rangoTurnoDescripcion;
        }

        public String getCotizacionEstado() {
            return cotizacionEstado;
        }

        public void setCotizacionEstado(String cotizacionEstado) {
            this.cotizacionEstado = cotizacionEstado;
        }

        public String getOficioDescripcion() {
            return oficioDescripcion;
        }

        public void setOficioDescripcion(String oficioDescripcion) {
            this.oficioDescripcion = oficioDescripcion;
        }
    }


}
