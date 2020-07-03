package com.application.boxmadikv1.especialista.menu.seleccionRubros.entidad;

public class SeleccionRubrosUi {
    String idSeleccionRubroId;
    String descripcion;
    String imageRubro;
    boolean estadoRubro;

    public SeleccionRubrosUi() {
    }

    public String getIdSeleccionRubroId() {
        return idSeleccionRubroId;
    }

    public void setIdSeleccionRubroId(String idSeleccionRubroId) {
        this.idSeleccionRubroId = idSeleccionRubroId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImageRubro() {
        return imageRubro;
    }

    public void setImageRubro(String imageRubro) {
        this.imageRubro = imageRubro;
    }

    public boolean isEstadoRubro() {
        return estadoRubro;
    }

    public void setEstadoRubro(boolean estadoRubro) {
        this.estadoRubro = estadoRubro;
    }
}
