package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.presentacion;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.entidad.DireccionUi;

public interface PresentacionPerfilView extends BaseActivityView<PresentacionPerfilPresenter> {
    void mostrarMensajeErrorTextPresentacion(String seleccione_una_direccion_correcta);

    void initStartActivityCursos(DireccionUi direccionUi, String presentacion);

    void mostrarMensaje(String mensajeError);

    void mostrarImagenTextSinValidar();

    void mostrarImagenTextValidado();

    void obtenerKeyUser();

    void mostrarPresentacion(String usuPresentacion);

    void deshabilitaTextPresentacion();

    void habilitaTextPresentacion();

    void initStartActivityEspecEditPerfil(String editPerfilCorrectamentePresentacion);

    void mostrarDialogProgressBar();

    void ocultarDialogProgressBar();

    void habilitarButtonGuardar();

    void deshabilitarButtonGuardar();
}
