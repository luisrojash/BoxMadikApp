package com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.entidadUi;

import com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.adapter.estructura.CeldasCursos;

public class CeldasResutadoUi extends CeldasCursos {

    private String descripcion;

    public CeldasResutadoUi(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
