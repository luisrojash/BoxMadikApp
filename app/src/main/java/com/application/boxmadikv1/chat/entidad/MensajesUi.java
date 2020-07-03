package com.application.boxmadikv1.chat.entidad;

public class MensajesUi {
    private String codigoMensaje;
    private String mensaje;
    private String fecha;
    private String codigoUsuario;
    // private String codigoEspecialista;
    private String fotoUsuario;
    private String nombreUsuario;
    private String tipoEstadoMensaje;

    public MensajesUi() {
    }

    public String getCodigoMensaje() {
        return codigoMensaje;
    }

    public void setCodigoMensaje(String codigoMensaje) {
        this.codigoMensaje = codigoMensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTipoEstadoMensaje() {
        return tipoEstadoMensaje;
    }

    public void setTipoEstadoMensaje(String tipoEstadoMensaje) {
        this.tipoEstadoMensaje = tipoEstadoMensaje;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
