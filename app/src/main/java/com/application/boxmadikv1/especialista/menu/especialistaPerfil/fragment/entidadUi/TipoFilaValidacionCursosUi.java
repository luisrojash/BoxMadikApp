package com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.entidadUi;

import com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.adapter.estructura.FilaCabeceraCursos;

public class TipoFilaValidacionCursosUi extends FilaCabeceraCursos {
    public enum EnumTipoValidacionCursoFila {FILAS_CURSOS_VALIDADO, FILAS_CURSOS_SIN_VALIDAR, DEFAULT}

    private String descripcion;
    private EnumTipoValidacionCursoFila enumTipoValidacionCursoFila;

    public TipoFilaValidacionCursosUi() {
        this.enumTipoValidacionCursoFila = EnumTipoValidacionCursoFila.DEFAULT;
    }

    /*public TipoFilaValidacionCursosUi(String descripcion) {
        this.descripcion = descripcion;
        this.enumTipoValidacionCursoFila = EnumTipoValidacionCursoFila.DEFAULT;
    }*/

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EnumTipoValidacionCursoFila getEnumTipoValidacionCursoFila() {
        return enumTipoValidacionCursoFila;
    }

    public void setEnumTipoValidacionCursoFila(EnumTipoValidacionCursoFila enumTipoValidacionCursoFila) {
        this.enumTipoValidacionCursoFila = enumTipoValidacionCursoFila;
    }
}
