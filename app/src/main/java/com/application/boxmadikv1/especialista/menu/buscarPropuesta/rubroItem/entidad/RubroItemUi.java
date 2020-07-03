package com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.entidad;

public class RubroItemUi {
    String rubroItemId;
    String descripcion;
    String imagenRubro;
    boolean estadoRubro;

    public RubroItemUi() {
    }

    public String getRubroItemId() {
        return rubroItemId;
    }

    public void setRubroItemId(String rubroItemId) {
        this.rubroItemId = rubroItemId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenRubro() {
        return imagenRubro;
    }

    public void setImagenRubro(String imagenRubro) {
        this.imagenRubro = imagenRubro;
    }

    public boolean isEstadoRubro() {
        return estadoRubro;
    }

    public void setEstadoRubro(boolean estadoRubro) {
        this.estadoRubro = estadoRubro;
    }
}
