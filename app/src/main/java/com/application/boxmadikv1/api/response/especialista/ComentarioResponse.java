package com.application.boxmadikv1.api.response.especialista;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComentarioResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String mensaje;
    @SerializedName("listaComentarios")
    @Expose
    List<ComentarioEspecResponse> comentarioEspecResponseList;

    public ComentarioResponse(Boolean error, String mensaje, List<ComentarioEspecResponse> comentarioEspecResponseList) {
        this.error = error;
        this.mensaje = mensaje;
        this.comentarioEspecResponseList = comentarioEspecResponseList;
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

    public List<ComentarioEspecResponse> getComentarioEspecResponseList() {
        return comentarioEspecResponseList;
    }

    public void setComentarioEspecResponseList(List<ComentarioEspecResponse> comentarioEspecResponseList) {
        this.comentarioEspecResponseList = comentarioEspecResponseList;
    }

    public class ComentarioEspecResponse {
        private String nombrePropuesta;
        private String fechaPropuesta;
        private String detallePropuesta;
        private String fotoCliente;
        private String nombreCliente;
        private String apellidoCliente;
        private String estrellasEspec;
        private String comentarioEspec;
        private String rubroImagen;
        private String usuRazonSocial;

        public ComentarioEspecResponse(String nombrePropuesta, String fechaPropuesta, String detallePropuesta, String fotoCliente, String nombreCliente, String apellidoCliente, String estrellasEspec, String comentarioEspec, String rubroImagen, String usuRazonSocial) {
            this.nombrePropuesta = nombrePropuesta;
            this.fechaPropuesta = fechaPropuesta;
            this.detallePropuesta = detallePropuesta;
            this.fotoCliente = fotoCliente;
            this.nombreCliente = nombreCliente;
            this.apellidoCliente = apellidoCliente;
            this.estrellasEspec = estrellasEspec;
            this.comentarioEspec = comentarioEspec;
            this.rubroImagen = rubroImagen;
            this.usuRazonSocial = usuRazonSocial;
        }

        public String getNombrePropuesta() {
            return nombrePropuesta;
        }

        public void setNombrePropuesta(String nombrePropuesta) {
            this.nombrePropuesta = nombrePropuesta;
        }

        public String getFechaPropuesta() {
            return fechaPropuesta;
        }

        public void setFechaPropuesta(String fechaPropuesta) {
            this.fechaPropuesta = fechaPropuesta;
        }

        public String getDetallePropuesta() {
            return detallePropuesta;
        }

        public void setDetallePropuesta(String detallePropuesta) {
            this.detallePropuesta = detallePropuesta;
        }

        public String getFotoCliente() {
            return fotoCliente;
        }

        public void setFotoCliente(String fotoCliente) {
            this.fotoCliente = fotoCliente;
        }

        public String getNombreCliente() {
            return nombreCliente;
        }

        public void setNombreCliente(String nombreCliente) {
            this.nombreCliente = nombreCliente;
        }

        public String getApellidoCliente() {
            return apellidoCliente;
        }

        public void setApellidoCliente(String apellidoCliente) {
            this.apellidoCliente = apellidoCliente;
        }

        public String getEstrellasEspec() {
            return estrellasEspec;
        }

        public void setEstrellasEspec(String estrellasEspec) {
            this.estrellasEspec = estrellasEspec;
        }

        public String getComentarioEspec() {
            return comentarioEspec;
        }

        public void setComentarioEspec(String comentarioEspec) {
            this.comentarioEspec = comentarioEspec;
        }

        public String getRubroImagen() {
            return rubroImagen;
        }

        public void setRubroImagen(String rubroImagen) {
            this.rubroImagen = rubroImagen;
        }

        public String getUsuRazonSocial() {
            return usuRazonSocial;
        }

        public void setUsuRazonSocial(String usuRazonSocial) {
            this.usuRazonSocial = usuRazonSocial;
        }
    }
}
