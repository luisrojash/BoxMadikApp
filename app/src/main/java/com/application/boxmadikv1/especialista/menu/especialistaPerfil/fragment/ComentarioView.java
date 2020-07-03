package com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.adapter.estructura.CeldasCursos;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.adapter.estructura.ColumnaCabeceraCursos;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.adapter.estructura.FilaCabeceraCursos;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.entidadUi.ComentariosUi;

import java.util.List;

public interface ComentarioView extends BaseActivityView<ComentarioPresenter> {
    void mostrarDataInicial(ComentariosUi comentariosUi);

    void mostrarMensaje(String mensaje);

    void mostrarListaTablas(List<ColumnaCabeceraCursos> columnHeaderList, List<FilaCabeceraCursos> rowHeaderList, List<List<CeldasCursos>> cellsList);

    void initListenerCursos(String keyUser);
}
