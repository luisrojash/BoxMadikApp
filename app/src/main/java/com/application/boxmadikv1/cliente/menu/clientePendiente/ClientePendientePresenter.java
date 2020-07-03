package com.application.boxmadikv1.cliente.menu.clientePendiente;

import com.application.boxmadikv1.base.fragment.BaseFragmentPresenter;
import com.application.boxmadikv1.cliente.menu.clientePendiente.entidad.ClientePendienteUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

public interface ClientePendientePresenter extends BaseFragmentPresenter<ClientePendienteView>{
    void onClickPendiente(ClientePendienteUi clientePendienteUi);
}
