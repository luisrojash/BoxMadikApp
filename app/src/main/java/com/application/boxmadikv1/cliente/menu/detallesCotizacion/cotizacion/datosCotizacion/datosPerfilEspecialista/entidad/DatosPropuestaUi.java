package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.entidad;

import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.entidadUi.CursosUi;

import java.util.List;

public class DatosPropuestaUi {
    private String nombrePropuesta;
    private String fechaPropuesta;
    private String detallePropuesta;
    private String fotoCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String estrellasEspec;
    private String comentarioEspec;
    private String imagenRubro;
    private String keyUser;
    private String usuRazonSocial;
    private List<CursosUi> cursosUiList;

    public DatosPropuestaUi() {
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

    public String getImagenRubro() {
        return imagenRubro;
    }

    public void setImagenRubro(String imagenRubro) {
        this.imagenRubro = imagenRubro;
    }

    public String getKeyUser() {
        return keyUser;
    }

    public void setKeyUser(String keyUser) {
        this.keyUser = keyUser;
    }

    public List<CursosUi> getCursosUiList() {
        return cursosUiList;
    }

    public String getUsuRazonSocial() {
        return usuRazonSocial;
    }

    public void setUsuRazonSocial(String usuRazonSocial) {
        this.usuRazonSocial = usuRazonSocial;
    }

    public void setCursosUiList(List<CursosUi> cursosUiList) {
        this.cursosUiList = cursosUiList;
    }
}
