package com.application.boxmadikv1.cliente.menu.clientePendiente;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.cliente.menu.clientePendiente.entidad.ClientePendienteUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

import java.util.List;

public interface ClientePendienteView extends BaseActivityView<ClientePendientePresenter> {

    void mostraListaClienteTodos(List<ClientePendienteUi> clientePendienteUiList);

    void mostrarMensaje(String mensaje);

    void onStartActivityDetalles(DetallesCotizacionUi  detallesCotizacionUi);
}
