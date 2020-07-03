package com.application.boxmadikv1.cliente.menu.crearPropuesta.entidad;

import java.util.List;

public class RubrosUi {
    String idRub;
    String descripcionRub;
    String imagenRub;
    String estadoRub;
    String presencialRub;
    boolean estadoRotar;
    List<OficiosUi> oficiosUiList;


    public String getIdRub() {
        return idRub;
    }

    public void setIdRub(String idRub) {
        this.idRub = idRub;
    }

    public String getDescripcionRub() {
        return descripcionRub;
    }

    public void setDescripcionRub(String descripcionRub) {
        this.descripcionRub = descripcionRub;
    }

    public String getImagenRub() {
        return imagenRub;
    }

    public void setImagenRub(String imagenRub) {
        this.imagenRub = imagenRub;
    }

    public String getEstadoRub() {
        return estadoRub;
    }

    public void setEstadoRub(String estadoRub) {
        this.estadoRub = estadoRub;
    }

    public String getPresencialRub() {
        return presencialRub;
    }

    public void setPresencialRub(String presencialRub) {
        this.presencialRub = presencialRub;
    }

    public boolean isEstadoRotar() {
        return estadoRotar;
    }

    public void setEstadoRotar(boolean estadoRotar) {
        this.estadoRotar = estadoRotar;
    }

    public List<OficiosUi> getOficiosUiList() {
        return oficiosUiList;
    }

    public void setOficiosUiList(List<OficiosUi> oficiosUiList) {
        this.oficiosUiList = oficiosUiList;
    }
}
