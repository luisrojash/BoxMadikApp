package com.application.boxmadikv1.api.response.especialista;

import com.application.boxmadikv1.especialista.menu.abstracto.entidad.EspecialistaEstadosUi;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.entidad.EspecialistaEnviadosUi;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListaCotizacionesResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String mensaje;
    @SerializedName("cotizaciones")
    @Expose
    private List<CotizacionesResponse> cotizacionesResponseList;

    List<EspecialistaEnviadosUi> especialistaEnviadosUis;

    List<EspecialistaEstadosUi> especialistaEstadosUis;

    public ListaCotizacionesResponse(Boolean error, String mensaje, List<CotizacionesResponse> cotizacionesResponseList, List<EspecialistaEnviadosUi> especialistaEnviadosUis, List<EspecialistaEstadosUi> especialistaEstadosUis) {
        this.error = error;
        this.mensaje = mensaje;
        this.cotizacionesResponseList = cotizacionesResponseList;
        this.especialistaEnviadosUis = especialistaEnviadosUis;
        this.especialistaEstadosUis = especialistaEstadosUis;
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

    public List<CotizacionesResponse> getCotizacionesResponseList() {
        return cotizacionesResponseList;
    }

    public void setCotizacionesResponseList(List<CotizacionesResponse> cotizacionesResponseList) {
        this.cotizacionesResponseList = cotizacionesResponseList;
    }

    public List<EspecialistaEnviadosUi> getEspecialistaEnviadosUis() {
        return especialistaEnviadosUis;
    }

    public void setEspecialistaEnviadosUis(List<EspecialistaEnviadosUi> especialistaEnviadosUis) {
        this.especialistaEnviadosUis = especialistaEnviadosUis;
    }

    public List<EspecialistaEstadosUi> getEspecialistaEstadosUis() {
        return especialistaEstadosUis;
    }

    public void setEspecialistaEstadosUis(List<EspecialistaEstadosUi> especialistaEstadosUis) {
        this.especialistaEstadosUis = especialistaEstadosUis;
    }

    public class CotizacionesResponse {
        /*private String detallePropuesta;
        private String codigoRangoPrecio;
        private String codigoRangoDias;
        private String codigoRangoTurno;
        private String codigoOficio;
        private String codigoPropuesta;
        private String codigoCotizacion;
        private String titulo;
        private String cotiFecha;
        private String rangoPrecioCodigo;
        private String codigoRubro;
        private String numeroCotizacion;
        private String promedioCotizacion;
        private String montoCotizado;
        private String codigoUsuarioCreaPropuesta;*/

        private String tituloPropuesta;
        private String detallePropuesta;
        private String cotiFecha;
        private String codigoRangoPrecio;
        private String codigoRangoDias;
        private String codigoRangoTurno;
        private String codigoRubro;
        private String codigoOficio;
        private String numerCotizacion;
        private String promedioCotizacion;
        private String estadoCotizacion;
        private String estadoPropuesta;
        private String simboloCotizacion;
        private String isoCotizacion;
        private String codigoPropuesta;
        private String codigoCotizacion;
        private String codigoUsuRevocaCrea;
        private String codigoUsuRevocaResp;
        private String usuarioCreaPropuesta;
        private String departamentoNombre;
        private String provinciaNombre;
        private String distritoNombre;
        private String cotiEstado;
        private String codigoPropuestaUsuario;
        private String codigoCotiUsuario;
        private String cotiEstadoCalifica;
        //Son Datos del Cliente
        private String usuNombre;
        private String usuAPellidos;
        private String usuRazonSocial;
        private String usuFoto;
        private String paisImagen;
        private String estadoRevocacion;
        private String ofiMonto;

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

        public String getCotiFecha() {
            return cotiFecha;
        }

        public void setCotiFecha(String cotiFecha) {
            this.cotiFecha = cotiFecha;
        }

        public String getCodigoRangoPrecio() {
            return codigoRangoPrecio;
        }

        public void setCodigoRangoPrecio(String codigoRangoPrecio) {
            this.codigoRangoPrecio = codigoRangoPrecio;
        }

        public String getCodigoRangoDias() {
            return codigoRangoDias;
        }

        public void setCodigoRangoDias(String codigoRangoDias) {
            this.codigoRangoDias = codigoRangoDias;
        }

        public String getCodigoRangoTurno() {
            return codigoRangoTurno;
        }

        public void setCodigoRangoTurno(String codigoRangoTurno) {
            this.codigoRangoTurno = codigoRangoTurno;
        }

        public String getCodigoRubro() {
            return codigoRubro;
        }

        public void setCodigoRubro(String codigoRubro) {
            this.codigoRubro = codigoRubro;
        }

        public String getCodigoOficio() {
            return codigoOficio;
        }

        public void setCodigoOficio(String codigoOficio) {
            this.codigoOficio = codigoOficio;
        }

        public String getNumerCotizacion() {
            return numerCotizacion;
        }

        public void setNumerCotizacion(String numerCotizacion) {
            this.numerCotizacion = numerCotizacion;
        }

        public String getPromedioCotizacion() {
            return promedioCotizacion;
        }

        public void setPromedioCotizacion(String promedioCotizacion) {
            this.promedioCotizacion = promedioCotizacion;
        }

        public String getEstadoCotizacion() {
            return estadoCotizacion;
        }

        public void setEstadoCotizacion(String estadoCotizacion) {
            this.estadoCotizacion = estadoCotizacion;
        }

        public String getEstadoPropuesta() {
            return estadoPropuesta;
        }

        public void setEstadoPropuesta(String estadoPropuesta) {
            this.estadoPropuesta = estadoPropuesta;
        }

        public String getSimboloCotizacion() {
            return simboloCotizacion;
        }

        public void setSimboloCotizacion(String simboloCotizacion) {
            this.simboloCotizacion = simboloCotizacion;
        }

        public String getIsoCotizacion() {
            return isoCotizacion;
        }

        public void setIsoCotizacion(String isoCotizacion) {
            this.isoCotizacion = isoCotizacion;
        }

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

        public String getCodigoUsuRevocaCrea() {
            return codigoUsuRevocaCrea;
        }

        public void setCodigoUsuRevocaCrea(String codigoUsuRevocaCrea) {
            this.codigoUsuRevocaCrea = codigoUsuRevocaCrea;
        }

        public String getCodigoUsuRevocaResp() {
            return codigoUsuRevocaResp;
        }

        public void setCodigoUsuRevocaResp(String codigoUsuRevocaResp) {
            this.codigoUsuRevocaResp = codigoUsuRevocaResp;
        }

        public String getUsuarioCreaPropuesta() {
            return usuarioCreaPropuesta;
        }

        public void setUsuarioCreaPropuesta(String usuarioCreaPropuesta) {
            this.usuarioCreaPropuesta = usuarioCreaPropuesta;
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

        public String getCotiEstado() {
            return cotiEstado;
        }

        public void setCotiEstado(String cotiEstado) {
            this.cotiEstado = cotiEstado;
        }

        public String getCodigoPropuestaUsuario() {
            return codigoPropuestaUsuario;
        }

        public void setCodigoPropuestaUsuario(String codigoPropuestaUsuario) {
            this.codigoPropuestaUsuario = codigoPropuestaUsuario;
        }

        public String getCodigoCotiUsuario() {
            return codigoCotiUsuario;
        }

        public void setCodigoCotiUsuario(String codigoCotiUsuario) {
            this.codigoCotiUsuario = codigoCotiUsuario;
        }

        public String getCotiEstadoCalifica() {
            return cotiEstadoCalifica;
        }

        public void setCotiEstadoCalifica(String cotiEstadoCalifica) {
            this.cotiEstadoCalifica = cotiEstadoCalifica;
        }

        public String getUsuNombre() {
            return usuNombre;
        }

        public void setUsuNombre(String usuNombre) {
            this.usuNombre = usuNombre;
        }

        public String getUsuAPellidos() {
            return usuAPellidos;
        }

        public void setUsuAPellidos(String usuAPellidos) {
            this.usuAPellidos = usuAPellidos;
        }

        public String getUsuRazonSocial() {
            return usuRazonSocial;
        }

        public void setUsuRazonSocial(String usuRazonSocial) {
            this.usuRazonSocial = usuRazonSocial;
        }

        public String getUsuFoto() {
            return usuFoto;
        }

        public void setUsuFoto(String usuFoto) {
            this.usuFoto = usuFoto;
        }

        public String getPaisImagen() {
            return paisImagen;
        }

        public void setPaisImagen(String paisImagen) {
            this.paisImagen = paisImagen;
        }

        public String getEstadoRevocacion() {
            return estadoRevocacion;
        }

        public void setEstadoRevocacion(String estadoRevocacion) {
            this.estadoRevocacion = estadoRevocacion;
        }

        public String getOfiMonto() {
            return ofiMonto;
        }

        public void setOfiMonto(String ofiMonto) {
            this.ofiMonto = ofiMonto;
        }
    }
}
