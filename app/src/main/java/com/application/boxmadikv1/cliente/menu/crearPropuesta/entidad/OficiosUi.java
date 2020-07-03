package com.application.boxmadikv1.cliente.menu.crearPropuesta.entidad;

public class OficiosUi {
    String idOficio;
    String descripcion;
    String estado;
    RubrosUi rubrosUi;

    public OficiosUi() {
    }

    public String getIdOficio() {
        return idOficio;
    }

    public void setIdOficio(String idOficio) {
        this.idOficio = idOficio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public RubrosUi getRubrosUi() {
        return rubrosUi;
    }

    public void setRubrosUi(RubrosUi rubrosUi) {
        this.rubrosUi = rubrosUi;
    }
}
