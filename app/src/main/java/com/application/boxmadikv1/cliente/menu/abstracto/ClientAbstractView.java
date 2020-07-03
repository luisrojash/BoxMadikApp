package com.application.boxmadikv1.cliente.menu.abstracto;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.cliente.menu.abstracto.entidad.ClienteEstadosUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

import java.util.List;

public interface ClientAbstractView extends BaseActivityView<ClientAbstractPresenter> {

    void mostraListaClienteEstados(List<ClienteEstadosUi> clienteEstadosUiList);

    void mostrarMensaje(String mensaje);

    void onStartActivityDetalles(DetallesCotizacionUi detallesCotizacionUi);

    void mostrarEmpty();

    void ocultarEmpty();

    void pintarColorTabs();

    void initListener();
}
