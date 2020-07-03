package com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog;

import com.application.boxmadikv1.base.BaseView;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.entidadUi.CursosUi;

import java.util.List;

public interface CursosDialogView extends BaseView<CursosDialogPresenter>{
    void mostrarMensaje(String mensaje);

    void mostrarListaCursos(List<CursosUi> cursosUiList);

    void ocultarDialog();
}
