package com.application.boxmadikv1.api.response.cliente;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListaCotizaResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("cotizacionesCliente")
    @Expose
    private List<CotizacionesResponse> cotizacionesResponseList;

    public ListaCotizaResponse(Boolean error, String message, List<CotizacionesResponse> cotizacionesResponseList) {
        this.error = error;
        this.message = message;
        this.cotizacionesResponseList = cotizacionesResponseList;
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

    public List<CotizacionesResponse> getCotizacionesResponseList() {
        return cotizacionesResponseList;
    }

    public void setCotizacionesResponseList(List<CotizacionesResponse> cotizacionesResponseList) {
        this.cotizacionesResponseList = cotizacionesResponseList;
    }

    public class CotizacionesResponse {
        private String codigoPropuesta;
        private String estadoPropuesta;
        private String codigoCotizacion;
        private String estadoCotizacion;
        private String codigoUsuarioCotizacion;
        private String usu_nombre;
        private String usu_apellidos;
        private String usu_foto;
        private String coti_fecha;
        private String coti_monto_inicial;
        private String estrellasUsuario;
        private String cotiDescripcion;
        private String cotiPendiente;
        private String cotiFinalizado;
        private String cotiAceptado;
        private String usuDireccion;
        private String paisImagen;
        private String usuCelular;
        private String usuEmail;
        private String usuRazonSocial;
        private String usuDepartamento;
        private String usuDistrito;

        public CotizacionesResponse(String codigoPropuesta, String estadoPropuesta,
                                    String codigoCotizacion, String estadoCotizacion,
                                    String codigoUsuarioCotizacion, String usu_nombre,
                                    String usu_apellidos, String usu_foto, String coti_fecha,
                                    String coti_monto_inicial, String estrellasUsuario,
                                    String cotiDescripcion, String cotiPendiente,
                                    String cotiFinalizado, String cotiAceptado,
                                    String usuDireccion, String paisImagen,
                                    String usuCelular, String usuEmail,
                                    String usuRazonSocial) {
            this.codigoPropuesta = codigoPropuesta;
            this.estadoPropuesta = estadoPropuesta;
            this.codigoCotizacion = codigoCotizacion;
            this.estadoCotizacion = estadoCotizacion;
            this.codigoUsuarioCotizacion = codigoUsuarioCotizacion;
            this.usu_nombre = usu_nombre;
            this.usu_apellidos = usu_apellidos;
            this.usu_foto = usu_foto;
            this.coti_fecha = coti_fecha;
            this.coti_monto_inicial = coti_monto_inicial;
            this.estrellasUsuario = estrellasUsuario;
            this.cotiDescripcion = cotiDescripcion;
            this.cotiPendiente = cotiPendiente;
            this.cotiFinalizado = cotiFinalizado;
            this.cotiAceptado = cotiAceptado;
            this.usuDireccion = usuDireccion;
            this.paisImagen = paisImagen;
            this.usuCelular = usuCelular;
            this.usuEmail = usuEmail;
            this.usuRazonSocial = usuRazonSocial;
        }

        public String getCodigoPropuesta() {
            return codigoPropuesta;
        }

        public void setCodigoPropuesta(String codigoPropuesta) {
            this.codigoPropuesta = codigoPropuesta;
        }

        public String getEstadoPropuesta() {
            return estadoPropuesta;
        }

        public void setEstadoPropuesta(String estadoPropuesta) {
            this.estadoPropuesta = estadoPropuesta;
        }

        public String getCodigoCotizacion() {
            return codigoCotizacion;
        }

        public void setCodigoCotizacion(String codigoCotizacion) {
            this.codigoCotizacion = codigoCotizacion;
        }

        public String getEstadoCotizacion() {
            return estadoCotizacion;
        }

        public void setEstadoCotizacion(String estadoCotizacion) {
            this.estadoCotizacion = estadoCotizacion;
        }

        public String getCodigoUsuarioCotizacion() {
            return codigoUsuarioCotizacion;
        }

        public void setCodigoUsuarioCotizacion(String codigoUsuarioCotizacion) {
            this.codigoUsuarioCotizacion = codigoUsuarioCotizacion;
        }

        public String getUsu_nombre() {
            return usu_nombre;
        }

        public void setUsu_nombre(String usu_nombre) {
            this.usu_nombre = usu_nombre;
        }

        public String getUsu_apellidos() {
            return usu_apellidos;
        }

        public void setUsu_apellidos(String usu_apellidos) {
            this.usu_apellidos = usu_apellidos;
        }

        public String getUsu_foto() {
            return usu_foto;
        }

        public void setUsu_foto(String usu_foto) {
            this.usu_foto = usu_foto;
        }

        public String getCoti_fecha() {
            return coti_fecha;
        }

        public void setCoti_fecha(String coti_fecha) {
            this.coti_fecha = coti_fecha;
        }

        public String getCoti_monto_inicial() {
            return coti_monto_inicial;
        }

        public void setCoti_monto_inicial(String coti_monto_inicial) {
            this.coti_monto_inicial = coti_monto_inicial;
        }

        public String getEstrellasUsuario() {
            return estrellasUsuario;
        }

        public void setEstrellasUsuario(String estrellasUsuario) {
            this.estrellasUsuario = estrellasUsuario;
        }

        public String getCotiDescripcion() {
            return cotiDescripcion;
        }

        public void setCotiDescripcion(String cotiDescripcion) {
            this.cotiDescripcion = cotiDescripcion;
        }

        public String getCotiPendiente() {
            return cotiPendiente;
        }

        public void setCotiPendiente(String cotiPendiente) {
            this.cotiPendiente = cotiPendiente;
        }

        public String getCotiFinalizado() {
            return cotiFinalizado;
        }

        public void setCotiFinalizado(String cotiFinalizado) {
            this.cotiFinalizado = cotiFinalizado;
        }

        public String getCotiAceptado() {
            return cotiAceptado;
        }

        public void setCotiAceptado(String cotiAceptado) {
            this.cotiAceptado = cotiAceptado;
        }

        public String getUsuDireccion() {
            return usuDireccion;
        }

        public void setUsuDireccion(String usuDireccion) {
            this.usuDireccion = usuDireccion;
        }

        public String getPaisImagen() {
            return paisImagen;
        }

        public void setPaisImagen(String paisImagen) {
            this.paisImagen = paisImagen;
        }

        public String getUsuCelular() {
            return usuCelular;
        }

        public void setUsuCelular(String usuCelular) {
            this.usuCelular = usuCelular;
        }

        public String getUsuEmail() {
            return usuEmail;
        }

        public void setUsuEmail(String usuEmail) {
            this.usuEmail = usuEmail;
        }

        public String getUsuRazonSocial() {
            return usuRazonSocial;
        }

        public void setUsuRazonSocial(String usuRazonSocial) {
            this.usuRazonSocial = usuRazonSocial;
        }

        public String getUsuDepartamento() {
            return usuDepartamento;
        }

        public void setUsuDepartamento(String usuDepartamento) {
            this.usuDepartamento = usuDepartamento;
        }

        public String getUsuDistrito() {
            return usuDistrito;
        }

        public void setUsuDistrito(String usuDistritito) {
            this.usuDistrito = usuDistritito;
        }
    }
}
