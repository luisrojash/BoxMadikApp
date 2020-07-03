package com.application.boxmadikv1.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListaChatGrupoResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("chatGrupos")
    @Expose
    private List<ListaChatGrupoResp> listaChatGrupoResps;

    public ListaChatGrupoResponse(Boolean error, String message, List<ListaChatGrupoResp> listaChatGrupoResps) {
        this.error = error;
        this.message = message;
        this.listaChatGrupoResps = listaChatGrupoResps;
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

    public List<ListaChatGrupoResp> getListaChatGrupoResps() {
        return listaChatGrupoResps;
    }

    public void setListaChatGrupoResps(List<ListaChatGrupoResp> listaChatGrupoResps) {
        this.listaChatGrupoResps = listaChatGrupoResps;
    }

    public class ListaChatGrupoResp {

        private String codigoGrupoChat;
        private String mensajeChat;
        private String codigoUsuarioPropuesta;
        private String codigoUsuCotiza;
        private String nombreUsuario;
        private String apellidoUsuario;
        private String tituloPropuesta;
        private String imagenRubro;
        private String usuFoto;
        private String estadoChatGrupo;
        private String mensajesNoLeidos;
        private String propuestaEstado;
        private String cotiEstado;
        private String usuNombreRazonSocial;

        public ListaChatGrupoResp() {
        }

        public String getCodigoGrupoChat() {
            return codigoGrupoChat;
        }

        public void setCodigoGrupoChat(String codigoGrupoChat) {
            this.codigoGrupoChat = codigoGrupoChat;
        }

        public String getMensajeChat() {
            return mensajeChat;
        }

        public void setMensajeChat(String mensajeChat) {
            this.mensajeChat = mensajeChat;
        }

        public String getCodigoUsuarioPropuesta() {
            return codigoUsuarioPropuesta;
        }

        public void setCodigoUsuarioPropuesta(String codigoUsuarioPropuesta) {
            this.codigoUsuarioPropuesta = codigoUsuarioPropuesta;
        }

        public String getCodigoUsuCotiza() {
            return codigoUsuCotiza;
        }

        public void setCodigoUsuCotiza(String codigoUsuCotiza) {
            this.codigoUsuCotiza = codigoUsuCotiza;
        }

        public String getNombreUsuario() {
            return nombreUsuario;
        }

        public void setNombreUsuario(String nombreUsuario) {
            this.nombreUsuario = nombreUsuario;
        }

        public String getApellidoUsuario() {
            return apellidoUsuario;
        }

        public void setApellidoUsuario(String apellidoUsuario) {
            this.apellidoUsuario = apellidoUsuario;
        }

        public String getTituloPropuesta() {
            return tituloPropuesta;
        }

        public void setTituloPropuesta(String tituloPropuesta) {
            this.tituloPropuesta = tituloPropuesta;
        }

        public String getImagenRubro() {
            return imagenRubro;
        }

        public void setImagenRubro(String imagenRubro) {
            this.imagenRubro = imagenRubro;
        }

        public String getUsuFoto() {
            return usuFoto;
        }

        public void setUsuFoto(String usuFoto) {
            this.usuFoto = usuFoto;
        }

        public String getEstadoChatGrupo() {
            return estadoChatGrupo;
        }

        public void setEstadoChatGrupo(String estadoChatGrupo) {
            this.estadoChatGrupo = estadoChatGrupo;
        }

        public String getMensajesNoLeidos() {
            return mensajesNoLeidos;
        }

        public void setMensajesNoLeidos(String mensajesNoLeidos) {
            this.mensajesNoLeidos = mensajesNoLeidos;
        }

        public String getPropuestaEstado() {
            return propuestaEstado;
        }

        public void setPropuestaEstado(String propuestaEstado) {
            this.propuestaEstado = propuestaEstado;
        }

        public String getCotiEstado() {
            return cotiEstado;
        }

        public void setCotiEstado(String cotiEstado) {
            this.cotiEstado = cotiEstado;
        }

        public String getUsuNombreRazonSocial() {
            return usuNombreRazonSocial;
        }

        public void setUsuNombreRazonSocial(String usuNombreRazonSocial) {
            this.usuNombreRazonSocial = usuNombreRazonSocial;
        }
    }


}
