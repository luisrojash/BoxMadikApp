package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.calificar;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;

public interface CalificarClienteView extends BaseActivityView<CalificarClientePresenter> {

    void mostrarDataInicial(ItemUi itemUi, String nombreCliente, String paisCliente, String imagenCliente);

    void mostrarMensaje(String mensaje);

    void mostrarProgressBarDialog();

    void ocultarProgressBarDialog();

    void finishActivity(String mensaje);
}
