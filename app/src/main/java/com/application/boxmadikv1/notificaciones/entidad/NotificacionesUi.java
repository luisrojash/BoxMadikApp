package com.application.boxmadikv1.notificaciones.entidad;

public class NotificacionesUi {
    private String id;
    private String titulo;
    private String subTitulo;
    private String SubbbTitulo;
    private String fecha;
    private String imagenNotificacion;
    private boolean estadoLeido;
    private String tipoNotificacion;
    private String codigoTipoNotificacion;
    private String codigoGrupoNotificacion;
    private String codigoPropuesta;
    private String codigoUsuarioPropuesta;
    private String codigoUsuarioCotizacion;
    private String propuestaEstado;
    private String cotizacionEstado;
    private String usuCodigoDocu;


    public NotificacionesUi() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    public String getSubbbTitulo() {
        return SubbbTitulo;
    }

    public void setSubbbTitulo(String subbbTitulo) {
        SubbbTitulo = subbbTitulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getImagenNotificacion() {
        return imagenNotificacion;
    }

    public void setImagenNotificacion(String imagenNotificacion) {
        this.imagenNotificacion = imagenNotificacion;
    }

    public boolean isEstadoLeido() {
        return estadoLeido;
    }

    public void setEstadoLeido(boolean estadoLeido) {
        this.estadoLeido = estadoLeido;
    }

    public String getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(String tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public String getCodigoTipoNotificacion() {
        return codigoTipoNotificacion;
    }

    public void setCodigoTipoNotificacion(String codigoTipoNotificacion) {
        this.codigoTipoNotificacion = codigoTipoNotificacion;
    }

    public String getCodigoGrupoNotificacion() {
        return codigoGrupoNotificacion;
    }

    public void setCodigoGrupoNotificacion(String codigoGrupoNotificacion) {
        this.codigoGrupoNotificacion = codigoGrupoNotificacion;
    }

    public String getCodigoPropuesta() {
        return codigoPropuesta;
    }

    public void setCodigoPropuesta(String codigoPropuesta) {
        this.codigoPropuesta = codigoPropuesta;
    }

    public String getCodigoUsuarioPropuesta() {
        return codigoUsuarioPropuesta;
    }

    public void setCodigoUsuarioPropuesta(String codigoUsuarioPropuesta) {
        this.codigoUsuarioPropuesta = codigoUsuarioPropuesta;
    }

    public String getCodigoUsuarioCotizacion() {
        return codigoUsuarioCotizacion;
    }

    public void setCodigoUsuarioCotizacion(String codigoUsuarioCotizacion) {
        this.codigoUsuarioCotizacion = codigoUsuarioCotizacion;
    }

    public String getPropuestaEstado() {
        return propuestaEstado;
    }

    public void setPropuestaEstado(String propuestaEstado) {
        this.propuestaEstado = propuestaEstado;
    }

    public String getCotizacionEstado() {
        return cotizacionEstado;
    }

    public void setCotizacionEstado(String cotizacionEstado) {
        this.cotizacionEstado = cotizacionEstado;
    }

    public String getUsuCodigoDocu() {
        return usuCodigoDocu;
    }

    public void setUsuCodigoDocu(String usuCodigoDocu) {
        this.usuCodigoDocu = usuCodigoDocu;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
