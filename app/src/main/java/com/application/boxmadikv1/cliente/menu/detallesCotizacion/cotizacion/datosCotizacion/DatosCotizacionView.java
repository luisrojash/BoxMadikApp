package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

public interface DatosCotizacionView extends BaseActivityView<DatosCotizacionPresenter> {

    void mostrarFragmentos(DetallesCotizacionUi detallesCotizacionUi,
                           CotizacionesUi cotizacionesUi);

    void mostrarDataInicial(String imagenRubro, String nombrePropuesta, String fechaPropuesta,String nombreDepartamento);

    void mostrarMensaje(String revocación_enviada);

    void finishActivity(DetallesCotizacionUi detallesCotizacionUi,
            CotizacionesUi cotizacionesUi);

    void startActivityMenuPrincipalCliente();

    void onFinishStartNotificacion(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi);
}
