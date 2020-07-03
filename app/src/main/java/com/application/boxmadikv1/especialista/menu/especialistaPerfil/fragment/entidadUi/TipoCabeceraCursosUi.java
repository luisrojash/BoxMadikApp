package com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.entidadUi;

import com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.adapter.estructura.ColumnaCabeceraCursos;

public class TipoCabeceraCursosUi extends ColumnaCabeceraCursos {

    public enum EnumTipoCabeceraCursoColumna{
        TIPO_CABECERA_CURSOS_VALIDACION, TIPO_CABECERA_CURSOS_TIPO_ESTUDIOS, TIPO_CABECERA_CURSOS_NOMBRE, TIPO_CABECERA_CURSOS_CENTRO_ESTUDIOS,
        TIPO_CABECERA_CURSOS_FECHA_INICIO, TIPO_CABECERA_CURSOS_FECHA_FIN, DEFAULT
    }
   /* public static final int TIPO_CABECERA_CURSOS_VALIDACION = 1;
    public static final int TIPO_CABECERA_CURSOS_TIPO_ESTUDIOS= 2;
    public static final int TIPO_CABECERA_CURSOS_NOMBRE = 3;
    public static final int TIPO_CABECERA_CURSOS_CENTRO_ESTUDIOS = 4;
    public static final int TIPO_CABECERA_CURSOS_FECHA_INICIO = 4;
    public static final int TIPO_CABECERA_CURSOS_FECHA_FIN = 5;*/

    private String descripcion;
    private EnumTipoCabeceraCursoColumna enumTipoCabecera;

    public TipoCabeceraCursosUi() {
        enumTipoCabecera = EnumTipoCabeceraCursoColumna.DEFAULT;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EnumTipoCabeceraCursoColumna getEnumTipoCabecera() {
        return enumTipoCabecera;
    }

    public void setEnumTipoCabecera(EnumTipoCabeceraCursoColumna enumTipoCabecera) {
        this.enumTipoCabecera = enumTipoCabecera;
    }
}
