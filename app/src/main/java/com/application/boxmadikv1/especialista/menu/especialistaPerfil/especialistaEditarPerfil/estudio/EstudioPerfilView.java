package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.DatosCursos;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.TipoCentroEstudiosUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.TipoEstudiosUi;

import java.util.List;

public interface EstudioPerfilView extends BaseActivityView<EstudioPerfilPresenter> {
    void mostrarListaAutoCompleteTipoEstudios(List<TipoEstudiosUi> tipoCentroEstudiosUiList);

    void mostrarListaAutoCompleteTipoCentroEstudios(List<TipoCentroEstudiosUi> tipoCentroEstudiosUiList);

    void mostrarMensaje(String mensaje);

    void limpiarEditTextTipoEstudio();

    void limpiarEditTextTipoCentroEstudio();

    void mostrarDialogFechaInicio();

    void mostrarDialogFechaFin();

    void agregarItemDataCurso(DatosCursos datosCursos);

    void mostrarTextTipoEstudio(String s);

    void mostrarTextTipoCentro(String s);

    void elimnarItemCurso(DatosCursos datosCursos);

    void obtenerKeyUser();

    void mostrarProgressBarDialog();

    void ocultarProgressBarDialog();

    void actualizarDataPreferencesConFoto(String nombreEdit, String apellidosEdit, String celularEdit, String usuFoto);

    void iniStartActivityMenuEspecialista(String editPerfilCorrectamenteUsuario);

    void mostrarLista(List<DatosCursos> datosCursosList);

    void limpiarTextPuesto();

    void limpiarTextCentro();

    void limpiartextNombreCurso();



    void limpiarFechaInicio();

    void limpiarFechaFin();

    void deshabilitarText();


    void habilitarButtonGuardar();

    void deshabilitarButtonGuardar();
}
