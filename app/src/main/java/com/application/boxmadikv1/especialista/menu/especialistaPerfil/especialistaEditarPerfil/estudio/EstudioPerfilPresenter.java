package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.DatosCursos;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.TipoCentroEstudiosUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.TipoEstudiosUi;

public interface EstudioPerfilPresenter extends BaseActivityPresenter<EstudioPerfilView> {

    void onClickAgregarItem(String nombreCurso);

    void onClickAutoCompleteTipoEstudio(TipoEstudiosUi tipoEstudiosUi);

    void onClickAutoCompleteTipoCentro(TipoCentroEstudiosUi tipoCentroEstudiosUi);

    void onClickFechaInicio();

    void onClickFechaFin();

    void onAceptarDateInicio(String dateTime,String nombreMes,String anio);

    void onAceptarDateFin(String dateTime,String nombreMes,String anio);

    void onLongClickCursoEliminar(DatosCursos datosCursos);

    void onKeyUser(String keyUser,String userFoto,String paisCodigo);

    void onClickGuardar();

    void onClickDeletePuesto();

    void onClickDeleteCentro();
}
