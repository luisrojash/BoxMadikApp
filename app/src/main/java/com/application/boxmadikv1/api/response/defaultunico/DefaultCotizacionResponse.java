package com.application.boxmadikv1.api.response.defaultunico;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*Clase Default Response Unica Para Cotizacion Cuando se Crea - que lleva a la pantalla de Detalles de Cotizacion
                                    del usuario que realizo la Cotizacion*/
public class DefaultCotizacionResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String mensaje;
    @SerializedName("CotizacionCreada")
    @Expose
    DefaultCotizacionResponse.CotizaCreadaResponse cotizaCreadaResponse;

    public DefaultCotizacionResponse() {
    }

    public DefaultCotizacionResponse(Boolean error, String mensaje, CotizaCreadaResponse cotizaCreadaResponse) {
        this.error = error;
        this.mensaje = mensaje;
        this.cotizaCreadaResponse = cotizaCreadaResponse;
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

    public CotizaCreadaResponse getCotizaCreadaResponse() {
        return cotizaCreadaResponse;
    }

    public void setCotizaCreadaResponse(CotizaCreadaResponse cotizaCreadaResponse) {
        this.cotizaCreadaResponse = cotizaCreadaResponse;
    }

    public class CotizaCreadaResponse{
        private String codigoPropuesta;
        private String codigoCotizacion;
        private String estadoCotizacion;
        private String codigoUsuarioCotizacion;
        private String usu_foto;
        private String usu_nombre;
        private String usu_apellidos;
        private String coti_fecha;
        private String coti_monto_inicial;
        private String estrellasUsuario;
        private String cotiDescripcion;
        private String cotiPendiente;
        private String cotiFinalizado;
        private String cotiAceptado;
        private String usuDireccion;
        private String paisImagen;
        private String pais_codigo;

        private String tituloPropuesta;
        private String detallePropuesta;
        private String fechaPropuesta;
        private String estadoPropuesta;
        private String codigoUsuPropuesta;
        private String departamentoDescripcion;
        private String distritoDescripcion;
        private String imagenRubro;
        private String usuRazonSocial;

        public String getCodigoPropuesta() {
            return codigoPropuesta;
        }

        public void setCodigoPropuesta(String codigoPropuesta) {
            this.codigoPropuesta = codigoPropuesta;
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

        public String getUsu_foto() {
            return usu_foto;
        }

        public void setUsu_foto(String usu_foto) {
            this.usu_foto = usu_foto;
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

        public String getTituloPropuesta() {
            return tituloPropuesta;
        }

        public void setTituloPropuesta(String tituloPropuesta) {
            this.tituloPropuesta = tituloPropuesta;
        }

        public String getDetallePropuesta() {
            return detallePropuesta;
        }

        public void setDetallePropuesta(String detallePropuesta) {
            this.detallePropuesta = detallePropuesta;
        }

        public String getFechaPropuesta() {
            return fechaPropuesta;
        }

        public void setFechaPropuesta(String fechaPropuesta) {
            this.fechaPropuesta = fechaPropuesta;
        }

        public String getEstadoPropuesta() {
            return estadoPropuesta;
        }

        public void setEstadoPropuesta(String estadoPropuesta) {
            this.estadoPropuesta = estadoPropuesta;
        }

        public String getCodigoUsuPropuesta() {
            return codigoUsuPropuesta;
        }

        public void setCodigoUsuPropuesta(String codigoUsuPropuesta) {
            this.codigoUsuPropuesta = codigoUsuPropuesta;
        }

        public String getDepartamentoDescripcion() {
            return departamentoDescripcion;
        }

        public void setDepartamentoDescripcion(String departamentoDescripcion) {
            this.departamentoDescripcion = departamentoDescripcion;
        }

        public String getDistritoDescripcion() {
            return distritoDescripcion;
        }

        public void setDistritoDescripcion(String distritoDescripcion) {
            this.distritoDescripcion = distritoDescripcion;
        }

        public String getImagenRubro() {
            return imagenRubro;
        }

        public void setImagenRubro(String imagenRubro) {
            this.imagenRubro = imagenRubro;
        }

        public String getPais_codigo() {
            return pais_codigo;
        }

        public void setPais_codigo(String pais_codigo) {
            this.pais_codigo = pais_codigo;
        }

        public String getUsuRazonSocial() {
            return usuRazonSocial;
        }

        public void setUsuRazonSocial(String usuRazonSocial) {
            this.usuRazonSocial = usuRazonSocial;
        }
    }
}
