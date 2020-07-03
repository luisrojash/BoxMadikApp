package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion;


import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.entidad.TipoMotivoRevocacionUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.entidad.TipoSolucionRevocacionUi;

import java.util.List;

public interface RevocacionView extends BaseActivityView<RevocacionPresenter> {

    void mostrarProcentajeTrabajo(int value);

    void mostrarListaMotivoRevocacion(List<TipoMotivoRevocacionUi> tipoMotivoRevocacionUiList);

    void mostrarListaSolucionRevocacion(List<TipoSolucionRevocacionUi> tipoSolucionRevocacionUiList);

    void mostrarMensaje(String ingrese_motivo_revocacion);

    void mostrarMensajeErrorEditTextObservacion(String ingrese_observación);

    void mostrarDataInicial(ItemUi itemUi);

    void ocultarDialogProgressBar();

    void mostrarDialogProgressBar();

    void initStartActivityMenuPrincial();


}
