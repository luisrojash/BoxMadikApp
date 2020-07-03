package com.application.boxmadikv1.bandeja.entidad;

public class BandejaUi {
    private String idUsuarioPropuesta;
    private String idUsuarioCotiza;
    private String idGrupoChat;
    private String tipoUsuario;
    private String imagenRubro;
    private String nombrePropuesta;
    private String nombreReceptor;
    private String mensaje;
    private String fotoReceptor;
    private int conteoMensaje;
    private String estadoGrupoLeido;
    private String estadoPropuesta;
    private String cotiEstado;


    public BandejaUi() {
    }

    public String getIdUsuarioPropuesta() {
        return idUsuarioPropuesta;
    }

    public void setIdUsuarioPropuesta(String idUsuarioPropuesta) {
        this.idUsuarioPropuesta = idUsuarioPropuesta;
    }

    public String getIdUsuarioCotiza() {
        return idUsuarioCotiza;
    }

    public void setIdUsuarioCotiza(String idUsuarioCotiza) {
        this.idUsuarioCotiza = idUsuarioCotiza;
    }

    public String getIdGrupoChat() {
        return idGrupoChat;
    }

    public void setIdGrupoChat(String idGrupoChat) {
        this.idGrupoChat = idGrupoChat;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getImagenRubro() {
        return imagenRubro;
    }

    public void setImagenRubro(String imagenRubro) {
        this.imagenRubro = imagenRubro;
    }

    public String getNombrePropuesta() {
        return nombrePropuesta;
    }

    public void setNombrePropuesta(String nombrePropuesta) {
        this.nombrePropuesta = nombrePropuesta;
    }

    public String getNombreReceptor() {
        return nombreReceptor;
    }

    public void setNombreReceptor(String nombreReceptor) {
        this.nombreReceptor = nombreReceptor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFotoReceptor() {
        return fotoReceptor;
    }

    public void setFotoReceptor(String fotoReceptor) {
        this.fotoReceptor = fotoReceptor;
    }

    public int getConteoMensaje() {
        return conteoMensaje;
    }

    public void setConteoMensaje(int conteoMensaje) {
        this.conteoMensaje = conteoMensaje;
    }

    public String getEstadoGrupoLeido() {
        return estadoGrupoLeido;
    }

    public void setEstadoGrupoLeido(String estadoGrupoLeido) {
        this.estadoGrupoLeido = estadoGrupoLeido;
    }

    public String getEstadoPropuesta() {
        return estadoPropuesta;
    }

    public void setEstadoPropuesta(String estadoPropuesta) {
        this.estadoPropuesta = estadoPropuesta;
    }

    public String getCotiEstado() {
        return cotiEstado;
    }

    public void setCotiEstado(String cotiEstado) {
        this.cotiEstado = cotiEstado;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
