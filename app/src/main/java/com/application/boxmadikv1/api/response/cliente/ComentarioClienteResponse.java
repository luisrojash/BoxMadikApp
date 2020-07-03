package com.application.boxmadikv1.api.response.cliente;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComentarioClienteResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String mensaje;

    @SerializedName("comentarioCliente")
    @Expose
    List<ComentariosClientResponse> comentariosClientResponses;

    public ComentarioClienteResponse(Boolean error, String mensaje, List<ComentariosClientResponse> comentariosClientResponses) {
        this.error = error;
        this.mensaje = mensaje;
        this.comentariosClientResponses = comentariosClientResponses;
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

    public List<ComentariosClientResponse> getComentariosClientResponses() {
        return comentariosClientResponses;
    }

    public void setComentariosClientResponses(List<ComentariosClientResponse> comentariosClientResponses) {
        this.comentariosClientResponses = comentariosClientResponses;
    }

    public class ComentariosClientResponse {
        private String usuFoto;
        private String usuNombre;
        private String usuApellido;
        private String comentarioCliente;
        private String estrellasCliente;
        private String usuRazonSocial;
        private String propuestaTitulo;
        private String fechaRegistroComentario;
        private String rubroImagen;

        public ComentariosClientResponse(String usuFoto, String usuNombre, String usuApellido, String comentarioCliente, String estrellasCliente, String usuRazonSocial, String propuestaTitulo, String fechaRegistroComentario, String rubroImagen) {
            this.usuFoto = usuFoto;
            this.usuNombre = usuNombre;
            this.usuApellido = usuApellido;
            this.comentarioCliente = comentarioCliente;
            this.estrellasCliente = estrellasCliente;
            this.usuRazonSocial = usuRazonSocial;
            this.propuestaTitulo = propuestaTitulo;
            this.fechaRegistroComentario = fechaRegistroComentario;
            this.rubroImagen = rubroImagen;
        }

        public String getUsuFoto() {
            return usuFoto;
        }

        public void setUsuFoto(String usuFoto) {
            this.usuFoto = usuFoto;
        }

        public String getUsuNombre() {
            return usuNombre;
        }

        public void setUsuNombre(String usuNombre) {
            this.usuNombre = usuNombre;
        }

        public String getUsuApellido() {
            return usuApellido;
        }

        public void setUsuApellido(String usuApellido) {
            this.usuApellido = usuApellido;
        }

        public String getComentarioCliente() {
            return comentarioCliente;
        }

        public void setComentarioCliente(String comentarioCliente) {
            this.comentarioCliente = comentarioCliente;
        }

        public String getEstrellasCliente() {
            return estrellasCliente;
        }

        public void setEstrellasCliente(String estrellasCliente) {
            this.estrellasCliente = estrellasCliente;
        }

        public String getUsuRazonSocial() {
            return usuRazonSocial;
        }

        public void setUsuRazonSocial(String usuRazonSocial) {
            this.usuRazonSocial = usuRazonSocial;
        }

        public String getPropuestaTitulo() {
            return propuestaTitulo;
        }

        public void setPropuestaTitulo(String propuestaTitulo) {
            this.propuestaTitulo = propuestaTitulo;
        }

        public String getFechaRegistroComentario() {
            return fechaRegistroComentario;
        }

        public void setFechaRegistroComentario(String fechaRegistroComentario) {
            this.fechaRegistroComentario = fechaRegistroComentario;
        }

        public String getRubroImagen() {
            return rubroImagen;
        }

        public void setRubroImagen(String rubroImagen) {
            this.rubroImagen = rubroImagen;
        }
    }
}
