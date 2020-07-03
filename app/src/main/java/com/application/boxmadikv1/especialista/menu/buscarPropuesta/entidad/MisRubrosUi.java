package com.application.boxmadikv1.especialista.menu.buscarPropuesta.entidad;

public class MisRubrosUi {
    public static final int DEFAULT_MIS_RUBROS = 0;
    public static final int MIS_RUBROS_NORMAL = 1;
    int tipoRubro;
    String idMisRubros;
    String descripcion;
    String imagenRubros;
    boolean estadoCheck;

    public MisRubrosUi() {
    }

    public int getTipoRubro() {
        return tipoRubro;
    }

    public void setTipoRubro(int tipoRubro) {
        this.tipoRubro = tipoRubro;
    }

    public String getIdMisRubros() {
        return idMisRubros;
    }

    public void setIdMisRubros(String idMisRubros) {
        this.idMisRubros = idMisRubros;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagenRubros() {
        return imagenRubros;
    }

    public void setImagenRubros(String imagenRubros) {
        this.imagenRubros = imagenRubros;
    }

    public boolean isEstadoCheck() {
        return estadoCheck;
    }

    public void setEstadoCheck(boolean estadoCheck) {
        this.estadoCheck = estadoCheck;
    }
}
